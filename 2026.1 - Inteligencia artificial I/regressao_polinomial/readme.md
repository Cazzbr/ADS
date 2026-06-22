# Regressão Polinomial — Modelo Original

## Visão Geral

O script original aplica regressão linear e regressão polinomial sobre o dataset **Bike Sharing** (mais de 17.000 registros de aluguéis de bicicleta por hora) para demonstrar como um modelo polinomial captura relações não-lineares que um modelo linear não consegue representar adequadamente.

---

## Dataset

**Bike Sharing Dataset** (Kaggle — `lakshmi25npathi/bike-sharing-dataset`)

| Coluna | Descrição |
|--------|-----------|
| `temp` | Temperatura normalizada (0 a 1), onde 1 representa aproximadamente 41°C |
| `cnt`  | Contagem total de aluguéis de bicicleta naquela hora |

O dataset é baixado automaticamente via `kagglehub` na execução do script.

---

## Variáveis do Modelo

- **X (variável independente):** `temp` — temperatura normalizada
- **y (variável dependente):** `cnt` — número total de aluguéis de bicicleta

A relação entre temperatura e aluguéis não é linear: o número de aluguéis aumenta com o calor, mas cai em temperaturas muito altas, formando uma curva em forma de arco — o que torna esse dataset ideal para demonstrar regressão polinomial.

---

## Fluxo do Script

### 1. Carregamento dos dados
O dataset `hour.csv` é baixado via `kagglehub` e carregado com `pandas`. Linhas com valores faltantes são removidas.

### 2. Separação das variáveis
```python
X = dataset[["temp"]].values   # temperatura normalizada
y = dataset["cnt"].values       # contagem de aluguéis
```

### 3. Divisão treino/teste
80% dos dados vão para treino e 20% para teste, com `random_state=42` para reprodutibilidade.

```python
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
```

### 4. Regressão Linear Simples
Treina um modelo `LinearRegression` direto com as features originais. Serve como baseline de comparação.

### 5. Regressão Polinomial (Grau 4)
Expande as features com `PolynomialFeatures(degree=4)`, gerando as colunas:

```
[1,  temp,  temp²,  temp³,  temp⁴]
```

Em seguida treina um novo `LinearRegression` sobre essas features expandidas. Apesar de usar `LinearRegression`, o modelo é polinomial em relação à variável original — a linearidade é em relação aos coeficientes, não às features.

### 6. Avaliação
Dois indicadores são calculados no conjunto de teste:

- **MSE (Erro Quadrático Médio):** penaliza erros grandes; quanto menor, melhor.
- **R² Score:** proporção da variância explicada pelo modelo; 1.0 é perfeito, 0.0 significa que o modelo não explica nada.

### 7. Visualização
O gráfico plota:
- Pontos de dispersão dos dados de teste reais
- Linha do modelo linear (reta)
- Curva do modelo polinomial (curva suave)

O gráfico é exibido com `plt.show()` (requer ambiente com display gráfico).

---

## Dependências

```
kagglehub
matplotlib
numpy
pandas
scikit-learn
```

---

## Limitações do Modelo Original

- Depende de conexão com a internet e de credenciais do Kaggle para baixar o dataset.
- `plt.show()` falha em ambientes sem display gráfico (servidores, WSL sem X11).
- Usa uma única variável preditora (`temp`), ignorando outras features potencialmente relevantes como estação, hora do dia e clima.
