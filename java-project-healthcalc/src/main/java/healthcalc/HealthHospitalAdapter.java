package healthcalc;

import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;

public class HealthHospitalAdapter implements HealthHospital {
    private HealthCalc calc;

    public HealthHospitalAdapter() {
        this.calc = HealthCalcImpl.getInstance();
    }

    @Override
    public float[] indiceMasaCorporal(float alturaMetros, int pesoGramos) {
        double alturaCm = alturaMetros * 100.0;
        double pesoKg = (double) pesoGramos / 1000.0;

        try {
            double imc = calc.bmi(pesoKg, alturaCm);
            
            return new float[]{(float) imc};
        } catch (Exception e) {
            System.out.println("Error en el cálculo: " + e.getMessage());
            return new float[]{0.0f};
        }
    }

    @Override
    public int pesoCorporalIdeal(char genero, float alturaMetros) {
        double alturaCm = alturaMetros * 100.0;
        
        try {
            double ibw = calc.idealBodyWeight(alturaCm, genero);
            return (int) Math.round(ibw);
        } catch (Exception e) {
            return 0;
        }
    }
}