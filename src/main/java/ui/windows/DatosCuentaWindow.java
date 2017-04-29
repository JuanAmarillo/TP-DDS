package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Cuenta;
import domain.Empresa;
import ui.vm.DatosCuentaVM;

@SuppressWarnings("serial")
public class DatosCuentaWindow extends Dialog<DatosCuentaVM> {

	public DatosCuentaWindow(WindowOwner owner, Empresa empresa, String string) {
		super(owner, new DatosCuentaVM(empresa, string));
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new HorizontalLayout());

		Table<Cuenta> cuentas = new Table<Cuenta>(form, Cuenta.class);
		cuentas.setWidth(300);
		cuentas.bindItemsToProperty("cuentas");
		cuentas.bindValueToProperty("cuentaSeleccionada");

		new Column<Cuenta>(cuentas).setTitle("Nombre de la cuenta").setFixedSize(150).bindContentsToProperty("nombre");
		new Column<Cuenta>(cuentas).setTitle("Balance").setFixedSize(150).bindContentsToProperty("balance");
	}
}
