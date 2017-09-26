package domain.metodologias;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.javatuples.Pair;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;

@Observable
//@Entity
//@Table(name = "metodologias")
public class Metodologia {
	@Id
	@GeneratedValue
	public int id;
	@Column(length = 30)
	private String nombre;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Condicion> condiciones;

	public Metodologia() {
	}

	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;
	}

	public List<Empresa> aplicarCondiciones(List<Empresa> empresas, String periodo) {
		List<EmpresaConPeso> empresasConPeso = empresas.stream().map(empresa -> new EmpresaConPeso(empresa, 0.0))
				.collect(Collectors.toList());
		List<EmpresaConPeso> empr = condiciones.stream()
				.reduce(empresasConPeso,
						(unasEmpresasConPeso, condicion) -> condicion.aplicarCondicion(unasEmpresasConPeso),
						(empresaConPesos, empresaConPesos2) -> {
							throw new RuntimeException("this reduction can't be parallel");
						} // error para poder cambiar el tipo del retorno
				);
		return empr.stream().sorted((empresaConPeso1, empresaConPeso2) -> Double.compare(empresaConPeso2.getPeso(), empresaConPeso1.getPeso())).map(empresaConPeso -> empresaConPeso.getEmpresa()).collect(Collectors.toList());
	}
	
	// GETTERS Y SETTERS//
	public boolean suNombreEs(String nombre) {
		return this.nombre.equals(nombre);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

}
