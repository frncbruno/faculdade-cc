# Aula 03 - 03/08/2026

## Conceitos

- Padrão arquitetural (MVC)
- Model View Controller: 

---

## 1. Métodos Básicos

### Pente (Comb Sort)
* **Memória:** Interna
* **Estabilidade:** Instável
* **Complexidade:** $O(n^2)$ (pior caso) | $O(n \log n)$ (caso médio com reduções de gap)
* **Porção ordenada:** Distribuída / Parcialmente ordenada ao longo de todo o vetor até o ajuste final.

### Agitação (Shake Sort ou Cocktail Sort)
* **Memória:** Interna
* **Estabilidade:** Estável
* **Complexidade:** $O(n^2)$
* **Porção ordenada:** Alternada entre o início e o final (bidirecional).

---

## 2. Métodos Intermediários e Avançados ($O(n \log n)$ por Comparação)

### Shellsort
* **Memória:** Interna
* **Estabilidade:** Instável
* **Complexidade:** Varia conforme a sequência de incrementos (geralmente entre $O(n \log^2 n)$ e $O(n^{1.5})$)
* **Porção ordenada:** Subvetores intercalados em múltiplos gaps até a ordenação completa.

### Heapsort
* **Memória:** Interna
* **Estabilidade:** Instável
* **Complexidade:** $O(n \log n)$ (em todos os casos)
* **Porção ordenada:** Final (os maiores elementos são extraídos da heap e posicionados ao final do vetor).

### Mergesort
* **Memória:** Interna (requer espaço auxiliar $O(n)$) ou Externa (muito utilizado em arquivos grandes)
* **Estabilidade:** Estável
* **Complexidade:** $O(n \log n)$ (em todos os casos)
* **Porção ordenada:** Subvetores mesclados de forma recursiva até formar o vetor completo.

### Quicksort
* **Memória:** Interna
* **Estabilidade:** Instável (pode ser implementado de forma estável, mas a versão padrão é instável)
* **Complexidade:** $O(n \log n)$ (caso médio) | $O(n^2)$ (pior caso)
* **Porção ordenada:** Particionamento em torno de um pivô (elementos menores à esquerda, maiores à direita, com convergência para as extremidades).

---

## 3. Métodos de Ordenação Linear (Não Comparativos)

### Bucketsort (Ordenação por Baldes)
* **Memória:** Interna (requer estruturas auxiliares para os baldes)
* **Estabilidade:** Estável (dependendo do algoritmo utilizado para ordenar os baldes internos)
* **Complexidade:** $O(n + k)$ (caso médio) | $O(n^2)$ (pior caso, se todos caírem no mesmo balde)
* **Porção ordenada:** Distribuída por faixas de valores (baldes) que são posteriormente concatenadas.

### Radixsort
* **Memória:** Interna
* **Estabilidade:** Estável
* **Complexidade:** $O(d \cdot (n + k))$ (onde $d$ é o número de dígitos e $k$ é a base do sistema numérico)
* **Porção ordenada:** Ordenado progressivamente por dígito ou posição significativa (geralmente do dígito menos significativo para o mais significativo - LSD).

---

## Resumo Comparativo

| Algoritmo | Memória | Estabilidade | Complexidade (Médio/Melhor) | Complexidade (Pior Caso) | Porção Ordenada |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Comb Sort** | Interna | Instável | $O(n \log n)$ | $O(n^2)$ | Distribuída |
| **Shake Sort** | Interna | Estável | $O(n^2)$ | $O(n^2)$ | Início e Final (Alternado) |
| **Shellsort** | Interna | Instável | $O(n \log^2 n)$ | $O(n^2)$ ou $O(n^{1.5})$ | Intercalada por Gaps |
| **Heapsort** | Interna | Instável | $O(n \log n)$ | $O(n \log n)$ | Final |
| **Mergesort** | Interna/Externa | Estável | $O(n \log n)$ | $O(n \log n)$ | Subvetores Mesclados |
| **Quicksort** | Interna | Instável | $O(n \log n)$ | $O(n^2)$ | Partições ao redor do Pivô |
| **Bucketsort**| Interna | Estável | $O(n + k)$ | $O(n^2)$ | Baldes Concatenados |
| **Radixsort** | Interna | Estável | $O(d \cdot (n + k))$ | $O(d \cdot (n + k))$ | Por Dígito/Posição |

# Aula 02 - 30/07/2026

## Conceitos

- *CRUD: Create, Retrieve, Update, Delete*
- Por que ordenar? Para otimizar a busca ou pesquisa
- Pesquisar (search/find): localizar ou buscar um dado dentro de uma estrutura via alguma chave
- Recuperar (retrieve): busca ou localização de dados com relevância (semântica ou significado)
- Estabilidade: quanto a estrutura é desordenada até chegar na ordenação
- Complexidade de algoritmo: medida do esforço computacional (tempo e/ou memória)
- Quanto menor a complexidade, mais eficiente tende a ser o algoritmo
- Alta complexidade, mais esforço. Baixa complexidade, menos esforço
  
- **Notação Big O:** representa o crescimento do custo de um algoritmo conforme o tamanho da entrada aumenta
- Complexidades mais comuns:
  - **O(1):** constante
  - **O(log n):** logarítmica        - menor complexidade
  - **O(n!):** fatorial              - maior complexidade
  - **O(n):** linear
  - **O(n log n):** linearítmica
  - **O(n²):** quadrática
- Na análise de Big O, constantes e termos menores são desconsiderados, mantendo apenas o termo de maior crescimento
- Como mede a complexidade: trocas + comparações


# Aula 01 - 27/07/2026

Abordagem inicial da matéria, discutindo o Plano de Ensino, a importância da ordenação, conceitos fundamentais e as categorias de ordenação.  
Discussão sobre critério de notas a partir desse novo semestre.

## Conceitos
- SORT
- SELECT
- BUBBLE
- INSERT
- Agitação, Shell, Pente (COMB_SORT), Radix, Bucket
- Complexidade: esforço computacional, se o algoritmo faz muito esforço é ruim
- Pesquisa digital: algoritmos
- Ordenar: organizar uma estrutura (lista ou vetor) a partir de um ou mais índices
