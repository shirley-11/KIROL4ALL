package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlID
	@Id 
	private String nombre;
	private int aforo;
	////SESIONES////
	@XmlIDREF
	@OneToMany (fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	
	public Sala() {
		super();
	}
	public Sala(String nombre, int aforo) {
		this.nombre = nombre;
		this.aforo = aforo;
		
	}
	
	////////////////////////////////////
	public String getNombre() {return nombre;}
	public void setNombre(String n) {this.nombre = n;}
	////////////////////////////////////
	public int getAforo() {return aforo;}
	public void setAforo(int a) {this.aforo = a;}
	//////////////////////////SESIONES DE LA SALA///////////////
	public List<Sesion> getSesionesSala() {return sesiones;}
	public void addSesion(Sesion newSesion) {
		System.out.println("Se va a añadir la sesion: " + newSesion + "a las anteriores: "
				+ sesiones);
		this.sesiones.add(newSesion);
		System.out.println("\n" + sesiones);
	}
	
	@Override
	public String toString() {
		return  nombre + ", aforo: " + aforo;
	}
	

}
