package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import domain.Socio;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SocioMenuGUI extends JPanel {
	private JButton btnReservarSesion;
	private JButton btnCancelarReserva;
	private JButton btnFacturas;
	private JButton btnSalir;
	private JTextArea textAreaTitulo;

	/**
	 * Create the panel.
	 */
	public SocioMenuGUI(MainGUIKirol mainGUIKirol, Socio s) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		textAreaTitulo = new JTextArea();
		textAreaTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		textAreaTitulo.setBounds(204, 78, 162, 38);
		add(textAreaTitulo);
		//Configuraciones para hacerlo invisible
		textAreaTitulo.setEditable(false); // para que el usuario no pueda escribir
		textAreaTitulo.setLineWrap(true); // que corte las líneas automáticamente
		textAreaTitulo.setWrapStyleWord(true); // que no corte palabras a la mitad
		textAreaTitulo.setOpaque(false); // sin fondo
		textAreaTitulo.setBorder(null); // sin borde
		textAreaTitulo.setText("Hola " + s.getNombre() + ", \n¿Qué desea hacer?");
		
		
		btnReservarSesion = new JButton("Reservar Sesi\u00F3n");//////////////////////////////////////////////////////////////BTNRESERVAR
		btnReservarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUIKirol.enseñarReservarS(s);
			}
		});
		btnReservarSesion.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnReservarSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReservarSesion.setBounds(158, 126, 260, 38);
		add(btnReservarSesion);
		
		
		btnCancelarReserva = new JButton("Cancelar Reserva");//////////////////////////////////////////////////////////////BTNCANCELAR RESERVA
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUIKirol.enseñarCancelarR(s);
			}
		});
		btnCancelarReserva.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnCancelarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelarReserva.setBounds(158, 174, 260, 38);
		add(btnCancelarReserva);
		
		
		btnFacturas = new JButton("Consultar o pagar facturas");//////////////////////////////////////////////////////////////BTNCANCELAR RESERVA
		btnFacturas.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFacturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnFacturas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFacturas.setBounds(158, 222, 260, 38);
		add(btnFacturas);
		
		
		btnSalir = new JButton("Salir");//////////////////////////////////////////////////////////////BTNSALIR
		btnSalir.addActionListener(new ActionListener() {////////////SALIR AL MENU PRINCIPAL
			public void actionPerformed(ActionEvent e) {
				mainGUIKirol.volverAMenuPrincipal();
			}
		});
		btnSalir.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnSalir.setForeground(Color.GRAY);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBounds(27, 309, 99, 38);
		add(btnSalir);

		//////////////////////////////////////////////COMPONENTES SE QUEDEN E N LA MITAD DE X////////////////////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar btnReservarSesion
		        int anchobtnReservarSesion = btnReservarSesion.getWidth();
		        btnReservarSesion.setBounds((anchoPanel - anchobtnReservarSesion) / 2, btnReservarSesion.getY(), anchobtnReservarSesion, btnReservarSesion.getHeight());		    
		           
		        // Centrar textAreaTitulo
		        int anchotextAreaTitulo = textAreaTitulo.getWidth();
		        textAreaTitulo.setBounds((anchoPanel - anchotextAreaTitulo) / 2, textAreaTitulo.getY(), anchotextAreaTitulo, textAreaTitulo.getHeight());
		        
		        // Centrar btnCancelarReserva
		        int anchobtnCancelarReserva = btnCancelarReserva.getWidth();
		        btnCancelarReserva.setBounds((anchoPanel - anchobtnCancelarReserva) / 2, btnCancelarReserva.getY(), anchobtnCancelarReserva, btnCancelarReserva.getHeight());
		        
		        //Centrar btnFacturas
		        int anchobtnFacturas = btnFacturas.getWidth();
		        btnFacturas.setBounds((anchoPanel - anchobtnFacturas) / 2, btnFacturas.getY(), anchobtnFacturas, btnFacturas.getHeight());
		     
		        
		    }
		});	

	}

}
