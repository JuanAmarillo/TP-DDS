package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargarIndicadorVM;

public class CargarIndicadorWindow extends Dialog<CargarIndicadorVM> {
	public CargarIndicadorWindow(WindowOwner parent) {
		super(parent, new CargarIndicadorVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));
		this.setTitle("Cargar indicador");

		new Label(form).setText("Cargar indicador personalizado");
		new TextBox(form).setWidth(250).bindValueToProperty("indicador");
	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new HorizontalLayout());
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
		//new Button(panelActions).setCaption("Cargar").onClick(this::null);
	}

}