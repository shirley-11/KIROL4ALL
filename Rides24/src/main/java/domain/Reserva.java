package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity 
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlIDREF
	@OneToOne
	private Socio socioReserva;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;
	
	@XmlIDREF
	@OneToOne
	private Sesion sesionReserva;
	private Date fechaReserva;
	private String estadoReserva;
	
	public Reserva() {
		super();
	}
	
	public Reserva(Socio s, Sesion sesion, Date fecha) {
		this.socioReserva = s;
		this.sesionReserva = sesion;
		this.fechaReserva = fecha;
		this.estadoReserva = "NO_ASIGNADA";
		
	}
	
	////////////////////////
	public Socio getSocioReserva() {return socioReserva;}
	public void setSocioReserva(Socio s) {this.socioReserva = s;}
	//////////////////////////
	public int getIdReserva() {return idReserva;}
	public void setIdReserva(int idR) {this.idReserva = idR;}
	///////////////////////
	public Sesion getSesionReserva() {return sesionReserva;}
	public void setSesionReservada(Sesion sesion) {this.sesionReserva= sesion;}
	///////////////////////
	public Date getFechaReserva() {return fechaReserva;}
	public void setFechaReserva(Date d) {this.fechaReserva = d;}
	/////////////////////////
	public String getEstadoReserva() {return estadoReserva;}
	public void setEstadoReserva(String h) {this.estadoReserva = h;}

	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaFormateadaR = formatoFecha.format(fechaReserva);
	    String fechaFormateadaS = formatoFecha.format(sesionReserva.getDate());
		
		
		return "Reserva: " + idReserva + 
				 "|| Sesion: " + sesionReserva.getActividad().getNombre()+" el "+ fechaFormateadaS + " a las " + sesionReserva.getHoraImpartición()
				
				+ " || fechaReserva: " + fechaFormateadaR + ", estado: " + estadoReserva ;
	}
	
	
	
	
	
	
	

}
