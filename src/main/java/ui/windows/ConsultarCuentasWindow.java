package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Cuenta;
import ui.windows.CalculadorDeIndicador;
import domain.Empresa;
import ui.vm.ConsultarCuentasVM;

@SuppressWarnings("serial")
public class ConsultarCuentasWindow extends Dialog<ConsultarCuentasVM> {

	public ConsultarCuentasWindow(WindowOwner parent) {
		super(parent, new ConsultarCuentasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.TablaCuentas(formPanel);
		this.TablaIndicadores(formPanel);
		this.selectorEmpresasPeriodos(formPanel);

	}

	protected void TablaCuentas(Panel formPanel) {
		Panel formTabla = new Panel(formPanel);
		formTabla.setLayout(new HorizontalLayout());

		Table<Cuenta> cuentas = new Table<Cuenta>(formTabla, Cuenta.class);
		cuentas.bindItemsToProperty("cuentas");
		cuentas.bindValueToProperty("cuentaSeleccionada");

		new Column<Cuenta>(cuentas).setTitle("Cuenta").setFixedSize(150).bindContentsToProperty("nombre");
		new Column<Cuenta>(cuentas).setTitle("Balance").setFixedSize(150).bindContentsToProperty("balance");
	}
	
	protected void TablaIndicadores(Panel formPanel) {
		Panel formTabla = new Panel(formPanel);
		formTabla.setLayout(new HorizontalLayout());

		Table<CalculadorDeIndicador> calculadores = new Table<CalculadorDeIndicador>(formTabla, CalculadorDeIndicador.class);
		calculadores.bindItemsToProperty("calculadores");
		calculadores.bindValueToProperty("calculadorSeleccionado");

		new Column<CalculadorDeIndicador>(calculadores).setTitle("Indicador").setFixedSize(150).bindContentsToProperty("nombre");
		new Column<CalculadorDeIndicador>(calculadores).setTitle("Valor").setFixedSize(150).bindContentsToProperty("valor");
	}

	protected void selectorEmpresasPeriodos(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(false);
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");

		new Label(form).setText("Periodo");
		Selector<String> selectorPeriodo = new Selector<String>(form).allowNull(false);
		selectorPeriodo.setWidth(150);
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");

	}

	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}

}
