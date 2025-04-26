package gui;

import javax.swing.JPanel;

import domain.Actividad;
import domain.Encargado;
import domain.Sala;
import domain.Sesion;
import exceptions.ActAlreadyExistsException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlanificarGUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblIntroducirDatos;
	private JLabel lblRespuestaBoton;
	
	
	private JComboBox<Sala> comboBoxSala;
	private DefaultComboBoxModel<Sala> salas = new DefaultComboBoxModel<Sala>();
	private Sala salaSeleccionada = null;
	
	
	private JComboBox<Actividad> comboBoxActividad;
	private DefaultComboBoxModel<Actividad> actividades = new DefaultComboBoxModel<Actividad>();
	private Actividad actividadSeleccionada  = null;
	
	
	private JComboBox<String> comboBoxFecha;
	private DefaultComboBoxModel<String> fechas = new DefaultComboBoxModel<String>();
	private String fechaSeleccionada  = null;
	
	
	private JComboBox<String> comboBoxHora;
	private DefaultComboBoxModel<String> horas = new DefaultComboBoxModel<String>();
	private String horaSeleccionada  = null;
	
	
	private JComboBox<String> comboBoxMin;
	private DefaultComboBoxModel<String> minutos = new DefaultComboBoxModel<String>();
	private String minutoSeleccionado  = null;
	
	
	private JButton btnAñadir;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public PlanificarGUI(MainGUIKirol mainGUIKirol, Encargado enc) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblIntroducirDatos = new JLabel("Introduce los datos");
		lblIntroducirDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducirDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducirDatos.setBounds(203, 24, 191, 41);
		add(lblIntroducirDatos);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSala.setBounds(50, 127, 45, 41);
		add(lblSala);	
		
		JLabel lblActividad = new JLabel("Actividad");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActividad.setBounds(20, 75, 75, 41);
		add(lblActividad);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(42, 177, 53, 41);
		add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHora.setBounds(432, 136, 88, 32);
		add(lblHora);
		
		JLabel lblmin = new JLabel(":");
		lblmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblmin.setBounds(465, 177, 26, 41);
		add(lblmin);
		
		
		
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(130, 301, 352, 41);
		add(lblRespuestaBoton);
		
		
		comboBoxSala = new JComboBox<Sala>();//////////////////////////////////////////////////////////////////////////////////SALA//////
		
		BLFacade bl = MainGUIKirol.getBusinessLogic();
		List<Sala> salasBL = bl.getSalas();
		for (Sala s : salasBL) {
			salas.addElement(s);
		}
		//salas.addAll(salasBL);
		comboBoxSala.setModel(salas);
		comboBoxSala.setSelectedItem(null);
		
		comboBoxSala.addActionListener(new ActionListener() {//////PULSAR
			public void actionPerformed(ActionEvent e) {
				salaSeleccionada = (Sala) comboBoxSala.getSelectedItem();
			}
		});
		comboBoxSala.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxSala.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxSala.setBounds(120, 127, 261, 41);
		add(comboBoxSala);
		
		
		
		comboBoxActividad = new JComboBox<Actividad>();///////////////////////////////////////////////////////////////////////ACTIVIDAD		
		List<Actividad>actBL = bl.getActividades();
		for (Actividad act : actBL) {
			actividades.addElement(act);
		}
		comboBoxActividad.setModel(actividades);
		comboBoxActividad.setSelectedItem(null);
		
		
		comboBoxActividad.addActionListener(new ActionListener() {///////////PULSAR
			public void actionPerformed(ActionEvent e) {
				actividadSeleccionada = (Actividad) comboBoxActividad.getSelectedItem();
			}
		});
		comboBoxActividad.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxActividad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxActividad.setBounds(120, 75, 328, 41);
		add(comboBoxActividad);
		
		
		
		
		
		comboBoxFecha = new JComboBox<String>();/////////////////////////////////////////////////////////////////////////////////FECHA
		LocalDate hoy = LocalDate.of(2025, 4, 7); // empieza desde 7 abril
		LocalDate fin = LocalDate.of(2025, 4, 13); //hasta 13 abril pero pongo 14 para que cuente las 13
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while (!hoy.isAfter(fin)) {
			fechas.addElement(hoy.format(formatter));
		    hoy = hoy.plusDays(1);
		}
		comboBoxFecha.setModel(fechas);
		comboBoxFecha.setSelectedItem(null);
		
		comboBoxFecha.addActionListener(new ActionListener() {///////////////PULSAR
			public void actionPerformed(ActionEvent e) {
				fechaSeleccionada = (String) comboBoxFecha.getSelectedItem();
			}
		});
		
		
		comboBoxFecha.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxFecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxFecha.setBounds(120, 179, 161, 38);
		add(comboBoxFecha);
		
		
		
		
		
		
		
		comboBoxHora = new JComboBox<String>(); /////////////////////////////////////////////////////////////////////////////HORA
		// De 09 a 12
		for (int i = 9; i <= 12; i++) {
		    horas.addElement(String.format("%02d", i));
		}

		// De 15 a 19
		for (int i = 15; i <= 19; i++) {
		    horas.addElement(String.format("%02d", i));
		}
		comboBoxHora.setModel(horas);
		comboBoxHora.setSelectedItem(null);
		
		comboBoxHora.addActionListener(new ActionListener() {/////////////PULSAR
			public void actionPerformed(ActionEvent e) {
				horaSeleccionada = (String) comboBoxHora.getSelectedItem();
			}
		});
		comboBoxHora.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxHora.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxHora.setBounds(380, 182, 88, 32);
		add(comboBoxHora);
		
		
		
		
		
		comboBoxMin = new JComboBox<String>(); ///////////////////////////////////////////////////////////////////////MINUTOS
		minutos.addElement("00");
		minutos.addElement("30");
		comboBoxMin.setModel(minutos);
		comboBoxMin.setSelectedItem(null);
		
		comboBoxMin.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				minutoSeleccionado = (String) comboBoxMin.getSelectedItem();
			}
		});
		comboBoxMin.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxMin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxMin.setBounds(489, 182, 88, 32);
		add(comboBoxMin);
		
		
		
		
		
		btnAñadir = new JButton("A\u00F1adir");////////////////////////////////////////////////////////////BOTON AÑADIR
		btnAñadir.addActionListener(new ActionListener() {/////////PULSAR
			public void actionPerformed(ActionEvent e) {
				if(salaSeleccionada!=null && actividadSeleccionada!=null && fechaSeleccionada!=null && horaSeleccionada!=null && minutoSeleccionado!=null) {
					String horaIm = horaSeleccionada + ":" + minutoSeleccionado;
					BLFacade bl = MainGUIKirol.getBusinessLogic();
					try {
						Sesion s = bl.añadirSesion(actividadSeleccionada, salaSeleccionada, fechaSeleccionada, horaIm);
						lblRespuestaBoton.setText("Sesion añadida: " + s.getIdSesion());
					}
					catch (ActAlreadyExistsException eSesion) {
						lblRespuestaBoton.setText(eSesion.getMessage());
					}
					salaSeleccionada = null;
					actividadSeleccionada = null;
					fechaSeleccionada = null;
					horaSeleccionada = null;
					minutoSeleccionado = null;
					
					
					comboBoxSala.setSelectedItem(null);
					comboBoxActividad.setSelectedItem(null);
					comboBoxFecha.setSelectedItem(null);
					comboBoxHora.setSelectedItem(null);
					comboBoxMin.setSelectedItem(null);
				}
				else {
					lblRespuestaBoton.setText("Porfavor seleccione todos los datos");
				}
			}
		});
		btnAñadir.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAñadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAñadir.setBounds(186, 253, 208, 38);
		add(btnAñadir);
		
		
		
		
		
		
		btnVolver = new JButton("Volver");////////////////////////////////////////////////////////////////BOTON VOLVER
		btnVolver.addMouseListener(new MouseAdapter() {//////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVolver.addActionListener(new ActionListener() {/////////PULSAR
			public void actionPerformed(ActionEvent e) {
				lblRespuestaBoton.setText("");
				salaSeleccionada = null;
				actividadSeleccionada = null;
				fechaSeleccionada = null;
				horaSeleccionada = null;
				minutoSeleccionado = null;
				mainGUIKirol.enseñarEncargadoMenu(enc);
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 320, 85, 38);
		add(btnVolver);
		
		
		/////////////////////////////////////////////////////////////COMPONENTES SE QUEDEN EN MITAD DE LA PANTALLA/////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar lblIntroducirDatos
		        int ancholblIntroducirDatos = lblIntroducirDatos.getWidth();
		        lblIntroducirDatos.setBounds((anchoPanel - ancholblIntroducirDatos) / 2, lblIntroducirDatos.getY(), ancholblIntroducirDatos, lblIntroducirDatos.getHeight());		    
		           
		        // Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		        
		        //Centrar btnAñadir
		        int anchobtnAñadir = btnAñadir.getWidth();
		        btnAñadir.setBounds((anchoPanel - anchobtnAñadir) / 2, btnAñadir.getY(), anchobtnAñadir, btnAñadir.getHeight());

		    }
		});
		

	}
}
