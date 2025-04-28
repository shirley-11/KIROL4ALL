package gui;

import javax.swing.JPanel;

import domain.Socio;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JList;

import domain.Reserva;
import domain.Sesion;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CancelarReservaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblSeleccioneLaReserva;
	private JLabel lblRespuestaBoton;
	
	private JScrollPane scrollPaneReservas;
	private JList<Reserva> listReservas;
	private DefaultListModel<Reserva> reservasInfo = new DefaultListModel<Reserva>();
	private Reserva reservaSeleccionada;
	
	private JButton btnCancelar;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public CancelarReservaGUI(MainGUIKirol mainGUIKirol, Socio s) {
		setBackground(new Color(203, 234, 254));
		setLayout(null);
		
		lblSeleccioneLaReserva = new JLabel("Seleccione la reserva que desea cancelar:");
		lblSeleccioneLaReserva.setVerticalAlignment(SwingConstants.CENTER);
		lblSeleccioneLaReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneLaReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneLaReserva.setBounds(142, 32, 306, 41);
		add(lblSeleccioneLaReserva);
		
		lblRespuestaBoton = new JLabel("");
		lblRespuestaBoton.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestaBoton.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRespuestaBoton.setBounds(63, 275, 465, 41);
		add(lblRespuestaBoton);
		
		scrollPaneReservas = new JScrollPane();//////////////////////////////////////////////////////////////LISTA RESERVAS
		scrollPaneReservas.setBounds(10, 83, 570, 134);
		add(scrollPaneReservas);
		
		listReservas = new JList<Reserva>();		
		listReservas.setModel(reservasInfo);
		scrollPaneReservas.setViewportView(listReservas);
		BLFacade bl = MainGUIKirol.getBusinessLogic();
		List<Reserva> reservas = bl.getReservas(s);
		reservasInfo.addAll(reservas);
		
		listReservas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
			     // we need to act only after changed //laboratorioMARRON
				if(!listReservas.isSelectionEmpty()) {
					reservaSeleccionada = listReservas.getSelectedValue();
					btnCancelar.setText("Cancelar la reserva: " + reservaSeleccionada.getIdReserva()); 
					btnCancelar.setEnabled(true);
				}
			}
		});
		
		btnCancelar = new JButton("Cancelar: ");//////////////////////////////////////////////////////////////BOTONCANCELAR		
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {//////PULSAR
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUIKirol.getBusinessLogic();
				boolean estadoReserva = bl.cancelarReserva(reservaSeleccionada);
				if (!estadoReserva) {
					lblRespuestaBoton.setText("Esta reserva ya está cancelada");
				}
				else {
					lblRespuestaBoton.setText("Reserva: "+ reservaSeleccionada.getIdReserva() + " Cancelada");
				}
				reservasInfo.clear();
				List<Reserva> reservas = bl.getReservas(s);
				reservasInfo.addAll(reservas);
				btnCancelar.setEnabled(false);
				btnCancelar.setText("Cancelar: ");
			}
		});
		btnCancelar.addMouseListener(new MouseAdapter() {/////CURSOR
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBounds(84, 227, 424, 38);
		add(btnCancelar);
		
		btnVolver = new JButton("Volver");//////////////////////////////////////////////////////////////BOTONVOLVER
		btnVolver.addActionListener(new ActionListener() {///////PULSAR
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setText("Cancelar: ");
				btnCancelar.setEnabled(false);
				mainGUIKirol.enseñarSocioMenu(s);
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {//////CURSOR
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

		        // Centrar lblTituloSeleccione
		        int ancholblSeleccioneLaReserva = lblSeleccioneLaReserva.getWidth();
		        lblSeleccioneLaReserva.setBounds((anchoPanel - ancholblSeleccioneLaReserva) / 2, lblSeleccioneLaReserva.getY(), ancholblSeleccioneLaReserva, lblSeleccioneLaReserva.getHeight());		    
		           
		        // Centrar lblRespuestaBoton
		        int ancholblRespuestaBoton = lblRespuestaBoton.getWidth();
		        lblRespuestaBoton.setBounds((anchoPanel - ancholblRespuestaBoton) / 2, lblRespuestaBoton.getY(), ancholblRespuestaBoton, lblRespuestaBoton.getHeight());
		        
		        // Centrar scrollPaneReservas
		        int anchoscrollPaneReservas = scrollPaneReservas.getWidth();
		        scrollPaneReservas.setBounds((anchoPanel - anchoscrollPaneReservas) / 2, scrollPaneReservas.getY(), anchoscrollPaneReservas, scrollPaneReservas.getHeight());
		        
		        //Centrar btnReservar
		        int anchobtnCancelar = btnCancelar.getWidth();
		        btnCancelar.setBounds((anchoPanel - anchobtnCancelar) / 2, btnCancelar.getY(), anchobtnCancelar, btnCancelar.getHeight());
		     
		        
		    }
		});	
		
		
	}
}
