package ui.windows;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;

public class ViewUtils {
	static void nuevaPantalla(Dialog <?> ventana){
		ventana.open();
		ventana.onAccept(() -> {});
	}
	
	static void setSize(int width, int height, List<?> lista) {
		sizeSetter(width, height, lista);
	}
	
	static void setSize(int width, int height, Selector<?> selector) {
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
}