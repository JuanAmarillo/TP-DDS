package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
		this.setEmpresaSeleccionada(this.empresas.get(0));
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "periodos");

	}

	public Set<String> getPeriodos() {
		return empresaSeleccionada.getPeriodos();
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;

	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		ObservableUtils.firePropertyChanged(this, "cuentas");
	}

	public Set<Cuenta> getCuentas() {
		return empresaSeleccionada.getCuentasSegun(periodoSeleccionado);
	}

	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

}
