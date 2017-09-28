package domain.condiciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import domain.indicadores.Indicador;
import domain.metodologias.EmpresaConPeso;

@Observable
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "condiciones")
public abstract class Condicion implements CondicionCustom {
	@Id
	@GeneratedValue
	Integer id;
	@Column(length = 30)
	protected String nombre;
	@Transient
	protected Indicador indicador;
	@Embedded
	protected OperadorCondicion operador;

	public Condicion() {
	}

	public Condicion(String nombre, Indicador indicador, OperadorCondicion operador) {
		this.nombre = nombre;
		this.indicador = indicador;
		this.operador = operador;
	}

	public abstract boolean esTaxativa();

	public abstract List<EmpresaConPeso> aplicarCondicionEnPeriodo(List<EmpresaConPeso> empresasConPeso,
			String periodo);

	public abstract List<EmpresaConPeso> aplicarCondicion(List<EmpresaConPeso> empresasConPeso);

	public List<String> obtenerPeriodos(List<EmpresaConPeso> empresasConPeso) {
		return empresasConPeso.stream().map(empresaConPeso -> empresaConPeso.getEmpresa().getPeriodos())
				.flatMap(Set::stream).collect(Collectors.toList());
	}

	// GETTERS Y SETTERS

	public Condicion(String nombre) {
		this.nombre = nombre;
	}

	public Integer comparar(Double valorIndicadorUno, Double valorIndicadorDos) {
		return operador.comparar(valorIndicadorUno, valorIndicadorDos);
	}

	public Double calcularIndicador(Empresa empresa, String periodo) {
		return indicador.calcularIndicador(empresa, periodo);
	}

	public boolean suNombreEs(String nombreCondicion) {
		return nombre.equals(nombreCondicion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public OperadorCondicion getOperador() {
		return operador;
	}

	public void setOperador(OperadorCondicion operador) {
		this.operador = operador;
	}

	public Integer getId() {
		return id;
	}

}
