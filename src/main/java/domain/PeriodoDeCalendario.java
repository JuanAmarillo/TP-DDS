package domain;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

import domain.interfaces.Periodo;

@Observable
public class PeriodoDeCalendario implements Periodo{
	private LocalDate inicioPeriodo;
	private LocalDate finPeriodo;
	private String periodo;

	public PeriodoDeCalendario(LocalDate inicio, LocalDate fin) {
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
