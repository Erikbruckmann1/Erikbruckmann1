# Exemplos de Testes com JUnit 5

Projeto simples para aprender **testes unitários em Java com JUnit 5 (Jupiter)**.
Ele contém duas classes "de produção" e as classes de teste correspondentes.

```
junit-exemplos/
├── pom.xml                                   # Configuração Maven + dependência do JUnit
└── src/
    ├── main/java/br/com/exemplo/
    │   ├── Calculadora.java                  # Operações aritméticas
    │   └── Algoritmos.java                   # Fatorial, primo, palíndromo, arrays
    └── test/java/br/com/exemplo/
        ├── CalculadoraTest.java              # Testes básicos, ciclo de vida, exceções
        └── AlgoritmosTest.java               # Testes parametrizados, @Nested, assertAll
```

## Como executar

Pré-requisitos: **Java 17+** e **Maven**.

```bash
cd junit-exemplos
mvn test
```

Saída esperada: `Tests run: 31, Failures: 0, Errors: 0, Skipped: 0` — **BUILD SUCCESS**.

Também é possível rodar pela IDE (IntelliJ, Eclipse, VS Code): clique no ícone ▶ ao lado da classe ou do método de teste.

---

## 1. Principais conceitos

### O que é um teste unitário?
É um código que verifica automaticamente se uma **unidade pequena** do sistema (normalmente um método)
se comporta como esperado. Ele deve ser:

- **Rápido** – roda em milissegundos;
- **Isolado** – não depende de outros testes, banco de dados ou rede;
- **Repetível** – sempre dá o mesmo resultado;
- **Autoverificável** – passa ou falha sozinho, sem alguém conferir a saída.

### O que é o JUnit?
É o framework de testes mais usado em Java. A versão 5 é composta por:

| Módulo | Função |
|---|---|
| **JUnit Platform** | Base que lança os testes (usada por Maven, Gradle e IDEs) |
| **JUnit Jupiter** | API nova: anotações (`@Test`...) e asserções (`assertEquals`...) |
| **JUnit Vintage** | Permite rodar testes antigos do JUnit 3/4 |

A dependência `junit-jupiter` no `pom.xml` já traz tudo o que é necessário. O escopo `test`
significa que ela só é usada durante os testes, não vai para o código final.

### Padrão AAA (Arrange, Act, Assert)
Forma recomendada de organizar cada teste:

1. **Arrange** – preparar os dados e objetos;
2. **Act** – executar o método testado;
3. **Assert** – verificar o resultado.

### Convenções
- Classe de teste = nome da classe testada + `Test` (`Calculadora` → `CalculadoraTest`).
- Fica em `src/test/java`, **no mesmo pacote** da classe testada.
- Nome do método descreve o comportamento: `deveLancarExcecaoAoDividirPorZero`.
- Teste o "caminho feliz" **e** os casos de borda (zero, negativo, `null`, array vazio).

---

## 2. Anotações mais usadas

| Anotação | Para que serve |
|---|---|
| `@Test` | Marca um método como teste |
| `@DisplayName("...")` | Nome legível exibido no relatório/IDE |
| `@BeforeEach` | Executa **antes de cada** teste (preparação) |
| `@AfterEach` | Executa **depois de cada** teste (limpeza) |
| `@BeforeAll` / `@AfterAll` | Executa **uma vez** antes/depois de todos os testes da classe (método `static`) |
| `@ParameterizedTest` | Executa o mesmo teste com várias entradas |
| `@ValueSource` | Fornece uma lista de valores simples para o teste parametrizado |
| `@CsvSource` | Fornece pares/tuplas de valores (ex.: entrada e resultado esperado) |
| `@NullAndEmptySource` | Fornece `null` e um valor vazio (`""`, array vazio...) |
| `@Nested` | Agrupa testes relacionados em uma classe interna |
| `@Disabled` | Desativa um teste temporariamente |

## 3. Asserções mais usadas

Importadas de `org.junit.jupiter.api.Assertions` (com `import static`):

