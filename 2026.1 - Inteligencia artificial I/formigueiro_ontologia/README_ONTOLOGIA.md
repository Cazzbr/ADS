# Formigueiro com Abordagem Baseada em Ontologia

## Visão Geral

Este projeto reproduz a simulação multiagente de forrageamento em enxame (**formigueiro_OLD**) utilizando uma **abordagem baseada em ontologia RDF/OWL** para persistência, registros de rotas e gerenciamento de estado. A lógica e comportamento da simulação mantêm-se idênticos ao original, mas agora com integração semântica via RDF.

## Arquitetura

### Componentes Principais

#### 1. **OntologyManager** (`ontology_manager.py`)
- Gerencia leitura/escrita do grafo RDF (`formigueiro.ttl`)
- Fornece métodos SPARQL para:
  - Ler configuração de formigas (quantidade, limites de passos)
  - Ler capacidade do alimento
  - Registrar rotas (sucesso/falha) como triplas RDF
  - Gerenciar contadores de sucesso e predação
  - Salvar estado final em `formigueiro_resultado.ttl`

#### 2. **AmbienteOntologia** (`environment_ontologia.py`)
- Reproduz exatamente a classe `Ambiente` do `formigueiro_OLD`
- Gerencia:
  - Grid 10×10
  - Posição aleatória do alimento (3-12 passos Manhattan de distância)
  - Contador de alimento coletado
  - Lista de caminhos inválidos (feromônio negativo)

#### 3. **FormigaOntologia** (`ant_ontologia.py`)
- Reproduz exatamente a classe `Formiga` do `formigueiro_OLD`
- Mantém:
  - Algoritmo de exploração aleatória
  - Comunicação via `asyncio.Queue` (broadcast direto)
  - Limite de 20 passos por tentativa
  - Registro de rotas de sucesso/falha na ontologia
  - Evitação de caminhos inválidos conhecidos
- Novidade: Integração com OntologyManager para persistência

#### 4. **Executor Principal** (`executor_formigueiro_ontologia.py`)
- Orquestra a simulação completa
- Lê configuração da ontologia (`get_populacao_total()`, `get_capacidade_alimento()`, etc.)
- Gerencia o loop principal assíncrono
- Monitora coleta de alimento e encerra quando atingida a capacidade
- Salva estado final da simulação em `formigueiro_resultado.ttl`

### Arquivo de Ontologia

#### `formigueiro.ttl` (Entrada)
- Define estrutura RDF: Classes, propriedades, relacionamentos
- Instâncias iniciais:
  - **Formigueiro**: populacaoTotal = 5
  - **Alimento**: capacidadeMaxima = 4, contadorSucesso = 0
  - **Formigas 01-05**: limitePassos = 20, ignoraLimiteEnergia = false
  - **Nós**: Estrutura de coordenadas para o grid

#### `formigueiro_resultado.ttl` (Saída)
- Estado final da simulação com:
  - Todas as rotas percorridas (`ex:percorre ex:Rota_*`)
  - Nós visitados com coordenadas
  - Contadores de coleta e predação atualizados
  - Histórico completo em RDF

## Fluxo de Execução

1. **Inicialização**:
   ```bash
   python3 executor_formigueiro_ontologia.py
   ```

2. **Carregamento da Ontologia**:
   - OntologyManager lê `formigueiro.ttl`
   - Extrai configurações (quantidade de formigas, capacidades, etc.)

3. **Criação de Agentes**:
   - Cria 5 FormigaOntologia instâncias
   - Cada formiga inicia no ponto de origem (0,0)

4. **Loop de Simulação**:
   - Cada formiga executa `agir()` de forma assíncrona
   - Explora aleatoriamente ou segue rota conhecida
   - Ao encontrar alimento ou falhar, registra na ontologia
   - Comunica sucesso/falha via broadcast
   - Simples continua até alimento esgotar

5. **Encerramento**:
   - Cancela todas as corrotinas
   - Imprime relatório final
   - Salva estado final em `formigueiro_resultado.ttl`

## Comparação: Original vs. Ontologia

| Aspecto | formigueiro_OLD | formigueiro_ontologia |
|---------|-----------------|----------------------|
| Exploração | Aleatória | Idêntica |
| Comunicação | asyncio.Queue | asyncio.Queue (mantido) |
| Persistência | Nenhuma | RDF (formigueiro_resultado.ttl) |
| Configuração | Hardcoded (NOMES_FORMIGAS) | Lida da ontologia |
| Registro de Rotas | Prints apenas | RDF + Prints |
| Contadores | Variáveis locais | RDF + Variáveis locais |

