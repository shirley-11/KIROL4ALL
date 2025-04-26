package gui;

import javax.swing.JPanel;

import domain.Actividad;
import domain.Encargado;
import exceptions.ActAlreadyExistsException;
import exceptions.NoMasReservasException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AñadirActividadGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblIntroducirDatos;
	private JLabel lblRespuestaBoton;
	
	private JTextField textNombre;
	private JTextField textGrado;
	private JTextField textPrecio;
	
	private JButton btnAñadir;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public AñadirActividadGUI(MainGUIKirol mainGUIKirol, Encargado encargado) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblIntroducirDatos = new JLabel("Introduce los datos");
		lblIntroducirDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducirDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducirDatos.setBounds(187, 40, 191, 41);
		add(lblIntroducirDatos);
		
		JLabel lblNombre = new JLabel("Nombre de la actividad");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(39, 91, 160, 38);
		add(lblNombre);
		
		JLabel lblGrado = new JLabel("Grado de exigencia (1-5)");
		lblGrado.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGrado.setBounds(39, 137, 175, 38);
		add(lblGrado);
		
		JLabel lblPrecio = new JLabel("Precio de la actividad");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecio.setBounds(39, 185, 175, 38);
		add(lblPrecio);
		
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(144, 281, 352, 41);
		add(lblRespuestaBoton);
		
		
		
		textNombre = new JTextField();///////////////////////////////////TEXT NOMBRE///////////////////////////////
		textNombre.setColumns(10);
		textNombre.setBounds(224, 89, 160, 38);
		add(textNombre);
		
		
		
		textGrado = new JTextField();///////////////////////////////////TEXT GRADO///////////////////////////////
		textGrado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				// Solo permitir números
		        if (!Character.isDigit(c)) {
		            e.consume();
		            return;
		        }

		        // Si no es un número entre 1 y 5, cancelar la tecla
		        if (c < '1' || c > '5') {
		            e.consume(); // no se escribe
		            return;
		        }

		        // Solo permitir 1 carácter
		        if (textGrado.getText().length() >= 1) {
		            e.consume(); // no se escribe más de un número
		            return;
		        }
			}
		});
		textGrado.setColumns(10);
		textGrado.setBounds(224, 137, 160, 38);
		add(textGrado);
		
		
	
		textPrecio = new JTextField();///////////////////////////////////TEXT PRECIO///////////////////////////////
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); //carácter que se quiere añadir pero no está en el texto todavía

		        // Solo permitir números
		        if (!Character.isDigit(c)) {
		            e.consume();
		            return;
		        }

		        String textoActual = textPrecio.getText().trim();

		        // Solo permitir dos dígitos
		        if (textoActual.length() >= 2) {
		            e.consume();
		            return;
		        }

		        // Validar el resultado 
		        String resultado = textoActual + c;
		        try {
		            int valor = Integer.parseInt(resultado);
		            if (valor < 1 || valor > 99) {
		                e.consume();
		            }
		        } catch (NumberFormatException ex) {
		            e.consume(); // Por si acaso
		        }
		    
			}
		});
		textPrecio.setColumns(10);
		textPrecio.setBounds(224, 185, 160, 38);
		add(textPrecio);
		
		btnAñadir = new JButton("A\u00F1adir");//////////////////////////////////boton AÑADIR///////////////////////////////	
		btnAñadir.addActionListener(new ActionListener() {////////PULSAR
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUIKirol.getBusinessLogic();
				if (!textNombre.getText().trim().isEmpty() && !textGrado.getText().trim().isEmpty() && !textPrecio.getText().trim().isEmpty()) {
					try {
						
						int grado = Integer.parseInt(textGrado.getText());
						int precio = Integer.parseInt(textPrecio.getText());
						Actividad act = bl.añadirActividad(textNombre.getText(), grado, precio);
						lblRespuestaBoton.setText("Actividad: "+act.getNombre()+ " añadida");
					} catch (ActAlreadyExistsException eActividad) {
						lblRespuestaBoton.setText(eActividad.getMessage());
					}
					textNombre.setText("");
					textGrado.setText("");
					textPrecio.setText("");						
				}
				else {
					lblRespuestaBoton.setText("Rellena todos los campos");
				}
				
			}
		});
		btnAñadir.addMouseListener(new MouseAdapter() {//////////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAñadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAñadir.setBounds(206, 233, 208, 38);
		add(btnAñadir);
		
		
		btnVolver = new JButton("Volver");///////////////////////////////////BOTON VOLVER///////////////////////////////
		btnVolver.addActionListener(new ActionListener() {/////////////PULSAR
			public void actionPerformed(ActionEvent e) {				
				textNombre.setText("");
				textGrado.setText("");
				textPrecio.setText("");
				lblRespuestaBoton.setText("");
				mainGUIKirol.enseñarEncargadoMenu(encargado);
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {////////////CURSOR
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

		        // Centrar lblIntroducirDatos
		        int ancholbllblIntroducirDatos = lblIntroducirDatos.getWidth();
		        lblIntroducirDatos.setBounds((anchoPanel - ancholbllblIntroducirDatos) / 2, lblIntroducirDatos.getY(), ancholbllblIntroducirDatos, lblIntroducirDatos.getHeight());		    
		           
		        // Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		        
		        // Centrar textNombre
		        int anchotextNombre = textNombre.getWidth();
		        textNombre.setBounds((anchoPanel - anchotextNombre) / 2, textNombre.getY(), anchotextNombre, textNombre.getHeight());
		        
		        //Centrar textGrado
		        int anchotextGrado = textGrado.getWidth();
		        textGrado.setBounds((anchoPanel - anchotextGrado) / 2, textGrado.getY(), anchotextGrado, textGrado.getHeight());
		        
		        //Centrar textPrecio
		        int anchotextPrecio = textPrecio.getWidth();
		        textPrecio.setBounds((anchoPanel - anchotextPrecio) / 2, textPrecio.getY(), anchotextPrecio, textPrecio.getHeight());
		        
		        //Centrar btnAñadir
		        int anchobtnAñadir = btnAñadir.getWidth();
		        btnAñadir.setBounds((anchoPanel - anchobtnAñadir) / 2, btnAñadir.getY(), anchobtnAñadir, btnAñadir.getHeight());
		     
		        
		    }
		});
		

	}
}
