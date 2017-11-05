package controllers.filters;

import org.junit.Test;

import persistencia.Transaction;
import spark.Request;
import spark.Response;

public class FilterTransactionController {
	
	public static void before(Request req, Response res){
		ejecutarSiNoEsUnGet(req, crearTransaccion());
	}
	public static void after(Request req, Response res){
		ejecutarSiNoEsUnGet(req, cerrarTransaccion());
	}
	
	private static void ejecutarSiNoEsUnGet(Request req,Runnable runnable){
		if(noEsUnGet(req)) 
			runnable.run();
	}
	
	public static Runnable crearTransaccion() {
		return ()->{Transaction.instance().crear();};
	}
	
	
	public static Runnable cerrarTransaccion() {
		return ()->{Transaction.instance().cerrar();};
	}
	
	private static boolean noEsUnGet(Request req) {
		return !req.requestMethod().equals("GET");
	}
}
