package application;

import java.io.IOException;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import archivos.ActualizaIndicadores;
import archivos.LevantaArchivoIndicadores;
import ui.windows.MainWindow;

public class InversionesApp extends Application {

	//public static String filepath = "src/test/resources/Indicadores.json";
	
	public static void main(String[] args) {
//		try {
//			new LevantaArchivoIndicadores(filepath).cargarArchivo();
//			ActualizaIndicadores.setFilepath(filepath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		new InversionesApp().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new MainWindow(this);
	}
}
