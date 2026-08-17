# Aula 07 - 17/08/2026

- Entrega de atividade. Testando medição de tempo de diferentes ordenações no modelo MVC.
- [Clique aqui](https://github.com/frncbruno/faculdade-cc/tree/main/pesquisa-e-ordenacao/Exercicios/Lista%202)

# Aula 06 - 13/08/2026

## Conceitos

- Medição de tempo de ordenações em C# (quick sort e agitação)

```csharp
using System;
using System.Collections.Generic;
using System.Diagnostics;
class Ordenacao
{

    public static void agitacao(List<int> lista)
    {
        bool houveTroca;
        int tmp;
        int ini = 0;
        int fim = lista.Count;
        int qtdComparacoes = 0, qtdTrocas = 0;  //avalia a complexidade ou o esforço

        do
        {
            houveTroca = false;
            for (int i = ini; i < fim - 1; i++)
            {
                qtdComparacoes++;
                if (lista[i] > lista[i + 1])
                {
                    qtdTrocas++;
                    houveTroca = true;
                    tmp = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = tmp;
                }
            }

            if (!houveTroca)
            {
                break;
            }
            fim--;

            houveTroca = false;
            for (int i = fim; i >= ini + 1; i--)
            {
                qtdComparacoes++;
                if (lista[i] < lista[i - 1])
                {
                    qtdTrocas++;
                    houveTroca = true;
                    tmp = lista[i];
                    lista[i] = lista[i - 1];
                    lista[i - 1] = tmp;
                }
            }
            ini++;

        } while (houveTroca);
    }
}

class Util
{
    public static void popular(List<int> lista, int quantidade)
    {
        Random gerador = new Random();
        for (int i = 0; i < quantidade; i++)
        {
            lista.Add(gerador.Next(100000));
        }

    }

    public static void exibir(List<int> lista)
    {
        foreach (var item in lista)
        {
            Console.WriteLine(item.ToString());
        }
    }

    public static void exibirTempo(Stopwatch sw, string frase)
    {
        Console.WriteLine(frase + " (ms): " + sw.ElapsedMilliseconds);
    }
}

public class Principal()
{
    public static void Main(string[] args)
    {
        
        List<int> listaAgitacao = new List<int>();      
        List<int> listaSort = new List<int>();
        Util.popular(listaAgitacao, 100000);
        Util.popular(listaSort, 100000);

        Stopwatch sw = new Stopwatch();

        sw.Start();
        Ordenacao.agitacao(listaAgitacao);
        sw.Stop();
        Util.exibirTempo(sw, "Ordenacao por agitacao");        
        sw.Reset();

        sw.Start();
        listaSort.Sort();
        sw.Stop();
        Util.exibirTempo(sw, "Ordenacao por sort nativo");
        sw.Reset();


        //Util.exibir(lista);
    }
}
```  

# Aula 05 - 10/08/2026

## Conceitos

## Bolha
- Simples (memoria RAM)
- Estável
- 0 (n²)
- A ideia da bolha é porque o maior valor em cada "pesado" é levado até o final da estrutura

## Seleção 
- Simples (memoria RAM)
- 0 (n²)
- Instável
- A ideia da seleção é porque a cada célula de vetor, ao avançar, se seleciona (ou laça) o menor valor para aquela posição

## Inserção
- Simples (memoria RAM)
- Estável
- 0 (n²)
- A ideia da inserção é porque cada vetor da porção direita da estrutura é inserido na sua posição correta na porção esquerda


# Aula 04 - 06/08/2026

Realização de exercícios sobre **MVC**.

## Conceitos

## Model
Responsável pelos **dados** e pela **lógica de negócio**.

- Armazena informações.
- Manipula os dados.
- Não exibe nada na tela.

**Exemplo:**
- `ListaModel.java`
- `Utilidades.java`

---

## View
Responsável pela **interface com o usuário**.

- Exibe informações.
- Recebe entradas do usuário (quando houver).
- Não contém regras de negócio.

**Exemplo:**
- `ListaView.java`

---

## Controller
Responsável por **controlar o fluxo da aplicação**.

- Recebe as ações do usuário.
- Chama o Model para processar os dados.
- Chama a View para exibir os resultados.

**Exemplo:**
- `ListaController.java`

---

## Main
Ponto de entrada da aplicação.

- Cria o Controller.
- Inicia a execução do programa.

**Exemplo:**
- `Main.java`

---

## Fluxo do MVC

```text
Main
  │
  ▼
Controller
  ├──► Model (processa os dados)
  └──► View (exibe os resultados)
```

## Resumo

| Camada | Função |
|--------|--------|
| **Model** | Dados e lógica de negócio. |
| **View** | Exibe informações ao usuário. |
| **Controller** | Coordena a comunicação entre Model e View. |
| **Main** | Inicia a aplicação. |

---

# Anotacoes: Padrao de Desenvolvimento e Benchmark

## 1. Padrao de Desenvolvimento

### MVC (Model-View-Controller)
Padrao arquitetural que separa a aplicacao em tres camadas principais para organizar o codigo e facilitar a escalabilidade e manutencao:
- **Model (Modelo):** Gerencia os dados, regras de negocio e logica de persistencia.
- **View (Visao):** A interface com o usuario (UI), responsavel por exibir visualmente os dados.
- **Controller (Controlador):** Intermedeia a comunicacao entre o usuario (View) e os dados (Model), tratando requisicoes e regras de fluxo.

### Documentacao
Pratica fundamental para garantir a manutenibilidade e o entendimento do sistema:
- **Codigo Limpo:** Nomes descritivos e codigo autoexplicativo como primeira linha de documentacao.
- **README.md:** Instrucoes claras de configuracao, instalacao, execucao e testes do projeto.
- **Documentacao Tecnica:** Descricao de APIs, fluxos arquiteturais e contratos de codigo.

---

## 2. Benchmark

Processo sistematico para avaliar o desempenho, a eficiencia e o consumo de recursos de algoritmos ou sistemas atraves de metricas padronizadas.

### Principais Medicoes
- **Complexidade Operacional:**
  - **Quantidade de Comparacoes:** Numero de vezes que elementos sao testados/comparados entre si (crucial para algoritmos de busca e ordenacao).
  - **Quantidade de Trocas (Swaps):** Numero de movimentacoes ou reordenacoes fisicas de dados na memoria.
- **Tempo de Execucao:**
  - Medicao do tempo real (wall-clock time) ou tempo de processador (CPU time) necessario para que o algoritmo conclua sua execucao sob diferentes tamanhos de entrada (N).

# Aula 03 - 03/08/2026

## Conceitos

O **MVC** (Model-View-Controller, ou Modelo-Visão-Controle) é um dos padrões de arquitetura de software mais conhecidos e utilizados no desenvolvimento de sistemas. Seu objetivo principal é a **separação de responsabilidades**, dividindo a aplicação em três camadas interconectadas para facilitar a manutenção, escalabilidade e reutilização de código.

---

## 1. As Três Camadas do MVC

### Model (Modelo)
* **O que é:** É o coração da aplicação. Representa a camada de dados, a lógica de negócios e as regras que governam como os dados são criados, armazenados e modificados.
* **Responsabilidades:**
  * Comunicação direta com o banco de dados (gerenciamento de persistência).
  * Validação de regras de negócio.
  * Notificação da camada de controle ou visão sobre alterações em seu estado (quando aplicável).

### View (Visão)
* **O que é:** É a interface de usuário (UI) da aplicação. É tudo o que o usuário enxerga e com o que interage diretamente (telas, formulários, botões, relatórios, etc.).
* **Responsabilidades:**
  * Exibir os dados fornecidos pelo Model de forma clara e amigável.
  * Capturar as ações e entradas do usuário (cliques, digitação) e enviá-las para o Controller.
  * **Regra de ouro:** A View não deve conter lógicas de negócios complexas ou acessos diretos ao banco de dados.

### Controller (Controlador)
* **O que é:** É o intermediário (o "cérebro" de coordenação) entre a View e o Model.
* **Responsabilidades:**
  * Receber as requisições e entradas do usuário vindas da View.
  * Processar essas entradas (muitas vezes acionando o Model para buscar ou salvar dados).
  * Selecionar a resposta adequada e decidir qual View deve ser renderizada e enviada de volta ao usuário.

---

## 2. Como Funciona o Fluxo de Dados

O ciclo de vida típico de uma requisição em uma arquitetura MVC segue esta sequência:

1. **Ação do Usuário:** O usuário interage com a interface (ex: clica em um botão de login ou preenche um formulário na **View**).
2. **Envio para o Controller:** A **View** envia essa requisição (dados de entrada) para o **Controller**.
3. **Processamento no Model:** O **Controller** recebe a requisição, interpreta-a e aciona o **Model** para processar os dados ou buscar informações no banco de dados.
4. **Retorno dos Dados:** O **Model** executa a lógica de negócio, interage com o banco de dados e retorna o resultado para o **Controller**.
5. **Atualização da View:** O **Controller** recebe os dados processados do **Model** e os encaminha para a **View** correta, que atualiza a interface para exibir o resultado ao usuário.

---

## 3. Vantagens da Arquitetura MVC

* **Separação de Interesses (Separation of Concerns):** Código limpo e modular. Alterações na interface (View) não afetam a lógica de negócios (Model).
* **Desenvolvimento Simultâneo:** Diferentes membros da equipe podem trabalhar em partes distintas ao mesmo tempo (um focado no design/View, outro no banco/Model e outro nas regras/Controller).
* **Facilidade de Manutenção e Testes:** Como as camadas são independentes, fica muito mais fácil testar a lógica de negócios de forma isolada e realizar manutenções corretivas ou evolutivas.
* **Reutilização de Código:** Um único Model pode ser reutilizado por diferentes Views (ex: uma aplicação web e um aplicativo mobile consumindo a mesma API/Model).

---

## Resumo Comparativo dos Componentes

| Componente | Responsabilidade Principal | Interage com |
| :--- | :--- | :--- |
| **Model (Modelo)** | Dados e Regras de Negócio | Banco de Dados e Controller |
| **View (Visão)** | Interface de Usuário (Apresentação) | Usuário e Controller |
| **Controller (Controlador)** | Intermediação e Fluxo da Aplicação | Model e View |

---

## 1. Métodos Básicos

### Bolha (Bubble Sort)
* **Memória:** Interna
* **Estabilidade:** Estável
* **Complexidade:** $O(n)$ (melhor caso, com otimização) | $O(n^2)$ (caso médio e pior caso)
* **Porção ordenada:** Final (os maiores elementos "borbulham" progressivamente para o final do vetor).

### Seleção (Selection Sort)
* **Memória:** Interna
* **Estabilidade:** Instável (pode ser implementado de forma estável, mas a versão clássica é instável)
* **Complexidade:** $O(n^2)$ (em todos os casos)
* **Porção ordenada:** Início (o menor elemento é selecionado e colocado na posição correta à esquerda a cada iteração).

### Inserção (Insertion Sort)
* **Memória:** Interna
* **Estabilidade:** Estável
* **Complexidade:** $O(n)$ (melhor caso, vetor já ordenado) | $O(n^2)$ (caso médio e pior caso)
* **Porção ordenada:** Início (os elementos são inseridos um a um em suas posições corretas dentro de um subvetor que cresce ordenado à esquerda).

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

## Resumo Comparativo Geral

| Algoritmo | Memória | Estabilidade | Complexidade (Melhor) | Complexidade (Médio) | Complexidade (Pior) | Porção Ordenada |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | Interna | Estável | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Final |
| **Selection Sort**| Interna | Instável | $O(n^2)$ | $O(n^2)$ | $O(n^2)$ | Início |
| **Insertion Sort**| Interna | Estável | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Início |
| **Comb Sort** | Interna | Instável | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$ | Distribuída |
| **Shake Sort** | Interna | Estável | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Início e Final (Alternado) |
| **Shellsort** | Interna | Instável | $O(n \log n)$ | $O(n \log^2 n)$ | $O(n^2)$ ou $O(n^{1.5})$ | Intercalada por Gaps |
| **Heapsort** | Interna | Instável | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | Final |
| **Mergesort** | Interna/Externa | Estável | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | Subvetores Mesclados |
| **Quicksort** | Interna | Instável | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$ | Partições ao redor do Pivô |
| **Bucketsort**| Interna | Estável | $O(n + k)$ | $O(n + k)$ | $O(n^2)$ | Baldes Concatenados |
| **Radixsort** | Interna | Estável | $O(d \cdot (n + k))$ | $O(d \cdot (n + k))$ | $O(d \cdot (n + k))$ | Por Dígito/Posição |

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
