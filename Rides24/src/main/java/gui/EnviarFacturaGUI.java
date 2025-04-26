package gui;

import javax.swing.JPanel;

import domain.Encargado;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import domain.Reserva;
import javax.swing.JComboBox;
import domain.Actividad;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class EnviarFacturaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldID;
	
	

	/**
	 * Create the panel.
	 */
	public EnviarFacturaGUI(MainGUIKirol mainGUIKirol, Encargado enc) {
		setLayout(null);
		
		JLabel lblIntroducir = new JLabel("Crea la factura");
		lblIntroducir.setVerticalAlignment(SwingConstants.CENTER);
		lblIntroducir.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducir.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducir.setBounds(205, 10, 139, 41);
		add(lblIntroducir);
		
		JScrollPane scrollPaneReservas = new JScrollPane();
		scrollPaneReservas.setBounds(139, 146, 319, 90);
		add(scrollPaneReservas);
		
		JList<Reserva> listReservas = new JList<Reserva>();
		scrollPaneReservas.setViewportView(listReservas);
		
		JComboBox<Actividad> comboBoxSocios = new JComboBox<Actividad>();
		comboBoxSocios.setBounds(73, 51, 153, 41);
		add(comboBoxSocios);
		
		JLabel lblSocio = new JLabel("Socio");
		lblSocio.setVerticalAlignment(SwingConstants.CENTER);
		lblSocio.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSocio.setBounds(10, 51, 53, 41);
		add(lblSocio);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setVerticalAlignment(SwingConstants.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(236, 51, 43, 41);
		add(lblFecha);
		
		JComboBox<String> comboBoxFecha = new JComboBox<String>();
		comboBoxFecha.setBounds(289, 51, 133, 41);
		add(comboBoxFecha);
		
		JLabel lblListaDeReservas = new JLabel("Lista de reservas del socio");
		lblListaDeReservas.setVerticalAlignment(SwingConstants.CENTER);
		lblListaDeReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeReservas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaDeReservas.setBounds(174, 107, 248, 29);
		add(lblListaDeReservas);
		
		JLabel lblIdfactura = new JLabel("IdFactura");
		lblIdfactura.setVerticalAlignment(SwingConstants.CENTER);
		lblIdfactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdfactura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdfactura.setBounds(432, 51, 71, 41);
		add(lblIdfactura);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(513, 51, 60, 41);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblPrecioTotal = new JLabel("Precio total: ");
		lblPrecioTotal.setVerticalAlignment(SwingConstants.CENTER);
		lblPrecioTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(10, 246, 153, 41);
		add(lblPrecioTotal);
		
		JButton btnNewButton = new JButton("Crear Factura");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(174, 246, 133, 41);
		add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 324, 85, 38);
		add(btnVolver);
		
		JButton btnEnviarPorCorreo = new JButton("Enviar por correo");
		btnEnviarPorCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEnviarPorCorreo.setBounds(420, 246, 153, 41);
		add(btnEnviarPorCorreo);
		
		JLabel lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(105, 324, 352, 41);
		add(lblRespuestaBoton);
		

	}
}
