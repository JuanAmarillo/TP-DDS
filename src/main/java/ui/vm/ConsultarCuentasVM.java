package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.*;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import interfaces.Indicador;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private List<Indicador> indicadores;
	private IndicadorCustom indicadorSeleccionado;
	
		

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
		this.setEmpresaSeleccionada(this.empresas.get(0));
		this.indicadores = RepositorioIndicadores.instance().getIndicadoresCargados();
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
		//indicadores.forEach(indicador-> indicador.setValor(empresaSeleccionada, periodoSeleccionado));
		
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

	public IndicadorCustom getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(IndicadorCustom indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}


}
