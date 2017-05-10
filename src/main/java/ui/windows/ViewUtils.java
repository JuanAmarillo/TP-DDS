package ui.windows;
import org.uqbar.arena.windows.Dialog;

public class ViewUtils {
	static void nuevaPantalla(Dialog <?> ventana){
		ventana.open();
		ventana.onAccept(() -> {});
	}

}