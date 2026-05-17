"""
executor_formigueiroFeronomio.py – Executor alternativo (com a classe
FormigaFeronomio de base_formigaFeronomio.py) agora orquestrado pela
ontologia formigueiro.ttl.

Toda a configuração (nomes, quantidades, posição do alimento, tamanduá)
é lida da ontologia via OntologyManager.
"""

import asyncio
import random
import tkinter as tk
from tkinter import ttk

from ontology_manager import OntologyManager
from base_formigaFeronomio import FormigaFeronomio


class InterfaceFormigueiro:
    def __init__(self, root, formigas, on_pausar, on_continuar, on_reiniciar, on_encerrar):
        self.root = root
        self.formigas = formigas
        self.alimento = FormigaFeronomio.ALIMENTO
        self.tamandua = None
        self.posicoes_eliminacao = []

        self.escala = 28
        self.margem = 36
        self.canvas_largura = 880
        self.canvas_altura = 620

        self.root.title("Formigueiro - Busca de Alimento (Ontologia)")
        self.container = tk.Frame(self.root, bg="#efe7da")
        self.container.pack(fill=tk.BOTH, expand=True)

        self.canvas = tk.Canvas(
            self.container,
            width=self.canvas_largura,
            height=self.canvas_altura,
            bg="#f6f1e8",
            highlightthickness=0,
        )
        self.canvas.pack(fill=tk.BOTH, expand=True)

        self.barra_controles = tk.Frame(self.container, bg="#efe7da", padx=10, pady=8)
        self.barra_controles.pack(fill=tk.X)

        self.status_var = tk.StringVar(value="Em execução")
        self.status_label = tk.Label(
            self.barra_controles,
            textvariable=self.status_var,
            bg="#efe7da",
            fg="#3d2c20",
            font=("Helvetica", 10, "bold"),
        )
        self.status_label.pack(side=tk.LEFT)

        self.btn_pausar = tk.Button(
            self.barra_controles,
            text="Pausar",
            command=on_pausar,
            bg="#ffd8a8",
            activebackground="#ffc078",
            relief=tk.RAISED,
            padx=14,
        )
        self.btn_pausar.pack(side=tk.RIGHT, padx=(8, 0))

        self.btn_continuar = tk.Button(
            self.barra_controles,
            text="Continuar",
            command=on_continuar,
            bg="#c3fae8",
            activebackground="#96f2d7",
            relief=tk.RAISED,
            padx=14,
            state=tk.DISABLED,
        )
        self.btn_continuar.pack(side=tk.RIGHT, padx=(8, 0))

        self.btn_reiniciar = tk.Button(
            self.barra_controles,
            text="Reiniciar",
            command=on_reiniciar,
            bg="#d0ebff",
            activebackground="#a5d8ff",
            relief=tk.RAISED,
            padx=14,
        )
        self.btn_reiniciar.pack(side=tk.RIGHT, padx=(8, 0))

        self.btn_encerrar = tk.Button(
            self.barra_controles,
            text="Encerrar",
            command=on_encerrar,
            bg="#ffc9c9",
            activebackground="#ffa8a8",
            relief=tk.RAISED,
            padx=14,
        )
        self.btn_encerrar.pack(side=tk.RIGHT, padx=(8, 0))

        # Paleta de cores ampla para suportar todas as formigas da ontologia
        _paleta = [
            "#d9480f", "#1c7ed6", "#2b8a3e", "#5f3dc4", "#e8590c",
            "#0b7285", "#c2255c", "#862e9c", "#5c940d", "#e67700",
            "#364fc7", "#087f5b", "#a61e4d", "#6741d9", "#d9480f",
            "#2f9e44", "#1971c2", "#9c36b5", "#e03131", "#0c8599",
        ]
        self.cores_formigas = {
            f.nome: _paleta[i % len(_paleta)] for i, f in enumerate(self.formigas)
        }

    def _projetar(self, ponto, min_x, max_y, offset_x, offset_y):
        x, y = ponto
        sx = offset_x + (x - min_x) * self.escala
        sy = offset_y + (max_y - y) * self.escala
        return sx, sy

    def _obter_limites(self):
        # Grid fixo 21x21: de -10 a +10 em x e y
        return -10, 10, -10, 10

    def desenhar(self, total_alimento):
        self.canvas.delete("all")

        min_x, max_x, min_y, max_y = self._obter_limites()

        # Calcula offsets para centralizar o grid no canvas
        cw = self.canvas.winfo_width() or self.canvas_largura
        ch = self.canvas.winfo_height() or self.canvas_altura
        grid_px_w = (max_x - min_x) * self.escala
        grid_px_h = (max_y - min_y) * self.escala
        offset_x = (cw - grid_px_w) / 2
        offset_y = (ch - grid_px_h) / 2

        for x in range(min_x, max_x + 1):
            x1, y1 = self._projetar((x, min_y), min_x, max_y, offset_x, offset_y)
            x2, y2 = self._projetar((x, max_y), min_x, max_y, offset_x, offset_y)
            self.canvas.create_line(x1, y1, x2, y2, fill="#e0d8cc")

        for y in range(min_y, max_y + 1):
            x1, y1 = self._projetar((min_x, y), min_x, max_y, offset_x, offset_y)
            x2, y2 = self._projetar((max_x, y), min_x, max_y, offset_x, offset_y)
            self.canvas.create_line(x1, y1, x2, y2, fill="#e0d8cc")

        base_x, base_y = self._projetar((0, 0), min_x, max_y, offset_x, offset_y)
        self.canvas.create_oval(base_x - 11, base_y - 11, base_x + 11, base_y + 11, fill="#6b4f3a", outline="")
        self.canvas.create_text(base_x, base_y - 18, text="Formigueiro", fill="#3d2c20", font=("Helvetica", 9, "bold"))

        comida_x, comida_y = self._projetar(self.alimento, min_x, max_y, offset_x, offset_y)
        self.canvas.create_rectangle(
            comida_x - 9,
            comida_y - 9,
            comida_x + 9,
            comida_y + 9,
            fill="#f08c00",
            outline="#ad6800",
            width=2,
        )
        self.canvas.create_text(comida_x, comida_y - 18, text="Alimento", fill="#995400", font=("Helvetica", 9, "bold"))

        if self.tamandua is not None:
            tx, ty = self._projetar(self.tamandua, min_x, max_y, offset_x, offset_y)
            self.canvas.create_oval(
                tx - 11,
                ty - 11,
                tx + 11,
                ty + 11,
                fill="#495057",
                outline="#212529",
                width=2,
            )
            self.canvas.create_text(tx, ty, text="T", fill="#f8f9fa", font=("Helvetica", 9, "bold"))
            self.canvas.create_text(tx, ty - 18, text="Tamandua", fill="#343a40", font=("Helvetica", 9, "bold"))

        # Marca com X os pontos onde houve eliminacao de formigas.
        for p in self.posicoes_eliminacao:
            ex, ey = self._projetar(p, min_x, max_y, offset_x, offset_y)
            self.canvas.create_line(ex - 8, ey - 8, ex + 8, ey + 8, fill="#c92a2a", width=3)
            self.canvas.create_line(ex - 8, ey + 8, ex + 8, ey - 8, fill="#c92a2a", width=3)

        # 1ª passagem: caminhos atuais das formigas (embaixo da trilha vencedora)
        for f in self.formigas:
            if f.eliminada:
                continue
            cor = self.cores_formigas.get(f.nome, "#343a40")
            if len(f.caminho) > 1:
                pontos_tela = []
                for p in f.caminho:
                    tx, ty = self._projetar(p, min_x, max_y, offset_x, offset_y)
                    pontos_tela.extend([tx, ty])
                self.canvas.create_line(*pontos_tela, fill=cor, width=2)

        # Trilha vencedora global: sempre desenhada por cima dos caminhos das formigas
        if FormigaFeronomio.ROTA_VENCEDORA and len(FormigaFeronomio.ROTA_VENCEDORA) > 1:
            pts_rv = []
            for p in FormigaFeronomio.ROTA_VENCEDORA:
                tx, ty = self._projetar(p, min_x, max_y, offset_x, offset_y)
                pts_rv.extend([tx, ty])
            self.canvas.create_line(*pts_rv, fill="#ffd43b", width=8, dash=(10, 4))
            self.canvas.create_line(*pts_rv, fill="#fab005", width=3)

        # 2ª passagem: ovals e labels das formigas (sempre no topo)
        for f in self.formigas:
            if f.eliminada:
                continue
            cor = self.cores_formigas.get(f.nome, "#343a40")
            x, y = self._projetar(f.posicao, min_x, max_y, offset_x, offset_y)
            self.canvas.create_oval(x - 7, y - 7, x + 7, y + 7, fill=cor, outline="#212529")
            self.canvas.create_text(x + 14, y - 12, text=f.nome, fill="#212529", font=("Helvetica", 9, "bold"))

        placar = "  |  ".join(
            f"{f.nome}: ELIMINADA" if f.eliminada else f"{f.nome}: {f.alimento_trazido}"
            for f in self.formigas
        )
        self.canvas.create_text(
            cw / 2,
            offset_y / 2,
            anchor="center",
            text=f"Total coletado: {total_alimento}/{FormigaFeronomio.AMBIENTE.limite_alimento}    {placar}",
            fill="#2f241c",
            font=("Helvetica", 11, "bold"),
        )

    def definir_status(self, pausado):
        if pausado:
            self.status_var.set("Pausado")
            self.btn_pausar.config(state=tk.DISABLED)
            self.btn_continuar.config(state=tk.NORMAL)
        else:
            self.status_var.set("Em execução")
            self.btn_pausar.config(state=tk.NORMAL)
            self.btn_continuar.config(state=tk.DISABLED)

