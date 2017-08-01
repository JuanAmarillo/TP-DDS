package ui.windows;

import java.util.ArrayList;

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
import ui.vm.cargarMetodologiaVM.CargarMetodologiasVM;

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
		botonesDeMovimiento(panelCondiciones);
		listaDeCondicionesAgregadas(panelCondiciones);
	}

	public void listasDeCondicionesDelSistema(Panel panelCondiciones) {
		Panel panelCondicionesDelSistema = ViewUtils.crearPanel(panelCondiciones, new VerticalLayout());
		seleccionDeCondicionesTaxativas(panelCondicionesDelSistema);
		seleccionDeCondicionesComparativas(panelCondicionesDelSistema);
	}

	private void botonesDeMovimiento(Panel panel) {
		Panel movimiento = ViewUtils.crearPanel(panel, new VerticalLayout());
		crearBotonMovimiento(movimiento, ">", this::moverHaciaLaDerechaTaxativa);
		crearBotonMovimiento(movimiento, "<", this::moverHaciaLaIzquierda);
		crearBotonMovimiento(movimiento, ">", this::moverHaciaLaDerechaComparativa);

	}

	public void crearBotonMovimiento(Panel movimiento, String nombreBoton, Action onClick) {
		rellenarConVacio(movimiento);
		ViewUtils.crearBoton(movimiento, nombreBoton, onClick);
	}
	
	private void seleccionDeCondicionesComparativas(Panel panel) {
		crearLista(panel, "Condiciones comparativas", "condicionesComparativas", "condicionComparativaSeleccionada",
				167);
	}

	private void seleccionDeCondicionesTaxativas(Panel panel) {
		crearLista(panel, "Condiciones taxativas", "condicionesTaxativas", "condicionTaxativaSeleccionada", 167);
	}

	public void listaDeCondicionesAgregadas(Panel form) {
		crearLista(form, "Condiciones de la metodologia", "condicionesAgregadas", "condicionAgregadaSeleccionada", 400);
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

	private void crearLista(Panel formPanel, String titulo, String lista, String elemento, Integer height) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1), titulo);
		List<?> condiciones = ViewUtils.crearListaConAdaptador(panel, lista, elemento, Condicion.class, "nombre");
		ViewUtils.setSize(250, height, condiciones);

	}

	public void rellenarConVacio(Panel botones) {
		new Label(botones);
		new Label(botones);
		new Label(botones);
		new Label(botones);
	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept).setAsDefault();
		ViewUtils.crearBoton(panelActions, "Cargar Metodologia", this::cargarMetodologia);
	}

	private void moverHaciaLaDerechaTaxativa() {
		try {
			this.getModelObject().moverHaciaLaDerechaTaxativa();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

	private void moverHaciaLaDerechaComparativa() {
		try {
			this.getModelObject().moverHaciaLaDerechaComparativa();
		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

	private void moverHaciaLaIzquierda() {
		this.getModelObject().sacarCondicion();
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
