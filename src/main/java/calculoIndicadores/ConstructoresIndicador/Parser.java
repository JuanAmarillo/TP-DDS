package calculoIndicadores.ConstructoresIndicador;

import java.util.List;
import domain.Empresa;

public class Parser extends OperacionesDisponibles {
	private String nombreIndicador;
	private Integer parentesisAbiertos = 0;

	protected Parser(List<String> tokens) {
		super(tokens);
	}

	public  Boolean parsear(){
		return primerToken(obtenerSiguienteToken());
	}
	
	private Boolean primerToken(String token){
		if(esUnTexto(token)){
			this.nombreIndicador = token;
			return segundoToken();
		}
		throw new RuntimeException("el Indicador solo puede estar formado por letras");
	}

	private Boolean segundoToken() {
		String siguienteToken = obtenerSiguienteToken();
		if(hayToken(siguienteToken))
			return igualdad(obtenerSiguienteToken());
		else
			throw new RuntimeException("Debe haber una igualdad");
	}
	
	private Boolean igualdad(String token){
		if(esUnaIgualidad(token)){
			return terminal();
		}
		throw new RuntimeException("Debe haber una igualdad");
	}
	
	private Boolean numero(String token){
		if(esUnNumero(token)){
			return noTerminal();
		}
		return false;
	}
	
	private Boolean palabra(String token){
		if(esUnTexto(token) && noEsElIndicadorAParsear(token)){
			return noTerminal();
		}
		return false;
	}
	
	private Boolean parentesisDerecho(String token){
		if(esUnParentesisDerecho(token)){
			this.parentesisAbiertos--;
			return noTerminal();
		}
		return false;
	}

	
	private Boolean noEsElIndicadorAParsear(String token){
		return !this.nombreIndicador.equals(token);
	}
	
	private Boolean operador(String token){
		if(esUnOperador(token)){
			return terminal();
		}
		return false;
	}
	
	private Boolean parentesisIzquierdo(String token){
		if(esUnParentesisIzquierdo(token)){
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
		    throw new RuntimeException("Falta un terminal");
	}

	private Boolean siguienteTerminal(String siguienteToken) {
		if( numero(siguienteToken) || palabra(siguienteToken) || parentesisIzquierdo(siguienteToken))
			return true;
		else
			throw new RuntimeException("Terminal Incorrecto");
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
