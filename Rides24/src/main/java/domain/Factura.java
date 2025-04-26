package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity 
public class Factura implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private int idFactura;
	@XmlIDREF
	@OneToOne
	private Socio socioFac;
	private String estado;
	private int precioTotal;
	@XmlIDREF
	@OneToMany (fetch = FetchType.LAZY)
	private List<Reserva> reservasPagar = new ArrayList<Reserva>();
	private Date fechaFac;
	
	public Factura () {
		super();
	}
	
	public Factura(int id, Socio sociof, Date date, List<Reserva> reservas) {
		this.idFactura = id;
		this.socioFac = sociof;
		this.estado = "NOPAGADO";
		this.precioTotal = 0;
		this.fechaFac = date;
		this.reservasPagar = reservas;
	}
	///////////////////////////////////////////////////////////
	public int getIdFactura() {return idFactura;}
	public void setIdFactura(int idFactura) {this.idFactura = idFactura;}
	///////////////////////////////////////////
	public Socio getSocioFac() {return socioFac;}
	public void setSocioFac(Socio socioFac) {this.socioFac = socioFac;}
	////////////////////////////////////////////
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	////////////////////////////////////////////////////////////////
	//public int getPrecioTotal() {return precioTotal;}
/////CALCULAR EL PRECIO DE LAS RESERVAS///////////////////////
	public int getPrecioTotal() {
		for (Reserva reservaPagar: reservasPagar) {
			if(reservaPagar.getEstadoReserva().equals("OK")) {
				precioTotal = precioTotal + reservaPagar.getSesionReserva().getActividad().getPrecio();
			}		
		}		
		return precioTotal;
	}
	
	public void setPrecioTotal(int precioTotal) {this.precioTotal = precioTotal;}
	////////////////////////////
	public Date getFechaFac() {return fechaFac;}
	public void setFechaFac(Date fechaFac) {this.fechaFac = fechaFac;}
	////////////////////////////

	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaFormateadaR = formatoFecha.format(fechaFac);
		
		return "Factura nº: " + idFactura + " SOCIO: " + socioFac.getNombre() + " ESTADO: " + estado + " PRECIO: "
				+ precioTotal + " RESERVAS: ||" + reservasPagar + "|| FECHA FACTURA: " + fechaFormateadaR ;
	}
	
	

}
