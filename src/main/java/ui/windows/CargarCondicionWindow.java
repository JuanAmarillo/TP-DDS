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

import domain.condiciones.BuilderCondicion;
import domain.condiciones.OperadoresCondicion.OperadorCondicion;
import ui.vm.CargarCondicionVM;

@SuppressWarnings("serial")
public class CargarCondicionWindow extends Dialog<CargarCondicionVM> {

	public CargarCondicionWindow(WindowOwner parent) {
		super(parent, new CargarCondicionVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel panelPrincipal = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		listarCondiciones(panelPrincipal);
		armarNuevaCondicion(panelPrincipal);
	}

	public void listarCondiciones(Panel panelPrincipal) {
		Panel condiciones = ViewUtils.crearPanel(panelPrincipal, new VerticalLayout(), "Condiciones cargadas");
		ViewUtils.crearLista(condiciones, "condiciones", "condicionSeleccionada").setHeight(200).setWidth(300);
	}

	public void armarNuevaCondicion(Panel panelPrincipal) {
		Panel nuevaCondicion = ViewUtils.crearPanel(panelPrincipal, new ColumnLayout(1), "Agregar una nueva condición");
		escribirNombreDeCondicion(nuevaCondicion);
		elegirTipoDeCondicion(nuevaCondicion);
		crearCondicion(nuevaCondicion);
		accionesPosibles(nuevaCondicion);
	}

	private void accionesPosibles(Panel condicion) {
		Panel botones = ViewUtils.crearPanel(condicion, new HorizontalLayout());
		ViewUtils.crearBoton(botones, "Cargar condición", this::cargarCondicion);
		ViewUtils.crearBoton(botones, "Eliminar condición", this::eliminarCondicion);
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
		num.setWidth(50).bindValueToProperty("valorCondicionTaxativa");
		num.bindVisibleToProperty("esTaxativa");
	}

	private void elegirTipoDeCondicion(Panel form) {
		RadioSelector<String> radioS = new RadioSelector<String>(form);
		radioS.bindItemsToProperty("builders").adaptWith(BuilderCondicion.class, "etiquetaBuilder");
		radioS.bindValueToProperty("builderSeleccionado");
		radioS.allowNull(false);
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
