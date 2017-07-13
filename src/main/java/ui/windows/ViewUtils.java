package ui.windows;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.Layout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.lacar.ui.model.Action;

public class ViewUtils {
	public static void nuevaPantalla(Dialog <?> ventana){
		ventana.open();
		ventana.onAccept(() -> {});
	}
	
	
	public static void setSize(int width, int height, Selector<?> selector) {
		sizeSetter(width, height, selector);
	}

	private static void sizeSetter(int width, int height, Selector<?> lista) {
		if(width>0 && height >0) {
			lista.setWidth(width);
			lista.setHeight(height);
		}
	}
	
	/* ver si vale la pena meter un Label en el selector*/
	public static <T> Selector<T> crearSelector(Panel panelAUsar, String elementos ,String elementoSeleccionado){
		Selector<T> selector = new Selector<T>(panelAUsar).allowNull(false);
		bindearElementos(elementos, elementoSeleccionado, selector);
		return selector;
	}
	
	public static <T> Selector<T> crearSelectorConAdaptador(Panel panelAUsar, String elementos, String elementoSeleccionado,Class<T> clase, String adaptador){
		Selector<T> selector = new Selector<T>(panelAUsar).allowNull(false);
		selector.bindItemsToProperty(elementos).adaptWith(clase, adaptador);
		selector.bindValueToProperty(elementoSeleccionado);
		return selector;
	}
	
	public static <T> List<T> crearLista(Panel panelAUsar, String elementos ,String elementoSeleccionado){
		List<T> lista = new List<T>(panelAUsar);
		bindearElementos(elementos, elementoSeleccionado, lista);
		return lista;
	}

	private static <T> void bindearElementos(String elementos, String elementoSeleccionado, Selector<T> selector) {
		selector.bindItemsToProperty(elementos);
		selector.bindValueToProperty(elementoSeleccionado);
	}
	
	public static void crearCheckBoxEnNuevoPanel(Panel panelAUsar, String elementoABindear, String nombreLabel){
		Panel comparativo = new Panel(panelAUsar).setLayout(new HorizontalLayout());
		new CheckBox(comparativo).bindValueToProperty(elementoABindear);
		new Label(comparativo).setText(nombreLabel);
	}
	
	public static Panel crearPanel(Panel panelAnterior,Layout layout){
		Panel nuevoPanel = new Panel(panelAnterior);
		nuevoPanel.setLayout(layout);
		return nuevoPanel;
	}
	
	public static Panel crearPanel(Panel panelAnterior,Layout layout,String nombreLabel){
		Panel nuevoPanel = crearPanel(panelAnterior, layout);
		new Label(nuevoPanel).setText(nombreLabel);
		return nuevoPanel;
	}
	
	public static Button crearBoton(Panel panelAUsar, String nombreBoton,Action onClick){
		return new Button(panelAUsar).setCaption(nombreBoton).onClick(onClick);
	}

}