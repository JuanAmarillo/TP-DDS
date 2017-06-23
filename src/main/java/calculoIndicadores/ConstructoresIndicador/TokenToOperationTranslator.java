package calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class TokenToOperationTranslator {
	protected Empresa empresa;
	protected String periodo;
	protected List<String> tokens;
	
	protected TokenToOperationTranslator(List<String> tokens){
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
		return token.matches("([0-9 ]*[a-zA-Z]+[0-9 ]*)+");
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
	
	protected boolean esUnaIgualdad(String token){
		return token.equals("=");
	}
	
}
