package healthcalc.model;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthCalcImpl implements HealthCalc {

    @Override
    public String bmiClassification(double bmi) throws InvalidHealthDataException {
        if (bmi < 0) {
            throw new InvalidHealthDataException("BMI cannot be negative.");
        }
        if (bmi > 150) {
            throw new InvalidHealthDataException("BMI must be within a possible biological range [0-150].");
        }
        String result;
        if (bmi < 16) result= "Delgadez Severa";
        else if (bmi < 17) result="Delgadez Moderada";
        else if (bmi < 18.5) result="Delgadez Leve";
        else if (bmi < 25) result="Normal";
        else if (bmi < 30) result="Sobrepeso";
        else if (bmi < 35) result="Obesidad I";
        else if (bmi < 40) result="Obesidad II";
        else result = "Obesidad III";

        return result;
    }

    @Override
    public double bmi(double weight, double height) throws InvalidHealthDataException {
        if (weight <= 0) {
            throw new InvalidHealthDataException("Weight must be positive.");
        }
        if (height <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (weight < 1 || weight > 700) {
            throw new InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.");
        }
        if (height < 30 || height > 300) {
            throw new InvalidHealthDataException("Height must be within a possible biological range [30-300] cm.");
        }
        double heightmeters=height/100;
        return weight / Math.pow(heightmeters, 2);
    }

    @Override
    public double idealBodyWeight(double height, char gender) throws InvalidHealthDataException {
        // Validación de la altura (en cm)
        if (height < 30 || height > 300) {
            throw new InvalidHealthDataException("Height must be within a possible biological range [30-300] cm.");
        }

        // Validación del género
        if (gender != 'H' && gender != 'M') {
            throw new InvalidHealthDataException("Gender must be 'H' (Men) or 'M' (Women).");
        }

        // Cálculo de la fórmula de Lorentz
        double ibw = 0.0;
        if (gender == 'H') {
            ibw = (height - 100) - ((height - 150) / 4.0);
        } else if (gender == 'M') {
            ibw = (height - 100) - ((height - 150) / 2.0);
        }
        
        return ibw;
    }


    @Override
    public String wcClassification(double waistCircumference, char gender) throws InvalidHealthDataException {
        // 1. Validación del perímetro de cintura (cm)
        if (waistCircumference < 30 || waistCircumference > 250) {
            throw new InvalidHealthDataException("Waist circumference must be within [30-250] cm.");
        }

        // 2. Validación del género
        if (gender != 'H' && gender != 'M') {
            throw new InvalidHealthDataException("Gender must be 'H' (Men) or 'M' (Women).");
        }

        // 3. Clasificación del riesgo cardiovascular según el perímetro de cintura y el género
        if (gender == 'H') {
            if (waistCircumference >= 102) return "Riesgo Muy Elevado";
            if (waistCircumference >= 94) return "Riesgo Elevado";
            return "Riesgo Bajo";
        } else { // Gender is 'M'
            if (waistCircumference >= 88) return "Riesgo Muy Elevado";
            if (waistCircumference >= 80) return "Riesgo Elevado";
            return "Riesgo Bajo";
        }
    }
    
}
