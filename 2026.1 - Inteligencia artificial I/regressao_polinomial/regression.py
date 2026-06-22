"""
Script para Análise de Regressão Polinomial.

Carrega o dataset de preços de ações da NVIDIA (1999-2024),
divide os dados em treino e teste, treina modelos de regressão linear e polinomial
para prever o preço de fechamento ao longo do tempo, avalia os
modelos e plota os resultados.
"""

# 1. Importação das bibliotecas necessárias
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import PolynomialFeatures

# --- PREPARAÇÃO DOS DADOS ---

# 2. Carregamento e união dos dois arquivos CSV da NVIDIA
data_dir = "nvidia-amd-intel-asus-msi-share-prices"

df_hist   = pd.read_csv(f"{data_dir}/NVIDIA (1999 -11.07.2023).csv")
df_recent = pd.read_csv(f"{data_dir}/Nvidia (2023 - 08.04.2024).csv")

dataset = pd.concat([df_hist, df_recent], ignore_index=True)
dataset["Date"] = pd.to_datetime(dataset["Date"])
dataset.drop_duplicates(subset="Date", inplace=True)
dataset.sort_values("Date", inplace=True)
dataset.dropna(inplace=True)

print(f"Dataset NVIDIA carregado: {len(dataset)} registros ({dataset['Date'].min().date()} → {dataset['Date'].max().date()})")
dataset.info()

# 3. Separação das variáveis
# X: dias desde a primeira data (valor numérico contínuo)
# y: preço de fechamento ajustado
first_date = dataset["Date"].min()
X = ((dataset["Date"] - first_date).dt.days).values.reshape(-1, 1)
y = dataset["Adj Close"].values

# 4. Divisão do dataset em conjunto de Treino e Teste
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# --- MODELO DE REGRESSÃO LINEAR ---

# 5. Treinamento do modelo de Regressão Linear Simples
lin_reg = LinearRegression()
lin_reg.fit(X_train, y_train)

# --- MODELO DE REGRESSÃO POLINOMIAL ---

# 6. Criação das features polinomiais (grau 4 captura melhor a tendência não-linear)
poly_features = PolynomialFeatures(degree=4)
X_train_poly = poly_features.fit_transform(X_train)
X_test_poly  = poly_features.transform(X_test)

# 7. Treinamento do modelo com as features polinomiais
poly_reg = LinearRegression()
poly_reg.fit(X_train_poly, y_train)

# --- AVALIAÇÃO DOS MODELOS ---

# 8. Previsões nos dados de teste
y_pred_linear = lin_reg.predict(X_test)
y_pred_poly   = poly_reg.predict(X_test_poly)

# 9. Métricas de avaliação
mse_linear = mean_squared_error(y_test, y_pred_linear)
mse_poly   = mean_squared_error(y_test, y_pred_poly)
r2_linear  = r2_score(y_test, y_pred_linear)
r2_poly    = r2_score(y_test, y_pred_poly)

print("\n--- Avaliação dos Modelos (no conjunto de teste) ---")
print("\nModelo de Regressão Linear:")
print(f"  Erro Quadrático Médio (MSE): {mse_linear:,.2f}")
print(f"  R² Score (Acurácia): {r2_linear:.4f}")
print("\nModelo de Regressão Polinomial (Grau 4):")
print(f"  Erro Quadrático Médio (MSE): {mse_poly:,.2f}")
print(f"  R² Score (Acurácia): {r2_poly:.4f}")
print("---------------------------------------------------")

# --- VISUALIZAÇÃO DOS RESULTADOS ---

# 10. Para curvas suaves, usamos o range completo de datas ordenado
X_full_sorted = np.linspace(X.min(), X.max(), 500).reshape(-1, 1)
y_full_linear = lin_reg.predict(X_full_sorted)
y_full_poly   = poly_reg.predict(poly_features.transform(X_full_sorted))

# Converter dias de volta para datas para o eixo X
dates_full = pd.to_datetime(X_full_sorted[:, 0], unit="D", origin=first_date)

# 11. Criação do gráfico
fig, ax = plt.subplots(figsize=(14, 7))

ax.scatter(
    pd.to_datetime(X[:, 0], unit="D", origin=first_date),
    y,
    color="lightblue", alpha=0.3, s=8, label="Dados Reais (Adj Close)"
)
ax.plot(dates_full, y_full_linear, color="blue",  linestyle="--", linewidth=2, label="Regressão Linear")
ax.plot(dates_full, y_full_poly,   color="green", linewidth=2,    label="Regressão Polinomial (Grau 4)")

ax.set_title("NVIDIA — Preço de Fechamento Ajustado ao Longo do Tempo", fontsize=16)
ax.set_xlabel("Data", fontsize=12)
ax.set_ylabel("Preço de Fechamento Ajustado (USD)", fontsize=12)
ax.legend()
ax.grid(True, linestyle=":", alpha=0.6)
fig.autofmt_xdate()

plt.savefig("regression_plot.png", dpi=150, bbox_inches="tight")
print("Gráfico salvo em: regression_plot.png")

# --- Exemplo de Previsão ---
# Previsão para 09/04/2024 (dia seguinte ao último registro)
target_date = pd.Timestamp("2024-04-09")
days_ahead = (target_date - first_date).days

pred_linear = lin_reg.predict([[days_ahead]])[0]
pred_poly   = poly_reg.predict(poly_features.transform([[days_ahead]]))[0]

print(f"\n--- Previsão para {target_date.date()} ---")
print(f"Previsão Linear:     USD {pred_linear:,.2f}")
print(f"Previsão Polinomial: USD {pred_poly:,.2f}")
print("----------------------------------------------------------")
