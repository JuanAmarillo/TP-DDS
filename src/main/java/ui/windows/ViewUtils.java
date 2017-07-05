package ui.windows;
import org.uqbar.arena.widgets.Button;
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
		if(width>0) {
			lista.setWidth(width);
		}
		if(height>0) {
			lista.setHeight(height);
		}
	}
	
	public static <T> Selector<T> crearSelector(Panel panelAUsar, String elementos ,String elementoSeleccionado){
		Selector<T> selector = new Selector<T>(panelAUsar).allowNull(false);
		bindearElementos(elementos, elementoSeleccionado, selector);
		return selector;
	}
	
	public static <T> List<T> crearLista(Panel panelAUsar, String elementos ,String elementoSeleccionado){
		List<T> lista = new List<T>(panelAUsar);
		bindearElementos(elementos, elementoSeleccionado, lista);
		return lista;
	}

	private static <T> void bindearElementos(String elementos, String elementoSeleccionado, Selector<T> lista) {
		lista.bindItemsToProperty(elementos);
		lista.bindValueToProperty(elementoSeleccionado);
	}
	
	
	public static void crearBoton(Panel panelAUsar, String nombreBoton,Action onClick){
		new Button(panelAUsar).setCaption(nombreBoton).onClick(onClick);
	}

}