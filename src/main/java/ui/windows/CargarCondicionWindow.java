package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import ui.vm.CargarCondicionVM;

@SuppressWarnings("serial")
public class CargarCondicionWindow extends Dialog<CargarCondicionVM> {

	public CargarCondicionWindow(WindowOwner parent) {
		super(parent, new CargarCondicionVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel condicionesPanel = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		listaCondiciones(condicionesPanel);
		condicionPersonalizada(condicionesPanel);
	}

	public void listaCondiciones(Panel condicionesPanel) {
		Panel formLista = ViewUtils.crearPanel(condicionesPanel, new VerticalLayout(), "Condiciones cargadas");
		ViewUtils.crearLista(formLista, "condiciones", "condicionSeleccionada").setHeight(200).setWidth(300);
	}

	public void condicionPersonalizada(Panel condicionPanel) {
		Panel form = ViewUtils.crearPanel(condicionPanel, new ColumnLayout(1), "Agregar una nueva condición");
		escribirNombreDeCondicion(form);
		elegirTipoDeCondicion(form);
		crearCondicion(form);
		accionesPosibles(form);
	}

	private void accionesPosibles(Panel form) {
		Panel miniBis = ViewUtils.crearPanel(form, new HorizontalLayout());
		ViewUtils.crearBoton(miniBis, "Cargar condición", this::cargarCondicion);
		ViewUtils.crearBoton(miniBis, "Eliminar condición", this::eliminarCondicion);
	}

	private void crearCondicion(Panel form) {
		Panel condicion = ViewUtils.crearPanel(form, new HorizontalLayout());
		ViewUtils.crearSelector(condicion, "indicadores", "indicadorSeleccionado");
		ViewUtils.crearSelectorConAdaptador(condicion, "operaciones", "operacionSeleccionada", OperadorCondicion.class,
				"nombre").setWidth(100);
		campoNumericoParaTaxativo(condicion);
	}

	private void campoNumericoParaTaxativo(Panel condicion) {
		NumericField num = new NumericField(condicion);
		num.setWidth(50).bindValueToProperty("valor");
		num.bindEnabledToProperty("taxativa");
		num.bindVisibleToProperty("taxativa");
	}

	private void elegirTipoDeCondicion(Panel form) {
		RadioSelector<String> radioS = new RadioSelector<String>(form);
		radioS.bindItemsToProperty("tipos");
		radioS.bindValueToProperty("tipoSeleccionado");
		radioS.allowNull(false);
		/*
		 * ViewUtils.crearCheckBoxEnNuevoPanel(form, "taxativa",
		 * "condición taxativa" ); ViewUtils.crearCheckBoxEnNuevoPanel(form,
		 * "comparativa", "condición comparativa");
		 */
	}

	private void escribirNombreDeCondicion(Panel form) {
		Panel nombreCondicion = ViewUtils.crearPanel(form, new VerticalLayout(), "Nombre de la condición: ");
		new TextBox(nombreCondicion).bindValueToProperty("nombreCondicion");
	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
	}

	public void cargarCondicion() {
		try {
			this.getModelObject().cargarCondicion();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

	public void eliminarCondicion() {
		try {
			this.getModelObject().eliminarCondicion();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}
}
