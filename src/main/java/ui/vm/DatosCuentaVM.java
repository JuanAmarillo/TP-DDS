package ui.vm;

import org.joda.time.DateTime;
import org.uqbar.commons.utils.Observable;

import domain.Empresa;

@Observable
public class DatosCuentaVM extends ListadoDatosCuentasVM<Empresa, DateTime>{

	public DatosCuentaVM(Empresa empresaSeleccionada, DateTime periodoSeleccionado) {
		super();
	}

}
