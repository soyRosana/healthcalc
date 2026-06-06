package healthcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HealthSteps {

    private HealthCalc healthCalc;
    private double weight;
    private double height;
    private double wc;
    private char gender;
    private double resultNum;
    private String resultString;
    private boolean exceptionThrown;

    // --- BACKGROUND / INICIALIZACIÓN ---

    @Given("la calculadora de salud está iniciada")
    public void la_calculadora_de_salud_está_iniciada() {
        healthCalc = new HealthCalcImpl();
        exceptionThrown = false;
    }

    @Given("el usuario ha seleccionado la métrica BMI")
    public void el_usuario_ha_seleccionado_la_métrica_bmi() {
        // Paso informativo
    }

    @Given("el usuario ha seleccionado la métrica Full BMI")
    public void el_usuario_ha_seleccionado_la_métrica_full_bmi() {
        // Paso informativo
    }

    @Given("el usuario ha seleccionado la métrica WC")
    public void el_usuario_ha_seleccionado_la_métrica_wc() {
        // Paso informativo
    }

    @Given("el usuario ha seleccionado la métrica IBW")
    public void el_usuario_ha_seleccionado_la_metrica_ibw() {
        //Paso Informativo
    }

    // --- PASOS PARA BMI ---

    @Given("el peso introducido es {double}")
    public void el_peso_es(Double value) {
        this.weight = value;
    }

    @Given("la altura introducida es {double}")
    public void la_altura_es(Double value) {
        this.height = value;
    }

    @When("ejecuto el cálculo de BMI")
    public void ejecuto_el_cálculo_de_bmi() {
        try {
            resultNum = healthCalc.bmi(weight, height);
            exceptionThrown = false;
        } catch (Exception e) {
            exceptionThrown = true;
        }
    }

    @Then("el resultado numérico debe ser {double}")
    public void el_resultado_numérico_debe_ser(Double expected) {
        assertEquals(expected, resultNum, 0.01);
    }

    // --- PASOS PARA WC (Perímetro Abdominal) ---

    @Given("el perímetro abdominal es {double}")
    public void el_perímetro_abdominal_es(Double value) {
        this.wc = value;
    }

    @Given("el género es {string}")
    public void el_género_es(String genderStr) {
        this.gender = genderStr.toUpperCase().charAt(0);
    }

    @When("ejecuto la clasificación de WC")
    public void ejecuto_la_clasificación_de_wc() {
        try {
            resultString = healthCalc.wcClassification(wc, gender);
            exceptionThrown = false;
        } catch (Exception e) {
            exceptionThrown = true;
        }
    }

    @Then("el resultado debe ser {string}")
    public void el_resultado_debe_ser(String expected) {
        assertEquals(expected, resultString);
    }
     // --- PASOS PARA IBW ---

    /*@Given("la altura introducida es {double}")
    public void la_altura_es(Double value) {
        this.height = value;
    }*/

    @When("ejecuto el cálculo de IBW")
    public void ejecuto_el_calculo_de_ibw() {
        try {
            resultNum = healthCalc.idealBodyWeight(height, gender);
            exceptionThrown = false;
        } catch (Exception e) {
            exceptionThrown = true;
        }
    }

    // --- PASOS PARA FULL BMI ---
    @Given("un valor de BMI es {double}")
    public void un_valor_de_bmi_es(Double valor) {
        this.resultNum=valor;
    }

    @When("ejecuto el cálculo de Full BMI")
    public void ejecuto_el_calculo_de_full_bmi() {
        try {
            resultString=healthCalc.bmiClassification(resultNum);
            exceptionThrown=false;
        } catch (Exception e) {
            exceptionThrown= true;
        }
    }
    //para leer el texto sin comillas, ya que el plugin de Cucumber agrega comillas a los strings por defecto
    @Then("^el resultado debe ser ([^\"].*)$")
    public void el_resultado_debe_ser_sin_comillas(String expected) {
        assertEquals(expected, resultString);
    }
    
    // --- MANEJO DE ERRORES COMÚN ---

    @Then("el sistema debe lanzar una excepción")
    public void el_sistema_debe_lanzar_una_excepción() {
        assertTrue(exceptionThrown, "Se esperaba una excepción pero el cálculo fue exitoso.");
    }
}