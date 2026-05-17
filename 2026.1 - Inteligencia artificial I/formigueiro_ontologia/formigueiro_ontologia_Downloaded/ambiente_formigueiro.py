"""
ambiente_formigueiro.py – Ambiente compartilhado com feromônio (comunicação indireta).

Cópia local para o pacote formigueiro_ontologia, garantindo que a classe
FormigaFeronomio resolve o import sem depender do diretório pai.
"""

import random


class AmbienteFormigueiro:
    """Ambiente compartilhado com feromonio (comunicacao indireta)."""

    def __init__(self, limite_alimento=4, evaporacao=0.02):
        self.limite_alimento = limite_alimento
        self.evaporacao = evaporacao
        self.feromonio = {}
        self.total_coletado = 0
        self.alimento_pos = (5, 10)
        self.posicoes_fixas = set()  # Posições imunes à evaporação

    def reset(self, alimento_pos=None):
        self.feromonio.clear()
        self.total_coletado = 0
        self.posicoes_fixas.clear()
        if alimento_pos is not None:
            self.alimento_pos = alimento_pos

    def sortear_alimento(self, min_coord=-9, max_coord=9, origem=(0, 0)):
        while True:
            pos = (random.randint(min_coord, max_coord), random.randint(min_coord, max_coord))
            if pos != origem:
                self.alimento_pos = pos
                return pos

    def registrar_coleta(self):
        if self.total_coletado >= self.limite_alimento:
            return False
        self.total_coletado += 1
        return True

    def alimento_esgotado(self):
        return self.total_coletado >= self.limite_alimento

    def valor_feromonio(self, posicao):
        return self.feromonio.get(posicao, 0.0)

    def depositar_trilha(self, caminho, intensidade=1.0):
        # Deposito decrescente: mais forte perto do alimento.
        n = len(caminho)
        if n == 0:
            return
        for i, p in enumerate(caminho):
            fator = (i + 1) / n
            self.feromonio[p] = self.feromonio.get(p, 0.0) + intensidade * fator

    def fixar_trilha(self, caminho):
        """Marca as posicoes do caminho como fixas (imunes a evaporacao)."""
        self.posicoes_fixas.update(caminho)

    def evaporar(self):
        if not self.feromonio:
            return
        remover = []
        taxa_restante = 1.0 - self.evaporacao
        for p, v in self.feromonio.items():
            if p in self.posicoes_fixas:
                continue  # Trilha vencedora: nao evapora
            novo = v * taxa_restante
            if abs(novo) < 0.01:
                remover.append(p)
            else:
                self.feromonio[p] = novo
        for p in remover:
            self.feromonio.pop(p, None)
