package ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class VerMetodologiasVM {

	public List<String> metodologias;
	public String metodologiaSeleccionada;
	
	public List<Empresa> empresas ;

	public List<Empresa> empresasCondicionadas;
	public String empresaCondicionadaSeleccionada;

	public VerMetodologiasVM() {
		empresas = new ArrayList<Empresa>();
		empresasCondicionadas = new ArrayList<Empresa>();
		metodologias = new ArrayList<String>();
		
	}
	
	public void aplicarMetodologia() {
		validarExistenciaDeEmpresas();
		Metodologia met = RepositorioMetodologias.instance().buscarMetodologia(metodologiaSeleccionada).get();
		empresasCondicionadas = met.aplicarMetodologia(empresas, "pascuas");
		ObservableUtils.firePropertyChanged(this, "empresasCondicionadas");
	}

	private void validarExistenciaDeEmpresas() {
		if(empresas.size() == 0)
			throw new RuntimeException("No hay empresas cargadas en el sistema para aplicar la metodologia");
	}
	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}
	
	public List<String> getMetodologias() {
		return RepositorioMetodologias.instance().getNombresMetodologias();
	}

	public void setMetodologias(List<String> metodologia) {
		this.metodologias = metodologia;
	}

	public String getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(String metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}
	
	public List<Empresa> getEmpresasCondicionadas() {
		return empresasCondicionadas;
	}

	public void setEmpresasCondicionadas(List<Empresa> empresasCondicionadas) {
		this.empresasCondicionadas = empresasCondicionadas;
	}

	public String getEmpresaCondicionadaSeleccionada() {
		return empresaCondicionadaSeleccionada;
	}
	

}