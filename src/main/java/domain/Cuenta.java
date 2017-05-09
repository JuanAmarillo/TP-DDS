package domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;


@Observable
public class Cuenta {
	private String nombre;
	private String periodo;
	private Float balance;
	
	@JsonCreator
	public Cuenta(@JsonProperty("nombre") String nombre,
				  @JsonProperty("periodo") String periodo,
				  @JsonProperty("balance") Float balance)
	{
		setNombre(nombre);
		setPeriodo(periodo);
		setBalance(balance);
	}
	
	public Cuenta(){};
	
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
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Boolean esIgualA(Cuenta cuenta) {
		return (cuenta.getBalance() == this.balance && cuenta.getNombre() == this.nombre && cuenta.getPeriodo() == this.periodo);
	}
}
