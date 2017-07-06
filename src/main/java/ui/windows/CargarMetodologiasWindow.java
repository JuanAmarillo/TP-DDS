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
		Panel taxativas = new Panel(formPanel);
		taxativas.setLayout(new HorizontalLayout());
		this.listaCondicionesTaxativas(taxativas);
		this.botonesTaxativas(taxativas);
		this.listaTaxativasDeLaMetodologia(taxativas);
		Panel comparativas = new Panel(formPanel);
		comparativas.setLayout(new HorizontalLayout());
		this.listaCondicionesComparativas(comparativas);
		this.botonesComparativas(comparativas);
		this.listaComparativasDeLaMetodologia(comparativas);
	}

	private void listaTaxativasDeLaMetodologia(Panel formPanel) {
		Panel panel = new Panel(formPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones taxativas de la metodologia");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("taxativasMetodologia");
		condiciones.bindValueToProperty("taxativaMetodologia");
	}

	private void botonesTaxativas(Panel formPanel) {
		Panel botones = new Panel(formPanel);
		botones.setLayout(new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		Button haciaDerecha = new Button(botones);
		haciaDerecha.setCaption(">").onClick(this::moverHaciaLaDerecha);
		Button haciaIzquierda = new Button(botones);
		haciaIzquierda.setCaption("<").onClick(this::moverHaciaLaIzquierda);
		
	}

	public void listaCondicionesTaxativas(Panel metodologiasPanel) {
		Panel panel = new Panel(metodologiasPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones taxativas");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("condicionesTaxativas");
		condiciones.bindValueToProperty("taxativaSeleccionada");
	}
	
	private void listaComparativasDeLaMetodologia(Panel formPanel) {
		Panel panel = new Panel(formPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones comparativas de la metodologia");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("comparativasMetodologia");
		condiciones.bindValueToProperty("comparativaMetodologia");
	}

	private void botonesComparativas(Panel formPanel) {
		Panel botones = new Panel(formPanel);
		botones.setLayout(new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		Button haciaDerecha = new Button(botones);
		haciaDerecha.setCaption(">").onClick(this::moverHaciaLaDerecha);
		Button haciaIzquierda = new Button(botones);
		haciaIzquierda.setCaption("<").onClick(this::moverHaciaLaIzquierda);
		
	}

	public void listaCondicionesComparativas(Panel metodologiasPanel) {
		Panel panel = new Panel(metodologiasPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones comparativas");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("condicionesComparativas");
		condiciones.bindValueToProperty("comparativaSeleccionada");
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
