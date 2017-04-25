package ui.windows;
import org.uqbar.arena.windows.Dialog;

public class ViewUtils {
	static void nuevaPantalla(DatosCuentasWindow datosCuentasWindow){
		datosCuentasWindow.open();
		datosCuentasWindow.onAccept(() -> {});
	}

}