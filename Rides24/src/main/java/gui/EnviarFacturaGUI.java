package gui;

import javax.swing.JPanel;

import domain.Encargado;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JScrollPane;
import javax.swing.JList;
import domain.Reserva;
import domain.Sala;
import domain.Socio;

import javax.swing.JComboBox;
import domain.Actividad;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class EnviarFacturaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblRespuestaBoton;
	private JLabel lblPrecioTotal;
	private JLabel lblIntroducir;
	private JLabel lblListaDeReservas;
	
	private JComboBox<Socio> comboBoxSocios;
	private DefaultComboBoxModel<Socio> socios = new DefaultComboBoxModel<Socio>();
	private Socio socioSeleccionado = null;
	private List<Reserva> reservasSocio;
	
	private JComboBox<String> comboBoxFecha;
	private DefaultComboBoxModel<String> fechas = new DefaultComboBoxModel<String>();
	private String fechaSeleccionada = null;
	
	private JTextField textFieldID;
		
	private JScrollPane scrollPaneReservas;
	private JList<Reserva> listReservas;
	private DefaultListModel<Reserva> reservasInfo = new DefaultListModel<Reserva>();

	
	
	private JButton btnCrearFactura;
	private JButton btnEnviarPorCorreo;
	private JButton btnVolver;
	
	

	/**
	 * Create the panel.
	 */
	public EnviarFacturaGUI(MainGUIKirol mainGUIKirol, Encargado enc) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblIntroducir = new JLabel("Crea la factura");
		lblIntroducir.setVerticalAlignment(SwingConstants.CENTER);
		lblIntroducir.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducir.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducir.setBounds(205, 10, 139, 41);
		add(lblIntroducir);
			
		
		
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
		
		JLabel lblIdfactura = new JLabel("IdFactura");
		lblIdfactura.setVerticalAlignment(SwingConstants.CENTER);
		lblIdfactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdfactura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdfactura.setBounds(432, 51, 71, 41);
		add(lblIdfactura);
		
		
		
		lblListaDeReservas = new JLabel("Lista de reservas del socio");
		lblListaDeReservas.setVerticalAlignment(SwingConstants.CENTER);
		lblListaDeReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeReservas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblListaDeReservas.setBounds(174, 107, 248, 29);
		add(lblListaDeReservas);

		lblPrecioTotal = new JLabel("Precio total: ");
		lblPrecioTotal.setVerticalAlignment(SwingConstants.CENTER);
		lblPrecioTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioTotal.setBounds(10, 246, 153, 41);
		add(lblPrecioTotal);
			
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(105, 324, 370, 41);
		add(lblRespuestaBoton);
		
		
		
		
		
		comboBoxSocios = new JComboBox<Socio>(); //////////////////////////////////////////////////////////////SOCIOS		
		BLFacade bl = MainGUIKirol.getBusinessLogic();
		socios.addAll(bl.getSocios());
		comboBoxSocios.setModel(socios);
		comboBoxSocios.setSelectedItem(null);
		
		comboBoxSocios.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				socioSeleccionado = (Socio) comboBoxSocios.getSelectedItem();
				reservasInfo.clear();
				reservasSocio = bl.getReservas(socioSeleccionado);
				reservasInfo.addAll(reservasSocio);
			}
		});
		comboBoxSocios.addMouseListener(new MouseAdapter() {///////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxSocios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxSocios.setBounds(73, 51, 153, 41);
		add(comboBoxSocios);
		
		
		comboBoxFecha = new JComboBox<String>();//////////////////////////////////////////////////////////////FECHA
		LocalDate hoy = LocalDate.of(2025, 4, 14);///14 abril lunes
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fechas.addElement(hoy.format(formatter));
		comboBoxFecha.setModel(fechas);
		comboBoxFecha.setSelectedItem(null);
		
		comboBoxFecha.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				fechaSeleccionada = (String) comboBoxFecha.getSelectedItem();
			}
		});
		comboBoxFecha.addMouseListener(new MouseAdapter() {///////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				comboBoxFecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		comboBoxFecha.setBounds(289, 51, 133, 41);
		add(comboBoxFecha);
		
		
		textFieldID = new JTextField();//////////////////////////////////////////////////////////////TEXTFIELD ID
		textFieldID.setBounds(513, 51, 60, 41);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		
		
		scrollPaneReservas = new JScrollPane();//////////////////////////////////////////////////////////////LISTA RESERVAS
		scrollPaneReservas.setBounds(139, 146, 319, 90);
		add(scrollPaneReservas);
		
		listReservas = new JList<Reserva>();
		listReservas.setModel(reservasInfo);
		scrollPaneReservas.setViewportView(listReservas);
		
		
		
		btnCrearFactura = new JButton("Crear Factura");//////////////////////////////////////////////////////////////BOTON CREAR
		btnCrearFactura.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCrearFactura.addMouseListener(new MouseAdapter() {///////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCrearFactura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnCrearFactura.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCrearFactura.setBounds(174, 246, 133, 41);
		add(btnCrearFactura);
		
		
		
		btnEnviarPorCorreo = new JButton("Enviar por correo");//////////////////////////////////////////////////////////////BOTON ENVIAR POR CORREO
		btnEnviarPorCorreo.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviarPorCorreo.addMouseListener(new MouseAdapter() {///////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEnviarPorCorreo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnEnviarPorCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEnviarPorCorreo.setBounds(420, 246, 153, 41);
		add(btnEnviarPorCorreo);
		
		
		
		btnVolver = new JButton("Volver");//////////////////////////////////////////////////////////////VOLVER
		btnVolver.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {///////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 324, 85, 38);
		add(btnVolver);
		
		
		
		
		//////////////////////////////////////////////COMPONENTES SE QUEDEN E N LA MITAD DE X////////////////////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar lblIntroducir
		        int ancholblIntroducir = lblIntroducir.getWidth();
		        lblIntroducir.setBounds((anchoPanel - ancholblIntroducir) / 2, lblIntroducir.getY(), ancholblIntroducir, lblIntroducir.getHeight());		    
		           
		        // Centrar lblListaDeReservas
		        int ancholblListaDeReservas = lblListaDeReservas.getWidth();
		        lblListaDeReservas.setBounds((anchoPanel - ancholblListaDeReservas) / 2, lblListaDeReservas.getY(), ancholblListaDeReservas, lblListaDeReservas.getHeight());
		        
		        // Centrar scrollPaneReservas
		        int anchoscrollPaneReservas = scrollPaneReservas.getWidth();
		        scrollPaneReservas.setBounds((anchoPanel - anchoscrollPaneReservas) / 2, scrollPaneReservas.getY(), anchoscrollPaneReservas, scrollPaneReservas.getHeight());
		        
		        //Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		     
		        
		    }
		});	

	}
}
