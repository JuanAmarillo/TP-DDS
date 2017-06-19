package calculoIndicadores.ConstructoresIndicador;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioIndicadores;

public class AnalisisSemantico extends OperacionesDisponibles{
	private Empresa empresa;
	private String periodo;

	protected AnalisisSemantico(Empresa empresa, String periodo,List<String> tokens) {
		super(tokens);
		this.empresa = empresa;
		this.periodo = periodo;
	}
	
	public Boolean analizarSemantica(){
		return tokens.stream().filter(unToken -> esUnTexto(unToken)).anyMatch(unToken-> sePuedeCalcular(unToken));
	}
	
	private Boolean sePuedeCalcular(String nombre){
		if(esUnaCuenta(nombre))
			return empresa.contieneLaCuentaDePeriodo(nombre, periodo);
		else
			return analizarIndicador(nombre);
	}
	
	private Boolean analizarIndicador(String nombre){
		return RepositorioIndicadores.instance().buscarIndicador(nombre).esCalculable(empresa, periodo);
	}

}
