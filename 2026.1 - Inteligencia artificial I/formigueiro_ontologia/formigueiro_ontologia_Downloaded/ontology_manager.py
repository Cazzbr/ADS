"""
ontology_manager.py – Camada de acesso à ontologia formigueiro.ttl

Carrega o grafo RDF uma única vez e expõe métodos SPARQL para leitura
e escrita dos dados que orquestram a simulação (agentes, alimento,
predador, feromônios, rotas).
"""

import os
from rdflib import Graph, Namespace, Literal, URIRef, RDF, XSD

EX = Namespace("http://example.org/simulador-formigueiro#")


class OntologyManager:
    """Gerenciador centralizado da ontologia formigueiro.ttl."""

    def __init__(self, ttl_path: str | None = None):
        if ttl_path is None:
            ttl_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "formigueiro.ttl")
        self.graph = Graph()
        self.graph.parse(ttl_path, format="turtle")
        self.graph.bind("ex", EX)
        self._route_counter = 0  # Contador global de rotas registradas

    # ------------------------------------------------------------------
    # Leitura de parâmetros gerais
    # ------------------------------------------------------------------

    def get_populacao_total(self) -> int:
        """Retorna ex:populacaoTotal do Formigueiro."""
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?pop WHERE { ?f a ex:Formigueiro ; ex:populacaoTotal ?pop . }
        """
        for row in self.graph.query(q):
            return int(row.pop)
        return 10  # fallback

    def get_capacidade_alimento(self) -> int:
        """Retorna ex:capacidadeMaxima do Alimento."""
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?cap WHERE { ?a a ex:Alimento ; ex:capacidadeMaxima ?cap . }
        """
        for row in self.graph.query(q):
            return int(row.cap)
        return 4

    def get_intervalo_tamandua(self) -> int:
        """Retorna ex:intervaloMovimentoSegundos do Tamanduá."""
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?seg WHERE { ?t a ex:Tamandua ; ex:intervaloMovimentoSegundos ?seg . }
        """
        for row in self.graph.query(q):
            return int(row.seg)
        return 5

    # ------------------------------------------------------------------
    # Leitura de parâmetros das formigas
    # ------------------------------------------------------------------

    def get_formigas(self) -> list[dict]:
        """Retorna uma lista de dicts com nome_local, limitePassos e ignoraLimiteEnergia."""
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?formiga ?limite ?ignora WHERE {
                ?formiga a ex:Formiga ;
                         ex:limitePassos ?limite ;
                         ex:ignoraLimiteEnergia ?ignora .
            }
            ORDER BY ?formiga
        """
        formigas = []
        for row in self.graph.query(q):
            # ex:Formiga01 -> "Formiga01" -> "Formiga_1"
            uri_str = str(row.formiga)
            nome_local = uri_str.split("#")[-1]  # Ex: "Formiga01"
            # Converte "Formiga01" -> "Formiga_1", "Formiga10" -> "Formiga_10"
            num_str = nome_local.replace("Formiga", "")
            try:
                num = int(num_str)
            except ValueError:
                num = 0
            nome_display = f"Formiga_{num}"

            formigas.append({
                "uri": str(row.formiga),
                "nome": nome_display,
                "nome_onto": nome_local,
                "limite_passos": int(row.limite),
                "ignora_limite_energia": str(row.ignora).lower() in ("true", "1"),
            })
        return formigas

    def get_limite_passos(self, nome_onto: str) -> int:
        """Retorna o limitePassos de uma formiga específica."""
        q = f"""
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?limite WHERE {{ ex:{nome_onto} ex:limitePassos ?limite . }}
        """
        for row in self.graph.query(q):
            print(f"--->Limite de passos para {nome_onto}: {row.limite}")
            return int(row.limite)
        return 20

    def get_ignora_limite_energia(self, nome_onto: str) -> bool:
        """Retorna se a formiga ignora o limite de energia ao seguir feromônio positivo."""
        q = f"""
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?ignora WHERE {{ ex:{nome_onto} ex:ignoraLimiteEnergia ?ignora . }}
        """
        for row in self.graph.query(q):
            return str(row.ignora).lower() in ("true", "1")
        return False

    # ------------------------------------------------------------------
    # Escrita / Atualização em tempo de execução
    # ------------------------------------------------------------------

    def registrar_rota(self, nome_onto: str, caminho: list[tuple], sucesso: bool) -> URIRef:
        """Adiciona uma instância ex:Rota na ontologia."""
        self._route_counter += 1
        rota_uri = EX[f"Rota_{nome_onto}_{self._route_counter}"]
        formiga_uri = EX[nome_onto]

        self.graph.add((rota_uri, RDF.type, EX.Rota))
        self.graph.add((formiga_uri, EX.percorre, rota_uri))

        for pos in caminho:
            no_uri = EX[f"No_{pos[0]}_{pos[1]}"]
            self.graph.add((no_uri, RDF.type, EX.No))
            self.graph.add((no_uri, EX.coordX, Literal(pos[0], datatype=XSD.integer)))
            self.graph.add((no_uri, EX.coordY, Literal(pos[1], datatype=XSD.integer)))
            self.graph.add((rota_uri, EX.compostaPor, no_uri))

        self.graph.add((rota_uri, EX.rotaValida, Literal(sucesso, datatype=XSD.boolean)))
        return rota_uri

    def registrar_feromonio(self, nome_onto: str, pos: tuple, next_pos: tuple | None, tipo: str = "positivo"):
        """Adiciona uma instância de ex:Feromonio na ontologia."""
        fero_uri = EX[f"Feromonio_{pos[0]}_{pos[1]}_{self._route_counter}"]
        formiga_uri = EX[nome_onto]
        no_uri = EX[f"No_{pos[0]}_{pos[1]}"]

        self.graph.add((fero_uri, RDF.type, EX.Feromonio))
        self.graph.add((formiga_uri, EX.deposita, fero_uri))
        self.graph.add((fero_uri, EX.impregnadoEm, no_uri))
        self.graph.add((fero_uri, EX.tipoFeromonio, Literal(tipo, datatype=XSD.string)))

        if next_pos is not None:
            next_no_uri = EX[f"No_{next_pos[0]}_{next_pos[1]}"]
            self.graph.add((fero_uri, EX.apontaPara, next_no_uri))

    def incrementar_predacao(self):
        """Incrementa ex:contadorPredadas do Tamanduá."""
        predador = EX.Predador01
        q = f"""
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?cnt WHERE {{ ex:Predador01 ex:contadorPredadas ?cnt . }}
        """
        cnt_atual = 0
        for row in self.graph.query(q):
            cnt_atual = int(row.cnt)
        # Remove o valor antigo e insere o novo
        self.graph.remove((predador, EX.contadorPredadas, None))
        self.graph.add((predador, EX.contadorPredadas, Literal(cnt_atual + 1, datatype=XSD.integer)))

    def incrementar_coleta(self):
        """Incrementa ex:contadorSucesso do Alimento."""
        alimento = EX.AlimentoAlvo
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?cnt WHERE { ex:AlimentoAlvo ex:contadorSucesso ?cnt . }
        """
        cnt_atual = 0
        for row in self.graph.query(q):
            cnt_atual = int(row.cnt)
        self.graph.remove((alimento, EX.contadorSucesso, None))
        self.graph.add((alimento, EX.contadorSucesso, Literal(cnt_atual + 1, datatype=XSD.integer)))

    def get_coleta_atual(self) -> int:
        """Retorna o valor atual de ex:contadorSucesso do Alimento."""
        q = """
            PREFIX ex: <http://example.org/simulador-formigueiro#>
            SELECT ?cnt WHERE { ex:AlimentoAlvo ex:contadorSucesso ?cnt . }
        """
        for row in self.graph.query(q):
            return int(row.cnt)
        return 0

    def resetar_contadores(self):
        """Reseta contadores de coleta e predação para um novo ciclo (via SPARQL UPDATE)."""
        # ── Versão anterior (imperativa, via graph.add / graph.remove): ──
        # alimento = EX.AlimentoAlvo
        # predador = EX.Predador01
        # self.graph.remove((alimento, EX.contadorSucesso, None))
        # self.graph.add((alimento, EX.contadorSucesso, Literal(0, datatype=XSD.integer)))
        # self.graph.remove((predador, EX.contadorPredadas, None))
        # self.graph.add((predador, EX.contadorPredadas, Literal(0, datatype=XSD.integer)))

        # ── Versão atual (declarativa, via SPARQL UPDATE): ──
        self.graph.update("""
            PREFIX ex: <http://example.org/simulador-formigueiro#>

            DELETE { ex:AlimentoAlvo ex:contadorSucesso ?old_col . }
            INSERT { ex:AlimentoAlvo ex:contadorSucesso 0 . }
            WHERE  { OPTIONAL { ex:AlimentoAlvo ex:contadorSucesso ?old_col . } }
        """)
        self.graph.update("""
            PREFIX ex: <http://example.org/simulador-formigueiro#>

            DELETE { ex:Predador01 ex:contadorPredadas ?old_pred . }
            INSERT { ex:Predador01 ex:contadorPredadas 0 . }
            WHERE  { OPTIONAL { ex:Predador01 ex:contadorPredadas ?old_pred . } }
        """)

    def atualizar_posicao_formiga(self, nome_onto: str, pos: tuple):
        """Atualiza ex:posicionadoEm da formiga para um novo Nó."""
        formiga_uri = EX[nome_onto]
        no_uri = EX[f"No_{pos[0]}_{pos[1]}"]
        # Garante que o nó exista
        self.graph.add((no_uri, RDF.type, EX.No))
        self.graph.add((no_uri, EX.coordX, Literal(pos[0], datatype=XSD.integer)))
        self.graph.add((no_uri, EX.coordY, Literal(pos[1], datatype=XSD.integer)))
        # Remove posição antiga e grava nova
        self.graph.remove((formiga_uri, EX.posicionadoEm, None))
        self.graph.add((formiga_uri, EX.posicionadoEm, no_uri))

    def atualizar_posicao_tamandua(self, pos: tuple):
        """Atualiza ex:posicionadoEm do Predador01 para um novo Nó."""
        predador = EX.Predador01
        no_uri = EX[f"No_{pos[0]}_{pos[1]}"]
        self.graph.add((no_uri, RDF.type, EX.No))
        self.graph.add((no_uri, EX.coordX, Literal(pos[0], datatype=XSD.integer)))
        self.graph.add((no_uri, EX.coordY, Literal(pos[1], datatype=XSD.integer)))
        self.graph.remove((predador, EX.posicionadoEm, None))
        self.graph.add((predador, EX.posicionadoEm, no_uri))

    def salvar_ontologia(self, ttl_path: str | None = None):
        """Salva o estado atual do grafo RDF de volta no arquivo .ttl."""
        if ttl_path is None:
            # Sobrescreve por padrão o arquivo de leitura original
            ttl_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "formigueiro_resultado.ttl")
        self.graph.serialize(destination=ttl_path, format="turtle")
        print(f"\n[OntologyManager] Estado final da ontologia salvo em: {ttl_path}")
