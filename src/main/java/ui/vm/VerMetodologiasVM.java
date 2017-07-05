package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class VerMetodologiasVM {

	public String condicion;
	public List<String> condiciones;
	public String condicionSeleccionada;

	public String empresa;
	public List<Empresa> empresas;

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public List<String> getCondiciones() {
		return RepositorioCondiciones.instance().getNombresDeCondiciones();
	}

	public String getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(String condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return RepositorioEmpresas.instance().getEmpresasCargadas();
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}