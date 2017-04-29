package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Set<String> periodos;
	private String periodoSeleccionado;

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.getInstance().getEmpresasCargadas();
		this.periodos = RepositorioEmpresas.getInstance().getPeriodos();
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

	public Set<String> getPeriodos() {
		return periodos;
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}
}
