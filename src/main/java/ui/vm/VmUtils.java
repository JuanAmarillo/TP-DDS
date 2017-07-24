package ui.vm;

import java.util.Arrays;
import java.util.LinkedList;

import org.uqbar.commons.model.ObservableUtils;

public class VmUtils {

	public static void avisarCambios(Object model, String... properties) {
		convertirALista(properties).stream().forEach(property -> ObservableUtils.firePropertyChanged(model, property));
	}

	public static LinkedList<String> convertirALista(String[] strings) {
		return new LinkedList<String>(Arrays.asList(strings));
	}
}
