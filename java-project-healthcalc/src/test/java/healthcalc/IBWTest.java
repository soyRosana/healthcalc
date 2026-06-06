package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;

/**
 * Tests for the HealthCalc interface regarding the Ideal Body Weight (IBW).
 * * Use the AAA pattern (Arrange, Act, Assert) for the tests.
 * * @author ISA06
 */
@DisplayName("Tests para la métrica IBW (Fórmula de Lorentz)")
public class IBWTest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = new HealthCalcImpl();
    }

    @Nested
    @DisplayName("Cálculos de IBW")
    class IBWMetricTests {

        @Test
        @DisplayName("Cálculo de IBW válido para Hombre (180cm)")
        void testIbwHombreValido() throws InvalidHealthDataException {
            // Arrange
            double height = 180.0;
            char gender = 'H';
            double expectedIbw = 72.5; // (180 - 100) - ((180 - 150) / 4)

            // Act
            double result = healthCalc.idealBodyWeight(height, gender);

            // Assert
            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Cálculo de IBW válido para Mujer (160cm)")
        void testIbwMujerValido() throws InvalidHealthDataException {
            // Arrange
            double height = 160.0;
            char gender = 'M';
            double expectedIbw = 55.0; // (160 - 100) - ((160 - 150) / 2)

            // Act
            double result = healthCalc.idealBodyWeight(height, gender);

            // Assert
            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Cálculo de IBW en el punto neutro de la fórmula (150cm)")
        void testIbwPuntoNeutro() throws InvalidHealthDataException {
            // Arrange
            double height = 150.0;
            double expectedIbw = 50.0; // La fracción se anula

            // Act & Assert (Se prueba con ambos géneros usando assertAll)
            assertAll(
                () -> assertEquals(expectedIbw, healthCalc.idealBodyWeight(height, 'H'), 0.01),
                () -> assertEquals(expectedIbw, healthCalc.idealBodyWeight(height, 'M'), 0.01)
            );
        }

        @ParameterizedTest(name = "Altura mínima inválida: {0} cm")
        @ValueSource(doubles = {-10.0, 0.0, 29.9})
        @DisplayName("Bloqueo de alturas inferiores al límite biológico mínimo (30 cm)")
        void testAlturaMinimaImposible(double height) {
            // Arrange
            char gender = 'H';
            
            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.idealBodyWeight(height, gender));
        }

        @ParameterizedTest(name = "Altura máxima inválida: {0} cm")
        @ValueSource(doubles = {300.1, 400.0, 1000.0})
        @DisplayName("Bloqueo de alturas superiores al límite biológico máximo (300 cm)")
        void testAlturaMaximoImposible(double height) {
            // Arrange
            char gender = 'M';
            
            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.idealBodyWeight(height, gender));
        }

        @ParameterizedTest(name = "Género inválido: ''{0}''")
        @ValueSource(chars = {'X', 'A', 'h', 'm', ' '}) // Asumimos que solo acepta 'H' y 'M' mayúsculas estrictamente, ajusta si aceptas minúsculas
        @DisplayName("Bloqueo de caracteres de género no reconocidos")
        void testGeneroInvalido(char gender) {
            // Arrange
            double height = 170.0;

            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.idealBodyWeight(height, gender));
        }
    }
}
