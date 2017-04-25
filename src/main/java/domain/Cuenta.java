package domain;

import org.joda.time.LocalDate;

public class Cuenta {
	private String nombre;
	private LocalDate periodo;
	private int balance;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getPeriodo() {
		return periodo;
	}
	public void setPeriodo(LocalDate periodo) {
		this.periodo = periodo;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
