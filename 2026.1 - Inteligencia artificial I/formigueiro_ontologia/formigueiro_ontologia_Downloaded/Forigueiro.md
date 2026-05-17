# Documentação do Projeto: Formigueiro Inteligente

Este documento apresenta a especificação técnica, regras de negócio e a documentação das classes da simulação multiagente **Formigueiro Inteligente**. O projeto consiste em uma simulação baseada em agentes utilizando comunicação assíncrona (`asyncio`), com interfaces gráficas locais (`Tkinter`) e web (`FastAPI` com WebSockets).

## 1. Regras de Negócio

1. **Objetivo das Formigas**: As formigas saem do formigueiro (localizado na coordenada `(0,0)`) para explorar o ambiente em busca de alimento e trazê-lo de volta para a colônia.
2. **Coleta de Alimento**: Existe uma fonte de alimento gerada aleatoriamente no grid. O alimento possui uma capacidade máxima (configurável). Quando uma formiga encontra o alimento, ela retorna à base para avisar as outras (por mensagem) ou deposita feromônio no caminho. O processo se repete até o alimento esgotar.
3. **Predador (Tamanduá)**: O ambiente conta com um tamanduá que atua como predador. Se uma formiga e o tamanduá ocuparem a mesma coordenada no grid, a formiga é devorada e morre (estado `DEAD`).
4. **Comportamento de Exploração**:
   - As formigas exploram aleatoriamente evitando voltar pelo caminho imediato.
   - Após 20 passos de exploração sem sucesso, a formiga desiste, retorna à base e marca a rota como falha para não repeti-la.
   - Quando o alimento esgota, as formigas ativas retornam e entram em estado finalizado (`FINISHED`).
5. **Comunicação e Coordenação**:
   - **Por Mensagens (Versão Padrão)**: As formigas utilizam filas (`asyncio.Queue`) para enviar transmissões (`broadcast`) umas às outras sobre o sucesso ou falha das rotas encontradas.
   - **Por Feromônio (Versão Feromônio)**: As formigas interagem de forma indireta (Estigmergia) deixando trilhas no ambiente. As outras formigas podem seguir essas trilhas até a comida.
6. **Movimentação do Tamanduá**: O predador se move a cada 5 segundos de tempo simulado para uma nova posição aleatória, aumentando a dinamicidade e o risco do ambiente.
7. **Ciclo de Simulação**: O ciclo é reiniciado automaticamente se o alimento se esgotar ou a colônia for extinta (todas as formigas morrerem/pararem). Há um período de contagem regressiva (5 segundos) entre os ciclos.
8. **Configurações**: O número de formigas e a capacidade de alimento podem ser configurados no arquivo `.env`.

---

## 2. Arquitetura

O sistema segue uma arquitetura orientada a componentes distribuídos baseada em **Sistemas Multiagentes (SMA)**, suportada por uma arquitetura de concorrência assíncrona e uma camada de apresentação flexível (Local e Web).

- **Núcleo do Motor de Simulação (Backend Assíncrono)**
  - O processamento de simulação roda em um *Event Loop* fornecido pela biblioteca nativa `asyncio` do Python.
  - Cada Formiga é modelada como uma `Task` separada no *Event Loop*, agindo de maneira concorrente, tomando suas próprias decisões (agentes autônomos e descentralizados).
  - O Tamanduá também possui uma `Task` paralela para o seu processamento periódico de movimento.

- **Variações do Algoritmo**
  - O sistema possui duas implementações principais de inteligência de enxame:
    - **Comunicação Direta (Mensagens)**: Baseado nos arquivos `ant.py`, `environment.py` e `formigueiro.py`. Usa `Queue` para simular uma rede de comunicação das formigas (trocando tuplas e rotas completas).
    - **Estigmergia (Feromônio)**: Baseado nos arquivos `ant_feromonio.py`, `environment_feromonio.py` e `formigueiro_feromonio.py`. O ambiente funciona como uma lousa global que guarda o estado dos feromônios, reduzindo a necessidade de os agentes se conhecerem entre si.

- **Camadas de Apresentação (Frontend)**
  - **Interface Desktop (`Tkinter`)**: Desenvolvida nos arquivos `simulation_gui.py` e executada por `formigueiro.py`. Renderiza a simulação num *Canvas* iterativamente de forma conectada ao loop do `asyncio`.
  - **Interface Web (`FastAPI` + WebSockets + HTML/JS/CSS)**: Configurada no arquivo `web_server.py`. Utiliza WebSockets para transmitir o estado (JSON) do motor gerador (`web_simulation.py`) para clientes web conectados, viabilizando um visualizador desacoplado e em tempo real no navegador (Frontend em `static/index.html`).

