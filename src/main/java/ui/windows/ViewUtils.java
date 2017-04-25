package ui.windows;
import org.uqbar.arena.windows.Dialog;

public class ViewUtils {
	/*static void nuevaPantalla(DatosCuentasWindow datosCuentasWindow){
		datosCuentasWindow.open();
		datosCuentasWindow.onAccept(() -> {});
	}
	El método comentado sólo crea diálogos con DatosCuentasWindows, hasta que no tengamos más código nos conviene crear un dialog genérico
	*/
	static void nuevaPantalla(Dialog<?> dialog) {
		dialog.open();
		dialog.onAccept(() -> {
		});
	}
}