package ui.windows;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
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
		Panel cuerpo = new Panel(formPanel);
		cuerpo.setLayout(new HorizontalLayout());
		this.listaCondiciones(cuerpo);
		this.botones(cuerpo);
		this.listaDeLaMetodologia(cuerpo);
	}

	private void listaDeLaMetodologia(Panel formPanel) {
		Panel panel = new Panel(formPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones de la metodologia");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("condMetodologia");
		condiciones.bindValueToProperty("condicionesMetodologia");
	}

	private void botones(Panel formPanel) {
		Panel botones = new Panel(formPanel);
		botones.setLayout(new ColumnLayout(1));
		Label rellenaVacio = new Label(botones);
		Label rellenaVacio1 = new Label(botones);
		Button haciaDerecha = new Button(botones);
		haciaDerecha.setCaption(">").onClick(this::moverHaciaLaDerecha);
		Button haciaIzquierda = new Button(botones);
		haciaIzquierda.setCaption("<").onClick(this::moverHaciaLaIzquierda);
		
	}

	public void listaCondiciones(Panel metodologiasPanel) {
		Panel panel = new Panel(metodologiasPanel);
		panel.setLayout(new ColumnLayout(1));
		new Label(panel).setText("Condiciones cargadas");
		List<String> condiciones = new List<String>(panel);
		ViewUtils.setSize(250, 100, condiciones);
		condiciones.bindItemsToProperty("condiciones");
		condiciones.bindValueToProperty("condicionSeleccionada");
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
