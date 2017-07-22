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
	
	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private CalculadorDeIndicador calculadorSeleccionado;

	public CuentasConIndicadoresVM() {
		if (hayEmpresasCargadas())
			setEmpresaSeleccionada(getEmpresas().get(0));
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
		this.periodoSeleccionado = periodoSeleccionado;;
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

	public List<CalculadorDeIndicador> getCalculadores() {
		return generarCalculadores(empresaSeleccionada, periodoSeleccionado);
	}

	private  List<CalculadorDeIndicador> generarCalculadores(Empresa empresa, String periodo) {
		return indicadoresCalculables(empresa, periodo).stream()
				.map(indicador-> new CalculadorDeIndicador(indicador,empresa,periodo)).collect(Collectors.toList());
	}

	public List<Indicador> indicadoresCalculables(Empresa empresa, String periodo) {
		return RepositorioIndicadores.instance().getIndicadoresCargados().stream()
				.filter(indicador -> indicador.esCalculable(empresa, periodo)).collect(Collectors.toList());
	}

	public CalculadorDeIndicador getCalculadorSeleccionado() {
		return calculadorSeleccionado;
	}

	public void setCalculadorSeleccionado(CalculadorDeIndicador calculadorSeleccionado) {
		this.calculadorSeleccionado = calculadorSeleccionado;
	}

}
