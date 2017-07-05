package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

import exceptions.NoHayEmpresasCargadasException;

@SuppressWarnings("serial")
public class MainWindow extends SimpleWindow<Object> {

	public MainWindow(WindowOwner parent) {
		super(parent, new Object());
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Inversiones");
		new Label(formPanel).setText("Hector Inversiones").setFontSize(25);

	}

	@Override
	protected void addActions(Panel panelActions) {
		panelActions.setLayout(new ColumnLayout(1));
		crearBotonConColor(panelActions, "Cargar cuentas"    , this::cargarCuentas);
		crearBotonConColor(panelActions, "Cargar indicadores", this::cargarIndicadores);
		crearBotonConColor(panelActions, "Cargar condiciones", this::cargarCondiciones);
		crearBotonConColor(panelActions, "Consultar valores de cuentas e indicadores", this::verCuentasConIndicadores);
		crearBotonConColor(panelActions, "Cargar metodologías" ,this::cargarMetodologias);
		crearBotonConColor(panelActions, "Evaluar metodologias",this::evaluarMetodologias);
	}
	
	public void crearBotonConColor(Panel panelAUsar, String nombreBoton, Action onClick){
		Button boton = ViewUtils.crearBoton(panelAUsar, nombreBoton, onClick);
		boton.setBackground(Color.lightGray).setWidth(350);
	}

	public void cargarCuentas() {
		ViewUtils.nuevaPantalla(new CargarCuentaWindow(this));
	}

	public void cargarIndicadores() {
		ViewUtils.nuevaPantalla(new CargarIndicadorWindow(this));
	}

	public void cargarCondiciones() {
		ViewUtils.nuevaPantalla(new CargarCondicionWindow(this));
	}

	public void verCuentasConIndicadores() {
		try {
			ViewUtils.nuevaPantalla(new CuentasConIndicadoresWindow(this));
		} catch (NoHayEmpresasCargadasException e) {
			this.showWarning(e.getMessage());
		}
	}

	public void cargarMetodologias() {
		try {
			ViewUtils.nuevaPantalla(new CargarMetodologiasWindow(this));
		} catch (NoHayEmpresasCargadasException e) {
			this.showWarning(e.getMessage());
		}
	}
	
	public void evaluarMetodologias() {
		ViewUtils.nuevaPantalla(new VerMetodologiasWindow(this));
	}

}
