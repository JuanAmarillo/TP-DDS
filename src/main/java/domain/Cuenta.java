package domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;


@Observable
public class Cuenta {
	private String nombre;
	private String periodo;
	private Double balance;
	
	@JsonCreator
	public Cuenta(@JsonProperty("nombre") String nombre,
				  @JsonProperty("periodo") String periodo,
				  @JsonProperty("balance") Double balance)
	{
		setNombre(nombre);
		setPeriodo(periodo);
		setBalance(balance);
	}
	
	//public Cuenta(){};
	
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
		return (cuenta.getBalance().equals(this.balance) && suNombreEs(cuenta.getNombre()) && cuenta.getPeriodo().equals(this.periodo));
	}
	
	public Boolean suNombreEs(String cuenta){
		return this.getNombre().equals(cuenta);
	}
}
