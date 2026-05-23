import random
import tkinter as tk
from tkinter import ttk, messagebox
from ag_balanca import (
    fitness_fn, random_selection, reproduce, mutate,
    soma_individuo, pesos_selecionados, PESOS
)


class InterfaceBalancaAG:
    def __init__(self, root):
        self.root = root
        self.root.title("Algoritmo Genético - A Balança Perfeita")
        self.root.geometry("1280x860")
        self.root.configure(bg="#f0fdf4")

        self.pesos = PESOS[:]
        self.peso_alvo = 50
        self.populacao = []
        self.melhor_historico = None
        self.geracao_atual = 0
        self.historico_fitness = []
        self.executando = False
        self.pausado = False

        self.tamanho_populacao = 100
        self.taxa_mutacao = 0.08
        self.geracoes = 500
        self.delay_ms = 80

        self._criar_layout()
        self._desenhar_balanca()

    # ──────────────────────────────────────────────
    # LAYOUT
    # ──────────────────────────────────────────────
    def _criar_layout(self):
        # ── Header ──
        header = tk.Frame(self.root, bg="#14532d", height=68)
        header.pack(fill="x")
        header.pack_propagate(False)
        tk.Label(header, text="⚖️  Algoritmo Genético — A Balança Perfeita",
                 font=("Segoe UI", 19, "bold"), bg="#14532d", fg="white"
                 ).pack(side="left", padx=22, pady=14)

        # ── Container principal ──
        container = tk.Frame(self.root, bg="#f0fdf4")
        container.pack(fill="both", expand=True, padx=14, pady=14)

        # ── Painel esquerdo (controles) ──
        left = tk.Frame(container, bg="white", width=400)
        left.pack(side="left", fill="y", padx=(0, 12))
        left.pack_propagate(False)

        tk.Label(left, text="⚙️  Parâmetros",
                 font=("Segoe UI", 13, "bold"), bg="white", fg="#14532d"
                 ).pack(pady=(16, 10), padx=16, anchor="w")

        # --- cards de parâmetros ---
        def card(parent):
            f = tk.Frame(parent, bg="#f0fdf4", pady=6)
            f.pack(fill="x", padx=14, pady=5)
            return f

        def entry_field(parent, label, default):
            tk.Label(parent, text=label, font=("Segoe UI", 10, "bold"),
                     bg="#f0fdf4", fg="#166534").pack(anchor="w", padx=8, pady=(6, 2))
            e = tk.Entry(parent, font=("Segoe UI", 11), width=22,
                         relief="solid", bd=1)
            e.insert(0, default)
            e.pack(padx=8, pady=(0, 6), fill="x")
            return e

        c1 = card(left)
        self.entry_alvo = entry_field(c1, "Peso Alvo (kg)", "50")
        self.entry_pop  = entry_field(c1, "Tamanho da População", "100")

        c2 = card(left)
        self.entry_mut   = entry_field(c2, "Taxa de Mutação (0–1)", "0.08")
        self.entry_ger   = entry_field(c2, "Gerações", "500")
        self.entry_delay = entry_field(c2, "Velocidade (ms/geração)", "80")

        # Pesos editáveis
        c3 = card(left)
        tk.Label(c3, text="Pesos disponíveis (separados por vírgula)",
                 font=("Segoe UI", 10, "bold"), bg="#f0fdf4", fg="#166534"
                 ).pack(anchor="w", padx=8, pady=(6, 2))
        self.entry_pesos = tk.Text(c3, font=("Cascadia Mono", 9),
                                   height=4, relief="solid", bd=1, wrap="word")
        self.entry_pesos.insert("1.0", ", ".join(map(str, PESOS)))
        self.entry_pesos.pack(padx=8, pady=(0, 6), fill="x")

        # Status
        c4 = card(left)
        tk.Label(c4, text="📊 Status", font=("Segoe UI", 11, "bold"),
                 bg="#f0fdf4", fg="#14532d").pack(anchor="w", padx=8, pady=(6, 4))
        self.lbl_geracao = tk.Label(c4, text="Geração: 0 / 0",
                                    font=("Segoe UI", 11, "bold"), bg="white", fg="#0f172a")
        self.lbl_geracao.pack(anchor="w", padx=8, pady=2)
        self.lbl_estado = tk.Label(c4, text="Estado: parado",
                                   font=("Segoe UI", 10), bg="white", fg="#64748b")
        self.lbl_estado.pack(anchor="w", padx=8, pady=2)
        self.lbl_soma = tk.Label(c4, text="Soma atual: 0 kg  (alvo: 50 kg)",
                                 font=("Segoe UI", 11, "bold"), bg="white", fg="#16a34a")
        self.lbl_soma.pack(anchor="w", padx=8, pady=2)
        self.lbl_fitness = tk.Label(c4, text="Fitness: 0",
                                    font=("Segoe UI", 10), bg="white", fg="#64748b")
        self.lbl_fitness.pack(anchor="w", padx=8, pady=2)
        self.lbl_pesos_sel = tk.Label(c4, text="Pesos na balança: —",
                                      font=("Segoe UI", 9), bg="white", fg="#334155",
                                      wraplength=260, justify="left")
        self.lbl_pesos_sel.pack(anchor="w", padx=8, pady=(2, 8))

        # Botões
        bf = tk.Frame(left, bg="white")
        bf.pack(fill="x", padx=16, pady=10)

        def btn(parent, txt, cmd, cor):
            return tk.Button(parent, text=txt, command=cmd,
                             font=("Segoe UI", 11, "bold"),
                             bg=cor, fg="white", border=0,
                             padx=10, pady=8, cursor="hand2", relief="raised")

        self.btn_iniciar   = btn(bf, "▶️  Iniciar",      self.iniciar,        "#16a34a")
        self.btn_pausar    = btn(bf, "⏸️  Pausar",       self.pausar_continuar,"#d97706")
        self.btn_reiniciar = btn(bf, "🔄  Reiniciar",    self.reiniciar,      "#7c3aed")
        self.btn_reset     = btn(bf, "🔁  Reset Total",  self.reset_completo, "#dc2626")
        for b in (self.btn_iniciar, self.btn_pausar, self.btn_reiniciar, self.btn_reset):
            b.pack(fill="x", pady=3)
        self.btn_pausar.configure(state="disabled")

        # ── Painel direito (visualização) ──
        right = tk.Frame(container, bg="white")
        right.pack(side="right", fill="both", expand=True)

        # Balança
        tk.Label(right, text="⚖️  Balança",
                 font=("Segoe UI", 13, "bold"), bg="white", fg="#14532d"
                 ).pack(pady=(14, 6), padx=16, anchor="w")
        self.canvas_balanca = tk.Canvas(right, bg="#f0fdf4", height=300,
                                        highlightthickness=1,
                                        highlightbackground="#bbf7d0")
        self.canvas_balanca.pack(padx=16, fill="x")

        # Lista de indivíduos
        tk.Label(right, text="📋  População (ordenada por fitness)",
                 font=("Segoe UI", 13, "bold"), bg="white", fg="#14532d"
                 ).pack(pady=(12, 4), padx=16, anchor="w")
        self.lbl_geracao_lista = tk.Label(right, text="Geração vigente: 0 / 0",
                                          font=("Segoe UI", 10), bg="white", fg="#64748b")
        self.lbl_geracao_lista.pack(anchor="w", padx=16)

        lista_frame = tk.Frame(right, bg="white")
        lista_frame.pack(fill="both", expand=True, padx=16, pady=(4, 16))
        self.lista_pop = tk.Listbox(lista_frame, font=("Cascadia Mono", 9),
                                    bg="#f8fafc", fg="#0f172a",
                                    highlightthickness=0, borderwidth=0,
                                    activestyle="none", selectbackground="#bbf7d0")
        scroll = tk.Scrollbar(lista_frame, orient="vertical", command=self.lista_pop.yview)
        self.lista_pop.configure(yscrollcommand=scroll.set)
        self.lista_pop.pack(side="left", fill="both", expand=True)
        scroll.pack(side="right", fill="y")

    # ──────────────────────────────────────────────
    # BALANÇA VISUAL
    # ──────────────────────────────────────────────
    def _desenhar_balanca(self, soma=0, alvo=None):
        if alvo is None:
            alvo = self.peso_alvo

        c = self.canvas_balanca
        c.delete("all")
        W = max(c.winfo_width(), 700)
        H = 300

        # Fundo suave
        c.create_rectangle(0, 0, W, H, fill="#f0fdf4", outline="")

        # Apoio central
        cx = W // 2
        c.create_rectangle(cx - 8, 60, cx + 8, 240, fill="#6b7280", outline="")
        c.create_rectangle(cx - 50, 235, cx + 50, 250, fill="#4b5563", outline="")
        c.create_oval(cx - 14, 54, cx + 14, 82, fill="#9ca3af", outline="")

        # Cálculo da inclinação com base na diferença
        dif = soma - alvo
        max_incl = 40
        incl = max(-max_incl, min(max_incl, dif * 0.6))

        # Viga
        viga_y = 80
        x_esq = cx - 200
        x_dir = cx + 200
        y_esq = viga_y + incl
        y_dir = viga_y - incl
        c.create_line(x_esq, y_esq, x_dir, y_dir, fill="#374151", width=5)

        # Cordas e pratos
        prato_h = 20
        for x_prato, y_topo, label, cor_prato, cor_txt in [
            (x_esq, y_esq, f"{soma} kg", "#4ade80", "#14532d"),
            (x_dir, y_dir, f"{alvo} kg\n(alvo)", "#60a5fa", "#1e3a8a"),
        ]:
            y_prato = y_topo + 70
            c.create_line(x_prato, y_topo, x_prato, y_prato, fill="#6b7280", width=2)
            c.create_rectangle(x_prato - 55, y_prato, x_prato + 55,
                                y_prato + prato_h, fill=cor_prato,
                                outline="#d1d5db", width=2)
            c.create_text(x_prato, y_prato + prato_h + 16,
                          text=label, font=("Segoe UI", 11, "bold"),
                          fill=cor_txt, justify="center")

        # Indicador de equilíbrio
        if soma == alvo:
            c.create_text(cx, H - 30, text="✅ EQUILIBRADA! Solução Exata!",
                          font=("Segoe UI", 13, "bold"), fill="#16a34a")
        else:
            sinal = "+" if dif > 0 else ""
            c.create_text(cx, H - 30,
                          text=f"Diferença: {sinal}{dif} kg",
                          font=("Segoe UI", 11), fill="#b45309")

        # Pesos na bandeja esquerda (bolinhas)
        sel = pesos_selecionados(self.melhor_historico) if self.melhor_historico else []
        if sel:
            n = len(sel)
            raio = min(18, 100 // max(n, 1))
            y_base = y_esq + 70 - prato_h // 2
            inicio_x = x_esq - min(55, n * (raio + 2))
            for i, p in enumerate(sel):
                bx = inicio_x + i * (raio * 2 + 3)
                c.create_oval(bx, y_base - raio, bx + raio * 2,
                              y_base + raio, fill="#22c55e", outline="#15803d")
                c.create_text(bx + raio, y_base,
                              text=str(p), font=("Segoe UI", 7, "bold"), fill="white")

    # ──────────────────────────────────────────────
    # CONTROLES
    # ──────────────────────────────────────────────
    def iniciar(self):
        if self.executando:
            return
        try:
            self.peso_alvo        = int(self.entry_alvo.get())
            self.tamanho_populacao = int(self.entry_pop.get())
            self.taxa_mutacao     = float(self.entry_mut.get())
            self.geracoes         = int(self.entry_ger.get())
            self.delay_ms         = int(self.entry_delay.get())
            raw = self.entry_pesos.get("1.0", tk.END).strip()
            self.pesos = [int(x.strip()) for x in raw.split(",") if x.strip()]
        except ValueError:
            messagebox.showerror("Erro", "Verifique os parâmetros — valores inválidos.")
            return

        if len(self.pesos) < 2:
            messagebox.showerror("Erro", "Informe ao menos 2 pesos.")
            return
        if self.peso_alvo <= 0:
            messagebox.showerror("Erro", "O peso alvo deve ser maior que 0.")
            return
        if not (0 <= self.taxa_mutacao <= 1):
            messagebox.showerror("Erro", "Taxa de mutação deve estar entre 0 e 1.")
            return

        n = len(self.pesos)
        self.populacao = [
            [random.randint(0, 1) for _ in range(n)]
            for _ in range(self.tamanho_populacao)
        ]
        self.melhor_historico = None
        self.geracao_atual = 0
        self.historico_fitness = []
        self.executando = True
        self.pausado = False
        self.btn_iniciar.configure(state="disabled")
        self.btn_pausar.configure(state="normal", text="⏸️  Pausar")
        self.lbl_estado.configure(text="Estado: executando")
        self._passo_geracao()

    def pausar_continuar(self):
        if not self.executando:
            return
        self.pausado = not self.pausado
        if self.pausado:
            self.btn_pausar.configure(text="▶️  Continuar")
            self.lbl_estado.configure(text="Estado: pausado")
        else:
            self.btn_pausar.configure(text="⏸️  Pausar")
            self.lbl_estado.configure(text="Estado: executando")

    def _parar(self, texto_estado):
        self.executando = False
        self.pausado = False
        self.btn_iniciar.configure(state="normal")
        self.btn_pausar.configure(state="disabled", text="⏸️  Pausar")
        self.lbl_estado.configure(text=texto_estado)

    def reiniciar(self):
        self._parar("Estado: parado")
        self.populacao = []
        self.melhor_historico = None
        self.geracao_atual = 0
        self.historico_fitness = []
        self._resetar_labels()
        self._desenhar_balanca()
        self.lista_pop.delete(0, tk.END)

    def reset_completo(self):
        self._parar("Estado: parado")
        self.populacao = []
        self.melhor_historico = None
        self.geracao_atual = 0
        self.historico_fitness = []
        for entry, val in [
            (self.entry_alvo, "50"), (self.entry_pop, "100"),
            (self.entry_mut, "0.08"), (self.entry_ger, "500"),
            (self.entry_delay, "80"),
        ]:
            entry.delete(0, tk.END)
            entry.insert(0, val)
        self.entry_pesos.delete("1.0", tk.END)
        self.entry_pesos.insert("1.0", ", ".join(map(str, PESOS)))
        self.pesos = PESOS[:]
        self.peso_alvo = 50
        self._resetar_labels()
        self._desenhar_balanca()
        self.lista_pop.delete(0, tk.END)

    def _resetar_labels(self):
        self.lbl_geracao.configure(text="Geração: 0 / 0")
        self.lbl_soma.configure(text="Soma atual: 0 kg  (alvo: ? kg)")
        self.lbl_fitness.configure(text="Fitness: 0")
        self.lbl_pesos_sel.configure(text="Pesos na balança: —")
        self.lbl_geracao_lista.configure(text="Geração vigente: 0 / 0")

    # ──────────────────────────────────────────────
    # LOOP DO AG
    # ──────────────────────────────────────────────
    def _passo_geracao(self):
        if not self.executando:
            return
        if self.pausado:
            self.root.after(100, self._passo_geracao)
            return
        if self.geracao_atual >= self.geracoes:
            self._parar("Estado: concluído")
            return

        # Ordena e guarda elitismo
        self.populacao.sort(
            key=lambda ind: fitness_fn(ind, self.peso_alvo, self.pesos),
            reverse=True
        )
        melhor_atual = self.populacao[0]
        if (self.melhor_historico is None or
                fitness_fn(melhor_atual, self.peso_alvo, self.pesos) >
                fitness_fn(self.melhor_historico, self.peso_alvo, self.pesos)):
            self.melhor_historico = melhor_atual.copy()

        # Solução exata?
        if fitness_fn(self.melhor_historico, self.peso_alvo, self.pesos) == self.peso_alvo:
            self._atualizar_painel()
            self._parar("✅ Solução exata encontrada!")
            messagebox.showinfo(
                "🎉 Sucesso!",
                f"Solução exata encontrada na geração {self.geracao_atual}!\n"
                f"Pesos: {pesos_selecionados(self.melhor_historico, self.pesos)}\n"
                f"Soma: {soma_individuo(self.melhor_historico, self.pesos)} kg"
            )
            return

        # Nova geração com elitismo
        nova_pop = [self.melhor_historico.copy()]
        while len(nova_pop) < self.tamanho_populacao:
            x = random_selection(self.populacao, fitness_fn, self.peso_alvo)
            y = random_selection(self.populacao, fitness_fn, self.peso_alvo)
            child = reproduce(x, y)
            child = mutate(child, self.taxa_mutacao)
            nova_pop.append(child)
        self.populacao = nova_pop

        self._atualizar_painel()
        self.geracao_atual += 1
        self.root.after(self.delay_ms, self._passo_geracao)

    # ──────────────────────────────────────────────
    # ATUALIZAÇÃO VISUAL
    # ──────────────────────────────────────────────
    def _atualizar_painel(self):
        if self.melhor_historico is None:
            return
        soma   = soma_individuo(self.melhor_historico, self.pesos)
        fit    = fitness_fn(self.melhor_historico, self.peso_alvo, self.pesos)
        sel    = pesos_selecionados(self.melhor_historico, self.pesos)
        dif    = abs(soma - self.peso_alvo)

        self.lbl_geracao.configure(
            text=f"Geração: {self.geracao_atual + 1} / {self.geracoes}")
        self.lbl_soma.configure(
            text=f"Soma atual: {soma} kg  (alvo: {self.peso_alvo} kg)  |  Δ = {dif} kg",
            fg="#16a34a" if dif == 0 else "#b45309")
        self.lbl_fitness.configure(text=f"Fitness: {fit} / {self.peso_alvo}")
        self.lbl_pesos_sel.configure(
            text=f"Pesos na balança: {sel if sel else '—'}")

        self._desenhar_balanca(soma=soma, alvo=self.peso_alvo)
        self._atualizar_lista()

    def _atualizar_lista(self):
        self.lbl_geracao_lista.configure(
            text=f"Geração vigente: {self.geracao_atual + 1} / {self.geracoes}")
        self.lista_pop.delete(0, tk.END)
        ordenada = sorted(
            self.populacao,
            key=lambda ind: fitness_fn(ind, self.peso_alvo, self.pesos),
            reverse=True
        )
        for idx, ind in enumerate(ordenada, 1):
            soma = soma_individuo(ind, self.pesos)
            fit  = fitness_fn(ind, self.peso_alvo, self.pesos)
            dif  = soma - self.peso_alvo
            sinal = f"+{dif}" if dif > 0 else str(dif)
            bits = "".join(map(str, ind))
            linha = (f"Ind {idx:03d}  |  Soma: {soma:4d} kg  "
                     f"|  Δ: {sinal:>5}  |  Fit: {fit:3d}  |  {bits}")
            self.lista_pop.insert(tk.END, linha)
            if dif == 0:
                self.lista_pop.itemconfig(tk.END, fg="#16a34a", bg="#f0fdf4")


# ──────────────────────────────────────────────
def executar_interface():
    root = tk.Tk()
    style = ttk.Style()
    if "clam" in style.theme_names():
        style.theme_use("clam")
    InterfaceBalancaAG(root)
    root.mainloop()


if __name__ == "__main__":
    executar_interface()
