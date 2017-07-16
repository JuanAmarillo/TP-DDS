package ui.vm;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import domain.condiciones.BuilderCondicion;
import domain.condiciones.BuilderCondicionComparativa;
import domain.condiciones.BuilderCondicionTaxativa;
import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.Mayor;
import domain.condiciones.OperadoresCondicion.Menor;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.repositorios.RepositorioCondiciones;
import domain.repositorios.RepositorioIndicadores;

@Observable
public class CargarCondicionVM {

	public String nombreCondicion = "";
	public String condicionSeleccionada = "";
	public String indicadorSeleccionado = "";
	public OperadorCondicion operacionSeleccionada;

	public double valor;

	public List<String> tipos = Arrays.asList("Taxativa", "Comparativa");
	public boolean taxativa=false;
	public boolean comparativa=false;

	public String tipoSeleccionado;

	private BuilderCondicion builderCondicion;

	public void cargarCondicion() {
		condicionSeleccionada();
		crearCondicion();
		avisarCambiosCondiciones();
	}

	private void crearCondicion() {
		Condicion condicion = buildearCondicion();
		RepositorioCondiciones.instance().agregarCondicion(condicion);
	}

	private Condicion buildearCondicion() {
		return builderCondicion.setNombre(nombreCondicion).setIndicador(indicadorSeleccionado)
				.setOperador(operacionSeleccionada).setValue(valor).build();
	}

	public void eliminarCondicion() {
		RepositorioCondiciones.instance().eliminarCondicion(condicionSeleccionada);
		avisarCambiosCondiciones();
	}

	private void condicionSeleccionada() {
		if (!taxativa && !comparativa)
			throw new RuntimeException("No se seleccionó el tipo de condición");
	}

	private void avisarCambiosCondiciones() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}

	private void crearBuilderComparativa() {
		this.builderCondicion = new BuilderCondicionComparativa();
	}

	private void crearBuilderTaxativa() {
		this.builderCondicion = new BuilderCondicionTaxativa();
	}

	// GETTERS Y SETTERS
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

	public List<OperadorCondicion> getOperaciones() {
		return Arrays.asList(new Mayor(), new Menor());
	}

	public OperadorCondicion getOperacionSeleccionada() {
		return operacionSeleccionada;
	}

	public void setOperacionSeleccionada(OperadorCondicion operacionSeleccionada) {
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
		this.taxativa = taxativa;
		crearBuilderTaxativa();
	}

	public boolean getComparativa() {
		return comparativa;
	}

	public void setComparativa(boolean comparativa) {
		this.comparativa = comparativa;
		crearBuilderComparativa();

	}

	public List<String> getTipos() {
		return tipos;
	}

	public String getTipoSeleccionado() {
		if (tipoSeleccionado == tipos.get(0)) { // Taxativa
			setTaxativa(true);
		}
		if (tipoSeleccionado == tipos.get(1)) { // Comparativa
			setComparativa(true);
		}
		return tipoSeleccionado;
	}

	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

}