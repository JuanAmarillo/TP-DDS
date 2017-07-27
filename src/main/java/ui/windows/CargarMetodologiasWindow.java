package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

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
		seleccionDeCondicionesTaxativas(panelCreacion);
		seleccionDeCondicionesComparativas(panelCreacion);
	}

	private void seleccionDeCondicionesComparativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondiciones(panelCondiciones, "Condiciones comparativas", "listaCondicionesComparativas",
				"condicionComparativaSeleccionada", CondicionComparativa.class);
		this.botones(panelCondiciones, this::moverHaciaLaDerechaC, this::moverHaciaLaIzquierdaC);
		this.listaDeLaMetodologia(panelCondiciones, "Condiciones comparativas de la metodologia", "condicionesComparativasAAgregar",
				"condicionComparativaAAgregarSeleccionada", CondicionComparativa.class);
	}

	private void seleccionDeCondicionesTaxativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondiciones(panelCondiciones, "Condiciones taxativas", "listaCondicionesTaxativas",
				"condicionTaxativaSeleccionada", CondicionTaxativa.class);
		this.botones(panelCondiciones, this::moverHaciaLaDerechaT, this::moverHaciaLaIzquierdaT);
		this.listaDeLaMetodologia(panelCondiciones, "Condiciones taxativas de la metodologia", "condicionesTaxativasAAgregar",
				"condicionTaxativaAAgregarSeleccionada", CondicionTaxativa.class);
	}

	private void nombreMetodologiaACrear(Panel formPanel) {
		new Label(formPanel).setText("Nombre");
		new TextBox(formPanel).setWidth(250).bindValueToProperty("nombreMetodologia");
	}

	private void listaDeLaMetodologia(Panel formPanel, String titulo, String lista, String elemento, Class<?> clase) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1), titulo);
		List<?> condiciones = ViewUtils.crearListaConAdaptador(panel, lista, elemento, clase, "nombre");
		ViewUtils.setSize(250, 100, condiciones);

	}

	public void listaCondiciones(Panel formPanel, String titulo, String lista, String elemento, Class<?> clase) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1), titulo);
		List<?> condiciones = ViewUtils.crearListaConAdaptador(panel, lista, elemento, clase, "nombre");
		ViewUtils.setSize(250, 100, condiciones);

	}

	private void botones(Panel formPanel, Action metodoHaciaDerecha, Action metodoHaciaIzquierda) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		@SuppressWarnings("unused")
		Label rellenaVacio = new Label(botones);
		@SuppressWarnings("unused")
		Label rellenaVacio1 = new Label(botones);
		ViewUtils.crearBoton(botones, ">", metodoHaciaDerecha);
		ViewUtils.crearBoton(botones, "<", metodoHaciaIzquierda);
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
		this.getModelObject().moverHaciaLaDerechaComparativa();
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
