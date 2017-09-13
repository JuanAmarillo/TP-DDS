package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@Table(name="cuentas")
public class Cuenta {
	@Id
	@GeneratedValue
	public Integer id;
	@Column(length=30)
	private String nombre;
	@Column(length=30)
	private String periodo;
	private Double balance;

	@JsonCreator
	public Cuenta(@JsonProperty("nombre") String nombre, @JsonProperty("periodo") String periodo,
			@JsonProperty("balance") Double balance) {
		setNombre(nombre);
		setPeriodo(periodo);
		setBalance(balance);
	}
	
	public Cuenta() {
		
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean esIgualA(Cuenta cuenta) {
		return (deBalance(cuenta.getBalance()) && deNombre(cuenta.getNombre())&& dePeriodo(cuenta.getPeriodo()));
	}

	public Boolean deNombre(String nombre) {
		return this.getNombre().equals(nombre);
	}

	public boolean dePeriodo(String periodo) {
		return this.getPeriodo().equals(periodo);
	}
	
	public boolean deBalance(Double balance){
		return this.getBalance().equals(balance);
	}
}
