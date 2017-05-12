package externos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseadorDeIndicadores {

	String ecuacionAParsear;
	
	public List<String> obtenerNombresDeCuentas(String ecuacion) {
		String token;
		ecuacionAParsear = ecuacion;
		List<String> cuentas = new ArrayList<>();
		token = obtenerSiguiente();
		while( !token.isEmpty()) {
			cuentas.add(token);
			token = obtenerSiguiente();
		}
		return cuentas;
	}	

	public String obtenerNombreIndicador(String ecuacion) {
		return ecuacion.split("=")[0].trim();
	}
	
	public String obtenerSiguiente() {
		int inicioToken = ecuacionAParsear.indexOf("'");
		int finToken;
		if (inicioToken != -1) {
			ecuacionAParsear = ecuacionAParsear.substring(inicioToken+1);
			finToken =  ecuacionAParsear.indexOf("'");
			String token = ecuacionAParsear.substring(0, finToken);
			ecuacionAParsear = ecuacionAParsear.substring(finToken+1);
			return token;
		}
		else
			return "";
	}
}
