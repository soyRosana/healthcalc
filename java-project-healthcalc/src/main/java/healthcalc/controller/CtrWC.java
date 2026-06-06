package healthcalc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import healthcalc.model.HealthCalc;
import healthcalc.view.ViewHealthCalc;

public class CtrWC implements ActionListener { 
    private HealthCalc model;
    private ViewHealthCalc view;

    public CtrWC(HealthCalc model, ViewHealthCalc view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("CALCULAR_WC")) {
            try {
                // obtenemos los datos del PanelWC
                double cintura = Double.parseDouble(view.getCintura());
                String generostr = view.getGenero().toUpperCase().trim();
                if (generostr.isEmpty()) {
                    view.setInterpretacion("Error: Introduce Género (H/M).");
                    return;
                }
                
                char genero = generostr.charAt(0);

                // usando de la practica 1 wcClassification
                String interpretacion = model.wcClassification(cintura, genero);
                
                view.setResultadoWC(cintura + " cm");
                view.setInterpretacionWC(interpretacion);

            } catch (NumberFormatException e) {
                view.setMessage("Error: Perímetro no válido.");
            } catch (Exception e) {
                view.setMessage(e.getMessage());
            }
        }
    }
}