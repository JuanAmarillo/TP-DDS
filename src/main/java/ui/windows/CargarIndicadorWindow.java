package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import domain.indicadores.IndicadorCustom;
import exceptions.NoSePuedeBorrarUnPredeterminadoException;
import ui.vm.CargarIndicadorVM;

@SuppressWarnings("serial")
public class CargarIndicadorWindow extends Dialog<CargarIndicadorVM> {

	public CargarIndicadorWindow(WindowOwner parent) {
		super(parent, new CargarIndicadorVM());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		Panel indicadoresPanel = ViewUtils.crearPanel(formPanel, new HorizontalLayout());
		this.listaIndicadores(indicadoresPanel);
		this.indicadorPersonalizado(indicadoresPanel);

	}

	public void listaIndicadores(Panel indicadoresPanel) {
		Panel form = ViewUtils.crearPanel(indicadoresPanel, new VerticalLayout(), "Indicadores Cargados");
		List<IndicadorCustom> indicadores = ViewUtils.crearLista(form, "indicadores", "indicadorSeleccionado");
		ViewUtils.setSize(150, 100, indicadores);
	}

	public void indicadorPersonalizado(Panel indicadoresPanel) {
		Panel form = ViewUtils.crearPanel(indicadoresPanel, new ColumnLayout(1));
		escribirIndicador(form);
		accionesIndicador(form);
	}

	private void escribirIndicador(Panel form) {
		new Label(form).setText("Indicador personalizado");
		new TextBox(form).setWidth(250).bindValueToProperty("indicador");
	}

	private void accionesIndicador(Panel form) {
		ViewUtils.crearBoton(form, "Cargar indicador"  , this::cargarIndicador);
		ViewUtils.crearBoton(form, "Eliminar indicador", this::eliminarIndicador);
	}

	@Override
	protected void addActions(Panel panelActions) {
		ViewUtils.crearBoton(panelActions, "Volver", this::accept);
	}

	public void eliminarIndicador() {
		try {
			this.getModelObject().eliminarIndicador();
		} catch (NoSePuedeBorrarUnPredeterminadoException e) {
			this.showWarning(e.getMessage());
		} catch (IOException e) {
			this.showWarning("La eliminacion no se realizo con exito, intenta mas tarde :p (la clasica)");
		} catch (RuntimeException e) {
			this.showWarning("Seleccione un Indicador a borrar");
		}
	}

	public void cargarIndicador() {
		try {
			this.getModelObject().cargarIndicador();
		} catch (RuntimeException | IOException e) {
			this.showWarning(e.getMessage());
		}
	}
}