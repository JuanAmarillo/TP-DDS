package ui.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioEmpresas;
import domain.repositorios.RepositorioMetodologias;

@Observable
public class VerMetodologiasVM {

	public List<String> metodologias;
	public String metodologiaSeleccionada = "";
	
	public List<String> empresas;
	public String empresaSeleccionada = "";
	
	public List<String> empresasCondicionadas;
	public String empresaCondicionadaSeleccionada = "";

	public VerMetodologiasVM() {
		empresas = new ArrayList<String>();
		empresasCondicionadas = new ArrayList<String>();
		metodologias = new ArrayList<String>();
		
	}
	
	public void aplicarMetodologia() {
		validarExistenciaDeEmpresas();
		Metodologia met = RepositorioMetodologias.instance().buscarMetodologia(metodologiaSeleccionada).get();
		empresasCondicionadas = met.aplicarMetodologia(RepositorioEmpresas.instance().getEmpresasCargadas(), "pascuas").stream().map(emp -> emp.getNombre()).collect(Collectors.toList());
		ObservableUtils.firePropertyChanged(this, "empresasCondicionadas");
	}

	private void validarExistenciaDeEmpresas() {
		System.out.println(RepositorioEmpresas.instance().getEmpresasCargadas().size());
		if(!RepositorioEmpresas.instance().tieneEmpresasCargadas())
			throw new RuntimeException("No hay empresas cargadas en el sistema para aplicar la metodologia");
	}
	
	public List<String> getEmpresas() {
		return RepositorioEmpresas.instance().getNombreEmpresas();
	}
	
	public List<String> getMetodologias() {
		System.out.println(RepositorioMetodologias.instance().getMetodologiasCargadas().size());
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
	
	public List<String> getEmpresasCondicionadas() {
		return empresasCondicionadas;
	}

	public void setEmpresasCondicionadas(List<String> empresasCondicionadas) {
		this.empresasCondicionadas = empresasCondicionadas;
	}

	public String getEmpresaCondicionadaSeleccionada() {
		return empresaCondicionadaSeleccionada;
	}

	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public void setEmpresas(List<String> empresas) {
		this.empresas = empresas;
	}

	public void setEmpresaCondicionadaSeleccionada(String empresaCondicionadaSeleccionada) {
		this.empresaCondicionadaSeleccionada = empresaCondicionadaSeleccionada;
	}
	

}