package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;

/**
 * Tests for the HealthCalc interface regarding Waist Circumference (WC).
 * * Use the AAA pattern (Arrange, Act, Assert) for the tests.
 * * @author ISA
 */
@DisplayName("Tests para la métrica del Perímetro Abdominal (WC).")
public class WCTest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = new HealthCalcImpl();
    }

    @Nested
    @DisplayName("Validación de seguridad y límites biológicos")
    class WCSafetyTests {

        @ParameterizedTest(name = "Perímetro mínimo inválido: {0} cm")
        @ValueSource(doubles = {-10.0, 0.0, 29.9})
        @DisplayName("Bloqueo de perímetros inferiores al límite biológico mínimo (30 cm)")
        void testPerimetroMinimoImposible(double wc) {
            // Arrange
            char gender = 'H';
            
            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.wcClassification(wc, gender));
        }

        @ParameterizedTest(name = "Perímetro máximo inválido: {0} cm")
        @ValueSource(doubles = {250.1, 300.0, 500.0})
        @DisplayName("Bloqueo de perímetros superiores al límite biológico máximo (250 cm)")
        void testPerimetroMaximoImposible(double wc) {
            // Arrange
            char gender = 'M';
            
            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.wcClassification(wc, gender));
        }

        @Test
        @DisplayName("Lanzar excepción cuando el género no es válido")
        void testGeneroInvalido() {
            // Arrange
            double wc = 90.0;
            char invalidGender = 'X';

            // Act & Assert
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.wcClassification(wc, invalidGender));
        }
    }

    @Nested
    @DisplayName("Clasificación del Riesgo Cardiovascular (WC)")
    class WCClassificationTests {

        @ParameterizedTest(name = "Hombre con {0} cm debe ser {1}")
        @CsvSource({
            "93.9, Riesgo Bajo",
            "94.0, Riesgo Elevado",
            "101.9, Riesgo Elevado",
            "102.0, Riesgo Muy Elevado"
        })
        @DisplayName("Límites de riesgo exactos para hombres")
        void testWCHombresLimites(double wc, String expectedCategory) throws InvalidHealthDataException {
            // Act
            String result = healthCalc.wcClassification(wc, 'H');

            // Assert
            assertEquals(expectedCategory, result);
        }

        @ParameterizedTest(name = "Mujer con {0} cm debe ser {1}")
        @CsvSource({
            "79.9, Riesgo Bajo",
            "80.0, Riesgo Elevado",
            "87.9, Riesgo Elevado",
            "88.0, Riesgo Muy Elevado"
        })
        @DisplayName("Límites de riesgo exactos para mujeres")
        void testWCMujeresLimites(double wc, String expectedCategory) throws InvalidHealthDataException {
            // Act
            String result = healthCalc.wcClassification(wc, 'M');

            // Assert
            assertEquals(expectedCategory, result);
        }

        @Test
        @DisplayName("Verificación de riesgo elevado con assertAll")
        void testRiesgoElevadoAssertAll() throws InvalidHealthDataException {
            // Arrange
            double wcHombre = 95.0;
            double wcMujer = 82.0;

            // Act
            String resH = healthCalc.wcClassification(wcHombre, 'H');
            String resM = healthCalc.wcClassification(wcMujer, 'M');

            // Assert
            assertAll(
                () -> assertEquals("Riesgo Elevado", resH),
                () -> assertEquals("Riesgo Elevado", resM)
            );
        }
    }
}