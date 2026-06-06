package healthcalc.view;

import java.awt.event.ActionListener;

public interface ViewHealthCalc {
    
    public String getPeso();
    public String getAlturaIBW();
    public String getAltura();

    public String getCintura();
    public String getGenero();
    public String getGeneroIBW();
    
    public void setResultadoIBW(String res);
    public void setResultadoWC(String res);
    public void setInterpretacionWC(String msg);
    
    public void setInterpretacion(String msg);
    public void setResultado(String res);
    public void setMessage(String msg);
    
   
    public void setController(ActionListener ctr);
    public void setControllerWC(ActionListener cWC);
    public void setControllerIBW(ActionListener cWc);
    public void setInterpretacionIBW(String texto);
	
}
