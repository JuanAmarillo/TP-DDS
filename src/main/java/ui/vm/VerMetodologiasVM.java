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
		validarExistenciaDeEmpresas();
		List<Empresa> empresasCalculadas = new AplicaMetodologia(getEmpresas()).aplicarMetodologia(metodologiaSeleccionada, "pascuas").obtenerLista();
		setEmpresasCondicionadas(empresasCalculadas);
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

}