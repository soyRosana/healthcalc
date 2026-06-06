package healthcalc.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelInfo extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelInfo() {
		setLayout(new BorderLayout(0, 10));
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(Color.WHITE);

		// Título
		JLabel lblTitulo = new JLabel("Bienvenido a HealthCalc", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblTitulo, BorderLayout.NORTH);

		// Texto informativo (usamos HTML para que el texto salte de línea solo)
		String texto = "<html>" +
				"<h3>¿Qué es HealthCalc?</h3>" +
				"<p>En esta interfaz podrás calcular tus métricas de salud a partir de tus datos:</p><br>" +
				"<ul>" +
				"<li><b>IMC:</b> Índice de Masa Corporal para evaluar el estado nutricional.</li>" +
				"<li><b>IBW:</b> Peso Corporal Ideal basado en su altura.</li>" +
				"<li><b>WC:</b> Clasificación del Riesgo Cardiovascular basado en perímetro de cintura.</li>" +
				"</ul><br>" +
				"<p><i>Seleccione una pestaña en la parte superior para comenzar.</i></p>" +
				"</html>";

		JLabel lblTexto = new JLabel(texto);
		lblTexto.setVerticalAlignment(SwingConstants.TOP);
		add(lblTexto, BorderLayout.CENTER);
		
		// Pie de página o versión
		JLabel lblVersion = new JLabel("Hospital Universitario Virgen de la Victoria (El Clínico) ", SwingConstants.RIGHT);
		lblVersion.setFont(new Font("Tahoma", Font.ITALIC, 10));
		add(lblVersion, BorderLayout.SOUTH);
	}
}