package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUIKirol extends JFrame {

	private JPanel cardPanel;
	private CardLayout cardLayout;
	LoginGUI loginPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUIKirol frame = new MainGUIKirol();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUIKirol() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 426);
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(100, 149, 237));
		cardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cardPanel);
		//cardPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelMENU = new JPanel();
		panelMENU.setBackground(new Color(203, 234, 254));
		cardPanel.add(panelMENU, "MENU");
		panelMENU.setLayout(null);
		
		JLabel lblBienvenida = new JLabel("Bienvenido, Que desea hacer?");
		lblBienvenida.setBackground(UIManager.getColor("Button.background"));
		lblBienvenida.setBounds(197, 63, 212, 50);
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		panelMENU.add(lblBienvenida);
		
		
		////////////////////////////////////////////////////////////////LOGIN//////////////////////////////////////////////////////
		loginPanel = new LoginGUI(this);
		cardPanel.add(loginPanel, "LOGIN");
		JButton btnLogin = new JButton("Iniciar Sesion");
		////////////////////////////cambiar panel
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "LOGIN");
			}
		});
		//boton login cursor mano
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnLogin.setBounds(166, 123, 260, 38);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMENU.add(btnLogin);
		
		
		
		////////////////////////////////////////////////////////////////REGISTRARSE////////////////////////////////////////////////////////////////
		JButton btnRegistrarse = new JButton("Registrarse");
		//boton REGISTRARSE cursor mano
		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnRegistrarse.setBounds(166, 171, 260, 38);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMENU.add(btnRegistrarse);
		
		JButton btnConsultSesiones = new JButton("Consultar sesiones");
		////boton SESIONES cursor mano
		btnConsultSesiones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConsultSesiones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnConsultSesiones.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConsultSesiones.setBounds(166, 219, 260, 38);
		panelMENU.add(btnConsultSesiones);
		
		/////////////////////////////////////////////COMPONENTES SE QUEDEN E N LA MITAD DE X////////////////////////////////////////////////
		panelMENU.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        int anchoPanel = panelMENU.getWidth();

		        // Centrar btnLogin
		        int anchoBtnLogin = btnLogin.getWidth();
		        btnLogin.setBounds((anchoPanel - anchoBtnLogin) / 2, btnLogin.getY(), anchoBtnLogin, btnLogin.getHeight());

		        // Centrar btnRegistrarse
		        int anchoBtnReg = btnRegistrarse.getWidth();
		        btnRegistrarse.setBounds((anchoPanel - anchoBtnReg) / 2, btnRegistrarse.getY(), anchoBtnReg, btnRegistrarse.getHeight());

		        // Centrar btnConsultSesiones
		        int anchoBtnConsult = btnConsultSesiones.getWidth();
		        btnConsultSesiones.setBounds((anchoPanel - anchoBtnConsult) / 2, btnConsultSesiones.getY(), anchoBtnConsult, btnConsultSesiones.getHeight());
		        
		        // Centrar lblBienvenida
		        int anchoLabel = lblBienvenida.getWidth();
		        lblBienvenida.setBounds((anchoPanel - anchoLabel) / 2, lblBienvenida.getY(), anchoLabel, lblBienvenida.getHeight());

		    }
		});
		////////////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////ENSEÑAR EL LAYOUT MENU/////////////////////////////////////////////////////
		cardLayout.show(cardPanel, "MENU");

	}
}
