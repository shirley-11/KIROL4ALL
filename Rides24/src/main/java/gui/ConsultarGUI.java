package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultarGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneSesiones;
	private JList<String> listSesiones = null;
	private DefaultListModel<String> sesionesInfo = new DefaultListModel<String>();
	
	private JComboBox<String> comboBoxGradoExig = null;
	private DefaultComboBoxModel<String> gradosExigencia = new DefaultComboBoxModel<String>();
	
	private JButton btnVolver;
	private JButton btnVerSesiones;
	private JLabel lblAvisoUsuarios;

	/**
	 * Create the panel.
	 */
	public ConsultarGUI(MainGUIKirol mainGUIKirol) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		JLabel lblTituloSesiones = new JLabel("Sesiones de la semana");
		lblTituloSesiones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTituloSesiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSesiones.setBounds(195, 32, 175, 37);
		add(lblTituloSesiones);
		
		JLabel lblBuscarPor = new JLabel("Si quieres buscar por grado de exigencia, puedes elegirlo:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBuscarPor.setBounds(61, 79, 337, 53);
		add(lblBuscarPor);
		
		scrollPaneSesiones = new JScrollPane();///////////////////////////////////LISTASESIONES///////////////////////////////
		scrollPaneSesiones.setBounds(82, 142, 421, 130);
		add(scrollPaneSesiones);
		
		listSesiones = new JList<String>();
		listSesiones.setModel(sesionesInfo);
		scrollPaneSesiones.setViewportView(listSesiones);

		comboBoxGradoExig = new JComboBox<String>();///////////////////////////////////CBOXGRADOS///////////////////////////////
		comboBoxGradoExig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxGradoExig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxGradoExig.setModel(gradosExigencia);
		gradosExigencia.addElement("ALTO");
		gradosExigencia.addElement("BAJO");
		comboBoxGradoExig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {///////AL SELECCIONAR UN GRADO
				sesionesInfo.clear();
				sesionesInfo.addElement("1 " + comboBoxGradoExig.getSelectedItem());
			}
		});
		comboBoxGradoExig.setBounds(408, 88, 130, 37);
		add(comboBoxGradoExig);
		
		
		btnVerSesiones = new JButton("Ver todas las sesiones");///////////////////////////////////BTNVERSESIONES///////////////////////////////
		btnVerSesiones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {///CURSOR
				btnVerSesiones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVerSesiones.addActionListener(new ActionListener() {////AL DARLE CLICK CARGAR TODAS LAS SESIONES
			public void actionPerformed(ActionEvent e) {
				sesionesInfo.clear();
				sesionesInfo.addElement("Sesion1 sin info");
			}
		});
		btnVerSesiones.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVerSesiones.setBounds(182, 292, 208, 38);
		add(btnVerSesiones);
		
		btnVolver = new JButton("Volver");/////////////////////////////////////////////////////////////BTNVOLVER///////////////////////////////
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mainGUIKirol.volverAMenuPrincipal();
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(22, 308, 93, 38);
		add(btnVolver);
		
		lblAvisoUsuarios = new JLabel("Para reservar hay que estar registrado"); ////////////////////label para que se registren
		lblAvisoUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvisoUsuarios.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblAvisoUsuarios.setBounds(182, 333, 208, 24);
		add(lblAvisoUsuarios);
		
		/////////////////////////////////////////////////////////////COMPONENTES SE QUEDEN EN MITAD DE LA PANTALLA/////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar btnVerSesiones
		        int anchobtnVerSesiones = btnVerSesiones.getWidth();
		        btnVerSesiones.setBounds((anchoPanel - anchobtnVerSesiones) / 2, btnVerSesiones.getY(), anchobtnVerSesiones, btnVerSesiones.getHeight());		    
		           
		        // Centrar lblTituloSesiones
		        int ancholblTituloSesiones = lblTituloSesiones.getWidth();
		        lblTituloSesiones.setBounds((anchoPanel - ancholblTituloSesiones) / 2, lblTituloSesiones.getY(), ancholblTituloSesiones, lblTituloSesiones.getHeight());
		        
		        //Centrar scrollPaneSesiones
		        int anchoscrollPaneSesiones = scrollPaneSesiones.getWidth();
		        scrollPaneSesiones.setBounds((anchoPanel - anchoscrollPaneSesiones) / 2, scrollPaneSesiones.getY(), anchoscrollPaneSesiones, scrollPaneSesiones.getHeight());

		        //Centrar lblAvisoUsuarios
		        int ancholblAvisoUsuarios = lblAvisoUsuarios.getWidth();
		        lblAvisoUsuarios.setBounds((anchoPanel - ancholblAvisoUsuarios) / 2, lblAvisoUsuarios.getY(), ancholblAvisoUsuarios, lblAvisoUsuarios.getHeight());

		    }
		});

	}
}