## Exemplo de Saída

### Execução
```
============================================================
   SIMULAÇÃO DO FORMIGUEIRO — Sistema Multiagente Assíncrono
   (Abordagem baseada em Ontologia)
============================================================
Ambiente criado: grade 10x10
[SEGREDO DO AMBIENTE] Alimento posicionado em: (4, 3) (distância Manhattan: 7 passos)
Formigas ativas : ['Formiga_1', 'Formiga_2', 'Formiga_3', 'Formiga_4', 'Formiga_5']
...

[Formiga_3] ENCONTROU ALIMENTO em (4, 3)!
[Formiga_3] entregou alimento! Total coletado: 1/4
[Formiga_3] rota de sucesso comunicada ao enxame: [(0, 0), (1, 0), ..., (4, 3)]

[Formiga_1] recebeu rota de sucesso de Formiga_3
[Formiga_1] ENCONTROU ALIMENTO em (4, 3)!
...

============================================================
   RELATÓRIO FINAL
============================================================
Alimento coletado   : 4/4
Rotas inválidas     : 3 registradas
Rota de sucesso     : [(0, 0), (1, 0), ..., (4, 3)]
SUCESSO: recursos totalmente coletados pelo enxame!
```

### Ontologia Resultante (formigueiro_resultado.ttl)
```turtle
ex:Formiga_1 ex:percorre ex:Rota_Formiga_1_3 .
ex:Rota_Formiga_1_3 a ex:Rota ;
    ex:rotaValida true ;
    ex:compostaPor [ ex:coordX 0 ; ex:coordY 0 ], [ ex:coordX 1 ; ex:coordY 0 ], ... .

ex:AlimentoAlvo ex:contadorSucesso 4 .
```

## Critérios de Sucesso

✅ **Implementação Completa**:
- Reproduz comportamento idêntico do original (exploração aleatória, comunicação)
- Coleta 4 unidades de alimento como esperado
- 5 formigas operando concorrentemente via asyncio
- Limite de 20 passos por tentativa respeitado

✅ **Integração Ontológica**:
- Todas as rotas registradas em RDF
- Configuração lida da ontologia
- Estado final persistido em formigueiro_resultado.ttl

✅ **Qualidade**:
- Saída mantém formato original
- Comportamento determinista (seeded se necessário)
- Execução consistente em múltiplos runs

## Tecnologias Utilizadas

- **Linguagem**: Python 3
- **Concorrência**: asyncio
- **Ontologia**: RDFLib (rdflib), RDF/Turtle
- **Sem dependências externas**: Apenas stdlib + rdflib

## Como Usar

### Instalação de Dependências
```bash
pip3 install rdflib
```

### Executar Simulação
```bash
cd formigueiro_ontologia
python3 executor_formigueiro_ontologia.py
```

### Consultar Ontologia Resultante
```python
from rdflib import Graph
g = Graph()
g.parse("formigueiro_resultado.ttl", format="turtle")
# Realizar queries SPARQL ou inspecionar triplas RDF
```

## Estrutura de Diretório

```
formigueiro_ontologia/
├── ant_ontologia.py                  # Classe FormigaOntologia
├── environment_ontologia.py          # Classe AmbienteOntologia
├── executor_formigueiro_ontologia.py # Executor principal
├── ontology_manager.py               # Gerenciador de RDF
├── formigueiro.ttl                   # Ontologia inicial
├── formigueiro_resultado.ttl         # Resultado (gerado)
└── README_ONTOLOGIA.md               # Este arquivo
```

## Notas de Desenvolvimento

1. **Comunicação**: Mantemos `asyncio.Queue` em vez de usar apenas RDF para simular comunicação direta entre formigas (como no original).

2. **Performance**: RDFLib é suficiente para esta escala. Para simulações muito maiores, considere usar triplestore dedicado.

3. **Extensibilidade**: A abordagem ontológica facilita:
   - Adicionar novos tipos de comportamento (patrulhas, defesa)
   - Integrar raciocínio OWL (inferência de rotas)
   - Gerar relatórios semânticos em SPARQL

4. **Validação**: As triplas RDF podem ser validadas contra formas SHACL para garantir consistência.

## Autores

Adaptação da simulação original do formigueiro_OLD com integração ontológica (2026).

## Licença

Mesmo projeto de pesquisa - ADS 2026.1 Inteligência Artificial I.
