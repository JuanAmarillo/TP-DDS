package calculoIndicadores.ConstructoresIndicador;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import calculoIndicadores.Token;
import domain.Empresa;


public class Analizador {
	private Empresa empresa;
	private String periodo;
	private List<String> tokens;
	
	public Analizador(Empresa empresa, String periodo){
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public Analizador scan(String indicador) {
		generarTokens(indicador);
		eliminarEspaciosInnecesarios();
		//tokens.forEach(lexema-> System.out.println(lexema)); 
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
	
	public Boolean parser(){
		return new Parser(empresa, periodo, tokens).parsear();
	}
	
	public Token compilar(){
		return new Compilador(empresa, periodo, tokensSinLaAsignacion()).compilar();
	}

	private List<String> tokensSinLaAsignacion() {
		return tokens.subList(2, ultimoIndiceDeTokens()); //este java que no tiene takeRight
	}

	private int ultimoIndiceDeTokens() {
		return tokens.size()-1;
	}

}