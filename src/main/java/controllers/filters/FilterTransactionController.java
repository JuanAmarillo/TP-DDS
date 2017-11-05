package controllers.filters;
import persistencia.Transaction;
import spark.Request;
import spark.Response;

public class FilterTransactionController {
	
	public static void before(Request req, Response res){
		if(noEsUnGet(req)) 
			Transaction.instance().crear();
	}

	
	public static void after(Request req, Response res){
		if(noEsUnGet(req)) 
			Transaction.instance().cerrar();
	}
	
	private static boolean noEsUnGet(Request req) {
		return !req.requestMethod().equals("GET");
	}
}
