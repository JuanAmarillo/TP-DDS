package ui.vm;

import java.util.List;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

import domain.Cuenta;
import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

@Observable
public class DatosCuentaVM {
	private List<Cuenta> cuentas;
	private Cuenta cuentaSeleccionada;
	
	public DatosCuentaVM(Empresa empresa, LocalDate periodo) {
		this.cuentas = RepositorioEmpresas.getCuentasSegun(empresa, periodo);
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}
	public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

}
