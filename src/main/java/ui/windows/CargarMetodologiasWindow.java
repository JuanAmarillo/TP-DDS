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

import domain.indicadores.IndicadorCustom;
import ui.vm.CargarMetodologiasVM;

public class CargarMetodologiasWindow extends Dialog<CargarMetodologiasVM> {

	public CargarMetodologiasWindow(WindowOwner parent) {
		super(parent, new CargarMetodologiasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		// TODO Auto-generated method stub
		formPanel.setLayout(new ColumnLayout(1));
		Label titulo = new Label(formPanel);
		titulo.setText("Creación de nuevas metodologias");
		new Label(formPanel).setText("Nombre");
		new TextBox(formPanel).setWidth(250).bindValueToProperty("nombreMetodologia");
		Panel panelCondiciones = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondiciones(panelCondiciones);
		this.botones(panelCondiciones);
		this.listaDeLaMetodologia(panelCondiciones);
	}

	private void listaDeLaMetodologia(Panel formPanel) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Condiciones taxativas de la metodologia");
		List<String> condiciones = ViewUtils.crearLista(panel, "condicionesAAgregar", "condicionAAgregarSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);

	}

	private void botones(Panel formPanel) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		ViewUtils.crearBoton(botones, ">"  , this::moverHaciaLaDerecha);
		ViewUtils.crearBoton(botones, "<"  , this::moverHaciaLaIzquierda);
		
	}

	public void listaCondiciones(Panel metodologiasPanel) {
		Panel panel = ViewUtils.crearPanel(metodologiasPanel, new ColumnLayout(1),"Condiciones taxativas");
		List<String> condiciones = ViewUtils.crearLista(panel, "listaCondiciones","condicionSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);

	}
	
	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept).setAsDefault();
		ViewUtils.crearBoton(panelActions, "Cargar Metodologia", this::cargarMetodologia );
	}
	
	private void moverHaciaLaDerecha() {
		this.getModelObject().moverHaciaLaDerecha();
	}
	
	private void moverHaciaLaIzquierda() {
		this.getModelObject().moverHaciaLaIzquierda();
	}
	
	private void cargarMetodologia() {
		try {
			this.getModelObject().cargarMetodologia();
			this.showInfo("La metodologia se cargo correctamente");
		}
		catch(RuntimeException e ) {this.showWarning(e.getMessage()); }
	}
	
}
