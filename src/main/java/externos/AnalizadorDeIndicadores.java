package externos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;

public class AnalizadorDeIndicadores {
	private Empresa empresa;
	private List<String> lexemas;
	
	public AnalizadorDeIndicadores scan(Indicador indicador) {
		generarTokens(indicador.ecuacion);
		eliminarEspaciosInnecesarios();
		lexemas.forEach(lexema-> System.out.println(lexema));
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
	
	public  Double parser(Empresa empresa){
		this.empresa = empresa;
		return analizarSiguienteToken(0.0,false);
		
	}
	
	private Double analisisSintactico(Double valor,boolean anteriorFueOperador){
		String token = lexemas.get(0);
		lexemas.remove(0);
		
		if(esUnTexto(token))
			return palabra(token, valor);
		if(esUnNumero(token))
			return numero(token,valor);
		if(esUnParentesis(token))
			return parentesis(token,valor);
		if(esUnOperador(token))
			return operador(token,valor,anteriorFueOperador);
		
		throw new RuntimeException("Invalid token");
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
	
	private Double palabra(String token,Double valor){
		return analizarSiguienteToken(valorDe(token),false);
	}
	
	private Double numero(String token,Double valor){
		return analizarSiguienteToken(Double.parseDouble(token),false);
	}
	
	private Double parentesis(String token,Double valor){
		switch (token) {
		case "(":
			return analizarSiguienteToken(analizarSiguienteToken(0.0,false),false);
		case ")":
			return valor;
		}
		return 0.0;
	}
	
	private Double operador(String token,Double valor,boolean anteriorFueOperador){
		if(anteriorFueOperador)
			throw new RuntimeException("Doble Operador");
		
		switch(token){
		case "+":
			return valor + analizarSiguienteToken(0.0,true);
		case "-":
			return valor - analizarSiguienteToken(0.0,true);
		case "*":
			return valor * analizarSiguienteToken(0.0,true);
		case "/":
			return valor / analizarSiguienteToken(0.0,true);
		}
		
		return 0.0;
	}
	
	private Double analizarSiguienteToken(Double valor,boolean anteriorFueOperador){
		if(lexemas.isEmpty()){
			if(anteriorFueOperador)
				throw new RuntimeException("falta Operando");
			else
				return valor;
		}
		else
			return analisisSintactico(valor,anteriorFueOperador);
	}
	
	private boolean esUnaCuenta(String cuenta){
		return empresa.contieneLaCuenta(cuenta);
	}
	
	private boolean esUnIndicador(String indicador){
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
	private Double valorDe(String cuentaOIndicador){
		if(esUnaCuenta(cuentaOIndicador))
			return empresa.getValorDeLaCuenta(cuentaOIndicador);
		if(esUnIndicador(cuentaOIndicador))
			return RepositorioIndicadores.instance().getValorDelIndicador(empresa,cuentaOIndicador);
		
		throw new RuntimeException("Invalid token");
	}
}