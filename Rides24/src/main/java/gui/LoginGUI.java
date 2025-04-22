package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Encargado;
import domain.Socio;
import exceptions.IncorrectPasswordException;
import exceptions.SocioNoRegistradoException;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class LoginGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldCORREO;
	private JPasswordField passwordField;
	private JButton btnEntrar;
	private JButton btnVolver;
	private JTextArea textAreaAviso;
	private JCheckBox chckbxEncargado;

	/**
	 * Create the panel.
	 */
	public LoginGUI(MainGUIKirol mainGUIKirol) {
		
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		JLabel lblIntroducirDatos = new JLabel("Introduce los datos");
		lblIntroducirDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducirDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducirDatos.setBounds(201, 55, 191, 41);
		add(lblIntroducirDatos);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setBounds(87, 104, 93, 38);
		add(lblCorreo);
		
		JLabel lblCONTRASEÑA = new JLabel("Contrase\u00F1a");
		lblCONTRASEÑA.setHorizontalAlignment(SwingConstants.LEFT);
		lblCONTRASEÑA.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCONTRASEÑA.setBounds(87, 152, 93, 38);
		add(lblCONTRASEÑA);
		
		textAreaAviso = new JTextArea();
		textAreaAviso.setForeground(new Color(165, 42, 42));
		textAreaAviso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaAviso.setBounds(145, 295, 316, 58);
		add(textAreaAviso);
		//Configuraciones para hacerlo invisible
		textAreaAviso.setEditable(false); // para que el usuario no pueda escribir
		textAreaAviso.setLineWrap(true); // que corte las líneas automáticamente
		textAreaAviso.setWrapStyleWord(true); // que no corte palabras a la mitad
		textAreaAviso.setOpaque(false); // sin fondo
		textAreaAviso.setBorder(null); // sin borde
		
		chckbxEncargado = new JCheckBox("\u00BFEres encargado?");///////////////////////////////////////////////checkbox
		chckbxEncargado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				chckbxEncargado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		chckbxEncargado.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxEncargado.setBounds(240, 198, 143, 32);
		add(chckbxEncargado);
		chckbxEncargado.setSelected(false);
		chckbxEncargado.setOpaque(false);
		chckbxEncargado.setBorder(null);

		
		
		textFieldCORREO = new JTextField();
		textFieldCORREO.setBounds(201, 106, 208, 38);
		add(textFieldCORREO);
		textFieldCORREO.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 154, 208, 38);
		add(passwordField);
		
		btnEntrar = new JButton("Iniciar Sesion");///////////////////////////////////////////////////////////////////////////////////BTNENTRAR
		btnEntrar.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				if(chckbxEncargado.isSelected()) {//////////////////////ENCARGADO	
					try {		
						BLFacade bl = MainGUIKirol.getBusinessLogic();
						Encargado encargado = bl.hacerLoginEncargado(textFieldCORREO.getText(), new String(passwordField.getPassword()));										
						mainGUIKirol.enseñarEncargadoMenu(encargado);		
					}catch (SocioNoRegistradoException eNoregistrado) {
						textAreaAviso.setVisible(true); // visible sólo cuando hay error
						textAreaAviso.setText(eNoregistrado.getMessage());
					}catch (IncorrectPasswordException eIncorrecto) {
						textAreaAviso.setVisible(true); // visible sólo cuando hay error
						textAreaAviso.setText(eIncorrecto.getMessage());
					}
					
				}else {//////////////////////NO ES ENCARGADO
					try {		
						BLFacade bl = MainGUIKirol.getBusinessLogic();
						Socio s = bl.hacerLogin(textFieldCORREO.getText(), new String(passwordField.getPassword()));										
						mainGUIKirol.enseñarSocioMenu(s);		
					}catch (SocioNoRegistradoException eNoregistrado) {
						textAreaAviso.setVisible(true); // visible sólo cuando hay error
						textAreaAviso.setText(eNoregistrado.getMessage());
					}catch (IncorrectPasswordException eIncorrecto) {
						textAreaAviso.setVisible(true); // visible sólo cuando hay error
						textAreaAviso.setText(eIncorrecto.getMessage());
					}
				}
				
				
			}
		});
		btnEntrar.addMouseListener(new MouseAdapter() { //////////////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBounds(201, 236, 208, 38);
		add(btnEntrar);
		
		btnVolver = new JButton("Volver"); ///////////////////////////////////////////////////////////////////////////////////BTNVOLVER
		btnVolver.addMouseListener(new MouseAdapter() {//////////////////////CURSOR MANO
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnVolver.addActionListener(new ActionListener() {//////////////////MENU
			public void actionPerformed(ActionEvent e) {
				mainGUIKirol.volverAMenuPrincipal();
			}
		});
		btnVolver.setForeground(Color.GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(20, 312, 93, 38);
		add(btnVolver);
		
		
		
		
		
		
		
		
		/////////////////////////////////////////////COMPONENTES SE QUEDEN E N LA MITAD DE X////////////////////////////////////////////////
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = getWidth();

		        // Centrar btnEntrar
		        int anchobtnEntrar = btnEntrar.getWidth();
		        btnEntrar.setBounds((anchoPanel - anchobtnEntrar) / 2, btnEntrar.getY(), anchobtnEntrar, btnEntrar.getHeight());		    
		           
		        // Centrar lblIntroducir
		        int anchoLabel = lblIntroducirDatos.getWidth();
		        lblIntroducirDatos.setBounds((anchoPanel - anchoLabel) / 2, lblIntroducirDatos.getY(), anchoLabel, lblIntroducirDatos.getHeight());
		        
		        // Centrar lblIntroducir
		        int anchotextAreaAviso = textAreaAviso.getWidth();
		        textAreaAviso.setBounds((anchoPanel - anchotextAreaAviso) / 2, textAreaAviso.getY(), anchotextAreaAviso, textAreaAviso.getHeight());
		        
		        //Centrar textFieldCORREO
		        int anchotextFieldCORREO = textFieldCORREO.getWidth();
		        textFieldCORREO.setBounds((anchoPanel - anchotextFieldCORREO) / 2, textFieldCORREO.getY(), anchotextFieldCORREO, textFieldCORREO.getHeight());

		        //Centrar passwordField
		        int anchopasswordField = passwordField.getWidth();
		        passwordField.setBounds((anchoPanel - anchopasswordField) / 2, passwordField.getY(), anchopasswordField, passwordField.getHeight());
		        
		        //Centrar chckbxEncargado
		        int anchochckbxEncargado = chckbxEncargado.getWidth();
		        chckbxEncargado.setBounds((anchoPanel - anchochckbxEncargado) / 2, chckbxEncargado.getY(), anchochckbxEncargado, chckbxEncargado.getHeight());
		        
		    }
		});		

	}
	
	public void limpiarCampos() {
		textFieldCORREO.setText("");
		passwordField.setText("");
		textAreaAviso.setText("");
		chckbxEncargado.setSelected(false);
		
	}
}
