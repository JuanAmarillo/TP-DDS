package application;

import java.io.IOException;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import domain.repositorios.RepositorioIndicadores;
import externos.ActualizaArchivos;
import externos.LevantaArchivoIndicadores;

import ui.windows.CargaDataWindow;

public class InversionesApp extends Application {

	public static String fp = "src/test/resources/Indicadores.json";
	
	public static void main(String[] args) {
		try {
			new LevantaArchivoIndicadores().cargarArchivo(fp);
			ActualizaArchivos.setFilepath(fp);
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
