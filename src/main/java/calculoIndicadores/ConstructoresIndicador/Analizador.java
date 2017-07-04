package calculoIndicadores.ConstructoresIndicador;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import calculoIndicadores.Calculable;
import domain.Empresa;


public class Analizador {
	private List<String> tokens;
	
	public Analizador scan(String ecuacion) {
		generarTokens(ecuacion);
		eliminarEspaciosInnecesarios();
		return this;
	}
	
	public void generarTokens(String ecuacion){
		String[] tokens = ecuacion.split("(?<=[-+()*/=])|(?=[-+()*/=])");
		this.tokens =  new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public void eliminarEspaciosInnecesarios(){
		this.tokens = tokens.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public void parser(){
		new Parser(tokens).parsear();
	}
	
	public Calculable compilar(){
		return new Compilador(tokens).compilar();
	}
	
	public Boolean sePuedeCalcular(Empresa empresa, String periodo){
		return new AnalisisSemantico(empresa,periodo,tokens).analizarSemantica();
	}

}