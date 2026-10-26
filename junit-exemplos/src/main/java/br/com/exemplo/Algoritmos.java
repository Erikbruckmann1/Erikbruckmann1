package br.com.exemplo;

/**
 * Algoritmos clássicos e simples para praticar testes unitários.
 */
public class Algoritmos {

    /** Retorna n! (fatorial). Lança exceção para n negativo. */
    public static long fatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n não pode ser negativo");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    /** Verifica se um número é primo. */
    public static boolean ehPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /** Verifica se um texto é palíndromo, ignorando maiúsculas e espaços. */
    public static boolean ehPalindromo(String texto) {
        if (texto == null) {
            return false;
        }
        String limpo = texto.replaceAll("\\s+", "").toLowerCase();
        return new StringBuilder(limpo).reverse().toString().equals(limpo);
    }

    /** Retorna o maior valor de um array. */
    public static int maiorValor(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("Array vazio ou nulo");
        }
        int maior = numeros[0];
        for (int numero : numeros) {
            if (numero > maior) {
                maior = numero;
            }
        }
        return maior;
    }

    /** Inverte a ordem dos elementos de um array, retornando um novo array. */
    public static int[] inverter(int[] numeros) {
        int[] invertido = new int[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            invertido[i] = numeros[numeros.length - 1 - i];
        }
        return invertido;
    }
}
