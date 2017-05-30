package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import ui.vm.CargarIndicadorVM;

@SuppressWarnings("serial")
public class CargarIndicadorWindow extends Dialog<CargarIndicadorVM> {
	public CargarIndicadorWindow(WindowOwner parent) {
		super(parent, new CargarIndicadorVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel form = new Panel(formPanel);
		form.setLayout(new ColumnLayout(2));
		this.setTitle("Cargar indicador");

		new Label(form).setText("Cargar indicador personalizado");
		//new TextBox(form).setWidth(250).bindValueToProperty("indicador");

		new Label(form).setText("Seleccione archivo");
		new FileSelector(form).path("src/test/resources").setCaption("Examinar").bindValueToProperty("filePath");
		new Label(form).bindValueToProperty("filePath");
	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new HorizontalLayout());
		new Button(panelActions).setCaption("Volver").onClick(this::accept).setAsDefault();
		new Button(panelActions).setCaption("Cargar indicador").onClick(this::cargarIndicador);
		new Button(panelActions).setCaption("Cargar archivo").onClick(this::cargarArchivoIndicadores);
	}

	public void cargarIndicador() {
		try {
			this.getModelObject().cargarIndicador();
			this.showInfo("El indicador se ha cargado con éxito");
		} catch (IOException e) {
			this.showWarning("No se pudo cargar el indicador");
		}
	}

	public void cargarArchivoIndicadores() {
		try {
			this.getModelObject().cargarArchivoIndicadores();
			this.showInfo("El archivo se ha cargado con éxito");
		} catch (IOException e) {
			this.showWarning("El archivo seleccionado es incorrecto");
		}
	}
	/*
	 * Cuando se carga el indicador, tendría que verificarse que no esté ya
	 * cargado, y si no estaba, agregarlo. El LevantaArchivos está acoplado
	 * exclusivamente a empresas.
	 */

}