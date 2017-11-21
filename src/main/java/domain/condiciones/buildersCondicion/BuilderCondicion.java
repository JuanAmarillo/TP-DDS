package domain.condiciones.buildersCondicion;

import org.uqbar.commons.utils.Observable;

import domain.condiciones.Condicion;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.repositorios.RepositorioIndicadores;
import domain.condiciones.buildersCondicion.Validador;
import exceptions.ValidadorException;
import exceptions.BuilderCondicionesException;

public abstract class BuilderCondicion {
	protected String etiquetaBuilder;
	protected String nombre;
	protected OperadorCondicion operador;
	protected Double value;
	protected Indicador indicador;

	public BuilderCondicion(String etiquetaBuilder) {
		this.etiquetaBuilder = etiquetaBuilder;
	}

	public String getEtiquetaBuilder() {
		return etiquetaBuilder;
	}

	public BuilderCondicion setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public BuilderCondicion setOperador(OperadorCondicion operador) {
		this.operador = operador;
		return this;
	}

	public BuilderCondicion setIndicador(String indicador) {
		this.indicador = RepositorioIndicadores.instance().findByName(indicador).orElse(null);
		return this;
	}

	public BuilderCondicion setValue(Double value) {
		this.value = value;
		return this;
	}

	public Condicion build() {
		try {
			validar();
			return buildPosta();
		} catch (ValidadorException E) {
			throw new BuilderCondicionesException(E.getMessage());
		}
	}

	public void validar() {
		new Validador().agregarValidacion(nombre == null || nombre.isEmpty(), "No se ingresó ningún nombre")
				.agregarValidacion(operador == null, "No se seleccionó ningún operador")
				.agregarValidacion(indicador == null, "No se seleccionó ningún indicador").validar();
	}

	public abstract Condicion buildPosta();

	public abstract Boolean esTaxativa();

}
