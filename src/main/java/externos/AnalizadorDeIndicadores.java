package externos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.indicadores.IndicadorCustom;
import domain.repositorios.RepositorioIndicadores;

public class AnalizadorDeIndicadores {
	private Empresa empresa;
	private String periodo;
	private List<String> lexemas;
	
	public AnalizadorDeIndicadores(Empresa empresa,String periodo){
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public AnalizadorDeIndicadores scan(IndicadorCustom indicador) {
		generarTokens(indicador.ecuacion);
		eliminarEspaciosInnecesarios();
		//lexemas.forEach(lexema-> System.out.println(lexema)); 
		return this;
	}
	
	public void generarTokens(String ecuacion){
		String[] tokens = ecuacion.split("(?<=[-+()*/])|(?=[-+()*/])");
		lexemas = new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public void eliminarEspaciosInnecesarios(){
		lexemas = lexemas.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public  Double parser(){
		return analizarSiguienteToken(0.0);
	}
	
	private Double analisisTokens(Double valor){
		String token = obtenerSiguienteToken();
		
		if(esUnTexto(token))
			return palabra(token);
		if(esUnNumero(token))
			return numero(token);
		if(esUnOperador(token))
			return operador(token,valor);
		if(esUnParentesis(token))
			return parentesis(token,valor);
		
		throw new RuntimeException("Invalid token");
	}
	
	private String obtenerSiguienteToken(){
		String token = lexemas.get(0);
		lexemas.remove(0);
		return token;
	}
	
	private boolean esUnTexto(String token){
		return token.matches("([ ]*[a-zA-Z]+[ ]*)+");
	}
	
	private boolean esUnNumero(String token){
		return token.matches("[0-9]+([.][0-9]+)?");
	}
	
	private boolean esUnParentesis(String token){
		return token.matches("[()]");
	}
	
	private boolean esUnOperador(String token){
		return token.matches("[-+*/]");
	}
	
	private Double palabra(String token){
		return analizarSiguienteToken(valorDe(token));
	}
	
	private Double numero(String token){
		return analizarSiguienteToken(Double.parseDouble(token));
	}
	
	private Double parentesis(String token,Double valor){
		switch (token) {
		case "(":
			return analizarSiguienteToken(analizarSiguienteToken(0.0));
		case ")":
			return valor;
		}
		return 0.0;
	}
	
	private Double operador(String token,Double valor){
		switch(token){
		case "+":
			return valor + analizarSiguienteToken(0.0);
		case "-":
			return valor - analizarSiguienteToken(0.0);
		case "*":
			return valor * analizarSiguienteToken(0.0);
		case "/":
			return valor / analizarSiguienteToken(0.0);
		}
		
		return 0.0;
	}
	
	private Double analizarSiguienteToken(Double valor){
		if(lexemas.isEmpty())
			return valor;
		else
			return analisisTokens(valor);
	}
	
	public Double valorDe(String cuentaOIndicador){
		if(empresa == null)
			return 0.0;
		if(esUnaCuenta(cuentaOIndicador))
			return empresa.getValorDeLaCuenta(cuentaOIndicador,periodo);
		if(esUnIndicador(cuentaOIndicador))
			return RepositorioIndicadores.instance().buscarIndicador(cuentaOIndicador).calcularIndicador(empresa,periodo);
	
		throw new RuntimeException("Invalid token");
	}
	
	private boolean esUnaCuenta(String cuenta){
		return empresa.contieneLaCuentaDePeriodo(cuenta, periodo);
	}
	
	private boolean esUnIndicador(String indicador){
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
}
