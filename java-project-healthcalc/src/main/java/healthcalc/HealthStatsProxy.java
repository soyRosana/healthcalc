package healthcalc;

import java.util.ArrayList;
import java.util.List;

import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.model.HealthCalc;
import healthcalc.model.HealthCalcImpl;

public class HealthStatsProxy implements HealthCalc, HealthStats {
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
}