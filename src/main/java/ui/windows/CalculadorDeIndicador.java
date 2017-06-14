package ui.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;
import domain.indicadores.*;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import interfaces.Indicador;
import externos.AnalizadorDeIndicadores;

@Observable
public class CalculadorDeIndicador {
	private Indicador indicador;
	private Double valor;
	
	public CalculadorDeIndicador(Indicador indicador){
		this.indicador=indicador;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Empresa empresa, String periodo) {
		this.valor = indicador.calcularIndicador(empresa, periodo);
	}
	public String getNombre() {
		return indicador.getNombre();
	}
	

}
