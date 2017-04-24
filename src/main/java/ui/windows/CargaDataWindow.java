package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import ui.vm.CargaDataVM;

public class CargaDataWindow extends SimpleWindow<CargaDataVM> {

	public CargaDataWindow(WindowOwner parent) {
		super(parent, new CargaDataVM());
		this.setMinHeight(250);
	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new ColumnLayout(1));
		new Button(panelActions).setCaption("Cargar cuentas"); // onClick(this::cargarCuentas);
		new Button(panelActions).setCaption("Consultar valores de cuentas"); // onClick(this::verCuentas);

	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		// TODO Auto-generated method stub
		this.setTitle("");

	}
}
