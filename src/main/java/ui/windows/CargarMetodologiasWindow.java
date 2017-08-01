package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

import domain.condiciones.Condicion;
import domain.condiciones.CondicionComparativa;
import domain.condiciones.CondicionTaxativa;
import ui.vm.CargarMetodologiasVM;

public class CargarMetodologiasWindow extends Dialog<CargarMetodologiasVM> {

	public CargarMetodologiasWindow(WindowOwner parent) {
		super(parent, new CargarMetodologiasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel panelCreacion = ViewUtils.crearPanel(formPanel, new ColumnLayout(1), "Creación de nuevas metodologias");
		nombreMetodologiaACrear(panelCreacion);
		listasDeCondiciones(panelCreacion);
		pesoDeLaComparativa(panelCreacion);
	}

	public void listasDeCondiciones(Panel panelCreacion) {
		Panel panelCondiciones = ViewUtils.crearPanel(panelCreacion, new HorizontalLayout());
		listasDeCondicionesDelSistema(panelCondiciones);
		listaDeCondicionesAgregadas(panelCondiciones);
	}

	public void listasDeCondicionesDelSistema(Panel panelCondiciones) {
		Panel panelCondicionesDelSistema = ViewUtils.crearPanel(panelCondiciones, new VerticalLayout());
		seleccionDeCondicionesTaxativas(panelCondicionesDelSistema);
		seleccionDeCondicionesComparativas(panelCondicionesDelSistema);
	}

	/*
	 * seleccionDeCondicionesComparativas y seleccionDeCondicionesTaxativas
	 * deberian ser uno solo para eso hay que tener los factory
	 */
	
	private void seleccionDeCondicionesComparativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		listaDeLaMetodologia(panelCondiciones, "Condiciones comparativas", "condicionesComparativas",
				"condicionComparativaSeleccionada", 167, CondicionComparativa.class);
		botones(panelCondiciones, this::moverHaciaLaDerechaC, this::moverHaciaLaIzquierdaC);
	}

	private void seleccionDeCondicionesTaxativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		listaDeLaMetodologia(panelCondiciones, "Condiciones taxativas", "condicionesTaxativas",
				"condicionTaxativaSeleccionada", 167, CondicionTaxativa.class);
		botones(panelCondiciones, this::moverHaciaLaDerechaT, this::moverHaciaLaIzquierdaT);
	}

	public void listaDeCondicionesAgregadas(Panel formPanel) {
		listaDeLaMetodologia(formPanel, "Condiciones de la metodologia", "condicionesAgregadas",
				"condicionAgregadaSeleccionada", 400, Condicion.class);
	}

	private void nombreMetodologiaACrear(Panel formPanel) {
		new Label(formPanel).setText("Nombre");
		new TextBox(formPanel).setWidth(250).bindValueToProperty("nombreMetodologia");
	}

	private void pesoDeLaComparativa(Panel formPanel) {
		Panel peso = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		new Label(peso).setText("Peso de la condicion comparativa");
		new NumericField(peso).setWidth(80).bindValueToProperty("pesoDeComparativa");
	}

	private void listaDeLaMetodologia(Panel formPanel, String titulo, String lista, String elemento, int height,
			Class<?> clase) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1), titulo);
		List<?> condiciones = ViewUtils.crearListaConAdaptador(panel, lista, elemento, clase, "nombre");
		ViewUtils.setSize(250, height, condiciones);

	}

	private void botones(Panel formPanel, Action metodoHaciaDerecha, Action metodoHaciaIzquierda) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		rellenarConVacio(botones);
		ViewUtils.crearBoton(botones, ">", metodoHaciaDerecha);
		ViewUtils.crearBoton(botones, "<", metodoHaciaIzquierda);
	}

	public void rellenarConVacio(Panel botones) {
		new Label(botones);
		new Label(botones);
	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept).setAsDefault();
		ViewUtils.crearBoton(panelActions, "Cargar Metodologia", this::cargarMetodologia);
	}

	private void moverHaciaLaDerechaT() {
		this.getModelObject().moverHaciaLaDerechaTaxativa();

	}

	private void moverHaciaLaIzquierdaT() {
		this.getModelObject().moverHaciaLaIzquierdaTaxativa();
	}

	private void moverHaciaLaDerechaC() {
		try {
			this.getModelObject().moverHaciaLaDerechaComparativa();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

	private void moverHaciaLaIzquierdaC() {
		this.getModelObject().moverHaciaLaIzquierdaComparativa();
	}

	private void cargarMetodologia() {
		try {
			this.getModelObject().cargarMetodologia();
			this.showInfo("La metodología se cargó correctamente");
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

}
