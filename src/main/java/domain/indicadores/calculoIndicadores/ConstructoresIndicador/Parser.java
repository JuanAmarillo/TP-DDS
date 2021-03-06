package domain.indicadores.calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import exceptions.ParsingException;

public class Parser {
	private TokenToOperationTranslator operation;
	private List<String> tokens;
	private String nombreIndicador;
	private Integer parentesisAbiertos = 0;

	public Parser(List<String> tokens) {
		this.tokens =  tokens;
		this.operation =  new TokenToOperationTranslator();
	}

	public  void parsear(){
		primerToken(obtenerSiguienteToken());
	}
	
	private void primerToken(String token){
		if(operation.esUnTexto(token)){
			guardarNombre(token);
			segundoToken();
		}
		else
			throw new ParsingException("el Indicador solo puede estar formado por letras y numeros");
	}

	public void guardarNombre(String token) {
		this.nombreIndicador = token;
	}


	private void segundoToken() {
		String siguienteToken = obtenerSiguienteToken();
		if(hayToken(siguienteToken))
			igualdad(siguienteToken);
		else
			throw new ParsingException("Debe haber una igualdad");
	}
	
	private void igualdad(String token){
		if(operation.esUnaIgualdad(token))
			terminal();
		else
			throw new ParsingException("No puede haber operaciones antes de la igualdad");
	}
	
	private Boolean numero(String token){
		if(operation.esUnNumero(token)){
			return noTerminal();
		}
		return false;
	}
	
	private Boolean palabra(String token){
		if(operation.esUnTexto(token) && noEsElIndicadorAParsear(token)){
			return noTerminal();
		}
		return false;
	}
	
	private Boolean parentesisDerecho(String token){
		if(operation.esUnParentesisDerecho(token)){
			this.parentesisAbiertos--;
			return noTerminal();
		}
		return false;
	}

	
	private Boolean noEsElIndicadorAParsear(String token){
		return !this.nombreIndicador.equals(token);
	}
	
	private Boolean operador(String token){
		if(operation.esUnOperador(token)){
			return terminal();
		}
		return false;
	}
	
	private Boolean parentesisIzquierdo(String token){
		if(operation.esUnParentesisIzquierdo(token)){
			this.parentesisAbiertos++;
			return terminal();
		}
		return false;
	}

	private Boolean noTerminal() {
		String siguienteToken = obtenerSiguienteToken();
		if(hayToken(siguienteToken))
			return operador(siguienteToken) || parentesisDerecho(siguienteToken);
		else
			return fin(siguienteToken);
	}
	
	private Boolean terminal() {
		String siguienteToken = obtenerSiguienteToken();
		if(hayToken(siguienteToken))
			return siguienteTerminal(siguienteToken);
		else
		    throw new ParsingException("Falta un terminal");
	}

	private Boolean siguienteTerminal(String siguienteToken) {
		if( numero(siguienteToken) || palabra(siguienteToken) || parentesisIzquierdo(siguienteToken))
			return true;
		else
			throw new ParsingException("Terminal Incorrecto");
	}
	
	private Boolean fin(String token){
		return !hayToken(token) && noHayParentesisAbiertos();
	}

	private boolean hayToken(String token) {
		return token != null;
	}
	
	private Boolean noHayParentesisAbiertos(){
		return parentesisAbiertos == 0;
	}
	
	private String obtenerSiguienteToken(){
		String token = null;
		if(!tokens.isEmpty()){
			token = tokens.get(0);
			tokens.remove(0);
		}
		return token;
	}


}
