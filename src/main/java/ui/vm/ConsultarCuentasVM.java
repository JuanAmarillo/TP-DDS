package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.*;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import ui.windows.CalculadorDeIndicador;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private List<CalculadorDeIndicador> calculadores;
	private CalculadorDeIndicador calculadorSeleccionado;
	
		

	public ConsultarCuentasVM() {
		this.empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
		this.setEmpresaSeleccionada(this.empresas.get(0));
		//this.calculadores = RepositorioIndicadores.instance().generarCalculadores();
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
		this.setCalculadores(empresaSeleccionada, periodoSeleccionado);
		calculadores.forEach(calculador-> calculador.setValor(empresaSeleccionada, periodoSeleccionado));
		
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
	
	public List<CalculadorDeIndicador> getCalculadores() {
		return calculadores;
	}
	
	public void setCalculadores(Empresa empresa, String periodo) {
		this.calculadores = RepositorioIndicadores.instance().generarCalculadores(empresa, periodo);
	}

	public CalculadorDeIndicador getCalculadorSeleccionado() {
		return calculadorSeleccionado;
	}

	public void setCalculadorSeleccionado(CalculadorDeIndicador calculadorSeleccionado) {
		this.calculadorSeleccionado = calculadorSeleccionado;
	}


}
