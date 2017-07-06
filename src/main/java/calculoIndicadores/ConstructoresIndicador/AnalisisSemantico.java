package calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class AnalisisSemantico{
	private TokenToOperationTranslator operation = new TokenToOperationTranslator();
	
	public Boolean analizarSemantica(List<String> tokens,Empresa empresa, String periodo){
		return tokens.stream().filter(unToken-> operation.esUnTexto(unToken))
				.allMatch(unToken-> sePuedeCalcular(unToken,empresa,periodo));
	}
	
	private Boolean sePuedeCalcular(String nombre,Empresa empresa, String periodo){
		if(operation.esUnaCuenta(nombre,empresa,periodo))
			return analizarCuenta(nombre,empresa,periodo);
		else if(operation.esUnIndicador(nombre))
			return analizarIndicador(nombre,empresa,periodo);
		else
			return noExisteElIndicadorOCuenta();
	}
	
	private Boolean noExisteElIndicadorOCuenta(){
		return false;
	}

	private Boolean analizarCuenta(String nombre,Empresa empresa,String periodo) {
		return empresa.contieneLaCuentaDePeriodo(nombre, periodo);
	}
	
	private Boolean analizarIndicador(String nombre,Empresa empresa, String periodo){
		return RepositorioIndicadores.instance().buscarIndicador(nombre).get().esCalculable(empresa, periodo);
	}

}
