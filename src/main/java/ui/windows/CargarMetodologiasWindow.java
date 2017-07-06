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
		Panel taxativas = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondicionesTaxativas(taxativas);
		this.botonesTaxativas(taxativas);
		this.listaTaxativasDeLaMetodologia(taxativas);
		Panel comparativas = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaCondicionesComparativas(comparativas);
		this.botonesComparativas(comparativas);
		this.listaComparativasDeLaMetodologia(comparativas);
	}

	private void listaTaxativasDeLaMetodologia(Panel formPanel) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Condiciones taxativas de la metodologia");
		List<String> condiciones = ViewUtils.crearLista(panel, "taxativasMetodologia", "taxativaMetodologiaSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);

	}

	private void botonesTaxativas(Panel formPanel) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		ViewUtils.crearBoton(botones, ">"  , this::moverHaciaLaDerecha);
		ViewUtils.crearBoton(botones, "<"  , this::moverHaciaLaIzquierda);
		
	}

	public void listaCondicionesTaxativas(Panel metodologiasPanel) {
		Panel panel = ViewUtils.crearPanel(metodologiasPanel, new ColumnLayout(1),"Condiciones taxativas");
		List<String> condiciones = ViewUtils.crearLista(panel, "condicionesTaxativas", "taxativaSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);

	}
	
	private void listaComparativasDeLaMetodologia(Panel formPanel) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Condiciones comparativas de la metodologia");
		List<String> condiciones = ViewUtils.crearLista(panel, "comparativasMetodologia", "comparativaMetodologiaSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);

	}

	private void botonesComparativas(Panel formPanel) {
		Panel botones = ViewUtils.crearPanel(formPanel, new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		ViewUtils.crearBoton(botones, ">"  , this::moverHaciaLaDerecha);
		ViewUtils.crearBoton(botones, "<"  , this::moverHaciaLaIzquierda);
		
	}

	public void listaCondicionesComparativas(Panel formPanel) {
		Panel panel = ViewUtils.crearPanel(formPanel, new ColumnLayout(1),"Condiciones comparativas");
		List<String> condiciones = ViewUtils.crearLista(panel, "condicionesComparativas", "comparativaSeleccionada");
		ViewUtils.setSize(250, 100, condiciones);		
	}

	@Override
	protected void addActions(Panel panelActions) {
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}
	
	private void moverHaciaLaDerecha() {
		
	}
	
	private void moverHaciaLaIzquierda() {
		
	}

}
