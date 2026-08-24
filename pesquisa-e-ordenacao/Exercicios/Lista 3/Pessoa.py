from functools import cmp_to_key

class Pessoa:
    def __init__(self, nome, idade):
        self.__nome = nome
        self.__idade = idade

    def get_nome(self):
        return self.__nome

    def set_nome(self, nome):
        self.__nome = nome

    def get_idade(self):
        return self.__idade

    def set_idade(self, idade):
        self.__idade = idade

    def compareTo(self, outra):
        resultado = (self.__nome > outra.__nome) - (self.__nome < outra.__nome)

        if resultado == 0:
            resultado = self.__idade - outra.__idade

        return resultado


pessoas = [
    Pessoa("Carlos", 25),
    Pessoa("Ana", 20),
    Pessoa("Bruno", 30),
    Pessoa("Ana", 18),
    Pessoa("Eduardo", 28)
]

pessoas.sort(key=cmp_to_key(lambda a, b: a.compareTo(b)))

for pessoa in pessoas:
    print(pessoa.get_nome(), pessoa.get_idade())
