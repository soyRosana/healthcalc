package healthcalc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import healthcalc.model.BasalMetabolicIndex;
import healthcalc.model.PersonImpl;
import healthcalc.model.Gender;
import healthcalc.model.BMICategory;
import healthcalc.view.ViewHealthCalc;

public class CtrIMC implements ActionListener { 
    private BasalMetabolicIndex model;
    private ViewHealthCalc view;

    public CtrIMC(BasalMetabolicIndex model, ViewHealthCalc view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("CALCULAR_IMC")) {
            try {
                float peso = Float.parseFloat(view.getPeso());
                float altura = Float.parseFloat(view.getAltura());
                PersonImpl person = new PersonImpl(peso, altura, Gender.MALE, 0, 0f);

                float imc = model.basalMetabolicIndex(person);
                BMICategory category = model.category(person);
                String interpretacion = category.getStrcat();
                
                view.setResultado(String.format("%.2f", imc));
                view.setInterpretacion(interpretacion);
                view.setMessage(""); 
            } catch (NumberFormatException e) {
                view.setMessage("Error en IMC: Datos no numéricos.");
            } catch (Exception e) {
                view.setMessage("Error: " + e.getMessage());
            }
        }
    }
}