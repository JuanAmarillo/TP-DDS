package externos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import domain.Empresa;
import domain.Indicador;
import domain.repositorios.RepositorioIndicadores;
import scala.collection.parallel.ParIterableLike.Collect;

public class AnalizadorDeIndicadores {
	private Empresa empresa;
	private List<String> lexemas;
	
	public AnalizadorDeIndicadores scan(Indicador indicador) {
		generarTokens(indicador);
		eliminarEspaciosInnecesarios();
		//lexemas.forEach(lexema-> System.out.println(lexema));
		return this;
	}
	
	public void generarTokens(Indicador indicador){
		String[] tokens = indicador.ecuacion.split("(?<=[+-//(//)//*])|(?=[+-//(//)//*])");
		lexemas = new LinkedList<String>(Arrays.asList(tokens));
	}
	
	public void eliminarEspaciosInnecesarios(){
		lexemas = lexemas.stream().map(unToken -> unToken.trim()).filter(unToken -> !unToken.isEmpty())
				.collect(Collectors.toList());
	}
	
	public  Double parser(Empresa empresa){
		this.empresa = empresa;
		return seguirSiNoEstaVacio(0.0);
		
	}
	
	private Double analisisSintactico(Double valor){
		String token = lexemas.get(0);
		lexemas.remove(0);
		
		if( token.matches("([ ]*[a-zA-Z]+[ ]*)+"))
			return seguirSiNoEstaVacio(valorDe(token));
		if(token.matches("[ ]*[0-9]+[ ]*"))
			return seguirSiNoEstaVacio(Double.parseDouble(token));
		
		switch(token){
		case "+":
			return valor + seguirSiNoEstaVacio(0.0);
		case "-":
			return valor - seguirSiNoEstaVacio(0.0);
		case "*":
			return valor * seguirSiNoEstaVacio(0.0);
		case "/":
			return valor / seguirSiNoEstaVacio(0.0);
		case "(":
			return seguirSiNoEstaVacio(seguirSiNoEstaVacio(0.0));
		case ")":
			return valor;
		}
		
		return 0.0;
		
	}
	
	private Double seguirSiNoEstaVacio(Double valor){
		if(lexemas.isEmpty())
			return valor;
		else
			return analisisSintactico(valor);
	}
	
	private Double valorDe(String cuentaOIndicador){
		if(empresa.contieneLaCuenta(cuentaOIndicador))
			return empresa.getValorDeLaCuenta(cuentaOIndicador);
		else
			return RepositorioIndicadores.instance().getValorDelIndicador(empresa,cuentaOIndicador);
	}
}
