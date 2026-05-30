package healthcalc;

import healthcalc.model.HealthCalcImpl;
import healthcalc.model.BasalMetabolicIndex;
import healthcalc.model.IdealBodyWeight;
import healthcalc.model.PersonImpl;
import healthcalc.model.Gender;


public class HealthHospitalAdapter implements HealthHospital {
    private BasalMetabolicIndex bmiCalc;
    private IdealBodyWeight ibwCalc;

    public HealthHospitalAdapter() {
        HealthCalcImpl instance = HealthCalcImpl.getInstance();
        this.bmiCalc = instance;
        this.ibwCalc = instance;
    }

    @Override
    public float[] indiceMasaCorporal(float alturaMetros, int pesoGramos) {
        float alturaCm = alturaMetros * 100.0f;
        float pesoKg = (float) pesoGramos / 1000.0f;

        PersonImpl person = new PersonImpl(pesoKg, alturaCm, Gender.MALE, 0);

        try {
            float imc = bmiCalc.basalMetabolicIndex(person);
            
            return new float[]{(float) imc, alturaMetros, (float) pesoKg};
        } catch (Exception e) {
            System.out.println("Error en el cálculo: " + e.getMessage());
            return new float[]{0.0f, alturaMetros, (float) pesoKg};
        }
    }

    @Override
    public int pesoCorporalIdeal(char genero, float alturaMetros) {
        float alturaCm = alturaMetros * 100.0f;
        Gender genderEnum = (genero == 'M') ? Gender.FEMALE : Gender.MALE;

        PersonImpl person = new PersonImpl(0f, alturaCm, genderEnum, 0);

        try {
            float ibw = ibwCalc.idealBodyWeight(person);
            return Math.round(ibw);
        } catch (Exception e) {
            return 0;
        }
    }
}