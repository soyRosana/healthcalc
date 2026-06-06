package healthcalc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//xtends JPanel en lugar de JFrame pq no es independiente
public class PanelIMC extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private JLabel lblResultado;
	private JLabel lblInterpretacion;
	private JButton btnCalcular;

	public PanelIMC() {
		
		setLayout(new GridLayout(0, 1, 0, 8));
		setBorder(new EmptyBorder(15, 15, 15, 15));

		
		add(new JLabel("<html><b>Cálculo de IMC (Índice de Masa Corporal)</b></html>"));
		
		add(new JLabel("Peso (kg):"));
		txtPeso = new JTextField();
		add(txtPeso);

		add(new JLabel("Altura (cm):"));
		txtAltura = new JTextField();
		add(txtAltura);

	
		btnCalcular = new JButton("Calcular IMC");
		btnCalcular.setBackground(new Color(173, 216, 230));
		add(btnCalcular);

		add(new JSeparator());

		
		lblResultado = new JLabel("Resultado: -");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblResultado);

		lblInterpretacion = new JLabel("Interpretación: -");
		add(lblInterpretacion);
		
		JLabel lblFormula = new JLabel("<html><hr><font size='2.5' color='black'>Para calcular el IMC se ha utilizado la siguiente fórmula:<br>IMC = peso / altura<sup>2</sup></font></html>");
	    add(lblFormula);
		
		
	}

	public String getPeso() { return txtPeso.getText(); }
	public String getAltura() { return txtAltura.getText(); }

	public void setResultado(String res) { lblResultado.setText("Resultado: " + res); }
	public void setInterpretacion(String texto) { lblInterpretacion.setText("Interpretación: " + texto); }

	public void setBtnController(ActionListener ctr) {
		btnCalcular.addActionListener(ctr);
		btnCalcular.setActionCommand("CALCULAR_IMC");
	}
}