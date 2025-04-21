package domain;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Sesion implements Serializable{
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSesion;
	
	@XmlIDREF
	@OneToOne
	private Actividad actividad;
	
	@XmlIDREF
	@OneToOne
	private Sala sala;
	private Date date;
	private String horaImpartición;	//HH:mm
	@XmlIDREF
	@OneToMany (fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Reserva> listaReservas = new ArrayList<Reserva>();
	////CONTROLAR EL AFORO/////
	private int reservasHechas;
	
	
	public Sesion() {
		super();
	}
	public Sesion(Actividad actividad, Sala sala, Date date, String horaImparticion) {
		this.actividad = actividad;
		this.sala = sala;
		this.date = date;
		this.horaImpartición = horaImparticion;
		this.reservasHechas = 0;
	}
	////////////////////////
	public int getIdSesion() {return idSesion;}
	public void setIdSesion(int idSesion) {this.idSesion = idSesion;}
	////////////////////////
	public Actividad getActividad() {return actividad;}
	public void setActividad(Actividad act) {this.actividad = act;}
	//////////////////////////
	public Sala getSala() {return sala;}
	public void setSala(Sala s) {this.sala = s;}
	///////////////////////
	public Date getDate() {return date;}
	public void setDate(Date d) {this.date = d;}
	/////////////////////////
	public String getHoraImpartición() {return horaImpartición;}
	public void setHoraImpartición(String h) {this.horaImpartición = h;}
	////////////////////////LISTARESERVAS/////////////////
	public List<Reserva> getListaReservas (){return listaReservas;}
	public String addReserva (Reserva reservaAdd) {
		
		if (this.reservasHechas < this.getAforoSesion()) {
			reservaAdd.setEstadoReserva("OK");
			listaReservas.add(reservaAdd);
			this.setNReservasHechas(reservasHechas +1);
			return"OK";
		}
		else {
			reservaAdd.setEstadoReserva("ESPERA");
			listaReservas.add(reservaAdd);
			this.setNReservasHechas(reservasHechas +1);
			return"ESPERA";
		}
		
	}
	
	///////////AFORO////////////////
	public int getAforoSesion() {return this.sala.getAforo();}
	public int getNReservasHechas () {return reservasHechas;}
	public void setNReservasHechas (int n) {this.reservasHechas = n;}
	
	//////TOSTRING
	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaFormateada = formatoFecha.format(date);
		
	    return actividad.getNombre() + " en " + sala.getNombre() + " el " + fechaFormateada + " a las " + horaImpartición + 
	            " | Exigencia: " + actividad.getGradoExigencia();
	}


}
