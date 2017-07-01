package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;


@SuppressWarnings("serial")
public class CargaDataWindow extends SimpleWindow<Object> {

	public CargaDataWindow(WindowOwner parent) {
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
		new Button(panelActions).setCaption("Cargar cuentas").onClick(this::cargarCuentas)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Cargar indicadores").onClick(this::cargarIndicadores)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Cargar condiciones").onClick(this::cargarCondiciones)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Consultar valores").onClick(this::verCuentas)
				.setBackground(Color.lightGray).setWidth(350);

	}

	public void cargarCuentas() {
		ViewUtils.nuevaPantalla(new CargarCuentaWindow(this));
	}

	public void verCuentas() {
		ViewUtils.nuevaPantalla(new CuentasConIndicadoresWindow(this));
	}

	public void cargarIndicadores() {
		ViewUtils.nuevaPantalla(new CargarIndicadorWindow(this));
	}
	
	public void cargarCondiciones(){
		ViewUtils.nuevaPantalla(new CargarCondicionWindow(this));
	}

}
