package domain.login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import domain.metodologias.Metodologia;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id @GeneratedValue
	private int id;
	
	private String nombreUsuario; // "Hola " + nombreUsuario
	private String nombreCuenta; //	login credential
	private String password;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "metodologia_id",  nullable=false)
	private Set<Metodologia> metodologias = new HashSet<Metodologia>();
	
	public Usuario(String nombreUsuario, String nombreCuenta, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombreCuenta = nombreCuenta;
		this.password = password;
	}
	
	public void agregarMetodologia(Metodologia met) {
		metodologias.add(met);
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

	public Set<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(Set<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public String getId() {
		return String.valueOf(id);
	}
}
