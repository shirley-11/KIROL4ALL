package domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Socio implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String correo;
	private String contrasena;
	private String nombre;
	private int numMaxReservas;
	
	public Socio() {
		super();
	}
	
	public Socio(String nombre, String correo, String contrasena) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.numMaxReservas = 6;
	}
	///////////////////////////////
	public String getCorreo() {return correo;}
	public void setCorreo(String c) {this.correo = c;}
	//////////////////////////////////
	public String getNombre() {return nombre;}
	public void setNombre(String n) {this.nombre = n;}
	/////////////////////////////////
	public String getContrasena() {return contrasena;}
	public void setContrasena(String cn) {this.contrasena = cn;}
	////////////////////////////////
	public int getNumMaxReservas() {return numMaxReservas;}
	public void setNumMaxReservas(int n) {this.numMaxReservas = n;}

	
}
