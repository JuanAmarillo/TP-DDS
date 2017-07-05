package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

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
		new Button(panelActions).setCaption("Cargar cuentas").onClick(this::cargarCuentas)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Cargar indicadores").onClick(this::cargarIndicadores)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Cargar condiciones").onClick(this::cargarCondiciones)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Consultar valores de cuentas e indicadores").onClick(this::verCuentas)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Cargar metodologías").onClick(this::cargarMetodologias)
				.setBackground(Color.lightGray).setWidth(350);
		new Button(panelActions).setCaption("Evaluar metodologias").onClick(this::evaluarMetodologias)
		.setBackground(Color.lightGray).setWidth(350);
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

	public void verCuentas() {
		try {
			ViewUtils.nuevaPantalla(new CuentasConIndicadoresWindow(this));
		} catch (NoHayEmpresasCargadasException e) {
			this.showWarning(e.getErrorMessage());
		}
	}

	public void cargarMetodologias() {
		try {
			ViewUtils.nuevaPantalla(new CargarMetodologiasWindow(this));
		} catch (NoHayEmpresasCargadasException e) {
			this.showWarning(e.getErrorMessage());
		}
	}
	
	public void evaluarMetodologias() {
		ViewUtils.nuevaPantalla(new VerMetodologiasWindow(this));
	}

}
