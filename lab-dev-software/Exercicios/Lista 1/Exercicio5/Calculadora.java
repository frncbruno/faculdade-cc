public class Calculadora implements ICalculadora {

    @Override
    public double somar(double n1, double n2) {
        return n1 + n2;
    }

    @Override
    public double subtrair(double n1, double n2) {
        return n1 - n2;
    }

    @Override
    public double multiplicar(double n1, double n2) {
        return n1 * n2;
    }

    @Override
    public double dividir(double n1, double n2) {
        if (n2 == 0) {
            throw new ArithmeticException("Não é possível dividir por zero.");
        }
        return n1 / n2;
    }

    @Override
    public double raizquadrada(double n1, double n2) {
        if (n1 < 0) {
            throw new IllegalArgumentException("Não existe raiz quadrada de número negativo.");
        }
        return Math.sqrt(n1);
    }

    @Override
    public double elevarPotencia(double n1, double n2) {
        return Math.pow(n1, n2);
    }

    @Override
    public double logaritmo10(double n1) {
        if (n1 <= 0) {
            throw new IllegalArgumentException("O logaritmo só existe para números positivos.");
        }
        return Math.log10(n1);
    }
}
