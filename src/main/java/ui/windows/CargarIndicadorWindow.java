package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Indicador;
import ui.vm.CargarIndicadorVM;

@SuppressWarnings("serial")
public class CargarIndicadorWindow extends Dialog<CargarIndicadorVM> {

	public CargarIndicadorWindow(WindowOwner parent) {
		super(parent, new CargarIndicadorVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel indicadoresPanel = new Panel(formPanel);
		indicadoresPanel.setLayout(new HorizontalLayout());

		this.listaIndicadores(indicadoresPanel);
		this.indicadorPersonalizado(indicadoresPanel);

	}

	public void listaIndicadores(Panel indicadoresPanel) {
		Panel form = new Panel(indicadoresPanel);
		new Label(form).setText("Indicadores Cargados");
		List<Indicador> indicadores = new List<Indicador>(form);
		indicadores.setWidth(150);
		indicadores.setHeight(100);
		indicadores.bindItemsToProperty("indicadores");
		indicadores.bindValueToProperty("indicadorSeleccionado");
	}

	public void indicadorPersonalizado(Panel indicadoresPanel) {
		Panel form = new Panel(indicadoresPanel);
		form.setLayout(new ColumnLayout(1));

		new Label(form).setText("Indicador personalizado");
		new TextBox(form).setWidth(250).bindValueToProperty("indicador");
		new Button(form).setCaption("Cargar indicador").onClick(this::cargarIndicador);
		new Button(form).setCaption("Eliminar indicador").onClick(this::eliminarIndicador);
	}

	@Override
	protected void addActions(Panel panelActions) {
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}

	public void eliminarIndicador() {
		try {
			this.getModelObject().eliminarIndicador();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}

	}

	public void cargarIndicador() {
		try {
			this.getModelObject().cargarIndicador();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}
}