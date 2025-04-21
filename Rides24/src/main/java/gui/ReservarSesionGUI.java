package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;

public class ReservarSesionGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReservarSesionGUI() {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		JLabel lblTituloSeleccione = new JLabel("Seleccione la sesi\u00F3n que desea  reservar:");
		lblTituloSeleccione.setVerticalAlignment(SwingConstants.CENTER);
		lblTituloSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSeleccione.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTituloSeleccione.setBounds(152, 32, 298, 41);
		add(lblTituloSeleccione);
		
		JScrollPane scrollPaneSesiones = new JScrollPane();
		scrollPaneSesiones.setBounds(73, 83, 461, 137);
		add(scrollPaneSesiones);
		
		JList listSesiones = new JList();
		scrollPaneSesiones.setViewportView(listSesiones);
		
		JButton btnReservar = new JButton("Reservar: ");
		btnReservar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReservar.setBounds(96, 230, 424, 38);
		add(btnReservar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 326, 85, 38);
		add(btnVolver);
		
		JLabel lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setBounds(73, 278, 465, 41);
		add(lblRespuestaBoton);

	}
}
