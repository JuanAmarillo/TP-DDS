package ui.vm;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Set<LocalDate> periodos;
	private LocalDate periodoSeleccionado;

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.getEmpresasCargadas();
		this.periodos = RepositorioEmpresas.getPeriodos();
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


	public Set<LocalDate> getPeriodos() {
		return periodos;
	}


	public LocalDate getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(LocalDate periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

}
