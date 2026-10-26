package br.com.exemplo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes dos Algoritmos")
class AlgoritmosTest {

    @Nested
    @DisplayName("Fatorial")
    class FatorialTest {

        // Um mesmo teste executado com vários pares (entrada, esperado)
        @ParameterizedTest(name = "{0}! = {1}")
        @CsvSource({
                "0, 1",
                "1, 1",
                "5, 120",
                "10, 3628800"
        })
        void deveCalcularFatorial(int n, long esperado) {
            assertEquals(esperado, Algoritmos.fatorial(n));
        }

        @Test
        @DisplayName("Deve rejeitar número negativo")
        void deveRejeitarNegativo() {
            assertThrows(IllegalArgumentException.class, () -> Algoritmos.fatorial(-1));
        }
    }

    @Nested
    @DisplayName("Número primo")
    class PrimoTest {

        @ParameterizedTest(name = "{0} é primo")
        @ValueSource(ints = {2, 3, 5, 7, 13, 97})
        void deveIdentificarPrimos(int n) {
            assertTrue(Algoritmos.ehPrimo(n));
        }

        @ParameterizedTest(name = "{0} não é primo")
        @ValueSource(ints = {-7, 0, 1, 4, 9, 100})
        void deveIdentificarNaoPrimos(int n) {
            assertFalse(Algoritmos.ehPrimo(n));
        }
    }

    @Nested
    @DisplayName("Palíndromo")
    class PalindromoTest {

        @ParameterizedTest(name = "\"{0}\" é palíndromo")
        @ValueSource(strings = {"arara", "Ana", "A sacada da casa"})
        void deveReconhecerPalindromos(String texto) {
            assertTrue(Algoritmos.ehPalindromo(texto));
        }

        @Test
        @DisplayName("Deve retornar false para texto que não é palíndromo")
        void deveRejeitarNaoPalindromo() {
            assertFalse(Algoritmos.ehPalindromo("java"));
        }

        @Test
        @DisplayName("Deve retornar false para null")
        void deveRetornarFalseParaNull() {
            assertFalse(Algoritmos.ehPalindromo(null));
        }
    }

    @Nested
    @DisplayName("Arrays")
    class ArraysTest {

        @Test
        @DisplayName("Deve encontrar o maior valor, inclusive com negativos")
        void deveEncontrarMaiorValor() {
            // assertAll executa TODAS as verificações e relata todas as falhas juntas
            assertAll(
                    () -> assertEquals(9, Algoritmos.maiorValor(new int[]{3, 9, 1})),
                    () -> assertEquals(-1, Algoritmos.maiorValor(new int[]{-5, -1, -3})),
                    () -> assertEquals(42, Algoritmos.maiorValor(new int[]{42}))
            );
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve rejeitar array nulo ou vazio")
        void deveRejeitarArrayInvalido(int[] numeros) {
            assertThrows(IllegalArgumentException.class, () -> Algoritmos.maiorValor(numeros));
        }

        @Test
        @DisplayName("Deve inverter o array")
        void deveInverterArray() {
            assertArrayEquals(new int[]{3, 2, 1}, Algoritmos.inverter(new int[]{1, 2, 3}));
        }
    }
}
