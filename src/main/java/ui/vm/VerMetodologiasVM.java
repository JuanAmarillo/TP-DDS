package ui.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.metodologias.AplicaMetodologia;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class VerMetodologiasVM {

	public List<Metodologia> metodologias;
	public Metodologia metodologiaSeleccionada;
	
	public List<String> periodos;
	public String periodoSeleccionado = "";

	public List<Empresa> empresas;
	public Empresa empresaSeleccionada;

	public List<Empresa> empresasCondicionadas;
	public Empresa empresaCondicionadaSeleccionada;

	public VerMetodologiasVM() {
		empresas = new ArrayList<Empresa>();
		empresasCondicionadas = new ArrayList<Empresa>();
		metodologias = new ArrayList<Metodologia>();
	}

	public void aplicarMetodologia() {
		validaciones();
		List<Empresa> empresasCalculadas = metodologiaAplicada();
		setEmpresasCondicionadas(empresasCalculadas);
	}

	public List<Empresa> metodologiaAplicada() {
		return new AplicaMetodologia().aplicar(metodologiaSeleccionada, getEmpresas(),periodoSeleccionado);
	}

	private void validaciones() {
		validarMetodologiaSeleccionada();
		validarExistenciaDeEmpresas();
		validarPeriodoSeleccionado();	
	}

	private void validarMetodologiaSeleccionada() {
		if (metodologiaSeleccionada == null)
			throw new RuntimeException("No se selecciono ninguna metodología");
	}

	private void validarPeriodoSeleccionado() {
		if (periodoSeleccionado.isEmpty())
			throw new RuntimeException("No se selecciono un periodo para realizar el calculo de la metodología");
	}

	private void validarExistenciaDeEmpresas() {
		if (!RepositorioEmpresas.instance().tieneEmpresasCargadas())
			throw new RuntimeException("No hay empresas cargadas en el sistema para aplicar la metodologia");
	}

	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	public List<Metodologia> getMetodologias() {
		return RepositorioMetodologias.instance().getMetodologiasCargadas();
	}

	public void setMetodologias(List<Metodologia> metodologia) {
		this.metodologias = metodologia;
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public List<Empresa> getEmpresasCondicionadas() {
		return empresasCondicionadas;
	}

	public void setEmpresasCondicionadas(List<Empresa> empresasCondicionadas) {
		this.empresasCondicionadas = empresasCondicionadas;
	}

	public Empresa getEmpresaCondicionadaSeleccionada() {
		return empresaCondicionadaSeleccionada;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public void setEmpresaCondicionadaSeleccionada(Empresa empresaCondicionadaSeleccionada) {
		this.empresaCondicionadaSeleccionada = empresaCondicionadaSeleccionada;
	}

	public List<String> getPeriodos() {
		return RepositorioEmpresas.instance().getPeriodos();
	}

	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}

	public String getPeriodoSeleccionado() {
		return periodoSeleccionado;
	}

	public void setPeriodoSeleccionado(String periodoSeleccionado) {
		this.periodoSeleccionado = periodoSeleccionado;
	}

}