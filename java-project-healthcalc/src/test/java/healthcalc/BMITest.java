package healthcalc;

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

@DisplayName("Tests para la calculadora de salud (Español/CM).")
public class BMITest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = new HealthCalcImpl();
    }

    @Nested
    @DisplayName("Métrica del BMI")
    class BMIMetricTests {

        @Test
        @DisplayName("Cálculo de BMI con valores estándar (usando CM)")
        void testBmiValido() throws InvalidHealthDataException {
            double weight = 70.0;
            double height = 175.0; 
            double expectedBmi = 70.0 / Math.pow(1.75, 2);

            double result = healthCalc.bmi(weight, height);

            assertEquals(expectedBmi, result, 0.01);
        }

        @Test
        @DisplayName("Lanzar excepción cuando el peso es cero")
        void testBmiPesoCero() {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.bmi(0, 170));
        }

        @ParameterizedTest(name = "Peso inválido: {0} kg")
        @ValueSource(doubles = {-10.0, 0.0, 0.99, 700.1})
        void testPesosImposibles(double weight) {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.bmi(weight, 170.0));
        }

        @ParameterizedTest(name = "Altura inválida: {0} cm")
        @ValueSource(doubles = {29.9, 0.0, -1.0, 300.1})
        @DisplayName("Bloqueo de alturas fuera de rango [30-300] cm")
        void testAlturasImposibles(double height) {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.bmi(70.0, height));
        }
    }

    @Nested
    @DisplayName("Clasificación en Español")
    class BMIClassificationTests {
        @ParameterizedTest(name = "BMI {0} debe ser Delgadez Leve")
        @ValueSource(doubles = {17.1, 18.4})
        void testBmiDelgadezLeve(double bmi) throws InvalidHealthDataException {
            assertEquals("Delgadez Leve", healthCalc.bmiClassification(bmi));
        }

        @ParameterizedTest(name = "BMI {0} debe ser Normal")
        @ValueSource(doubles = {18.5, 22.0, 24.9})
        void testBmiNormal(double bmi) throws InvalidHealthDataException {
            assertEquals("Normal", healthCalc.bmiClassification(bmi));
        }

        @ParameterizedTest(name = "BMI {0} debe ser Sobrepeso")
        @ValueSource(doubles = {25.0, 27.5, 29.9})
        void testBmiSobrepeso(double bmi) throws InvalidHealthDataException {
            assertEquals("Sobrepeso", healthCalc.bmiClassification(bmi));
        }

        @ParameterizedTest(name = "BMI {0} debe ser Obesidad I")
        @ValueSource(doubles = {30.0, 34.9})
        void testBmiObesidad(double bmi) throws InvalidHealthDataException {
            assertEquals("Obesidad I", healthCalc.bmiClassification(bmi));
        }

        @ParameterizedTest(name = "Límites exactos")
        @CsvSource({
            "15.0, Delgadez Severa",
            "16.5, Delgadez Moderada",
            "18.0, Delgadez Leve",
            "20.0, Normal",
            "26.0, Sobrepeso",
            "32.0, Obesidad I",
            "37.0, Obesidad II",
            "45.0, Obesidad III"
        })
        void testBmiClassificationLimites(double bmi, String expected) throws InvalidHealthDataException {
            assertEquals(expected, healthCalc.bmiClassification(bmi));
        }
    }
}