package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.Dialog;
import ui.vm.CargarCuentaVM;

public class CargarCuentaWindow extends Dialog<CargarCuentaVM> {

	public CargarCuentaWindow(WindowOwner parent) {
		super(parent, new CargarCuentaVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));
		this.setTitle("Cargar cuenta");

		new Label(form).setText("Ingrese nombre de cuenta");
		new TextBox(form).setWidth(150).bindValueToProperty("nombreCuenta");

		new Label(form).setText("Seleccione archivo");
		new FileSelector(form).setCaption("Examinar").bindValueToProperty("filePath");

		new Label(form).bindValueToProperty("filePath");

	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new HorizontalLayout());
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
		new Button(panelActions).setCaption("Cargar").onClick(this::cargarCuenta);
	}

	public void cargarCuenta() {

	}

}
