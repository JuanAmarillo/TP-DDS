package calculoIndicadores.ConstructoresIndicador;

import calculoIndicadores.*;
import domain.repositorios.RepositorioIndicadores;

public enum TokenToOperation{
	
	NUMERO(){
		@Override
		public boolean matches(String token){
			return token.matches("[0-9]+([.][0-9]+)?");
		}
		
		@Override
		public void createOperation(String token,Compilador compilador){
			compilador.numero(token);
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
			compilador.indicador(token);
			
		}
	},
	
	CUENTA(){
		
		@Override
		public boolean matches(String token) {
			return !RepositorioIndicadores.instance().contieneElIndicador(token);
		}

		@Override
		public void createOperation(String token, Compilador compilador) {
			compilador.cuenta(token);
		}
	};
	
	;
	public abstract boolean matches(String token);
	public abstract void createOperation(String token,Compilador compilador);
}
