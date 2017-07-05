package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;
import ui.windows.CargarCondicionWindow;

@Observable
public class CargarCondicionVM {

	public String nombreCondicion = "";
	public String condicionSeleccionada = "";
	public String indicadorSeleccionado = "";

	public boolean taxativa = false;
	public boolean comparativa = false;
	
	public List<String> operaciones = Arrays.asList(">", "<");
	public String operacionSeleccionada = "";

	public double valor;
	
	public void cargarCondicion() {
		validarCampos();
		crearCondicion();
		avisarCambiosCondiciones();
		
	}

	private void crearCondicion() {
		if(taxativa) 
			crearCondicionTaxativa();
		else 
			crearCondicionComparativa();
	}
	
	public void eliminarCondicion() {
		RepositorioCondiciones.instance().eliminarCondicion(condicionSeleccionada);
		avisarCambiosCondiciones();
	}
	
	private void validarCampos() {
		if(nombreCondicion.isEmpty())
		{ throw new RuntimeException("No se ingresó ningún nombre");}
		if(!(taxativa || comparativa))
		{ throw new RuntimeException("No se seleccionó el tipo de condición");}
		if(indicadorSeleccionado.isEmpty())
		{ throw new RuntimeException("No se seleccionó ningún indicador");}
		if(operacionSeleccionada.isEmpty())
		{ throw new RuntimeException("No se seleccionó ningún operador");}
		
	}

	private void completarBuild(Condicion condicion) {
		condicion.setOperador(operacionSeleccionada);
		condicion.setIndicador(RepositorioIndicadores.instance().buscarIndicador(indicadorSeleccionado).get());
		condicion.setNombre(tipoCondicion() + " - " + nombreCondicion);
		RepositorioCondiciones.instance().agregarCondicion(condicion);
	}
	
	
	private String tipoCondicion() {
		if(taxativa) 
			return "Taxativa";
		else 
			return "Comparativa";
	}

	private void crearCondicionTaxativa() {
		CondicionTaxativa condicionTaxativa = new CondicionTaxativa();
		condicionTaxativa.setValorDeComparacion(valor);
		completarBuild(condicionTaxativa);
	}
	
	private void crearCondicionComparativa() {
		CondicionComparativa condicionComparativa = new CondicionComparativa();
		completarBuild(condicionComparativa);
		
	}	
	
	private void avisarCambiosCondiciones() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}
	
	//GETTERS Y SETTERS
	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String condicion) {
		this.nombreCondicion = condicion;
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
	
	public List<String> getIndicadores() {
		return RepositorioIndicadores.instance().getNombresDeIndicadores();
	}

	public String getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(String indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public List<String> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<String> operaciones) {
		this.operaciones = operaciones;
	}

	public String getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(String operacionSeleccionada) {
		this.operacionSeleccionada = operacionSeleccionada;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean getTaxativa() {
		return taxativa;
	}

	public void setTaxativa(boolean taxativa) {
		this.taxativa    = taxativa;
		this.comparativa = false;
	}

	public boolean getComparativa() {
		return comparativa;
	}

	public void setComparativa(boolean comparativa) {
		this.comparativa = comparativa;
		this.taxativa    = false;
	}
	
	
	
}