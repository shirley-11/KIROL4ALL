package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Socio;
import exceptions.SocioRegistradoException;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarseGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldNombre;
	private JTextField textFieldCorreo;
	private JPasswordField passwordField;
	private JLabel lblAVISARFALLO;
	private JLabel lblINTRODDATOS;
	private JButton btnRegistarse;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public RegistrarseGUI(MainGUIKirol mainGUIKirol) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblINTRODDATOS = new JLabel("Introduce los datos para crear una nueva cuenta");
		lblINTRODDATOS.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblINTRODDATOS.setHorizontalAlignment(SwingConstants.CENTER);
		lblINTRODDATOS.setBounds(89, 47, 356, 49);
		add(lblINTRODDATOS);
		
		JLabel lblNOMBRE = new JLabel("Nombre");
		lblNOMBRE.setHorizontalAlignment(SwingConstants.LEFT);
		lblNOMBRE.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNOMBRE.setBounds(99, 106, 81, 31);
		add(lblNOMBRE);
		
		JLabel lblCORREO = new JLabel("Correo");
		lblCORREO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCORREO.setBounds(99, 147, 81, 31);
		add(lblCORREO);
		
		JLabel lblcontraseña = new JLabel("Contrase\u00F1a");
		lblcontraseña.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcontraseña.setBounds(99, 188, 81, 31);
		add(lblcontraseña);
		
		lblAVISARFALLO = new JLabel("");//////////////////PARA PONER EXCEPCIONES
		lblAVISARFALLO.setForeground(new Color(165, 42, 42));
		lblAVISARFALLO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAVISARFALLO.setBounds(142, 304, 380, 38);
		add(lblAVISARFALLO);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNombre.setForeground(new Color(0, 0, 0));
		textFieldNombre.setBounds(189, 106, 204, 31);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCorreo = new JTextField();///////////////////////////TEXTFIELDCORREO/////////////////////////////
		textFieldCorreo.setForeground(new Color(0, 0, 0));
		textFieldCorreo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldCorreo.setBounds(189, 147, 204, 31);
		add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		passwordField = new JPasswordField();///////////////////////////TEXTFIELDCONTRASEÑA/////////////////////////////
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setToolTipText("Introduce tu contrase\u00F1a aqu\u00ED");
		passwordField.setBounds(189, 188, 204, 31);
		add(passwordField);
		
		btnRegistarse = new JButton("Registrarse");/////////////////////////////////////////////TBTNREGISTRARSE///////////////////////////////////////////
		btnRegistarse.addActionListener(new ActionListener() {////para REGISTRARSE
			public void actionPerformed(ActionEvent e) {
				try {
					BLFacade bl = MainGUIKirol.getBusinessLogic();
					Socio s = bl.registrarse(textFieldNombre.getText(), textFieldCorreo.getText(), new String(passwordField.getPassword()));
					
					mainGUIKirol.enseñarSocioMenu(s);
				} catch (SocioRegistradoException eRegistrado) {
					lblAVISARFALLO.setText(eRegistrado.getMessage());
				}
			}
		});
		btnRegistarse.addMouseListener(new MouseAdapter() {/////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnRegistarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegistarse.setBounds(189, 240, 208, 38);
		add(btnRegistarse);
		
		btnVolver = new JButton("Volver");///////////////////////////////////////TBTNVOLVER//////////////////////////////////////////////////////////////
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
		btnVolver.setBounds(21, 304, 93, 38);
		add(btnVolver);
		
		
		/////////////////////////////////////////////////////////////COMPONENTES SE QUEDEN EN MITAD DE LA PANTALLA/////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar btnRegistarse
		        int anchobtnRegistarse = btnRegistarse.getWidth();
		        btnRegistarse.setBounds((anchoPanel - anchobtnRegistarse) / 2, btnRegistarse.getY(), anchobtnRegistarse, btnRegistarse.getHeight());		    
		           
		        // Centrar lblINTRODDATOS
		        int ancholblINTRODDATOS = lblINTRODDATOS.getWidth();
		        lblINTRODDATOS.setBounds((anchoPanel - ancholblINTRODDATOS) / 2, lblINTRODDATOS.getY(), ancholblINTRODDATOS, lblINTRODDATOS.getHeight());
		        
		        //Centrar textFieldNombre
		        int anchotextFieldNombre = textFieldNombre.getWidth();
		        textFieldNombre.setBounds((anchoPanel - anchotextFieldNombre) / 2, textFieldNombre.getY(), anchotextFieldNombre, textFieldNombre.getHeight());
		        
		        //Centrar textFieldCorreo
		        int anchotextFieldCorreo = textFieldCorreo.getWidth();
		        textFieldCorreo.setBounds((anchoPanel - anchotextFieldCorreo) / 2, textFieldCorreo.getY(), anchotextFieldCorreo, textFieldCorreo.getHeight());

		        //Centrar passwordField
		        int anchopasswordField = passwordField.getWidth();
		        passwordField.setBounds((anchoPanel - anchopasswordField) / 2, passwordField.getY(), anchopasswordField, passwordField.getHeight());
		        
		    }
		});
	}
	
	public void limpiarCampos() {
		textFieldNombre.setText("");
		textFieldCorreo.setText("");
		passwordField.setText("");
		lblAVISARFALLO.setText("");
		
	}
}
