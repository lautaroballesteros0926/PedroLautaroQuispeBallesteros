package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class CalculadorTest {
    private Calculador calculador;

    @BeforeEach
    public void setUp(){
        calculador=new Calculador();
    }
    @Test
    public void testSum_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculador.sumar(numeroA, numeroB);

        // Assert
        assertEquals(15, resultado, "10 + 5 deberia ser 15");
    }
    @Test
    public void testSum_NegativeNumbers_ShouldCorrectSum(){
        int numeroA= -63;
        int numeroB=50;

        int resultado= calculador.sumar(numeroA,numeroB);
        assertEquals(-13,resultado,"-63 + 50 deeberia ser -13");
    }
    // uso de prueba parametrizadas
    @ParameterizedTest
    @CsvSource({
        "100000000,9999999,109999999",
        "150000000,955566666,1105566666",
        "199999999,99999999,299999998"
    })
    public void testSum_BigNumber_ShouldCorrectSum(int numeroA, int numeroB,int suma_esperada){
        int suma=calculador.sumar(numeroA,numeroB);
        assertThat(suma).isEqualTo(suma_esperada);
    }
    @Test
    public void testSum_NegativeandPositiveNumbers_ShouldRetunrCorrectSum(){
        int numA=44;
        int numB=-44;
        int suma=calculador.sumar(numA,numB);
        assertThat(suma).isEqualTo(0);
    }

    // Testeando la resta
    @Test
    public void testResta_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculador.restar(numeroA, numeroB);

        // Assert
        assertEquals(5, resultado, "10 - 5 deberia ser 5");
    }
    @Test
    public void testResta_NegativeNumbers_ShouldCorrectSum(){
        int numeroA= -70;
        int numeroB=-60;
        int resultado= calculador.restar(numeroA,numeroB);
        assertEquals(-10,resultado,"-70 -(-60) deeberia ser -10");
    }

    // uso de prueba parametrizadas
    @ParameterizedTest
    @CsvSource({
            "-100000000,9999999,-109999999",
            "-199929999,955566666,-1155496665",
            "199999999,-8888888,208888887"
    })
    public void testResta_BigNumber_ShouldCorrectSum(int numeroA, int numeroB,int resta_esperada){
        int resta=calculador.restar(numeroA,numeroB);
        assertThat(resta).isEqualTo(resta_esperada);
    }
    @Test
    public void testResta_NegativeandPositiveNumbers_ShouldReturnCorrectResta(){
        int numA=-54;
        int numB=-54;
        int suma=calculador.restar(numA,numB);
        assertThat(suma).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
        "99999,16112,1611183888",
        "13443,99999,1344286557",
        "23434,88888,2083001392"
    })
    public void testMultiplicacion_BigNumbers_ShouldReturnCorrectMultiplicacion(int numeroA,int numeroB,int resultado){
        int multi=calculador.multiplicacion(numeroA,numeroB);
        assertThat(multi).isEqualTo(resultado);
    }
    @Test
    public void testMultiplicacion_numbermultipliedbyzero(){
        int numeroA= 0;
        int numeroB=23434;
        int multi= calculador.multiplicacion(numeroA,numeroB);
        assertThat(multi).isEqualTo(0);
    }
    @Test
    public void testdivision_divisionbyzero(){
        int numeroA=255;
        int numeroB=0;
        assertThrows(ArithmeticException.class,()->calculador.division(numeroA,numeroB));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 10000, 0.0001",   // 1 / 10000 = 0.0001
            "10, 1000, 0.01",     // 10 / 1000 = 0.01
            "100, 100, 1.0",      // 100 / 100 = 1.0
            "1000, 10, 100.0",    // 1000 / 10 = 100.0
            "2, 3, 0.6667"        // 2 / 3 = 0.6667
    })
    public void testDivisionConNumerosChiquitos(int numeroA, int numeroB, double resultadoEsperado) {
        double MARGEN_ERROR = 0.0001;
        double resultado = calculador.division(numeroA, numeroB);
        assertEquals(resultadoEsperado, resultado, MARGEN_ERROR);
    }

}