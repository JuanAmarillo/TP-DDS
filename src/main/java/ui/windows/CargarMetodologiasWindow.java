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

import ui.vm.CargarMetodologiasVM;

public class CargarMetodologiasWindow extends Dialog<CargarMetodologiasVM> {

	public CargarMetodologiasWindow(WindowOwner parent) {
		super(parent, new CargarMetodologiasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel panelCreacion = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Creación de nuevas metodologias");
		nombreMetodologiaACrear(panelCreacion);		
		seleccionDeCondicionesTaxativas(panelCreacion);
		seleccionDeCondicionesComparativas(panelCreacion);
	}

	private void seleccionDeCondicionesComparativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondiciones(panelCondiciones, "Condiciones Comparativas", "listaCondicionesComparativas", "condicionComparativaSeleccionada");
		this.botones(panelCondiciones, this::moverHaciaLaDerechaC, this::moverHaciaLaIzquierdaC);
		this.listaDeLaMetodologia(panelCondiciones, "condicionesComparativasAAgregar", "condicionComparativaAAgregarSeleccionada");
	}

	private void seleccionDeCondicionesTaxativas(Panel formPanel) {
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondiciones(panelCondiciones, "Condiciones taxativas", "listaCondicionesTaxativas", "condicionTaxativaSeleccionada");
		this.botones(panelCondiciones, this::moverHaciaLaDerechaT, this::moverHaciaLaIzquierdaT);
		this.listaDeLaMetodologia(panelCondiciones, "condicionesTaxativasAAgregar", "condicionTaxativaAAgregarSeleccionada");
	}


	private void nombreMetodologiaACrear(Panel formPanel) {
		new Label(formPanel).setText("Nombre");
		new TextBox(formPanel).setWidth(250).bindValueToProperty("nombreMetodologia");
	}

	private void listaDeLaMetodologia(Panel formPanel, String lista, String elemento) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Condiciones taxativas de la metodologia");
		List<String> condiciones = ViewUtils.crearLista(panel, lista, elemento);
		ViewUtils.setSize(250, 100, condiciones);

	}

	private void botones(Panel formPanel, Action metodoHaciaDerecha, Action metodoHaciaIzquierda) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		ViewUtils.crearBoton(botones, ">"  , metodoHaciaDerecha);
		ViewUtils.crearBoton(botones, "<"  , metodoHaciaIzquierda);
		
	}

	public void listaCondiciones(Panel metodologiasPanel, String titulo, String lista, String elemento) {
		Panel panel = ViewUtils.crearPanel(metodologiasPanel, new ColumnLayout(1),titulo);
		List<String> condiciones = ViewUtils.crearLista(panel, lista, elemento);
		ViewUtils.setSize(250, 100, condiciones);

	}
	
	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept).setAsDefault();
		ViewUtils.crearBoton(panelActions, "Cargar Metodologia", this::cargarMetodologia );
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
			this.showInfo("La metodologia se cargo correctamente");
		}
		catch(RuntimeException e ) {this.showWarning(e.getMessage()); }
	}
	
}


