package calculoIndicadores.ConstructoresIndicador;


import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class TokenToOperationTranslator {
	
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
	
	protected boolean esUnaCuenta(String cuenta,Empresa empresa, String periodo){
		return empresa.contieneLaCuentaDePeriodo(cuenta, periodo);
	}
	
	protected boolean esUnIndicador(String indicador){
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}
	
	protected boolean esUnaIgualdad(String token){
		return token.equals("=");
	}
	
}
