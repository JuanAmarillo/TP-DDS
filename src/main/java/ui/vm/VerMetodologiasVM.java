
package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class VerMetodologiasVM {

	public List<Metodologia> metodologias;
	public Metodologia metodologiaSeleccionada;
	public List<Empresa> empresas;
	public Empresa empresaSeleccionada;

	public List<Empresa> empresasOrdenadas;
	public Empresa empresaOrdenadaSeleccionada;

	public VerMetodologiasVM() {
		empresas = RepositorioEmpresas.instance().getEmpresasCargadas();
		;
		empresasOrdenadas = new ArrayList<Empresa>();
		metodologias = new ArrayList<Metodologia>();
	}

	public void aplicarMetodologia() {
		validaciones();
		List<Empresa> empresasO = metodologiaSeleccionada.aplicarCondiciones(empresas /* , periodoSeleccionado */);
		this.setEmpresasOrdenadas(empresasO);
	}

	private void validaciones() {
		validarMetodologiaSeleccionada();
		validarExistenciaDeEmpresas();
	}

	private void validarMetodologiaSeleccionada() {
		if (metodologiaSeleccionada == null)
			throw new RuntimeException("No se seleccionó ninguna metodología");
	}

	private void validarExistenciaDeEmpresas() {
		if (!RepositorioEmpresas.instance().tieneEmpresasCargadas())
			throw new RuntimeException("No hay empresas cargadas en el sistema para aplicar la metodología");
	}

	// GETTERS Y SETTERS//

	public List<Metodologia> getMetodologias() {
		return RepositorioMetodologias.instance().getMetodologiasCargadas();
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	public List<String> getPeriodos() {
		return RepositorioEmpresas.instance().getPeriodos();
	}

	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<Empresa> getEmpresasOrdenadas() {
		return empresasOrdenadas;
	}

	public void setEmpresasOrdenadas(List<Empresa> empresasOrdenadas) {
		this.empresasOrdenadas = empresasOrdenadas;
	}

	public Empresa getEmpresaOrdenadaSeleccionada() {
		return empresaOrdenadaSeleccionada;
	}

	public void setEmpresaOrdenadaSeleccionada(Empresa empresaOrdenadaSeleccionada) {
		this.empresaOrdenadaSeleccionada = empresaOrdenadaSeleccionada;
	}

}