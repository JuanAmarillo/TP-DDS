package domain;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	private String nombre;
	private Periodo periodo;
	private Float balance;
	
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
