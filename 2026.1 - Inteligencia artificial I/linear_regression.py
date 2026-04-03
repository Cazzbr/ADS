import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score


def make_dataset(n=120, seed=42):
    rng = np.random.default_rng(seed)
    hours_studied = rng.uniform(1, 10, n)
    noise = rng.normal(0, 5, n)
    exam_score = 5 * hours_studied + 20 + noise
    return hours_studied.reshape(-1, 1), exam_score


def main():
    print("=== Aprendizagem Supervisionada: Regressão Linear ===")
    print("Objetivo: Prever a nota de um aluno em um exame com base nas horas estudadas.\n")

    X, y = make_dataset()

    print("1) Primeiras 5 amostras (entrada -> saída):")
    for i in range(5):
        print(f"   Horas: {X[i, 0]:.2f} -> Pontos: {y[i]:.2f}")

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=7
    )

    print("\n2) Treinando o modelo...")
    model = LinearRegression()
    model.fit(X_train, y_train)

    print(f"   Interceptação aprendida(b): {model.intercept_:.4f}")
    print(f"   Coeficiente aprendido (m): {model.coef_[0]:.4f}")
    print("   Equação do modelo:")
    print(f"   predicted_score = {model.coef_[0]:.4f} * hours + {model.intercept_:.4f}")

    y_pred = model.predict(X_test)

    mae = mean_absolute_error(y_test, y_pred)
    mse = mean_squared_error(y_test, y_pred)
    rmse = np.sqrt(mse)
    r2 = r2_score(y_test, y_pred)

    print("\n3) Resultados nos dados de teste:")
    print(f"   MAE  (Média do erro absoluto): {mae:.4f}")
    print(f"   MSE  (Média do erro quadrático):  {mse:.4f}")
    print(f"   RMSE (erro em unidades de pontos):   {rmse:.4f}")
    print(f"   R²   (qualidade do ajuste):            {r2:.4f}")

    print("\n4) Previsões de exemplo vs valores reais:")
    for actual_hours, actual_score, predicted_score in zip(X_test[:10], y_test[:10], y_pred[:10]):
        error = actual_score - predicted_score
        print(
            f"   Horas={actual_hours[0]:.2f} | Real={actual_score:.2f} | "
            f"Previsto={predicted_score:.2f} | Erro={error:.2f}"
        )

    print("\n5) Exemplo de previsão manual:")
    hours = np.array([[6.5]])
    predicted = model.predict(hours)[0]
    print(f"   Para {hours[0,0]:.1f} horas de estudo, pontuação prevista = {predicted:.2f}")    


if __name__ == "__main__":
    main()
