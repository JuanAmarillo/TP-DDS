package ui.vm;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.Indicador;
import domain.indicadores.IndicadorCalculado;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioIndicadores;
import exceptions.NoHayEmpresasCargadasException;

@Observable
public class CuentasConIndicadoresVM {

	private Empresa empresaSeleccionada;
	private String periodoSeleccionado;
	private Cuenta cuentaSeleccionada;
	private IndicadorCalculado calculadorSeleccionado;

	public CuentasConIndicadoresVM() {
		if (hayEmpresasCargadas())
			setEmpresaSeleccionada(getEmpresas().get(0));
		else
			throw new NoHayEmpresasCargadasException();
	}

	public boolean hayEmpresasCargadas() {
		return RepositorioEmpresas.instance().hayElementosCargados();
	}

	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getElementos();
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		VmUtils.avisarCambios(this, "periodos","cuentas","calculadores");
	}

	public Set<String> getPeriodos() {
		return empresaSeleccionada.getPeriodos();
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;

	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
		VmUtils.avisarCambios(this,"cuentas","calculadores");
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

	public List<IndicadorCalculado> getCalculadores() {
		return indicadoresCargados().stream()
				.map(indicador -> indicador.calcular(empresaSeleccionada, periodoSeleccionado))
				.collect(Collectors.toList());
	}

	public List<Indicador> indicadoresCargados() {
		return RepositorioIndicadores.instance().getElementos();
	}


	public IndicadorCalculado getCalculadorSeleccionado() {
		return calculadorSeleccionado;
	}

	public void setCalculadorSeleccionado(IndicadorCalculado calculadorSeleccionado) {
		this.calculadorSeleccionado = calculadorSeleccionado;
	}

}
