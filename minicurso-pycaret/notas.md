# Anotações - PyCaret - 16/09/2026

PyCaret não é uma alternativa ao machine learning tradicional. É uma camada de automação construída sobre ele.  
- Automatiza tarefas repetitivas de pré-processamento
- Compara múltiplos modelos com uma linha de código
- Reproduz experimentos com configuração centralizada
- Reduz tempo de experimentação, não de raciocínio
- setup(), compare_models(), create_model(), tune_model(), evaluate_model()
- Data Leakage -- tomar cuidado
- Uso ideal: Dados tabulares estruturados, baseline rápido necessário, time pequeno e tempo limitado
- Uso bom: Experimento antes de ML customizado, ensino e didática, prototipagem
- Cuidado: dados muito pequenos (abaixo de 500 linhas), classes muito desbalanceadas
- Não indicado: dados não tabulares (imagem, texto), modelos customizados complexos, produção com requisitos de performance extremos
- Aplicar no dataset IBM telco customer churn

```python
import pandas as pd

df = pd.read_csv("../data/Telco-Customer-Churn.csv")

df.head(2)

df.shape

df.info()

df["Churn"].value_counts()

df.isnull().sum()

df["TotalCharges"].dtype

df["TotalCharges"].value_counts().head()

from pycaret.classification import *

clf = setup(
    data=df,
    target="Churn",
    session_id=123
)

best_model = compare_models()

knn = create_model(best_model)

evaluate_model(knn)

tuned_knn = tune_model(knn)

print(knn)
print(tuned_knn)

predictions = predict_model(tuned_knn)

predictions.head()

novo_cliente = df.drop(columns=["Churn"]).iloc[[0]]

predict_model(
    tuned_knn,
    data=novo_cliente
)

# Agora, para criar um modelo:

import os
os.makedirs("models", exist_ok=True)

save_model(tuned_knn, "models/churn_model_v1")

# Para usar:

modelo_carregado = load_model("models/churn_model_v1")

predict_model(
    modelo_carregado,
    data=novo_cliente
)
```

<img width="534" height="655" alt="{96338448-391F-4001-9F4D-99C531943657}" src="https://github.com/user-attachments/assets/0f8f61b2-181d-4668-bca7-c6b7baa2f139" />

<img width="483" height="726" alt="{B5206CBE-CD43-47EC-8536-E65DD4D394B0}" src="https://github.com/user-attachments/assets/03199a1e-18da-4bbb-8021-e63da42fd9cd" />

<img width="996" height="475" alt="{140A8B58-769A-409E-85F4-5D546504093A}" src="https://github.com/user-attachments/assets/37e3c7d8-e56d-4120-9de9-60c5be8d4138" />

<img width="667" height="512" alt="{5040000A-FFA6-48AC-96D0-0AED093608CB}" src="https://github.com/user-attachments/assets/f3d791d1-0487-419e-a4e1-3476d1c13376" />

<img width="1404" height="728" alt="{FE776380-F80E-4C4F-9A15-919918ECA7D6}" src="https://github.com/user-attachments/assets/9784931f-7185-4303-b51a-0c302169fd34" />

