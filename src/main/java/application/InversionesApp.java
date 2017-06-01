package application;

import java.io.IOException;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import externos.LevantaArchivoIndicadores;

import ui.windows.CargaDataWindow;

public class InversionesApp extends Application {
	public static void main(String[] args) {
		try {
			new LevantaArchivoIndicadores().cargarArchivo("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		new InversionesApp().start();

	}

	@Override
	protected Window<?> createMainWindow() {

		return new CargaDataWindow(this);
	}
}
