package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Cuenta;
import domain.Empresa;
import domain.indicadores.IndicadorCalculado;
import ui.vm.CuentasConIndicadoresVM;

@SuppressWarnings("serial")
public class CuentasConIndicadoresWindow extends Dialog<CuentasConIndicadoresVM> {

	public CuentasConIndicadoresWindow(WindowOwner parent) {
		super(parent, new CuentasConIndicadoresVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.TablaCuentas(formPanel);
		this.TablaIndicadores(formPanel);
		this.selectorEmpresasPeriodos(formPanel);

	}

	protected void TablaCuentas(Panel formPanel) {
		Panel formTabla = new Panel(formPanel);

		Table<Cuenta> cuentas = new Table<Cuenta>(formTabla, Cuenta.class);
		cuentas.bindItemsToProperty("cuentas");
		cuentas.bindValueToProperty("cuentaSeleccionada");
		cuentas.setWidth(250);

		new Column<Cuenta>(cuentas).setTitle("Cuenta").setFixedSize(250).bindContentsToProperty("nombre");
		new Column<Cuenta>(cuentas).setTitle("Balance").setFixedSize(250).bindContentsToProperty("balance");
	}

	protected void TablaIndicadores(Panel formPanel) {
		Panel formTabla = new Panel(formPanel);

		Table<IndicadorCalculado> calculadores = new Table<IndicadorCalculado>(formTabla, IndicadorCalculado.class);
		calculadores.bindItemsToProperty("calculadores");
		calculadores.bindValueToProperty("calculadorSeleccionado");

		new Column<IndicadorCalculado>(calculadores).setTitle("Indicador").setFixedSize(250)
				.bindContentsToProperty("nombre");
		new Column<IndicadorCalculado>(calculadores).setTitle("Valor").setFixedSize(250)
				.bindContentsToProperty("valorString");
	}

	protected void selectorEmpresasPeriodos(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));
		// ViewUtils.crearPanel(formPanel, new ColumnLayout(2));

		new Label(form).setText("Empresa");
		ViewUtils.crearSelectorConAdaptador(form, "empresas", "empresaSeleccionada", Empresa.class, "nombre");

		new Label(form).setText("Período");
		ViewUtils.crearSelector(form, "periodos", "periodoSeleccionado");

	}

	protected void addActions(Panel actions) {
		ViewUtils.crearBoton(actions, "Volver", this::accept);
	}

}
