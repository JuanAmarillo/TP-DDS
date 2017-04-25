package ui.vm;

import java.util.List;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;

@Observable
public class ConsultarCuentasVM {

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private LocalDate periodo;

	public ConsultarCuentasVM() {
		super();
		//this.empresas = cargarEmpresasDelArchivo
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public LocalDate getPeriodo() {
		return periodo;
	}

	public void setPeriodo(LocalDate periodo) {
		this.periodo = periodo;
	}
}
