package healthcalc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class HealthCalcView extends JFrame implements ViewHealthCalc {
    private JTabbedPane tabbedPane;
    private JLabel lblMensaje;
    
    // Paneles de las pestañas
    private PanelIMC panelIMC;
    private PanelInfo panelInfo;
    private PanelIBW panelIBW; 
    private PanelWC panelWC;

    public HealthCalcView() {
        setTitle("Calculadora de Salud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 650);
       
        JPanel contentPane = new JPanel(new BorderLayout(0, 15));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setPreferredSize(new Dimension(550, 30));
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setFont(new Font("Tahoma", Font.ITALIC, 11));
        contentPane.add(lblMensaje, BorderLayout.SOUTH);;

        tabbedPane = new JTabbedPane();
        
        panelInfo = new PanelInfo();
        panelIMC = new PanelIMC();
        panelIBW = new PanelIBW(); 
        panelWC = new PanelWC();
        
        tabbedPane.addTab("Información", panelInfo);
        tabbedPane.addTab("IMC", panelIMC);
        tabbedPane.addTab("IBW", panelIBW); 
        tabbedPane.addTab("WC", panelWC);  

        contentPane.add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    public String getPeso() { return panelIMC.getPeso(); }

    @Override
    public String getAltura() { return panelIMC.getAltura(); }

    @Override
    public void setResultado(String res) { panelIMC.setResultado(res); }

    @Override
    public void setInterpretacion(String texto) { panelIMC.setInterpretacion(texto); }

    @Override
    public void setMessage(String msg) { lblMensaje.setText(msg); }

    @Override
    public String getCintura() { 
        return panelWC.getCintura(); 
    }

    @Override
    public String getGenero() { 
        return panelWC.getGenero(); 
    }
    @Override
    public String getGeneroIBW() { 
    	return panelIBW.getGenero(); }

    @Override
    public void setResultadoWC(String res) {
        panelWC.setResultado(res);
    }
    @Override
    public void setResultadoIBW(String res) { 
    	panelIBW.setResultado(res); }
    
    @Override
    public void setInterpretacionWC(String texto) {
        panelWC.setInterpretacionWC(texto); 
    }

    @Override
    public void setController(ActionListener cIMC) { //, ActionListener cIBW, ActionListener cWC 
        // pasar el controlador a los paneles
        panelIMC.setBtnController(cIMC);
        
    }
    @Override
    public void setControllerWC(ActionListener cWC) {  
        panelWC.setBtnController(cWC);
    }

	@Override
	public String getAlturaIBW() {
		return panelIBW.getAltura();
	}

	@Override
	public void setControllerIBW(ActionListener cIBW) {
		panelIBW.setBtnController(cIBW);
	}
    @Override
    public void setInterpretacionIBW(String t) { 
        panelIBW.setInterpretacion(t); 
}
    
}
