package calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

abstract class OperacionesDisponibles {
	protected Empresa empresa;
	protected String periodo;
	protected List<String> tokens;
	
	protected OperacionesDisponibles(List<String> tokens){
		this.tokens = tokens;
	}
	
	protected boolean esUnNumero(String token){
		return token.matches("[0-9]+([.][0-9]+)?");
	}
	
	protected boolean esUnParentesisIzquierdo(String token){
		return token.matches("[(]");
	}
	
	protected boolean esUnParentesisDerecho(String token){
		return token.matches("[)]");
	}
	
	protected boolean esUnOperador(String token){
		return token.matches("[-+*/]");
	}
	
	protected boolean esUnTexto(String token){
		return token.matches("([ ]*[a-zA-Z]+[ ]*)+");
	}
	
	protected boolean esUnParentesis(String token){
		return esUnParentesisIzquierdo(token) || esUnParentesisDerecho(token);
	}
	
	protected boolean esUnaCuenta(String cuenta){
		return empresa.contieneLaCuentaDePeriodo(cuenta, periodo);
	}
	
	protected boolean esUnIndicador(String indicador){
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
	protected boolean esUnaIgualidad(String token){
		return token.matches("[=]");
	}
	
}
