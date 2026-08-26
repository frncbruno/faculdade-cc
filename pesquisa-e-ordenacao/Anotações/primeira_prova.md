# Anotações antes da primeira prova

## 1. O que é ordenação e por que ela é importante?
Ordenar é organizar uma estrutura de dados, como uma lista ou vetor, de forma crescente ou decrescente, utilizando uma ou mais chaves de controle.  
*Mas por que ordenar?*  
Ordenar → deixa a pesquisa/busca mais eficiente.

## 2. O que é pesquisar?
Localizar/buscar um dado dentro de uma estrutura utilizando alguma chave.  
*Qual a diferença de pesquisar e ordenar?*  
Ordenação organiza os dados de acordo com uma ou mais chaves, enquanto pesquisa procura/localiza um dado dentro da estrutura por meio de uma chave.  
- Recuperar: localizar ou buscar dados com relevância (semântica ou significado)

## 3. O que são chaves?
A ordenação pode utilizar uma ou mais chaves de controle. 
- Chave primária: **a principal chave para ordenação**, em bancos bem organizados seria o CPF, por exemplo. Mas caso não há, pode ser nome, idade, etc.
- Chave secundária: **usada quando existe desempate na chave primária**. Por exemplo, se a chave primária for nome, pode acontecer o acaso de existirem mais de dois "Bruno", então o desempate ficaria para a chave secundária idade.
```java
int resultado = this.getNome().compareTo(o.getNome());

if(resultado != 0){
    return resultado;
}

return Integer.compare(this.idade, o.getIdade());
```
Primeiro compara nome.

Se os nomes forem diferentes, utiliza o resultado.

Se os nomes forem iguais, utiliza idade como segundo critério.

## 4. Ordenar uma lista de números x ordenar objetos
Quando temos algo simples como números, é fácil definir o **maior e menor**. Mas quando temos objetos, existe mais de uma informação, por exemplo *"nome"* e *"idade"*.  

E o computador precisa saber: *"Quando você fala para ordenar Pessoa, eu devo considerar o quê?"*  
É aí que entra o controle da chave e o **compareTo**.  

```java
Pessoa implements Comparable<Pessoa>

...

@Override
public int compareTo(Pessoa o)
```
Dentro dele, primeiro é feita a comparação pelo nome e, em caso de empate, pela idade.

## 5. O que é o compareTo?
Utilizado para definir como os objetos serão **comparados/ordenados**.

```java
public int compareTo(Pessoa o) {
   int resultado = this.getNome().compareTo(o.getNome());

   if(resultado != 0){
       return resultado;
   }

   return Integer.compare(this.idade, o.getIdade());
}
```

1. compara o nome. 
2. verifica se houve diferença - if(resultado != 0). Se houve diferença, ja existe uma ordem definida.
3. se empatou, compara a idade - Integer.compare(this.idade, o.getIdade()).

## 6. E se eu quiser controlar outra ordenação?

```java
lista.sort((p1, p2) -> Integer.compare(p2.getIdade(), p1.getIdade()));
```
Nesse caso, a ordenação é controlada diretamente pela idade, em ordem decrescente. Ou seja:
- compareTo: Define a comparação/ordenação padrão dos objetos.
- sort com comparação: Permite controlar outro critério de ordenação.

## 7. Algoritmo estável ou instável
Estável: Um algoritmo estável garante a ordenação temporária da estrutura durante o processo.  

| Algoritmo  | Estabilidade |
| ---------- | ------------ |
| Bolha      | Estável      |
| Inserção   | Estável      |
| Agitação   | Estável      |
| Mergesort  | Estável      |
| Bucketsort | Estável      |
| Radix      | Estável      |

Instável: O algoritmo não garante a ordenação temporária da estrutura durante o processo.  

| Algoritmo | Estabilidade |
| --------- | ------------ |
| Seleção   | Instável     |
| Pente     | Instável     |
| Shellsort | Instável     |
| Heapsort  | Instável     |
| Quicksort | Instável     |

## 8. "Simples" x "não simples"

Simples
- Bolha
- Seleção
- Inserção

Todos são classificados nas fontes como **algoritmos simples** e de **memória interna/RAM**.

## 9. Complexidade: como saber qual é maior?
Complexidade computacional = esforço computacional do algoritmo. Analisando principalmente:
- quantidade de **comparações** * quantidade de **trocas**

```java
int qtdComparacoes = 0, qtdTrocas = 0;
```
Essas variáveis são utilizadas para medir a complexidade/esforço.

## 10. Comparações x trocas
- Comparações: Quando o algoritmo verifica dois valores. 5 > 3? Isso é uma comparação.
- Troca: Quando os elementos realmente mudam de posição. [5,3] vira [3, 5]
- Quantidade de comparações = número de vezes que elementos são testados/comparados.
- Quantidade de trocas = número de movimentações/reordenações físicas dos dados.

## 11. Como comparar a complexidade de dois algoritmos? 
- mais esforço → maior complexidade
- menos esforço → menor complexidade  

O(log n)  → menor complexidade  
O(n)      → linear  
O(n²)     → maior  
O(n^k)    → maior  
O(n!)     → pior/maior  

O(log n) < O(n) < O(n²) < O(n^k) < O(n!)

## 11. Cenários de pior caso
- Bolha: lista ordenada de forma decrescente quando queremos ordenar de forma crescente
- Seleção: lista ordenada
- Inserção: lista ordenada de forma decrescente quando queremos ordenar de forma crescente

## 12. Código bolha para revisão

```java
public static void bolha(int[] vetor) {

    int aux;

    for (int i = 0; i < vetor.length - 1; i++) {

        for (int j = 0; j < vetor.length - 1 - i; j++) {

            if (vetor[j] > vetor[j + 1]) {

                aux = vetor[j];
                vetor[j] = vetor[j + 1];
                vetor[j + 1] = aux;
            }
        }
    }
}
```

```java
void bolha(List<> lista) {
    boolean houveTroca;
    int tmp;
    int qtdComparacoes = 0, qtdTrocas = 0;

    do {
        houveTroca = False;
        for (int i = 0; i < lista.size()-1; i++){
            qtdComparacoes++;
            if (lista.get(i) > lista.get(i+1)) {
                qtdTrocas++;
                houveTroca = True;
                tmp = lista.get(i);
                lista.set(i, lista.get(i+1));
                lista.set(i+1, tmp);
            }
        }
    } while (houveTroca);
}
```


