import time
from model import ListaModel, Utilidades
from view import ListaView

class ListaController:
    def __init__(self):
        self.model = ListaModel()
        self.view = ListaView()

    def executar(self):
        # Medição Rotina 1
        inicio = time.time_ns()
        Utilidades.popular_lista(
            self.model.get_lista_aleatoria(),
            100000,
            100,
            100000,
            True
        )
        fim = time.time_ns()
        self.view.exibir_tempo("Rotina 1", (fim - inicio) / 1_000_000)

        # Medição Rotina 2
        inicio = time.time_ns()
        Utilidades.popular_lista(
            self.model.get_lista_sequencial(),
            100000,
            1,
            100000,
            False
        )
        fim = time.time_ns()
        self.view.exibir_tempo("Rotina 2", (fim - inicio) / 1_000_000)

        # Caso queira mostrar as listas:
        # self.view.exibir_lista(self.model.get_lista_aleatoria(), "Lista Aleatória")
        # self.view.exibir_lista(self.model.get_lista_sequencial(), "Lista Sequencial")