package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
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
		condicionesPanel.setLayout(new ColumnLayout(1));
		
		this.listaCondiciones(condicionesPanel);
		this.condicionPersonalizada(condicionesPanel);
	}

	public void listaCondiciones(Panel condicionesPanel) {
		new Label(condicionesPanel).setText("Condiciones cargadas");
		ViewUtils.crearLista(condicionesPanel, "condiciones", "condicionSeleccionada").setHeight(200)
			.setWidth(300);
	}

	public void condicionPersonalizada(Panel condicionPanel) {
		Panel form = new Panel(condicionPanel);
		form.setLayout(new ColumnLayout(1));
		
		Label titulo = new Label(form);
		titulo.setText("Agregar una nueva condicion");
		
		Panel nombreCondicion = new Panel(form);
		Label etiquetaNombre = new Label(nombreCondicion);
		etiquetaNombre.setText("Nombre de la condicion: ");
		TextBox textbox = new TextBox(nombreCondicion);
		textbox.bindValueToProperty("nombreCondicion");
		Panel tax = new Panel(form).setLayout(new HorizontalLayout());
		CheckBox taxativa = new CheckBox(tax);
		taxativa.bindValueToProperty("taxativa");
		Label etiquetaTaxativa = new Label(tax);
		etiquetaTaxativa.setText("Condicion taxativa");
		
		Panel comp = new Panel(form).setLayout(new HorizontalLayout());
		CheckBox comparativa = new CheckBox(comp);
		comparativa.bindValueToProperty("comparativa");
		Label etiquetaComparativa = new Label(comp);
		etiquetaComparativa.setText("Condicion comparativa");
		
		Panel mini = new Panel(form);
		mini.setLayout(new HorizontalLayout());
		ViewUtils.crearSelector(mini, "indicadores","indicadorSeleccionado");
		ViewUtils.crearSelector(mini, "operaciones", "operacionSeleccionada");

		NumericField num = new NumericField(mini);
		num.setWidth(50).bindValueToProperty("valor");
		num.bindEnabledToProperty("taxativa");
		num.bindVisibleToProperty("taxativa");

		Panel miniBis = new Panel(form);
		miniBis.setLayout(new HorizontalLayout());

		ViewUtils.crearBoton(miniBis, "Cargar condicion", this::cargarCondicion);
		ViewUtils.crearBoton(miniBis, "Eliminar condicion", this::eliminarCondicion);


	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
	}
	
	public void cargarCondicion() {
		try {
			this.getModelObject().cargarCondicion();
		}
		catch(RuntimeException e) { this.showWarning(e.getMessage()); }
	}
	
	public void eliminarCondicion() {
		try {
			this.getModelObject().eliminarCondicion();
		}
		catch(RuntimeException e) {this.showWarning(e.getMessage()); }
	}
}
