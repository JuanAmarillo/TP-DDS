package calculoIndicadores.ConstructoresIndicador;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import calculoIndicadores.Calculable;
import domain.Empresa;


public class Analizador {
	private String ecuacion;
	
	public Analizador(String ecuacion){
		this.ecuacion = ecuacion;
	}
	
	public List<String> generarTokens(){
		String[] tokens = ecuacion.split("(?<=[-+()*/=])|(?=[-+()*/=])");
		return eliminarEspaciosInnecesarios(new LinkedList<String>(Arrays.asList(tokens)));
	}
	
	public List<String> eliminarEspaciosInnecesarios(List<String> tokens){
		return tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public void parser(){
		new Parser(generarTokens()).parsear();
	}
	
	public Calculable compilar(){
		return new Compilador(generarTokens()).compilar();
	}
	
	public Boolean sePuedeCalcular(Empresa empresa, String periodo){
		return new AnalisisSemantico(empresa,periodo,generarTokens()).analizarSemantica();
	}

}