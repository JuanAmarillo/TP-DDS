package externos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import domain.Empresa;
import domain.Indicador;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ParseadorDeIndicadores {

	String ecuacionAParsear;
	
	public Indicador parsearEcuacion(String ecuacion) {
		Indicador resultado = new Indicador();
		resultado.setEcuacion(getEcuacion(ecuacion));
		resultado.setNombreIndicador(getNombreIndicador(ecuacion));
		return resultado;
	}
	
	public Map<String,Double> getValoresYCuentas(Set<String> cuentas, Empresa emp) {
		Map<String,Double> map = new HashMap<>();
		cuentas.stream().forEach(cuenta -> map.put(cuenta, emp.getValorDeLaCuenta(cuenta)));
		return map;
	}
	
	public Set<String> obtenerNombresDeCuentas(String ec) {
		String token;
		ecuacionAParsear = ec;
		Set<String> cuentas = new HashSet<>();
		token = obtenerSiguiente();
		while( !token.isEmpty()) {
			cuentas.add(token);
			token = obtenerSiguiente();
		}
		return cuentas;
	}	

	public String getNombreIndicador(String ec) {
		return ec.split("=")[0].trim();
	}
	
	public String getEcuacion(String ec) {
		return ec.split("=")[1].trim();
	}
	
	public String obtenerSiguiente() {
		int inicioToken = ecuacionAParsear.indexOf("_");
		int finToken;
		if (inicioToken != -1) {
			ecuacionAParsear = ecuacionAParsear.substring(inicioToken+1);
			finToken =  ecuacionAParsear.indexOf("_");
			String token = ecuacionAParsear.substring(0, finToken);
			ecuacionAParsear = ecuacionAParsear.substring(finToken+1);
			return token;
		}
		else
			return "";
	}
}
