package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.Encargado;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class EncargadoMenuGUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextArea textAreaTitulo;
	private JButton btnAñadirActividad;
	private JButton btnPlanificar;
	private JButton btnEnviarFacturas;
	private JButton btnSalir;

	/**
	 * Create the panel.
	 */
	public EncargadoMenuGUI(MainGUIKirol mainGUIKirol, Encargado e) {
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
		textAreaTitulo.setText("Hola " + e.getNombre() + ", \n¿Qué desea hacer?");

		
		
		btnAñadirActividad = new JButton("A\u00F1adir Actividad");//////////////////////////////////////////////////////////////BTNAÑADIR
		btnAñadirActividad.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAñadirActividad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnAñadirActividad.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAñadirActividad.setBounds(158, 126, 260, 38);
		add(btnAñadirActividad);
		
		
		btnPlanificar = new JButton("Planificar Sesiones");//////////////////////////////////////////////////////////////BTNPLANIFICAR
		btnPlanificar.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlanificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnPlanificar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlanificar.setBounds(158, 174, 260, 38);
		add(btnPlanificar);
				
		
		btnEnviarFacturas = new JButton("Enviar facturas");//////////////////////////////////////////////////////////////BTNENVIAR FACTURAS
		btnEnviarFacturas.addMouseListener(new MouseAdapter() {////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEnviarFacturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnEnviarFacturas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEnviarFacturas.setBounds(158, 222, 260, 38);
		add(btnEnviarFacturas);
		
		
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

		        // Centrar btnAñadirActividad
		        int anchobtnbtnAñadirActividad = btnAñadirActividad.getWidth();
		        btnAñadirActividad.setBounds((anchoPanel - anchobtnbtnAñadirActividad) / 2, btnAñadirActividad.getY(), anchobtnbtnAñadirActividad, btnAñadirActividad.getHeight());		    
		           
		        // Centrar textAreaTitulo
		        int anchotextAreaTitulo = textAreaTitulo.getWidth();
		        textAreaTitulo.setBounds((anchoPanel - anchotextAreaTitulo) / 2, textAreaTitulo.getY(), anchotextAreaTitulo, textAreaTitulo.getHeight());
		        
		        // Centrar btnPlanificar
		        int anchobtnPlanificar = btnPlanificar.getWidth();
		        btnPlanificar.setBounds((anchoPanel - anchobtnPlanificar) / 2, btnPlanificar.getY(), anchobtnPlanificar, btnPlanificar.getHeight());
		        
		        //Centrar btnEnviarFacturas
		        int anchobtnEnviarFacturas = btnEnviarFacturas.getWidth();
		        btnEnviarFacturas.setBounds((anchoPanel - anchobtnEnviarFacturas) / 2, btnEnviarFacturas.getY(), anchobtnEnviarFacturas, btnEnviarFacturas.getHeight());
		     
		        
		    }
		});
	}
}
