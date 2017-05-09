package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.utils.Observable;

import domain.repositorios.RepositorioEmpresas;
import domain.Empresa;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Set<String> periodos;
	private String periodoSeleccionado;


	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		this.periodos = empresaSeleccionada.getPeriodos();
		
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
