package ui.windows;
import org.uqbar.arena.windows.Dialog;

public class ViewUtils {
	static void nuevaPantalla(Dialog<?> dialog){
		dialog.open();
		dialog.onAccept(() -> {});
	}
}