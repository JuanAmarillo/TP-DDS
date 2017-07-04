package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.condiciones.Condicion;
import domain.indicadores.Indicador;
import ui.vm.CargarCondicionVM;

@SuppressWarnings("serial")
public class CargarCondicionWindow extends Dialog<CargarCondicionVM> {

	public CargarCondicionWindow(WindowOwner parent) {
		super(parent, new CargarCondicionVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel condicionesPanel = new Panel(formPanel);
		condicionesPanel.setLayout(new HorizontalLayout());

		this.listaCondiciones(condicionesPanel);
		this.condicionPersonalizada(condicionesPanel);
	}

	public void listaCondiciones(Panel condicionesPanel) {
		Panel form = new Panel(condicionesPanel);
		new Label(form).setText("Condiciones cargadas");
		List<Condicion> condiciones = new List<Condicion>(form);
		condiciones.setWidth(150);
		condiciones.setHeight(100);
		condiciones.bindItemsToProperty("condiciones");
		condiciones.bindValueToProperty("condicionSeleccionada");
	}

	public void condicionPersonalizada(Panel condicionPanel) {
		Panel form = new Panel(condicionPanel);
		form.setLayout(new ColumnLayout(1));
		
		new Label(form).setText("Condición personalizada");
		Selector<String> selectorTipo = new Selector<String>(form).allowNull(false);
		selectorTipo.bindItemsToProperty("tipos");
		selectorTipo.bindValueToProperty("tipoSeleccionado");

		Panel mini = new Panel(form);
		mini.setLayout(new HorizontalLayout());
		
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(mini).allowNull(false);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new NumericField(mini).setWidth(50);
		
		Panel miniBis = new Panel(form);
		miniBis.setLayout(new HorizontalLayout());
		
		new Button(miniBis).setCaption("Cargar condición");// .onClick(this::cargarCondicion);
		new Button(miniBis).setCaption("Eliminar condición");// .onClick(this::eliminarCondicion);
	}
}
