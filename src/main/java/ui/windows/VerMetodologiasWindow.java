package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.Empresa;
import domain.metodologias.Metodologia;
import ui.vm.VerMetodologiasVM;

public class VerMetodologiasWindow extends Dialog<VerMetodologiasVM> {

	public VerMetodologiasWindow(WindowOwner parent) {
		super(parent, new VerMetodologiasVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel metodologiasPanel = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaMetodologias(metodologiasPanel);
		this.listaEmpresas(metodologiasPanel);
		this.cuadroResultados(formPanel);
	}

	private void cuadroResultados(Panel formPanel) {
		new Label(formPanel).setText("Empresas ordenadas por la metodología");
		ViewUtils.crearListaConAdaptador(formPanel, "empresasOrdenadas", "empresaOrdenadaSeleccionada", Empresa.class,
				"nombre").setHeight(180);

	}

	public void listaMetodologias(Panel metodologiasPanel) {
		Panel metods = ViewUtils.crearPanel(metodologiasPanel, new VerticalLayout(), "Metodologías cargadas");
		List<Metodologia> condiciones = ViewUtils.crearListaConAdaptador(metods, "metodologias",
				"metodologiaSeleccionada", Metodologia.class, "nombre");
		ViewUtils.setSize(250, 100, condiciones);
		ViewUtils.crearBoton(metods, "Aplicar metodología", this::aplicarMetodologia);
	}

	public void listaEmpresas(Panel metodologiasPanel) {
		Panel empr = ViewUtils.crearPanel(metodologiasPanel, new VerticalLayout(), "Empresas cargadas");
		List<Empresa> empresas = ViewUtils.crearListaConAdaptador(empr, "empresas", "empresaSeleccionada",
				Empresa.class, "nombre");
		empresas.setWidth(150);
		empresas.setHeight(100);
	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
	}

	public void aplicarMetodologia() {
		try {
			this.getModelObject().aplicarMetodologia();

		} catch (RuntimeException e) {
			this.showWarning(e.getMessage());
		}
	}

}