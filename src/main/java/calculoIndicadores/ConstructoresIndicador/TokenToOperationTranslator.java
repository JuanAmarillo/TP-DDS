package calculoIndicadores.ConstructoresIndicador;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class TokenToOperationTranslator {

	public boolean esUnNumero(String token) {
		return TokenToOperation.NUMERO.matches(token);
	}

	public boolean esUnParentesisIzquierdo(String token) {
		return TokenToOperation.PARENTESISIZQUIERDO.matches(token);
	}

	public boolean esUnParentesisDerecho(String token) {
		return TokenToOperation.PARENTESISDERECHO.matches(token);
	}

	public boolean esUnOperador(String token) {
		return token.matches("[-+*/]");
	}

	public boolean esUnTexto(String token) {
		return token.matches("([0-9 ]*[a-zA-Z]+[0-9 ]*)+");
	}

	public boolean esUnParentesis(String token) {
		return esUnParentesisIzquierdo(token) || esUnParentesisDerecho(token);
	}

	public boolean esUnaCuenta(String cuenta, Empresa empresa, String periodo) {
		return empresa.contieneLaCuentaDePeriodo(cuenta, periodo);
	}

	public boolean esUnIndicador(String indicador) {
		return RepositorioIndicadores.instance().contieneElIndicador(indicador);
	}

	public boolean esUnaIgualdad(String token) {
		return token.equals("=");
	}

}
