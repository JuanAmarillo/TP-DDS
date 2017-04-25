package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Empresa;
import ui.vm.ConsultarCuentasVM;

public class ConsultarCuentasWindow extends Dialog<ConsultarCuentasVM>{
	
	public ConsultarCuentasWindow(WindowOwner parent){
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
		new TextBox(form).setWidth(150).bindValueToProperty("periodo");
		
		
	}

}
