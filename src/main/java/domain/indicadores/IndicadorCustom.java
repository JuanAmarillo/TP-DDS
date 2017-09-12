package domain.indicadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.calculoIndicadores.Calculable;
import domain.indicadores.calculoIndicadores.Token;
import domain.indicadores.calculoIndicadores.ConstructoresIndicador.Analizador;

@Observable
@Entity
@Table(name="indicadores")
public class IndicadorCustom extends Indicador {
	@Id
	@GeneratedValue
	public Integer id;
	@Column(length=100)
	public String expresion;
	@Transient
	public Calculable calculo;

	public IndicadorCustom(String nombre,String expresion,Calculable calculo) {
		super(nombre);
		this.expresion = expresion;
		this.calculo   = calculo;
	}
	
	public IndicadorCustom() {
		
	}

	@Override
	public Double calcularIndicador(Empresa empresa, String periodo) {
		return calculo.calcularValor(empresa, periodo);			
	}

	// SETTERS Y GETTERS //

	public void setCalculo(Calculable calculo) {
		this.calculo = calculo;
	}

	public Token getCalculo() {
		return calculo;
	}
	
	public Integer getId() {
		return id;
	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
//	@Column(length=30)
//	public String getNombrecito() {
//		return getNombre();
//	}
	
	@Override
	public boolean esCustom() {
		return true;
	}
	
	public void holi() {
		
	}

	

}
