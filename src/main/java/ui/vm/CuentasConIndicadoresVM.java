package ui.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.*;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import exceptions.NoHayEmpresasCargadasException;
import ui.windows.CalculadorDeIndicador;

@Observable
public class CuentasConIndicadoresVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private List<CalculadorDeIndicador> calculadores;
	private CalculadorDeIndicador calculadorSeleccionado;
	
		

	public CuentasConIndicadoresVM() {
		if(RepositorioEmpresas.instance().tieneEmpresasCargadas()) {
			this.empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
			this.setEmpresaSeleccionada(this.empresas.get(0));
		}
		else
			throw new NoHayEmpresasCargadasException();
		
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
		ArrayList<CalculadorDeIndicador> calculadoresPosibles = new ArrayList<CalculadorDeIndicador>();
		indicadoresCalculables(empresa, periodo).forEach(indicador -> calculadoresPosibles.add(new CalculadorDeIndicador(indicador)));
		this.calculadores = calculadoresPosibles;
	}
	
	public List<Indicador> indicadoresCalculables(Empresa empresa, String periodo) {
		return RepositorioIndicadores.instance().getIndicadoresCargados().stream().filter(indicador -> indicador.esCalculable(empresa, periodo)).collect(Collectors.toList());
	}

	public CalculadorDeIndicador getCalculadorSeleccionado() {
		return calculadorSeleccionado;
	}

	public void setCalculadorSeleccionado(CalculadorDeIndicador calculadorSeleccionado) {
		this.calculadorSeleccionado = calculadorSeleccionado;
	}


}