def configurar_rede(formigas):
    for f in formigas:
        f.network.clear()
        for vizinha in formigas:
            if f.nome != vizinha.nome:
                f.network[vizinha.nome] = vizinha.inbox

def resetar_estado_formigas(formigas, controle_execucao):
    FormigaFeronomio.TOTAL_COLETADO = 0
    FormigaFeronomio.ROTA_VENCEDORA = None
    for f in formigas:
        f.posicao = (0, 0)
        f.caminho = [(0, 0)]
        f.inbox = asyncio.Queue()
        f.network = {}
        f.rotas_proibidas = set()
        f.rota_sucesso = None
        f.alimento_trazido = 0
        f.retornando = False
        f.caminho_retorno = []
        f.eliminada = False
        f.controle_execucao = controle_execucao

    configurar_rede(formigas)


async def main():
    # ── Carrega a ontologia como fonte única de verdade ──
    onto = OntologyManager()

    # Lê parâmetros da ontologia
    num_formigas = onto.get_populacao_total()
    capacidade_alimento = onto.get_capacidade_alimento()
    intervalo_tamandua = onto.get_intervalo_tamandua()
    formigas_onto = onto.get_formigas()

    print(f"=== ONTOLOGIA CARREGADA (executor) ===")
    print(f"  População: {num_formigas}")
    print(f"  Capacidade de alimento: {capacidade_alimento}")
    print(f"  Intervalo do tamanduá: {intervalo_tamandua}s")
    print(f"  Formigas na ontologia: {len(formigas_onto)}")
    print(f"======================================")

    # Atualiza o limite de alimento na classe compartilhada
    FormigaFeronomio.LIMITE_ALIMENTO = capacidade_alimento
    FormigaFeronomio.AMBIENTE.limite_alimento = capacidade_alimento
    # ── Injeta o OntologyManager na classe para que cada formiga acesse ──
    FormigaFeronomio.ONTO = onto

    # Inicializa as formigas — usa TODAS as instâncias definidas na ontologia
    nomes_para_usar = min(num_formigas, len(formigas_onto))
    nomes = [fi["nome_onto"].replace("Formiga0", "F").replace("Formiga", "F") for fi in formigas_onto[:nomes_para_usar]]

    formigas = [FormigaFeronomio(nome, nomes) for nome in nomes]

    # Posição aleatória do alimento dentro do grid (-9 a 9), fora da origem
    def gerar_alimento():
        while True:
            pos = (random.randint(-9, 9), random.randint(-9, 9))
            if pos != (0, 0):
                return pos

    def gerar_tamandua(alimento_pos):
        while True:
            pos = (random.randint(-9, 9), random.randint(-9, 9))
            if pos != (0, 0) and pos != alimento_pos:
                return pos

    FormigaFeronomio.ALIMENTO = gerar_alimento()
    print(f"--- Alimento posicionado em {FormigaFeronomio.ALIMENTO} ---")

    controle_execucao = asyncio.Event()
    controle_execucao.set()
    for f in formigas:
        f.controle_execucao = controle_execucao

    estado_ui = {
        "pausado": False,
        "reiniciar_solicitado": False,
        "encerrar_solicitado": False,
        "alimento_esgotado": False,
    }

    def pausar():
        if not estado_ui["pausado"]:
            estado_ui["pausado"] = True
            controle_execucao.clear()
            interface.definir_status(True)

    def continuar():
        if estado_ui["pausado"]:
            estado_ui["pausado"] = False
            controle_execucao.set()
            interface.definir_status(False)

    def reiniciar():
        estado_ui["reiniciar_solicitado"] = True

    def encerrar():
        estado_ui["encerrar_solicitado"] = True

    root = tk.Tk()
    root.lift()
    root.attributes('-topmost', True)
    root.after(200, lambda: root.attributes('-topmost', False))
    interface = InterfaceFormigueiro(root, formigas, pausar, continuar, reiniciar, encerrar)
    interface.alimento = FormigaFeronomio.ALIMENTO
    tamandua_pos = gerar_tamandua(FormigaFeronomio.ALIMENTO)
    interface.tamandua = tamandua_pos
    # Registra posição do tamanduá na ontologia
    onto.atualizar_posicao_tamandua(tamandua_pos)
    print(f"--- Tamandua posicionado em {tamandua_pos} ---")

    # ── Intervalo do tamanduá vindo da ontologia ──
    proxima_troca_tamandua = asyncio.get_running_loop().time() + float(intervalo_tamandua)

    # Roteamento: Conecta a caixa de entrada (inbox) de cada formiga com as demais
    print("--- Estabelecendo rede de feromônios (mensageria) ---")
    configurar_rede(formigas)

    # Dispara as corrotinas de busca
    print("--- Formigas iniciando o forrageamento ---")
    tarefas_por_formiga = {f: asyncio.create_task(f.agir()) for f in formigas}

    while True:
        try:
            root.update_idletasks()
            root.update()
        except tk.TclError:
            print("\nJanela encerrada pelo usuário. Finalizando simulação...")
            break

        if estado_ui["reiniciar_solicitado"]:
            estado_ui["reiniciar_solicitado"] = False
            estado_ui["pausado"] = False
            estado_ui["alimento_esgotado"] = False
            FormigaFeronomio.ALIMENTO = gerar_alimento()
            interface.alimento = FormigaFeronomio.ALIMENTO
            tamandua_pos = gerar_tamandua(FormigaFeronomio.ALIMENTO)
            interface.tamandua = tamandua_pos
            interface.posicoes_eliminacao = []
            # Registra na ontologia
            onto.atualizar_posicao_tamandua(tamandua_pos)
            onto.resetar_contadores()
            proxima_troca_tamandua = asyncio.get_running_loop().time() + float(intervalo_tamandua)
            print(f"--- Alimento reposicionado em {FormigaFeronomio.ALIMENTO} ---")
            print(f"--- Tamandua reposicionado em {tamandua_pos} ---")
            controle_execucao.set()
            interface.definir_status(False)
            interface.btn_pausar.config(state=tk.NORMAL)

            for tarefa in tarefas_por_formiga.values():
                tarefa.cancel()
            await asyncio.gather(*tarefas_por_formiga.values(), return_exceptions=True)

            resetar_estado_formigas(formigas, controle_execucao)
            interface.desenhar(0)
            tarefas_por_formiga = {f: asyncio.create_task(f.agir()) for f in formigas}
            print("\n--- Simulação reiniciada ---")

        if estado_ui["encerrar_solicitado"]:
            print("\nSimulação encerrada pelo botão Encerrar.")
            break

        agora = asyncio.get_running_loop().time()
        if agora >= proxima_troca_tamandua:
            tamandua_pos = gerar_tamandua(FormigaFeronomio.ALIMENTO)
            interface.tamandua = tamandua_pos
            onto.atualizar_posicao_tamandua(tamandua_pos)
            proxima_troca_tamandua = agora + float(intervalo_tamandua)
            print(f"--- Tamandua reposicionado em {tamandua_pos} ---")

        for f in formigas:
            if f.eliminada:
                continue
            if f.posicao == tamandua_pos:
                f.eliminada = True
                f.caminho = [f.posicao]
                interface.posicoes_eliminacao.append(f.posicao)
                tarefa = tarefas_por_formiga.get(f)
                if tarefa is not None:
                    tarefa.cancel()
                # Registra predação na ontologia
                onto.incrementar_predacao()
                print(f"[{f.nome}] foi eliminada pelo tamandua em {tamandua_pos}.")

        total_alimento_coletado = sum(f.alimento_trazido for f in formigas if not f.eliminada)
        interface.desenhar(total_alimento_coletado)

        # O alimento se esgota conforme capacidade da ontologia
        if total_alimento_coletado >= capacidade_alimento and not estado_ui["alimento_esgotado"]:
            estado_ui["alimento_esgotado"] = True
            print("\n=================================================")
            print(" ALIMENTO ESGOTADO! Reiniciando em 5 segundos...")
            print("=================================================")
            for tarefa in tarefas_por_formiga.values():
                tarefa.cancel()
            await asyncio.gather(*tarefas_por_formiga.values(), return_exceptions=True)
            tarefas_por_formiga = {}
            # Relatório final no console
            print("\n--- Relatório de Forrageamento ---")
            for f in formigas:
                print(f"{f.nome}: {f.alimento_trazido} porção(ões) de alimento coletada(s).")
            # Contagem regressiva de 5 segundos mantendo a janela responsiva
            for seg in range(5, 0, -1):
                interface.status_var.set(f"Alimento esgotado! Reiniciando em {seg}s...")
                try:
                    root.update_idletasks()
                    root.update()
                except tk.TclError:
                    break
                await asyncio.sleep(1)
            estado_ui["reiniciar_solicitado"] = True

        await asyncio.sleep(0.1)  # Pausa para ceder processamento às formigas

    # Cancela tarefas ainda ativas (encerrar/fechar janela)
    for tarefa in tarefas_por_formiga.values():
        tarefa.cancel()

    await asyncio.gather(*tarefas_por_formiga.values(), return_exceptions=True)

    try:
        root.destroy()
    except tk.TclError:
        pass

    if not estado_ui["alimento_esgotado"]:
        # Relatório final no console (caso encerrado antes do fim)
        print("\n--- Relatório de Forrageamento ---")
        for f in formigas:
            print(f"{f.nome}: {f.alimento_trazido} porção(ões) de alimento coletada(s).")

    # Salva o resultado no arquivo ao encerrar a simulação
    onto.salvar_ontologia()

if __name__ == "__main__":
    asyncio.run(main())