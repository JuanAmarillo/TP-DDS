package domain.login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id @GeneratedValue
	private int id;
	
	private String nombreUsuario; // "Hola " + nombreUsuario
	private String nombreCuenta; //	login credential
	private String password;
	
	public Usuario(String nombreUsuario, String nombreCuenta, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombreCuenta = nombreCuenta;
		this.password = password;
	}
	
	//GETTERS Y SETTERS 
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
