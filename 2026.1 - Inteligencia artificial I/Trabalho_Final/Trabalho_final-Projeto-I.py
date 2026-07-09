import random

import joblib
import pandas as pd
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB

CORES_FOLHA = ["verde", "amarela", "marrom"]


def cor_para_codigo(cor):
    return CORES_FOLHA.index(cor)


def gerar_diagnostico(umidade, cor, tamanho):
    # Regra usada para simular o diagnóstico real da planta
    if umidade < 30:
        diagnostico = "Sede"
    elif cor == "marrom" or (cor == "amarela" and tamanho < 20):
        diagnostico = "Doente"
    else:
        diagnostico = "Saudavel"
    return diagnostico


# Passo 1: Geração de Dados
def generate_plant_dataset(filepath="dataset_plantas.csv", n_samples=100, seed=42):
    if seed:
        random.seed(seed)
    linhas = []

    for _ in range(n_samples):
        cor = random.choice(CORES_FOLHA)
        umidade = round(random.uniform(5, 95), 1)
        tamanho = round(random.uniform(5, 50), 1)

        diagnostico = gerar_diagnostico(umidade, cor, tamanho)

        # Ruído para simular incerteza dos sensores/diagnóstico
        if random.random() < 0.1:
            diagnostico = random.choice(["Saudavel", "Sede", "Doente"])

        linhas.append([cor, umidade, tamanho, diagnostico])

    df = pd.DataFrame(
        linhas, columns=["cor_folha", "umidade_solo", "tamanho_cm", "diagnostico"]
    )
    df.to_csv(filepath, index=False)
    return filepath


# Passo 2: Treinamento do Modelo (scikit-learn + pandas)
def train_model(dataset_path="dataset_plantas.csv", model_path="modelo_plantas.joblib"):
    df = pd.read_csv(dataset_path)
    df["cor_folha_cod"] = df["cor_folha"].apply(cor_para_codigo)

    X = df[["cor_folha_cod", "umidade_solo", "tamanho_cm"]]
    y = df["diagnostico"]

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42
    )

    model = GaussianNB()
    model.fit(X_train.to_numpy(), y_train)

    accuracy = accuracy_score(y_test, model.predict(X_test.to_numpy()))
    joblib.dump(model, model_path)

    return model, accuracy


# Passo 3: Criação do Ambiente
class Planta:
    def __init__(self, cor_folha, umidade_solo, tamanho_cm):
        self.cor_folha = cor_folha
        self.umidade_solo = umidade_solo
        self.tamanho_cm = tamanho_cm

    def sensor_features(self):
        return [cor_para_codigo(self.cor_folha), self.umidade_solo, self.tamanho_cm]


class Estufa:
    def __init__(self, linhas=5, colunas=5, seed=None):
        if seed is not None:
            random.seed(seed)
        self.linhas = linhas
        self.colunas = colunas
        self.grid = [
            [self._gerar_planta() for _ in range(colunas)] for _ in range(linhas)
        ]

    def _gerar_planta(self):
        cor = random.choice(CORES_FOLHA)
        umidade = round(random.uniform(5, 95), 1)
        tamanho = round(random.uniform(5, 50), 1)
        return Planta(cor, umidade, tamanho)

    def get_planta(self, linha, coluna):
        return self.grid[linha][coluna]


# Passo 4: Criação do Agente Híbrido
class AgenteAgricultor:
    CUSTO_MOVIMENTO_BATERIA = 1
    CUSTO_REGAR_AGUA = 5
    CUSTO_DEFENSIVO_BATERIA = 3

    def __init__(self, modelo, estufa, agua=50, bateria=100):
        self.modelo = modelo
        self.agua = agua
        self.bateria = bateria
        self.log = []
        self.estufa = estufa

    def _perceber(self, planta):
        return planta.sensor_features()

    def _raciocinar(self, percepcao):
        return self.modelo.predict([percepcao])[0]

    def _agir(self, diagnostico, linha, coluna):
        self.bateria -= self.CUSTO_MOVIMENTO_BATERIA

        if diagnostico == "Sede":
            if self.agua >= self.CUSTO_REGAR_AGUA:
                self.agua -= self.CUSTO_REGAR_AGUA
                acao = "regar"
            else:
                acao = "ignorar (sem agua)"
        elif diagnostico == "Doente":
            if self.bateria >= self.CUSTO_DEFENSIVO_BATERIA:
                self.bateria -= self.CUSTO_DEFENSIVO_BATERIA
                acao = "aplicar_defensivo"
            else:
                acao = "ignorar (sem bateria)"
        else:
            acao = "ignorar"

        planta = self.estufa.get_planta(linha, coluna)
        diag = gerar_diagnostico(
            planta.umidade_solo, planta.cor_folha, planta.tamanho_cm
        )

        self.log.append(
            f"({linha},{coluna}) diagnostico={diagnostico} acao={acao} "
            f"agua={self.agua} bateria={self.bateria}"
            f"      |||  Estado real da planta: {diag} ---->>> {'acerto' if diag == diagnostico else 'ERRO'}"
        )
        return acao

    def inspecionar_estufa(self, estufa):
        for linha in range(estufa.linhas):
            for coluna in range(estufa.colunas):
                if self.bateria <= 0:
                    self.log.append("Bateria esgotada - inspecao interrompida")
                    return
                planta = estufa.get_planta(linha, coluna)
                percepcao = self._perceber(planta)
                diagnostico = self._raciocinar(percepcao)
                self._agir(diagnostico, linha, coluna)


def main():
    print("||| Robo Agricola - Naive Bayes + Agente Reativo |||\n")

    # Passo 1
    dataset_path = generate_plant_dataset()
    print(f"Dataset gerado em: {dataset_path}")

    # Passo 2
    model, accuracy = train_model(dataset_path)
    print(f"Modelo treinado (GaussianNB). Acuracia no teste: {accuracy:.1%}\n")

    # Passo 3
    estufa = Estufa(linhas=5, colunas=5, seed=123)

    # Passo 4
    agente = AgenteAgricultor(model, estufa, agua=50, bateria=100)
    agente.inspecionar_estufa(estufa)

    print("Log de inspecao:")
    for entrada in agente.log:
        print(f"  {entrada}")

    print(f"\nRecursos restantes -> agua: {agente.agua}, bateria: {agente.bateria}")


if __name__ == "__main__":
    main()
