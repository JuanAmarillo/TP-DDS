package domain.indicadores.calculoIndicadores.ConstructoresIndicador;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import domain.indicadores.calculoIndicadores.Calculable;


public class Analizador {
	private String ecuacion;
	
	public Analizador(String ecuacion){
		this.ecuacion = ecuacion;
	}
	
	public List<String> generarTokens(){
		String[] tokens = separarPorTokens();
		return eliminarEspaciosInnecesarios(convertirALista(tokens));
	}

	private LinkedList<String> convertirALista(String[] tokens) {
		return new LinkedList<String>(Arrays.asList(tokens));
	}

	private String[] separarPorTokens() {
		return ecuacion.split("(?<=[-+()*/=])|(?=[-+()*/=])");
	}
	
	private List<String> eliminarEspaciosInnecesarios(List<String> tokens){
		return tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public void parser(){
		new Parser(generarTokens()).parsear();
	}
	
	public Calculable compilar(){
		return new Compilador().compilar(generarTokens());
	}
	

}