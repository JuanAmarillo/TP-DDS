package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class VerMetodologiasVM {

	public List<Metodologia> metodologias;

	public String metodologiaSeleccionada;

	public List<Empresa> empresas;


	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}
	
	public List<Metodologia> getMetodologias() {
		return RepositorioMetodologias.instance().getMetodologiasCargadas();
	}

	public void setMetodologias(List<Metodologia> metodologia) {
		this.metodologias = metodologia;
	}

	public String getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(String metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

}