package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.condiciones.CondicionTaxativa;
import ui.vm.CargarPesoVM;

public class CargarPesoWindow extends Dialog<CargarPesoVM> {

	public CargarPesoWindow(WindowOwner parent, CondicionTaxativa condicionTaxativa) {
		super(parent, new CargarPesoVM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Cargar peso de la condición");
		Panel form = ViewUtils.crearPanel(mainPanel, new VerticalLayout());

		new Label(form).setText("Indique un peso para la condición");
		NumericField valor = new NumericField(form);
		valor.bindValueToProperty("valor");
	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new HorizontalLayout());
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
		ViewUtils.crearBoton(panelActions, "Cargar", this::cargarPeso);
	}

	public void cargarPeso() {
		this.getModelObject().cargarPeso();
		this.showInfo("Se ha cargado con éxito");
		this.accept();
	}
}
