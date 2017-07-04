package ui.windows;

import ui.vm.VerMetodologiasVM;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Empresa;
import domain.condiciones.Condicion;

public class VerMetodologiasWindow extends Dialog<VerMetodologiasVM> {

	public VerMetodologiasWindow(WindowOwner parent) {
		super(parent, new VerMetodologiasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		// TODO Auto-generated method stub
		Panel metodologiasPanel = new Panel(formPanel);
		metodologiasPanel.setLayout(new HorizontalLayout());
		this.listaMetodologias(metodologiasPanel);
		this.listaEmpresas(metodologiasPanel);
	}

	public void listaMetodologias(Panel metodologiasPanel) {
		Panel metods = new Panel(metodologiasPanel);
		new Label(metods).setText("Condiciones cargadas");
		List<Condicion> condiciones = new List<Condicion>(metods);
		condiciones.setWidth(250);
		condiciones.setHeight(100);
		condiciones.bindItemsToProperty("condiciones");
		condiciones.bindValueToProperty("condicionSeleccionada");
		new Button(metods).setCaption("Aplicar metodología");
	}

	public void listaEmpresas(Panel metodologiasPanel) {
		Panel empr = new Panel(metodologiasPanel);
		new Label(empr).setText("Empresas cargadas");
		List<Empresa> empresas = new List<Empresa>(empr);
		empresas.setWidth(150);
		empresas.setHeight(100);
		empresas.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
	}

	@Override
	protected void addActions(Panel panelActions) {
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
	}

}
