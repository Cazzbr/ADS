#!/usr/bin/env python3
"""
Implementação Naive Bayes do zero em Python
Sem usar bibliotecas externas (apenas matemática básica)
"""

import csv
import math
from collections import defaultdict


class NaiveBayes:
    """
    Implementação do algoritmo Naive Bayes para classificação
    Suporta features categóricas e numéricas (usando distribuição Gaussiana)
    """

    def __init__(self):
        self.classes = []
        self.class_probs = {}  # P(classe)
        self.feature_probs = {}  # P(feature|classe)
        self.feature_stats = {}  # Para features numéricas: média e desvio padrão
        self.feature_types = {}  # 'categorical' ou 'numerical'
        self.total_samples = 0

    def _calculate_mean(self, values):
        """Calcula a média de uma lista de valores"""
        return sum(values) / len(values)

    def _calculate_std(self, values, mean):
        """Calcula o desvio padrão de uma lista de valores"""
        variance = sum((x - mean) ** 2 for x in values) / len(values)
        return math.sqrt(variance)

    def _gaussian_probability(self, x, mean, std):
        """Calcula a probabilidade usando distribuição Gaussiana"""
        if std == 0:
            return 1 if x == mean else 0

        exponent = math.exp(-((x - mean) ** 2) / (2 * std**2))
        return (1 / (math.sqrt(2 * math.pi) * std)) * exponent

    def _determine_feature_type(self, feature_values):
        """Determina se a feature é categórica ou numérica"""
        # Se todos os valores são números (int ou float), considera numérica
        try:
            [float(val) for val in feature_values]
            return "numerical"
        except (ValueError, TypeError):
            return "categorical"

    def fit(self, X, y):
        """
        Treina o modelo Naive Bayes

        Args:
            X: Lista de listas - features de treino
            y: Lista - labels de treino
        """
        self.total_samples = len(y)
        self.classes = list(set(y))

        # Conta amostras por classe
        class_counts = defaultdict(int)
        for label in y:
            class_counts[label] += 1

        # Calcula probabilidades das classes P(classe)
        for cls in self.classes:
            self.class_probs[cls] = class_counts[cls] / self.total_samples

        # Determina tipos das features
        num_features = len(X[0])
        for feature_idx in range(num_features):
            feature_values = [sample[feature_idx] for sample in X]
            self.feature_types[feature_idx] = self._determine_feature_type(
                feature_values
            )

        # Calcula probabilidades das features por classe
        self.feature_probs = {cls: {} for cls in self.classes}
        self.feature_stats = {cls: {} for cls in self.classes}

        for cls in self.classes:
            # Filtra amostras da classe atual
            class_samples = [X[i] for i in range(len(X)) if y[i] == cls]

            for feature_idx in range(num_features):
                feature_values = [sample[feature_idx] for sample in class_samples]

                if self.feature_types[feature_idx] == "categorical":
                    # Para features categóricas: conta frequências
                    value_counts = defaultdict(int)
                    for value in feature_values:
                        value_counts[value] += 1

                    # Aplica suavização de Laplace
                    unique_values = set([sample[feature_idx] for sample in X])
                    total_count = len(feature_values)

                    self.feature_probs[cls][feature_idx] = {}
                    for value in unique_values:
                        # Suavização de Laplace: (count + 1) / (total + num_unique_values)
                        prob = (value_counts[value] + 1) / (
                            total_count + len(unique_values)
                        )
                        self.feature_probs[cls][feature_idx][value] = prob

                else:  # numerical
                    # Para features numéricas: calcula média e desvio padrão
                    numeric_values = [float(val) for val in feature_values]
                    mean = self._calculate_mean(numeric_values)
                    std = self._calculate_std(numeric_values, mean)

                    self.feature_stats[cls][feature_idx] = {"mean": mean, "std": std}

    def _predict_sample(self, sample):
        """Prediz a classe para uma única amostra"""
        class_scores = {}

        for cls in self.classes:
            # Inicia com a probabilidade da classe P(classe)
            score = math.log(self.class_probs[cls])

            for feature_idx, feature_value in enumerate(sample):
                if self.feature_types[feature_idx] == "categorical":
                    # Para features categóricas
                    if feature_value in self.feature_probs[cls][feature_idx]:
                        prob = self.feature_probs[cls][feature_idx][feature_value]
                    else:
                        # Valor não visto no treino - usa suavização
                        total_unique = len(self.feature_probs[cls][feature_idx])
                        prob = 1 / (
                            sum(self.feature_probs[cls][feature_idx].values())
                            * total_unique
                            + total_unique
                        )

                    score += math.log(prob)

                else:  # numerical
                    # Para features numéricas usa distribuição Gaussiana
                    mean = self.feature_stats[cls][feature_idx]["mean"]
                    std = self.feature_stats[cls][feature_idx]["std"]
                    prob = self._gaussian_probability(float(feature_value), mean, std)

                    # Evita log(0)
                    if prob > 0:
                        score += math.log(prob)
                    else:
                        score += -float("inf")

            class_scores[cls] = score

        # Retorna a classe com maior score
        return max(class_scores.items(), key=lambda x: x[1])[0]

    def predict(self, X):
        """
        Faz predições para múltiplas amostras

        Args:
            X: Lista de listas - features para predição

        Returns:
            Lista de predições
        """
        return [self._predict_sample(sample) for sample in X]

    def predict_proba(self, X):
        """
        Retorna probabilidades de cada classe para as amostras

        Args:
            X: Lista de listas - features para predição

        Returns:
            Lista de dicionários {classe: probabilidade}
        """
        results = []

        for sample in X:
            class_scores = {}

            for cls in self.classes:
                score = math.log(self.class_probs[cls])

                for feature_idx, feature_value in enumerate(sample):
                    if self.feature_types[feature_idx] == "categorical":
                        if feature_value in self.feature_probs[cls][feature_idx]:
                            prob = self.feature_probs[cls][feature_idx][feature_value]
                        else:
                            total_unique = len(self.feature_probs[cls][feature_idx])
                            prob = 1 / (
                                sum(self.feature_probs[cls][feature_idx].values())
                                * total_unique
                                + total_unique
                            )
                        score += math.log(prob)
                    else:
                        mean = self.feature_stats[cls][feature_idx]["mean"]
                        std = self.feature_stats[cls][feature_idx]["std"]
                        prob = self._gaussian_probability(
                            float(feature_value), mean, std
                        )
                        if prob > 0:
                            score += math.log(prob)
                        else:
                            score += -float("inf")

                class_scores[cls] = score

            # Converte log-probabilidades para probabilidades normalizadas
            max_score = max(class_scores.values())
            exp_scores = {
                cls: math.exp(score - max_score) for cls, score in class_scores.items()
            }
            total_exp = sum(exp_scores.values())
            probabilities = {
                cls: exp_score / total_exp for cls, exp_score in exp_scores.items()
            }

            results.append(probabilities)

        return results


