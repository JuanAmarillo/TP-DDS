package ui.vm;

import java.util.List;
import java.util.Set;

import org.uqbar.commons.utils.Observable;

import domain.Cuenta;
import domain.Empresa;

@Observable
public class DatosCuentaVM {
	private Set<Cuenta> cuentas;
	private Cuenta cuentaSeleccionada;

	public DatosCuentaVM(Empresa empresa, String periodo) {
		this.cuentas = empresa.getCuentasSegun(periodo);
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

}
