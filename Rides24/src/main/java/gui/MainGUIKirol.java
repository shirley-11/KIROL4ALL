package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Encargado;
import domain.Socio;

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
	private static final long serialVersionUID = 1L;
	private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private JPanel cardPanel;
	private CardLayout cardLayout;
	LoginGUI loginPanel;
	RegistrarseGUI registrarsePanel;
	ConsultarGUI consultarPanel;
	protected JLabel jLabelSelectOption;

	/**
	 * Launch the application.
	 *
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
	*/

	/**
	 * Create the frame.
	 */
	public MainGUIKirol() {
		super();
		
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
		
		JLabel lblBienvenida = new JLabel("Bienvenido, \u00BFQu\u00E9 desea hacer?");
		lblBienvenida.setBackground(UIManager.getColor("Button.background"));
		lblBienvenida.setBounds(182, 63, 229, 50);
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
		registrarsePanel = new RegistrarseGUI(this);
		cardPanel.add(registrarsePanel, "REGISTRARSE");
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {///////CAMBIAR PANEL
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "REGISTRARSE");
			}
		});
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
		
		////////////////////////////////////////////////////////////////CONSULTAR SES////////////////////////////////////////////////////////////////
		consultarPanel = new ConsultarGUI(this);
		cardPanel.add(consultarPanel, "CONSULTAR");
		JButton btnConsultSesiones = new JButton("Consultar sesiones");
		btnConsultSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "CONSULTAR");
			}
		});
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
		
		jLabelSelectOption = new JLabel("");
		jLabelSelectOption.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLabelSelectOption.setBounds(166, 315, 260, 24);
		panelMENU.add(jLabelSelectOption);
		
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
	public void volverAMenuPrincipal() {
		loginPanel.limpiarCampos();
		registrarsePanel.limpiarCampos();
		cardLayout.show(cardPanel, "MENU");
		setTitle("");
	}
	
	public void enseñarSocioMenu(Socio s) {
		SocioMenuGUI socioMenuPanel = new SocioMenuGUI(this, s);
		cardPanel.add(socioMenuPanel, "SOCIOMENU");
		cardLayout.show(cardPanel, "SOCIOMENU");
		setTitle(s.getNombre());
	}
	
	public void enseñarEncargadoMenu(Encargado e) {
		EncargadoMenuGUI encargadoMenuPanel = new EncargadoMenuGUI(this, e);
		cardPanel.add(encargadoMenuPanel, "ENCARGADOMENU");
		cardLayout.show(cardPanel, "ENCARGADOMENU");
		setTitle(e.getNombre());
	}
	
	public void enseñarReservarS(Socio s) {
		ReservarSesionGUI reservarSesionPanel = new ReservarSesionGUI(this, s);
		cardPanel.add(reservarSesionPanel, "RESERVARSESION");
		cardLayout.show(cardPanel, "RESERVARSESION");
		setTitle(s.getNombre());
	}
	public void enseñarCancelarR(Socio s) {
		CancelarReservaGUI cancelarReservaPanel = new CancelarReservaGUI(this, s);
		cardPanel.add(cancelarReservaPanel, "CANCELARR");
		cardLayout.show(cardPanel, "CANCELARR");
		setTitle(s.getNombre());
	}
	public void enseñarPagarR(Socio s) {
		PagarFacturasGUI pagarFacturasPanel = new PagarFacturasGUI(this, s);
		cardPanel.add(pagarFacturasPanel, "PAGAR");
		cardLayout.show(cardPanel, "PAGAR");
		setTitle(s.getNombre());
	}
	public void enseñarAñadir(Encargado enc) {
		AñadirActividadGUI añadirActPanel = new AñadirActividadGUI(this, enc);
		cardPanel.add(añadirActPanel, "AÑADIR");
		cardLayout.show(cardPanel, "AÑADIR");
		setTitle(enc.getNombre());
	}
	public void enseñarPlanificar(Encargado enc) {
		PlanificarGUI planificarPanel = new PlanificarGUI(this, enc);
		cardPanel.add(planificarPanel, "PLANIFICAR");
		cardLayout.show(cardPanel, "PLANIFICAR");
		setTitle(enc.getNombre());
	}
	public void enseñarEnviarFactura(Encargado enc) {
		EnviarFacturaGUI enviarFPanel = new EnviarFacturaGUI(this, enc);
		cardPanel.add(enviarFPanel, "ENVIARF");
		cardLayout.show(cardPanel, "ENVIARF");
		setTitle(enc.getNombre());
	}
}
