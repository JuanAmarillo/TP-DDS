package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import ui.vm.CargaDataVM;

public class CargaDataWindow extends SimpleWindow<CargaDataVM> {

	public CargaDataWindow(WindowOwner parent) {
		super(parent, new CargaDataVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Inversiones");
		new Label(formPanel).setText("Hector Inversiones").setFontSize(25);

	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new ColumnLayout(1));
		new Button(panelActions).setCaption("Cargar cuentas").onClick(this::cargarCuentas)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Consultar valores de cuentas").onClick(this::verCuentas)
				.setBackground(Color.lightGray).setWidth(350);
	}

	public void cargarCuentas() {
		ViewUtils.nuevaPantalla(new CargarCuentaWindow(this));
	}

	public void verCuentas() {
		ViewUtils.nuevaPantalla(new ConsultarCuentasWindow(this));
	}
}
