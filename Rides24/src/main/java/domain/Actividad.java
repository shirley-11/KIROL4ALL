package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Actividad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String nombre;
	private int gradoExigencia;
	private int precio;
	
	public Actividad() {
		super();
	}
	
	public Actividad(String nombre, int gradoExigencia, int precio) {
		this.nombre = nombre;
		this.gradoExigencia = gradoExigencia;
		this.precio = precio;
	}
	//////////////////////////////////////////////
	public String getNombre() {return nombre;}
	public void setNombre(String n) {this.nombre = n;}
	//////////////////////////////
	public int getGradoExigencia() {return gradoExigencia;}
	public void setGradoExigencia(int g) {this.gradoExigencia = g;}
	/////////////////////////////////////////////
	public int getPrecio() {return precio;}
	public void setPrecio(int p) {this.precio = p;}
	////////////////////////////////////////////////

}
