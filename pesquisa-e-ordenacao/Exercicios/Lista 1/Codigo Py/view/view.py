class ListaView:
    def exibir_lista(self, lista: list, titulo: str):
        print(titulo)
        for numero in lista:
            print(numero)
        print("---------------------")
        print(f"Total: {len(lista)}")

    def exibir_tempo(self, rotina: str, tempo_ms: float):
        print(f"Tempo (ms) {rotina}: {tempo_ms:.2f}")