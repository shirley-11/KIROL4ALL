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
public class Encargado implements Serializable{
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String correo;
	private String contrasena;
	private String nombre;
	
	public Encargado() {
		super();
	}
	
	public Encargado(String nombre, String correo, String contrasena) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;		
	}
	///////////////////////////////
	public String getCorreo() {return correo;}
	public void setCorreo(String correo) {this.correo = correo;}
	///////////////////////////////
	public String getContrasena() {return contrasena;}
	public void setContrasena(String contrasena) {this.contrasena = contrasena;}
	///////////////////////////////
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	

}
