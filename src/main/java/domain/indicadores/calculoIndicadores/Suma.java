package domain.indicadores.calculoIndicadores;

import domain.Empresa;

//@Token
public class Suma extends Operador{
	
	public Suma() {
		super();
		this.prioridad = 3;
	}
	
	public Double calcularValor(Empresa empresa, String periodo){
		return operandoUno.calcularValor(empresa,periodo) + operandoDos.calcularValor(empresa,periodo);
	}
	
	
	
//	@NoTerminal
//	public boolean esElTokenBuscado(String token){
//		return token.matches("[+]");
//	}
}
