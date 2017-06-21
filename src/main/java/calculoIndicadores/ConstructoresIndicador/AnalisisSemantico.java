package calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class AnalisisSemantico extends OperacionesDisponibles{
	
	protected AnalisisSemantico(Empresa empresa, String periodo,List<String> tokens) {
		super(tokens);
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public Boolean analizarSemantica(){
		return tokens.stream().filter(unToken-> esUnTexto(unToken)).allMatch(unToken-> sePuedeCalcular(unToken));
	}
	
	private Boolean sePuedeCalcular(String nombre){
		if(esUnaCuenta(nombre))
			return analizarCuenta(nombre);
		else if(esUnIndicador(nombre))
			return analizarIndicador(nombre);
		else
			return noExisteElIndicadorOCuenta();
	}
	
	private Boolean noExisteElIndicadorOCuenta(){
		return false;
	}

	private Boolean analizarCuenta(String nombre) {
		return empresa.contieneLaCuentaDePeriodo(nombre, periodo);
	}
	
	private Boolean analizarIndicador(String nombre){
		return RepositorioIndicadores.instance().buscarIndicador(nombre).get().esCalculable(empresa, periodo);
	}

}
