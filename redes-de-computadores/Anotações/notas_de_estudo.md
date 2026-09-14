# Aula - 

# Aula - 08/09/2026

a) 192.168.25.15/27
Rede: 192.168.25.0
Broadcast: 192.168.25.31

30 computadores na /27
/27, tirei 5 bits pra host (32-27). 2^5 = 32

b) 200.104.16.20
255.255.255.192
R: 200.104.16.0
B: 200.104.16.63

c) 10.0.103.2/23 23 = 2^ 9 = 512, cabe 510 computadores
R: 10.0.102.0
B: 10.0.103.255

d) 150.30.4.250/22 2^10 = 1024
R: 150.30.4.0
B: 150.30.7.255

e) 20.254.3.8/20 = 2^12 = 4096
R: 20.254.0.0
B: 20.254.15.255

f) 256.30.126.7/9
R:
B:

g) IP: 200.30.128.0
	255.255.248.0

LAB1 (150 PCS)
LAB2 (500 PCS)
LAB3 (50 PCS)
LAB4 (1030 PCS)
LAB5 (15 PCS)

IP: 200.30.128.0
255.255.248.0

200.30.128.0/21

total de endereços: 2048

hosts utilizáveis: 2046

Lab4: 1030 PCs
/21 não comporta 1030 hosts com sobra adequada, então:

/21 - 2048 endereços

hosts utilizáveis: 2046

máscara: 255.255.248.0

broadcast: 200.30.135.255

Lab2: 500 PCs
/23 - 512 endereços

hosts utilizáveis: 510

máscara: 255.255.254.0

broadcast: 200.30.137.255

Lab1: 150 PCs
/24 - 256 endereços

hosts utilizáveis: 254

máscara: 255.255.255.0

broadcast: 200.30.138.255

Lab3: 50 PCs
/26 - 64 endereços

hosts utilizáveis: 62

máscara: 255.255.255.192

broadcast: 200.30.139.63

Lab5: 15 PCs
/27 - 32 endereços

hosts utilizáveis: 30

máscara: 255.255.255.224

broadcast: 200.30.139.95

# Aula 01 - 28/07/2026

Abordagem inicial da matéria, discutindo o Plano de Ensino.

## Conceitos
- ipconfig
- getmac: pega o endereço MAC, seria o ID do dispositivo
- arp -a: mostra a interface da rede (quais computadores são vizinhos)
- 192.168.x.x: o computador interpreta como rede local
