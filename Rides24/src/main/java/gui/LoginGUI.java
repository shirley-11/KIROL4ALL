package gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginGUI extends JPanel {
	private JTextField textFieldCORREO;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginGUI(MainGUIKirol mainGUIKirol) {
		
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		JLabel lblIntroducirDatos = new JLabel("Introduce los datos");
		lblIntroducirDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroducirDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducirDatos.setBounds(198, 79, 191, 41);
		add(lblIntroducirDatos);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setBounds(87, 151, 93, 38);
		add(lblCorreo);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(87, 199, 93, 38);
		add(lblNewLabel);
		
		textFieldCORREO = new JTextField();
		textFieldCORREO.setBounds(231, 153, 208, 38);
		add(textFieldCORREO);
		textFieldCORREO.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(231, 199, 208, 38);
		add(passwordField);
		
		JButton btnEntrar = new JButton("Iniciar Sesion");
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBounds(231, 271, 208, 38);
		add(btnEntrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.DARK_GRAY);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVolver.setBounds(10, 322, 93, 38);
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

		    }
		});		

	}
}
