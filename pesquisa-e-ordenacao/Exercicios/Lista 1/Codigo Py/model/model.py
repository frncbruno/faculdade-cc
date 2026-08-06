import random

class ListaModel:
    def __init__(self):
        self.lista_aleatoria = []
        self.lista_sequencial = []

    def get_lista_aleatoria(self):
        return self.lista_aleatoria

    def get_lista_sequencial(self):
        return self.lista_sequencial


class Utilidades:
    @staticmethod
    def popular_lista(lista: list, quantidade_numeros: int, inicio: int, fim: int, aleatorio: bool):
        if aleatorio:
            for _ in range(quantidade_numeros):
                lista.append(random.randint(inicio, fim - 1))
        else:
            for i in range(inicio, quantidade_numeros + 1):
                lista.append(i)