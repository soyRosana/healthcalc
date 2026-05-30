package healthcalc;

import java.util.ArrayList;
import java.util.List;

import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalcImpl;
import healthcalc.model.BasalMetabolicIndex;
import healthcalc.model.IdealBodyWeight;
import healthcalc.model.OtraMetrica;
import healthcalc.model.Person;
import healthcalc.model.Gender;
import healthcalc.model.HealthCalc;
import healthcalc.model.BMICategory;

public class HealthStatsProxy implements HealthCalc, HealthStats,BasalMetabolicIndex, IdealBodyWeight, OtraMetrica {
    private HealthCalc calculadora;
    private List<DatosPaciente> listaPacientes;

    public HealthStatsProxy() {
        this.calculadora = HealthCalcImpl.getInstance();
        this.listaPacientes = new ArrayList<>();
    }

    @Override
    public double bmi(double weight, double height) throws InvalidHealthDataException {
        double resultado = calculadora.bmi(weight, height);
        listaPacientes.add(new DatosPaciente((float)weight, (float)height, 'U', (float)resultado));
        return resultado;
    }

    @Override
    public String bmiClassification(double bmi) throws InvalidHealthDataException {
        return calculadora.bmiClassification(bmi);
    }

    @Override
    public double idealBodyWeight(double height, char gender) throws InvalidHealthDataException {
        double result = calculadora.idealBodyWeight(height, gender);
        listaPacientes.add(new DatosPaciente(0f, (float)height, gender, 0f));
        return result;
    }

    @Override
    public String wcClassification(double waistCircumference, char gender) throws InvalidHealthDataException {
        return calculadora.wcClassification(waistCircumference, gender);
    }

    @Override
    public float alturaMedia() {
        if (listaPacientes.isEmpty()) return 0;
        float suma = 0;
        int cont = 0;
        for (DatosPaciente p : listaPacientes) {
            if (p.getAltura() > 0) {
                suma += p.getAltura();
                cont++;
            }
        }
        return cont == 0 ? 0 : suma / cont;
    }

    @Override
    public float pesoMedio() {
        if (listaPacientes.isEmpty()) return 0;
        float suma = 0;
        int cont = 0;
        for (DatosPaciente p : listaPacientes) {
            if (p.getPeso() > 0) {
                suma += p.getPeso();
                cont++;
            }
        }
        return cont == 0 ? 0 : suma / cont;
    }

    @Override
    public float imcMedio() {
        if (listaPacientes.isEmpty()) return 0;
        float suma = 0;
        int cont = 0;
        for (DatosPaciente p : listaPacientes) {
            if (p.getImc() > 0) {
                suma += p.getImc();
                cont++;
            }
        }
        return cont == 0 ? 0 : suma / cont;
    }

    @Override
    public int numSexoH() {
        int cont = 0;
        for (DatosPaciente p : listaPacientes) {
            if (p.getGenero() == 'H') cont++;
        }
        return cont;
    }

    @Override
    public int numSexoM() {
        int cont = 0;
        for (DatosPaciente p : listaPacientes) {
            if (p.getGenero() == 'M') cont++;
        }
        return cont;
    }

    @Override
    public int numTotalPacientes() {
        return listaPacientes.size();
    }


    @Override
    public float basalMetabolicIndex(Person person) {
        try {
            //this.bmi para que se registre en listaPacientes
            
            return (float) this.bmi(person.weight(), person.height());
        } catch (InvalidHealthDataException e) {
            return 0f;
        }
    }

    @Override
    public BMICategory category(Person person) {
        try {
            float bmiValue = basalMetabolicIndex(person);
            String result = this.bmiClassification(bmiValue);
            
            switch(result) {
                case "Delgadez Severa": return BMICategory.SEVERE_THINNESS;
                case "Delgadez Moderada": return BMICategory.MODERATE_THINNESS;
                case "Delgadez Leve": return BMICategory.MILD_THINNESS;
                case "Normal": return BMICategory.NORMAL;
                case "Sobrepeso": return BMICategory.OVERWEIGHT;
                case "Obesidad I": return BMICategory.OBESE_CLASS_I;
                case "Obesidad II": return BMICategory.OBESE_CLASS_II;
                case "Obesidad III": return BMICategory.OBESE_CLASS_III;
                default: return BMICategory.NORMAL;
            }
        } catch (InvalidHealthDataException e) {
            return BMICategory.NORMAL;
        }
    }

    @Override
    public float idealBodyWeight( Person person) {
        try {
            char genderChar = (person.gender() == Gender.MALE) ? 'H' : 'M';

            //this.idealBodyWeight para que se registre en listaPacientes
            return (float) this.idealBodyWeight(person.height(), genderChar);
        } catch (InvalidHealthDataException e) {
            return 0f;
        }
    }


    @Override
    //implementacion generica para WC para cumplir con UML
    public float m(Person person) {
        return 0f;
    }
}