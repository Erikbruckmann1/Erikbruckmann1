package br.com.exemplo;

/**
 * Calculadora simples usada como alvo dos testes.
 */
public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    /**
     * Divide a por b.
     *
     * @throws ArithmeticException se b for zero
     */
    public double dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return (double) a / b;
    }
}
