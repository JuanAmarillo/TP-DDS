package domain.indicadores;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import domain.Empresa;

@Entity
@Table(name = "indicadores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Indicador {
	@Id
	@GeneratedValue
	Integer id;
	protected String nombre;

	public Indicador() {
	}

	public Indicador(String nombre) {
		this.nombre = nombre;
	}

	public boolean suNombreEs(String nombreIndicador) {
		return this.nombre.equals(nombreIndicador);
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public IndicadorCalculado calcular(Empresa empresa, String periodo) {
		try {
			return new IndicadorCalculado(this, empresa, periodo, calcularIndicador(empresa, periodo));
		} catch (RuntimeException e) {
			return new IndicadorCalculado(this, empresa, periodo);
		}
	}

	abstract public Double calcularIndicador(Empresa empresa, String periodo);

	abstract public boolean esCustom();

}
