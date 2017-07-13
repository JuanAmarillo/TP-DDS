package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargarCuentaVM;

@SuppressWarnings("serial")
public class CargarCuentaWindow extends Dialog<CargarCuentaVM> {

	public CargarCuentaWindow(WindowOwner parent) {
		super(parent, new CargarCuentaVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Cargar cuentas de una empresa");
		Panel form = ViewUtils.crearPanel(formPanel, new ColumnLayout(2));

		new Label(form).setText("Seleccione archivo");
		new FileSelector(form).path("src/test/resources").setCaption("Examinar").bindValueToProperty("filePath");
		new Label(form).bindValueToProperty("filePath");
	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new HorizontalLayout());
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
		ViewUtils.crearBoton(panelActions, "Cargar", this::cargarArchivo);
	}

	public void cargarArchivo() {
		try {
			this.getModelObject().cargarEmpresa();
			this.showInfo("Se ha cargado con éxito el archivo");
		} catch (IOException e) {
			this.showWarning("El archivo seleccionado posee un formato incorrecto");
		} catch (RuntimeException e){
			this.showError("Seleccione un archivo a cargar");
		}
	}

}