def calculate_accuracy(y_true, y_pred):
    """Calcula a acurácia das predições"""
    correct = sum(1 for true, pred in zip(y_true, y_pred) if true == pred)
    return correct / len(y_true)


def load_titanic_data():
    """
    Carrega um dataset simplificado do Titanic
    Features: [classe, sexo, idade, embarque]
    Target: sobreviveu (1) ou não (0)
    """
    # Dados simplificados baseados no dataset real do Titanic
    # Features: [classe, sexo, idade, porto_embarque]
    X_titanic = [
        [1, "female", 29, "C"],  # 1ª classe, mulher, 29 anos, Cherbourg
        [1, "male", 35, "S"],  # 1ª classe, homem, 35 anos, Southampton
        [1, "female", 38, "C"],  # 1ª classe, mulher, 38 anos, Cherbourg
        [1, "male", 54, "S"],  # 1ª classe, homem, 54 anos, Southampton
        [2, "female", 27, "S"],  # 2ª classe, mulher, 27 anos, Southampton
        [2, "male", 30, "S"],  # 2ª classe, homem, 30 anos, Southampton
        [2, "female", 24, "C"],  # 2ª classe, mulher, 24 anos, Cherbourg
        [2, "male", 25, "S"],  # 2ª classe, homem, 25 anos, Southampton
        [3, "female", 22, "S"],  # 3ª classe, mulher, 22 anos, Southampton
        [3, "male", 20, "S"],  # 3ª classe, homem, 20 anos, Southampton
        [3, "female", 18, "Q"],  # 3ª classe, mulher, 18 anos, Queenstown
        [3, "male", 32, "S"],  # 3ª classe, homem, 32 anos, Southampton
        [3, "male", 26, "S"],  # 3ª classe, homem, 26 anos, Southampton
        [3, "female", 35, "Q"],  # 3ª classe, mulher, 35 anos, Queenstown
        [1, "female", 2, "S"],  # 1ª classe, menina, 2 anos, Southampton
        [1, "male", 4, "S"],  # 1ª classe, menino, 4 anos, Southampton
        [2, "male", 0.75, "S"],  # 2ª classe, bebê, 9 meses, Southampton
        [3, "female", 1, "Q"],  # 3ª classe, bebê, 1 ano, Queenstown
        [3, "male", 15, "S"],  # 3ª classe, adolescente, 15 anos, Southampton
        [1, "female", 58, "C"],  # 1ª classe, mulher idosa, 58 anos, Cherbourg
        [2, "female", 45, "S"],  # 2ª classe, mulher, 45 anos, Southampton
        [3, "male", 40, "S"],  # 3ª classe, homem, 40 anos, Southampton
        [3, "female", 28, "S"],  # 3ª classe, mulher, 28 anos, Southampton
        [1, "male", 47, "C"],  # 1ª classe, homem, 47 anos, Cherbourg
        [2, "male", 28, "S"],  # 2ª classe, homem, 28 anos, Southampton
        [3, "male", 21, "S"],  # 3ª classe, homem, 21 anos, Southampton
        [3, "female", 19, "S"],  # 3ª classe, mulher, 19 anos, Southampton
        [1, "female", 17, "C"],  # 1ª classe, jovem, 17 anos, Cherbourg
        [2, "female", 36, "S"],  # 2ª classe, mulher, 36 anos, Southampton
        [3, "male", 33, "Q"],  # 3ª classe, homem, 33 anos, Queenstown
    ]

    # Target: 1 = sobreviveu, 0 = não sobreviveu
    # Baseado em padrões reais: mulheres e crianças primeiro, classe mais alta = maior chance
    y_titanic = [
        1,
        0,
        1,
        1,  # 1ª classe: mulheres sobrevivem, homens adultos têm chance
        1,
        0,
        1,
        0,  # 2ª classe: mulheres sobrevivem mais
        1,
        0,
        1,
        0,  # 3ª classe: mulheres têm chance, homens menos
        0,
        1,
        1,
        1,  # continuação 3ª classe
        0,
        1,
        1,
        0,  # crianças tendem a sobreviver
        1,
        1,
        0,
        1,  # mulheres idosas e de classe alta sobrevivem mais
        0,
        0,
        1,
        1,  # padrão continua
        1,
        0,  # últimos casos
    ]

    return X_titanic, y_titanic


