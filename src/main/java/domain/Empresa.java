package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {
	private String nombre;
	private List<Cuenta> cuentas = new ArrayList<>();
	//private List<Indicador> indicadores = new ArrayList<>();
	
	public String getNombre(){
		return nombre;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

}
