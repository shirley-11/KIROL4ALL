package gui;

import javax.swing.JPanel;

import domain.Factura;
import domain.Reserva;
import domain.Socio;
import exceptions.ErrorPagoException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import businessLogic.BLFacade;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PagarFacturasGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblSeleccioneFact;
	private JLabel lblRespuestaBoton;	
	
	private JScrollPane scrollPaneFacturas;
	private JList<Factura> listFacturas;
	private DefaultListModel<Factura> facturasInfo = new DefaultListModel<Factura>();
	private Factura facturaSeleccionada;
	
	private JButton btnPagar;
	private JButton btnVolver;


	

	/**
	 * Create the panel.
	 */
	public PagarFacturasGUI(MainGUIKirol mainGUIKirol, Socio s) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblSeleccioneFact = new JLabel("Seleccione la factura a pagar");
		lblSeleccioneFact.setVerticalAlignment(SwingConstants.CENTER);
		lblSeleccioneFact.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneFact.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneFact.setBounds(134, 10, 306, 41);
		add(lblSeleccioneFact);
		
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(68, 273, 465, 41);
		add(lblRespuestaBoton);

		
		scrollPaneFacturas = new JScrollPane();//////////////////////////////////////////////////////////////LISTA FACTURAS
		scrollPaneFacturas.setBounds(43, 51, 523, 164);
		add(scrollPaneFacturas);
		
		listFacturas = new JList<Factura>();
		listFacturas.setModel(facturasInfo);
		scrollPaneFacturas.setViewportView(listFacturas);
		BLFacade bl = MainGUIKirol.getBusinessLogic();
		List<Factura> facturas = bl.getFacturas(s);
		facturasInfo.addAll(facturas);
		
		listFacturas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
			     // we need to act only after changed //laboratorioMARRON
				if(!listFacturas.isSelectionEmpty()) {
					facturaSeleccionada = listFacturas.getSelectedValue();
					btnPagar.setText("Pagar la factura: " + facturaSeleccionada.getIdFactura()); 
					btnPagar.setEnabled(true);
				}
			}
		});
		
		btnPagar = new JButton("Pagar: ");//////////////////////////////////////////////////////////////BOTON PAGAR
		btnPagar.setEnabled(false);
		btnPagar.addActionListener(new ActionListener() {/////PULSAR
			public void actionPerformed(ActionEvent e) {
				
				/**
				try {
					BLFacade bl = MainGUIKirol.getBusinessLogic();
					String pago = bl.pagarFactura(facturaSeleccionada.getIdFactura());
					lblRespuestaBoton.setText(pago);
				} catch (ErrorPagoException ePagoHaIdoMal) {
					lblRespuestaBoton.setText(ePagoHaIdoMal.getMessage());
				}
				facturasInfo.clear();
				BLFacade bl = MainGUIKirol.getBusinessLogic();
				List<Factura> facturas = bl.getFacturas(s);
				facturasInfo.addAll(facturas);
				btnPagar.setEnabled(false);
				btnPagar.setText("Pagar: ");
				
				*/
				
				 lblRespuestaBoton.setText("Procesando el pago . . .");
				 btnPagar.setEnabled(false);
				 btnVolver.setEnabled(false);

				    // Crear un Timer que espera 5 segundos (5000 ms)
				    Timer timer = new Timer(5000, new ActionListener() {
				        @Override
				        public void actionPerformed(ActionEvent evt) {
				            try {
				                BLFacade bl = MainGUIKirol.getBusinessLogic();
				                String pago = bl.pagarFactura(facturaSeleccionada.getIdFactura());
				                lblRespuestaBoton.setText(pago);
				            } catch (ErrorPagoException ePagoHaIdoMal) {
				                lblRespuestaBoton.setText(ePagoHaIdoMal.getMessage());
				            }

				            facturasInfo.clear();
				            BLFacade bl = MainGUIKirol.getBusinessLogic();
				            List<Factura> facturas = bl.getFacturas(s);
				            facturasInfo.addAll(facturas);
				            btnPagar.setEnabled(false);
				            btnVolver.setEnabled(true);
				            btnPagar.setText("Pagar: ");
				        }
				    });

				    timer.setRepeats(false); // Para que se ejecute solo una vez
				    timer.start();
				
			}
		});
		btnPagar.addMouseListener(new MouseAdapter() {/////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 13));		
		btnPagar.setBounds(197, 225, 243, 38);
		add(btnPagar);	
		
		btnVolver = new JButton("Volver");//////////////////////////////////////////////////////////////BOTON VOLVER
		btnVolver.addActionListener(new ActionListener() {/////PULSAR
			public void actionPerformed(ActionEvent e) {
				btnPagar.setText("Pagar: ");
				btnPagar.setEnabled(false);
				mainGUIKirol.enseñarSocioMenu(s);
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {/////CURSOR
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

		        // Centrar scrollPaneFacturas
		        int anchoscrollPaneFacturas = scrollPaneFacturas.getWidth();
		        scrollPaneFacturas.setBounds((anchoPanel - anchoscrollPaneFacturas) / 2, scrollPaneFacturas.getY(), anchoscrollPaneFacturas, scrollPaneFacturas.getHeight());		    
		           
		        // Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		        
		        // Centrar btnPagar
		        int anchobtnPagar = btnPagar.getWidth();
		        btnPagar.setBounds((anchoPanel - anchobtnPagar) / 2, btnPagar.getY(), anchobtnPagar, btnPagar.getHeight());
	        
		        // Centrar lblSeleccioneFact
		        int ancholblSeleccioneFact = lblSeleccioneFact.getWidth();
		        lblSeleccioneFact.setBounds((anchoPanel - ancholblSeleccioneFact) / 2, lblSeleccioneFact.getY(), ancholblSeleccioneFact, lblSeleccioneFact.getHeight());
	        
		    }
		});	
	}
}
