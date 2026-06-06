package healthcalc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import healthcalc.model.WaistCircumference;
import healthcalc.model.PersonImpl;
import healthcalc.model.Gender;
import healthcalc.view.ViewHealthCalc;

public class CtrWC implements ActionListener { 
    private WaistCircumference model;
    private ViewHealthCalc view;

    public CtrWC(WaistCircumference model, ViewHealthCalc view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("CALCULAR_WC")) {
            try {
                // obtenemos los datos del PanelWC
                Float cintura = Float.parseFloat(view.getCintura());
                String generostr = view.getGenero().toUpperCase().trim();
                if (generostr.isEmpty()) {
                    view.setInterpretacion("Error: Introduce Género (H/M).");
                    return;
                }
                
                Gender genderEnum = (generostr.charAt(0) == 'M') ? Gender.FEMALE : Gender.MALE;
                PersonImpl p = new PersonImpl(0f, 0f, genderEnum, 0, cintura);

                String interpretacion = model.waistCircumference(p);
                
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