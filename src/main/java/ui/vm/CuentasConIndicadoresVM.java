package ui.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.*;
import domain.indicadores.EitherIndicador;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import exceptions.NoHayEmpresasCargadasException;
import ui.windows.CalculadorDeIndicador;

@Observable
public class CuentasConIndicadoresVM {

	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private EitherIndicador calculadorSeleccionado;

	public CuentasConIndicadoresVM() {
		if (hayEmpresasCargadas()){
			setEmpresaSeleccionada(getEmpresas().get(0));
			setPeriodoSeleccionado("1er semestre 2017");
		}
		else
			throw new NoHayEmpresasCargadasException();
	}

	public boolean hayEmpresasCargadas() {
		return RepositorioEmpresas.instance().tieneEmpresasCargadas();
	}

	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "periodos");
		ObservableUtils.firePropertyChanged(this, "cuentas");
		ObservableUtils.firePropertyChanged(this, "calculadores");
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
		ObservableUtils.firePropertyChanged(this, "calculadores");

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

	public List<EitherIndicador> getCalculadores() {
		return indicadoresCargados().stream()
				.map(indicador -> indicador.calcular(empresaSeleccionada, periodoSeleccionado))
				.collect(Collectors.toList());
	}

	public List<Indicador> indicadoresCargados() {
		return RepositorioIndicadores.instance().getIndicadoresCargados();
	}


	public EitherIndicador getCalculadorSeleccionado() {
		return calculadorSeleccionado;
	}

	public void setCalculadorSeleccionado(EitherIndicador calculadorSeleccionado) {
		this.calculadorSeleccionado = calculadorSeleccionado;
	}

}
