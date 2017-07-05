package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
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
		Panel form = ViewUtils.crearPanel(condicionPanel, new ColumnLayout(1));
		new Label(form).setText("Agregar una nueva condicion");
		
		escribirNombreDeCondicion(form);
		elegirTipoDeCondicion(form);
		crearCondicion(form);
		accionesPosibles(form);
	}

	private void accionesPosibles(Panel form) {
		Panel miniBis = ViewUtils.crearPanel(form, new HorizontalLayout());
		ViewUtils.crearBoton(miniBis, "Cargar condicion", this::cargarCondicion);
		ViewUtils.crearBoton(miniBis, "Eliminar condicion", this::eliminarCondicion);
	}

	private void crearCondicion(Panel form) {
		Panel condicion = ViewUtils.crearPanel(form, new HorizontalLayout());
		ViewUtils.crearSelector(condicion, "indicadores", "indicadorSeleccionado");
		ViewUtils.crearSelector(condicion, "operaciones", "operacionSeleccionada");
		campoNumericoParaTaxativo(condicion);
	}

	private void campoNumericoParaTaxativo(Panel condicion) {
		NumericField num = new NumericField(condicion);
		num.setWidth(50).bindValueToProperty("valor");
		num.bindEnabledToProperty("taxativa");
		num.bindVisibleToProperty("taxativa");
	}

	private void elegirTipoDeCondicion(Panel form) {
		ViewUtils.crearCheckBoxEnNuevoPanel(form, "taxativa",    "Condicion taxativa"   );
		ViewUtils.crearCheckBoxEnNuevoPanel(form, "comparativa", "Condicion comparativa");
	}


	private void escribirNombreDeCondicion(Panel form) {
		Panel nombreCondicion = new Panel(form);
		new Label(nombreCondicion).setText("Nombre de la condicion: ");
		new TextBox(nombreCondicion).bindValueToProperty("nombreCondicion");
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
