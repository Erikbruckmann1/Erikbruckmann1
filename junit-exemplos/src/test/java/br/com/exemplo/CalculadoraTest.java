package br.com.exemplo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes da Calculadora")
class CalculadoraTest {

    private Calculadora calculadora;

    // Executa ANTES de cada @Test: garante um objeto novo e limpo por teste
    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    // Executa DEPOIS de cada @Test: útil para liberar recursos
    @AfterEach
    void tearDown() {
        calculadora = null;
    }

    @Test
    @DisplayName("Deve somar dois números positivos")
    void deveSomarDoisNumeros() {
        // Arrange (preparar)
        int a = 2;
        int b = 3;

        // Act (agir)
        int resultado = calculadora.somar(a, b);

        // Assert (verificar)
        assertEquals(5, resultado);
    }

    @Test
    @DisplayName("Deve subtrair resultando em número negativo")
    void deveSubtrair() {
        assertEquals(-2, calculadora.subtrair(3, 5));
    }

    @Test
    @DisplayName("Deve multiplicar por zero e retornar zero")
    void deveMultiplicarPorZero() {
        assertEquals(0, calculadora.multiplicar(10, 0));
    }

    @Test
    @DisplayName("Deve dividir com casas decimais")
    void deveDividir() {
        // O terceiro parâmetro é a tolerância (delta) para comparar doubles
        assertEquals(2.5, calculadora.dividir(5, 2), 0.0001);
    }

    @Test
    @DisplayName("Deve lançar exceção ao dividir por zero")
    void deveLancarExcecaoAoDividirPorZero() {
        ArithmeticException excecao = assertThrows(
                ArithmeticException.class,
                () -> calculadora.dividir(10, 0)
        );
        assertEquals("Divisão por zero não é permitida", excecao.getMessage());
    }
}
