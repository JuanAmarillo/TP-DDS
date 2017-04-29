package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Empresa;
import ui.vm.ConsultarCuentasVM;

@SuppressWarnings("serial")
public class ConsultarCuentasWindow extends Dialog<ConsultarCuentasVM> {

	public ConsultarCuentasWindow(WindowOwner parent) {
		super(parent, new ConsultarCuentasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(false);
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");

		new Label(form).setText("Periodo");
		Selector<String> selectorPeriodo = new Selector<String>(form).allowNull(false);
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");
	}

	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Consultar").onClick(this::verDatosCuentas);
		new Button(actions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}

	protected void verDatosCuentas() {
		ViewUtils.nuevaPantalla(new DatosCuentaWindow(this, this.getModelObject().getEmpresaSeleccionada(),	this.getModelObject().getPeriodoSeleccionado()));
	}

}
