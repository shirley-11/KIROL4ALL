package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Sesion;
import domain.Socio;
import exceptions.NoMasReservasException;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ReservarSesionGUI extends JPanel {

	private JLabel lblTituloSeleccione;
	private JLabel lblRespuestaBoton;
	
	private JScrollPane scrollPaneSesiones;
	private JList<Sesion> listSesiones;
	private DefaultListModel<Sesion> sesionesInfo = new DefaultListModel<Sesion>();
	private Sesion sesionSeleccionada;
	
	private JButton btnReservar;
	private JButton btnVolver;
	

	/**
	 * Create the panel.
	 */
	public ReservarSesionGUI(MainGUIKirol mainGUIKirol, Socio s) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblTituloSeleccione = new JLabel("Seleccione la sesi\u00F3n que desea  reservar:");
		lblTituloSeleccione.setVerticalAlignment(SwingConstants.CENTER);
		lblTituloSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSeleccione.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTituloSeleccione.setBounds(152, 32, 298, 41);
		add(lblTituloSeleccione);
		
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setBounds(73, 278, 465, 41);
		add(lblRespuestaBoton);
		
		scrollPaneSesiones = new JScrollPane();///////////////////////////////////LISTA SESIONES///////////////////////////////
		scrollPaneSesiones.setBounds(73, 83, 461, 137);
		add(scrollPaneSesiones);
		
		listSesiones = new JList<Sesion>();
		listSesiones.setModel(sesionesInfo);
		scrollPaneSesiones.setViewportView(listSesiones);
		BLFacade bl = MainGUIKirol.getBusinessLogic();
		List<Sesion> sesiones = bl.getSesiones();
		sesionesInfo.addAll(sesiones);
		
		listSesiones.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
			     // we need to act only after changed //laboratorioMARRON
				if (!listSesiones.isSelectionEmpty()) {
					sesionSeleccionada = listSesiones.getSelectedValue();
					btnReservar.setText("Reservar: " + sesionSeleccionada); 
					btnReservar.setEnabled(true);
					 
				}
			}
		});
		
		
		
		
		
		btnReservar = new JButton("Reservar: ");////////////////////////////////////////////////////////BOTONRESERVAR
		btnReservar.setEnabled(false);
		btnReservar.addActionListener(new ActionListener() {/////PULSAR
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUIKirol.getBusinessLogic();
				try {
					String respuesta = "El estado de tu reserva y su identificador son: " + bl.reservarSesion(s, sesionSeleccionada);
					lblRespuestaBoton.setText(respuesta);
				} catch (NoMasReservasException eReserva) {
					lblRespuestaBoton.setText(eReserva.getMessage());
				}
				
			}
		});
		btnReservar.addMouseListener(new MouseAdapter() {////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnReservar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReservar.setBounds(96, 230, 424, 38);
		add(btnReservar);
		
		
		btnVolver = new JButton("Volver");//////////////////////////////////////////////////////////////BOTONVOLVER
		btnVolver.addActionListener(new ActionListener() {//////PULSAR
			public void actionPerformed(ActionEvent e) {
				btnReservar.setText("Reservar: ");
				btnReservar.setEnabled(false);
				mainGUIKirol.enseñarSocioMenu(s);
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {//////////////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 326, 85, 38);
		add(btnVolver);
		
		
		
		//////////////////////////////////////////////COMPONENTES SE QUEDEN E N LA MITAD DE X////////////////////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar lblTituloSeleccione
		        int ancholblTituloSeleccione = lblTituloSeleccione.getWidth();
		        lblTituloSeleccione.setBounds((anchoPanel - ancholblTituloSeleccione) / 2, lblTituloSeleccione.getY(), ancholblTituloSeleccione, lblTituloSeleccione.getHeight());		    
		           
		        // Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		        
		        // Centrar scrollPaneSesiones
		        int anchoscrollPaneSesiones = scrollPaneSesiones.getWidth();
		        scrollPaneSesiones.setBounds((anchoPanel - anchoscrollPaneSesiones) / 2, scrollPaneSesiones.getY(), anchoscrollPaneSesiones, scrollPaneSesiones.getHeight());
		        
		        //Centrar btnReservar
		        int anchobtnReservar = btnReservar.getWidth();
		        btnReservar.setBounds((anchoPanel - anchobtnReservar) / 2, btnReservar.getY(), anchobtnReservar, btnReservar.getHeight());
		     
		        
		    }
		});	

	}
}
