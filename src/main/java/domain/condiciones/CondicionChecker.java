package domain.condiciones;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import domain.condiciones.condicionesPredeterminadas.TEmpresaMas10Años;

public class CondicionChecker {
	
	HashMap<String, Condicion> nombreCondiciones = new HashMap<String, Condicion>();
	
	List<Condicion> condiciones;
	
	public CondicionChecker(List<Condicion> condicionesObtenidasDeBdd){
		this.condiciones = condicionesObtenidasDeBdd;
		setHashMap();
	}
	
	private void setHashMap() {
		// TODO Auto-generated method stub
		
	}

	public List<Condicion> checkCondiciones() {
		return condiciones.stream().map(condicion -> checkearCondicion(condicion)).collect(Collectors.toList());
	}
	
	public Condicion checkearCondicion(Condicion condicion) {
		return new TEmpresaMas10Años();
	}
}
