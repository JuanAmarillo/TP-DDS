import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import ui.windows.CargaDataWindow;

public class InversionesApp extends Application {
	public static void main(String[] args) {
		new InversionesApp().start();
	}

	@Override
	protected Window<?> createMainWindow() {

		return new CargaDataWindow(this);
	}
}
