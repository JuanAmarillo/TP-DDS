package domain;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {
	private LocalDate inicioPeriodo;
	private LocalDate finPeriodo;
	private String periodo;
	
	public Periodo(LocalDate inicio, LocalDate fin){
		this.setInicioPeriodo(inicio);
		this.setFinPeriodo(fin);
		this.periodo = inicio.toString() + " - " + fin.toString();
	}

	public LocalDate getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(LocalDate inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public LocalDate getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(LocalDate finPeriodo) {
		this.finPeriodo = finPeriodo;
	}
	
	public String getPeriodo() {
		return periodo;
	}
}