def analyze_titanic_features(X, y):
    """Analisa as características do dataset Titanic"""
    print("📊 Análise do Dataset Titanic")
    print("-" * 40)

    # Conta sobreviventes
    survivors = sum(y)
    total = len(y)
    print(f"Total de passageiros: {total}")
    print(f"Sobreviventes: {survivors} ({survivors / total:.1%})")
    print(f"Não sobreviventes: {total - survivors} ({(total - survivors) / total:.1%})")
    print()

    # Análise por gênero
    print("Análise por Gênero:")
    male_survivors = sum(
        1 for i, person in enumerate(X) if person[1] == "male" and y[i] == 1
    )
    male_total = sum(1 for person in X if person[1] == "male")
    female_survivors = sum(
        1 for i, person in enumerate(X) if person[1] == "female" and y[i] == 1
    )
    female_total = sum(1 for person in X if person[1] == "female")

    print(
        f"  Homens: {male_survivors}/{male_total} sobreviveram ({male_survivors / male_total:.1%})"
    )
    print(
        f"  Mulheres: {female_survivors}/{female_total} sobreviveram ({female_survivors / female_total:.1%})"
    )
    print()

    # Análise por classe
    print("Análise por Classe:")
    for classe in [1, 2, 3]:
        class_survivors = sum(
            1 for i, person in enumerate(X) if person[0] == classe and y[i] == 1
        )
        class_total = sum(1 for person in X if person[0] == classe)
        if class_total > 0:
            print(
                f"  {classe}ª Classe: {class_survivors}/{class_total} sobreviveram ({class_survivors / class_total:.1%})"
            )
    print()


def load_mobile_data(filepath="train.csv"):
    """Carrega o dataset de classificação de preço de celular"""
    X, y = [], []
    with open(filepath) as f:
        reader = csv.DictReader(f)
        for row in reader:
            X.append(
                [float(row[col]) for col in reader.fieldnames if col != "price_range"]
            )
            y.append(int(row["price_range"]))
    return X, y


def main():
    """Função principal com classificação de preço de celular usando Naive Bayes"""
    print("=== Classificação de Preço de Celular com Naive Bayes ===\n")

    # Carrega dados do CSV
    X, y = load_mobile_data()

    # Treina o modelo Naive Bayes
    print("🤖 Treinando Modelo Naive Bayes")
    print("-" * 40)

    nb = NaiveBayes()
    nb.fit(X, y)

    print("✅ Modelo treinado com sucesso!")
    print(f"Classes identificadas: {nb.classes}")
    print()

    # Avalia performance no conjunto de treino
    print("📈 Avaliação do Modelo")
    print("-" * 40)

    train_predictions = nb.predict(X)
    accuracy = calculate_accuracy(y, train_predictions)
    print(f"Acurácia no conjunto de treino: {accuracy:.1%}")

    # Matriz de confusão manual
    true_positives = sum(
        1 for true, pred in zip(y, train_predictions) if true == 1 and pred == 1
    )
    false_positives = sum(
        1 for true, pred in zip(y, train_predictions) if true == 0 and pred == 1
    )
    true_negatives = sum(
        1 for true, pred in zip(y, train_predictions) if true == 0 and pred == 0
    )
    false_negatives = sum(
        1 for true, pred in zip(y, train_predictions) if true == 1 and pred == 0
    )

    print("\nMatriz de Confusão (classe 0 vs 1):")
    print(f"  Verdadeiros Positivos: {true_positives}")
    print(f"  Falsos Positivos: {false_positives}")
    print(f"  Verdadeiros Negativos: {true_negatives}")
    print(f"  Falsos Negativos: {false_negatives}")

    # Métricas adicionais
    if (true_positives + false_positives) > 0:
        precision = true_positives / (true_positives + false_positives)
        print(f"\nPrecisão: {precision:.1%}")

    if (true_positives + false_negatives) > 0:
        recall = true_positives / (true_positives + false_negatives)
        print(f"Recall: {recall:.1%}")


if __name__ == "__main__":
    main()
