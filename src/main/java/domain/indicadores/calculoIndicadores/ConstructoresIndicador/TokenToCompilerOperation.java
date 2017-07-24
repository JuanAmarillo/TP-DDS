package domain.indicadores.calculoIndicadores.ConstructoresIndicador;

import domain.indicadores.calculoIndicadores.*;
import domain.repositorios.RepositorioIndicadores;

public enum TokenToCompilerOperation{
	
	NUMERO(){
		@Override
		public boolean matches(String token){
			return token.matches("[0-9]+([.][0-9]+)?");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.ingresarTerminal(new Numero(Double.parseDouble(token)));
		}
	},
	
	SUMA(){

		@Override
		public boolean matches(String token) {
			return token.matches("[+]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.ingresarOperador(new Suma());
		}
		
	},
	
	RESTA(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("[-]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.ingresarOperador(new Resta());
		}
	},
	
	MULTIPLICACION(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("[*]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.ingresarOperador(new Multiplicacion());
		}
	},
	
	DIVISION(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("[/]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.ingresarOperador(new Division());
		}
	},
	
	PARENTESISIZQUIERDO(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("[(]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.parentesisIzquierdo();
		}
	},
	
	PARENTESISDERECHO(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("[)]");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.parentesisDerecho();
		}
	},
	
	INDICADOR(){

		@Override
		public boolean matches(String token) {
			return RepositorioIndicadores.instance().contieneElIndicador(token);
		}

		@Override
		public void createOperation(String token, Compilador compilador) {
			compilador.ingresarTerminal(new IndicadorCalculo(token));
			
		}
	},
	
	CUENTA(){
		
		@Override
		public boolean matches(String token) {
			return token.matches("([0-9 ]*[a-zA-Z]+[0-9 ]*)+");
		}

		@Override
		public void createOperation(String token, Compilador compilador) {
			compilador.ingresarTerminal(new CuentaCalculo(token));
		}
	};
	
	;
	public abstract boolean matches(String token);
	public abstract void createOperation(String token,Compilador compilador);
}
