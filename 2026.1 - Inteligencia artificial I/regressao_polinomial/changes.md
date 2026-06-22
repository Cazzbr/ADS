# Registro de Modificações — regression.py

Este documento detalha todas as alterações feitas no script original de regressão polinomial.

---

## 1. Correção do backend do Matplotlib

**Problema:** O script original chamava `plt.show()` para exibir o gráfico, o que exige uma conexão com um servidor de display (X11/Wayland). Em ambientes sem display gráfico configurado, isso causa o erro:

```
_tkinter.TclError: no display name and no $DISPLAY environment variable
```

**Solução:** Forçar o backend `Agg` antes de importar `pyplot`, e substituir `plt.show()` por `plt.savefig()`.

```python
# Antes
import matplotlib.pyplot as plt
# ...
plt.show()

# Depois
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
# ...
plt.savefig("regression_plot.png", dpi=150, bbox_inches="tight")
```

O backend `Agg` renderiza o gráfico em memória (sem janela) e permite salvar a imagem em disco. O arquivo `regression_plot.png` é gerado no diretório de execução do script.

---

## 2. Troca do dataset

**Antes:** O dataset **Bike Sharing** era baixado automaticamente do Kaggle via `kagglehub`, exigindo credenciais configuradas e conexão com a internet.

**Depois:** O dataset de **preços de ações da NVIDIA (1999–2024)** é lido diretamente de arquivos CSV locais, sem dependência de rede.

### Arquivos utilizados

| Arquivo | Período |
|---------|---------|
| `NVIDIA (1999 -11.07.2023).csv` | Janeiro/1999 → Julho/2023 |
| `Nvidia (2023 - 08.04.2024).csv` | Janeiro/2023 → Abril/2024 |

Os dois arquivos são concatenados, duplicatas removidas (pela coluna `Date`) e o resultado ordenado cronologicamente, resultando em **6.341 registros** cobrindo 25 anos de histórico.

```python
df_hist   = pd.read_csv(f"{data_dir}/NVIDIA (1999 -11.07.2023).csv")
df_recent = pd.read_csv(f"{data_dir}/Nvidia (2023 - 08.04.2024).csv")

dataset = pd.concat([df_hist, df_recent], ignore_index=True)
dataset["Date"] = pd.to_datetime(dataset["Date"])
dataset.drop_duplicates(subset="Date", inplace=True)
dataset.sort_values("Date", inplace=True)
```

---

## 3. Troca das variáveis do modelo

| | Modelo Original | Modelo Modificado |
|---|---|---|
| **X** | `temp` — temperatura normalizada (0 a 1) | Dias desde 1999-01-25 (inteiro) |
| **y** | `cnt` — contagem de aluguéis de bicicleta | `Adj Close` — preço de fechamento ajustado (USD) |

### Por que dias e não a data diretamente?

Modelos de machine learning do scikit-learn não aceitam objetos `datetime` como entrada. A data é convertida para um número inteiro representando quantos dias se passaram desde a primeira data do dataset:

```python
first_date = dataset["Date"].min()
X = ((dataset["Date"] - first_date).dt.days).values.reshape(-1, 1)
y = dataset["Adj Close"].values
```

Essa representação preserva a ordem e a escala temporal sem perda de informação.

### Por que `Adj Close` e não `Close`?

O preço de fechamento ajustado (`Adj Close`) desconta eventos corporativos como desdobramentos de ações (*stock splits*) e distribuição de dividendos, tornando a série temporal comparável ao longo de todo o período histórico.

---

## 4. Remoção da dependência `kagglehub`

O import e a chamada a `kagglehub.dataset_download()` foram removidos inteiramente, pois os dados agora são lidos de arquivos locais.

```python
# Removido
import kagglehub
path = kagglehub.dataset_download("lakshmi25npathi/bike-sharing-dataset")
url = path + "/hour.csv"
dataset = pd.read_csv(url)
```

---

## 5. Melhoria na visualização

**Antes:** O gráfico plotava os pontos de teste ordenados manualmente e as linhas de predição sobre esses pontos.

**Depois:** As curvas de predição são geradas sobre 500 pontos uniformemente espaçados ao longo de todo o intervalo histórico, produzindo curvas mais suaves e representativas. O eixo X exibe datas reais (não índices numéricos), com rotação automática dos rótulos.

```python
X_full_sorted = np.linspace(X.min(), X.max(), 500).reshape(-1, 1)
dates_full = pd.to_datetime(X_full_sorted[:, 0], unit="D", origin=first_date)

ax.scatter(pd.to_datetime(X[:, 0], unit="D", origin=first_date), y, ...)
ax.plot(dates_full, y_full_linear, ...)
ax.plot(dates_full, y_full_poly,   ...)
fig.autofmt_xdate()
```

---

## 6. Atualização do exemplo de previsão

**Antes:** A previsão de exemplo usava uma temperatura normalizada fixa (`0.6 ≈ 21.4°C`).

**Depois:** A previsão é feita para o dia seguinte ao último registro do dataset (09/04/2024), calculando os dias decorridos desde a data inicial:

```python
target_date = pd.Timestamp("2024-04-09")
days_ahead  = (target_date - first_date).days
```

---

## Resumo das Métricas (resultado da execução)

| Modelo | MSE | R² |
|--------|-----|----|
| Regressão Linear | 7.729,52 | 0,3347 |
| Regressão Polinomial (Grau 4) | 2.536,99 | 0,7816 |

O modelo polinomial explica **78% da variância** do preço histórico da NVIDIA, contra apenas 33% do modelo linear — evidenciando que a relação entre tempo e preço da ação é fortemente não-linear, com crescimento acelerado especialmente nos últimos anos.
