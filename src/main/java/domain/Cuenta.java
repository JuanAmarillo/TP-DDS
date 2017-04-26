package domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private Periodo periodo;
	private Float balance;
	
	@JsonCreator
	public Cuenta(@JsonProperty("nombre") String nombre,
				  @JsonProperty("periodo_inicio") LocalDate periodo_inicio, 
				  @JsonProperty("periodo_fin") LocalDate periodo_fin,
				  @JsonProperty("balance") Float balance)
	{
		setNombre(nombre);
		setPeriodo(new Periodo(periodo_inicio,periodo_fin));
		setBalance(balance);
	}
	
	public Cuenta(){};
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
}
