package healthcalc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class PanelWC extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtCintura;
    private JTextField txtGenero;
    private JLabel lblResultado;
    private JLabel lblInterpretacion;
    private JButton btnCalcular;

    public PanelWC() {
        
        setLayout(new GridLayout(0, 1, 0, 8));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(new JLabel("<html><b>Clasificación del Riesgo Cardiovascular basado en perímetro de cintura</b></html>"));
        
        add(new JLabel("Perímetro de cintura (cm):"));
        txtCintura = new JTextField();
        add(txtCintura);

        add(new JLabel("Género (H/M):"));
        txtGenero = new JTextField();
        add(txtGenero);

        btnCalcular = new JButton("Calcular WC");
        btnCalcular.setBackground(new Color(173, 216, 230));
        add(btnCalcular);

        add(new JSeparator());

        lblResultado = new JLabel("Resultado: -");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblResultado);

        lblInterpretacion = new JLabel("Interpretación: -");
        add(lblInterpretacion);
        
        JLabel lblFormula = new JLabel("<html><hr><font size='2.5' color='black'>Para realizar la clasificación se han utilizado los siguientes valores de referencia:<br><b>(Riesgo Elevado):</b><br>&nbsp;&nbsp;• Hombres: &ge;94 - 102 cm<br>&nbsp;&nbsp;• Mujeres: &ge;80 - 88 cm</font></html>");
        add(lblFormula);
    }

    public String getCintura() { return txtCintura.getText(); }
    public String getGenero() { return txtGenero.getText(); }

    public void setResultado(String res) { lblResultado.setText("Resultado: " + res); }
    public void setInterpretacionWC(String texto) { lblInterpretacion.setText("Interpretación: " + texto); }

    public void setBtnController(ActionListener ctr) {
        btnCalcular.addActionListener(ctr);
        btnCalcular.setActionCommand("CALCULAR_WC");
    }
}
