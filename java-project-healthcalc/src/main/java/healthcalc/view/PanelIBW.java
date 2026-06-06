package healthcalc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class PanelIBW extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtAltura;
    private JTextField txtGenero;
    private JButton btnCalcular;
    private JLabel lblResultado;
    private JLabel lblInterpretacion;

    public PanelIBW() {
        setLayout(new GridLayout(0, 1, 0, 8));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        add(new JLabel("<html><b>Cálculo de IBW (Peso Ideal)</b></html>"));
        
        add(new JLabel("Altura (cm):"));
        txtAltura = new JTextField();
        add(txtAltura);
        
        add(new JLabel("Género (H/M):"));
        txtGenero = new JTextField();
        add(txtGenero);
        
        btnCalcular = new JButton("Calcular IBW");
        btnCalcular.setBackground(new Color(173, 216, 230));
        add(btnCalcular);
        
        lblResultado = new JLabel("Resultado: -");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblResultado);

        lblInterpretacion = new JLabel("Interpretación: -");
        add(lblInterpretacion);

        JLabel lblFormula = new JLabel("<html><hr><font size='2.5' color='black'>Para calcular el IBW se ha utilizado la fórmula de Lorentz:<br>&nbsp;&nbsp;• <b>Hombres:</b> (altura - 100) - ((altura - 150) / 4)<br>&nbsp;&nbsp;• <b>Mujeres:</b> (altura - 100) - ((altura - 150) / 2)</font></html>");
        add(lblFormula);
        
    }
    
    public String getAltura() { return txtAltura.getText(); }
    
    public String getGenero() { return txtGenero.getText(); }
    
    public void setBtnController(ActionListener ctr) {
        btnCalcular.addActionListener(ctr);
        btnCalcular.setActionCommand("CALCULAR_IBW");
    }
    public void setResultado(String r) { 
        lblResultado.setText("Resultado: " + r + " kg"); 
    }
    public void setInterpretacion(String t){
        lblInterpretacion.setText("<html>Interpretación: " + t + "</html>");
    }
}
