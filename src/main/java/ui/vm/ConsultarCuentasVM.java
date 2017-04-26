package ui.vm;

import java.util.List;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private List<LocalDate> periodos;
	private LocalDate periodoSeleccionado;

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.getEmpresas();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<LocalDate> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<LocalDate> periodos) {
		this.periodos = periodos;
	}

	public LocalDate getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(LocalDate periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

}
