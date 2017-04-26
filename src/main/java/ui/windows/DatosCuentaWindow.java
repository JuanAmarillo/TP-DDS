package ui.windows;

import org.joda.time.DateTime;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Empresa;
import ui.vm.DatosCuentaVM;

public class DatosCuentaWindow extends Dialog<DatosCuentaVM>{

	public DatosCuentaWindow(WindowOwner owner, Empresa empresaSeleccionada, DateTime periodoSeleccionado){
		super(owner, new DatosCuentaVM(empresaSeleccionada,periodoSeleccionado));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		
	}

}