| Asserção | Verifica que... |
|---|---|
| `assertEquals(esperado, atual)` | os valores são iguais |
| `assertEquals(esperado, atual, delta)` | dois `double` são iguais dentro de uma tolerância |
| `assertTrue(cond)` / `assertFalse(cond)` | a condição é verdadeira / falsa |
| `assertNull(obj)` / `assertNotNull(obj)` | o objeto é / não é `null` |
| `assertArrayEquals(esp, atual)` | dois arrays têm o mesmo conteúdo |
| `assertThrows(Excecao.class, () -> ...)` | o código lança a exceção esperada |
| `assertAll(...)` | todas as verificações são executadas, e todas as falhas reportadas juntas |

> ⚠️ A ordem importa: o **valor esperado vem primeiro**, o obtido depois. Inverter deixa a mensagem de erro confusa.

---

## 4. Explicação do código

### `Calculadora.java`
Classe com quatro métodos: `somar`, `subtrair`, `multiplicar` e `dividir`.
O `dividir` tem uma **regra de negócio**: se o divisor for zero, lança
`ArithmeticException` com uma mensagem. Isso permite testar exceções.

### `CalculadoraTest.java`
Demonstra o básico:

```java
private Calculadora calculadora;

@BeforeEach
void setUp() {
    calculadora = new Calculadora();   // objeto novo para cada teste → isolamento
}
```

- **`deveSomarDoisNumeros`** – exemplo completo do padrão AAA, com `assertEquals(5, resultado)`.
- **`deveSubtrair` / `deveMultiplicarPorZero`** – testes curtos de casos de borda (resultado negativo, multiplicação por zero).
- **`deveDividir`** – compara `double` usando *delta* (`0.0001`), porque números de ponto flutuante podem ter pequenos erros de arredondamento.
- **`deveLancarExcecaoAoDividirPorZero`** – usa `assertThrows` com uma *lambda* `() -> calculadora.dividir(10, 0)`.
  O método retorna a exceção capturada, permitindo verificar também a mensagem.

### `Algoritmos.java`
Métodos `static` com algoritmos clássicos:

| Método | Lógica |
|---|---|
| `fatorial(n)` | Laço multiplicando de 2 até `n`; `0! = 1`; negativo lança `IllegalArgumentException` |
| `ehPrimo(n)` | Números < 2 não são primos; testa divisores só até √n (`i * i <= n`), o que é mais eficiente |
| `ehPalindromo(texto)` | Remove espaços, converte para minúsculas e compara com o texto invertido (`StringBuilder.reverse`) |
| `maiorValor(array)` | Começa com o 1º elemento e percorre o array guardando o maior; array nulo/vazio lança exceção |
| `inverter(array)` | Cria um novo array, copiando da última posição para a primeira |

### `AlgoritmosTest.java`
Demonstra recursos intermediários:

- **`@Nested`** – cada algoritmo tem sua própria classe interna (`FatorialTest`, `PrimoTest`, ...),
  deixando o relatório organizado em árvore.
- **`@ParameterizedTest` + `@CsvSource`** – no fatorial, cada linha `"5, 120"` vira uma execução
  do teste com `n = 5` e `esperado = 120`. Evita copiar e colar o mesmo teste várias vezes.
- **`@ValueSource`** – nos primos e palíndromos, passa uma lista de valores que devem dar `true` (ou `false`).
- **`@NullAndEmptySource`** – em `maiorValor`, executa o teste com `null` e com `new int[]{}` automaticamente.
- **`assertAll`** – verifica vários casos de `maiorValor` de uma vez; se mais de um falhar, todos aparecem no relatório.
- **`assertArrayEquals`** – compara o conteúdo dos arrays em `inverter` (`assertEquals` compararia só a referência).

---

## 5. Exercícios sugeridos

1. Adicione `potencia(base, expoente)` na `Calculadora` e escreva os testes.
2. Crie `fibonacci(n)` em `Algoritmos` e teste com `@CsvSource`.
3. Quebre um teste de propósito (mude o valor esperado) e leia a mensagem de falha.
4. Use `@BeforeAll` para imprimir uma mensagem uma única vez antes de todos os testes.
