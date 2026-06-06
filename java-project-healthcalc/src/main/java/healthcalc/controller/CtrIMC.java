package healthcalc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import healthcalc.model.HealthCalc;
import healthcalc.view.ViewHealthCalc;

public class CtrIMC implements ActionListener { 
    private HealthCalc model;
    private ViewHealthCalc view;

    public CtrIMC(HealthCalc model, ViewHealthCalc view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("CALCULAR_IMC")) {
            try {
                double peso = Double.parseDouble(view.getPeso());
                double altura = Double.parseDouble(view.getAltura());
                double imc = model.bmi(peso, altura);
                
                view.setResultado(String.format("%.2f", imc));
                view.setInterpretacion(model.bmiClassification(imc));
                view.setMessage(""); 
            } catch (NumberFormatException e) {
                view.setMessage("Error en IMC: Datos no numéricos.");
            } catch (Exception e) {
                view.setMessage("Error: " + e.getMessage());
            }
        }
    }
}