---

## 3. Documentação das Classes

Abaixo as principais entidades (Classes) utilizadas na aplicação e suas variações.

### 3.1. `Environment` (e `Environment_feromonio`)
Representa o mapa de mundo e as restrições globais da simulação.

- **Atributos Principais:**
  - `food_pos`: Tupla `(x, y)` indicando a posição atual do alimento.
  - `tamandua_pos`: Tupla `(x, y)` indicando a posição do predador.
  - `capacity` / `collected`: Capacidade máxima da fonte de alimento e a quantidade já coletada.
  - `pheromone`: (Apenas na versão feromônio) Dicionário que mapeia uma coordenada `(x,y)` ao próximo passo na trilha ótima.
- **Métodos Principais:**
  - `has_food(pos)`: Retorna um booleano verificando se há alimento e se a capacidade não esgotou.
  - `collect_food()`: Incrementa o contador de coleta, validando o limite de capacidade da fonte.
  - `is_exhausted()`: Checa se todo alimento dessa iteração já foi coletado.
  - `mark_pheromone(route)`: (Apenas versão feromônio) Aplica os marcadores da rota de sucesso no dicionário do ambiente.

### 3.2. `Ant` (e `Ant_feromonio`)
Atores principais da simulação que contêm a máquina de estados e as regras de navegação.

- **Atributos Principais:**
  - `name`: Identificador único da formiga (Ex: "Formiga_1").
  - `pos`: Posição atual `(x, y)`. A colônia localiza-se na coordenada `(0, 0)`.
  - `state`: Estado atual na máquina de estados (pode ser: `EXPLORING`, `RETURNING_SUCCESS`, `RETURNING_FAILURE`, `EXPLOITING`, `DEAD`, `FINISHED`).
  - `history` e `all_paths`: Armazenam os passos da rota atual e um registro histórico geral.
  - `inbox` / `others`: (Versão Mensagem) Dicionário de caixas de entrada de mensagens e referência às caixas das demais companheiras.
- **Métodos Principais:**
  - `agir()`: Loop principal assíncrono da formiga. Processa as mensagens, avalia o estado (`state`), as condições de ambiente (se esgotou alimento) e delega o movimento para o próximo estado.
  - `explorar()`: Lógica para caminhar aleatoriamente de quadrado em quadrado. Verifica condições de limite (20 passos) e o encontro com o alimento (ou a trilha de feromônio).
  - `retornar_ao_formigueiro(motivo)`: Retrocede os passos até retornar à colônia `(0, 0)` e dissemina a informação de falha ou sucesso no caminho.
  - `seguir_rota_sucesso()`: Caminha expressamente do formigueiro até a comida, explorando rotas comprovadas ("Explotation").
  - `check_predator()`: Validar intersecção com o tamanduá e acionar o óbito `DEAD` em caso verdadeiro.
  - `broadcast(msg_type, content)`: Dispara dados para o *Queue* de *todas* as outras formigas da colônia.

### 3.3. `SimulationGUI` (Interface Tkinter)
Classe de visualização local que manipula a interface gráfica de botões, abas e tela de desenho.

- **Responsabilidades:**
  - Configuração do sistema de grid 2D.
  - Transposição de coordenadas do plano cartesiano da simulação para as dimensões de *pixels* do Canvas.
  - Renderização das formigas e suas rotas.
  - Exposição de botões de controle: "Pausar", "Reset", "Cancelar".
  - Atualização dos passos totais e status das formigas nos painéis laterais.

### 3.4. `web_server.py` e `web_simulation.py` (Módulo Web)
Lida com a estrutura que expõe a lógica Python para aplicações externas.

- **`web_server.py`**: Aplicação FastAPI. Serve arquivos estáticos e gerencia conexões WebSocket para comunicação full-duplex de eventos (comandos de pause, reset e updates de tick do motor) aos navegadores.
- **`web_simulation.py`**: Contém a função assíncrona geradora `run_simulation()`. Esta função encapsula o loop principal da simulação do formigueiro e dá `yield` com o snapshot (dicionário JSON-friendly) do mapa e agentes, permitindo enviar estado serializado via web socket aos clientes web.
