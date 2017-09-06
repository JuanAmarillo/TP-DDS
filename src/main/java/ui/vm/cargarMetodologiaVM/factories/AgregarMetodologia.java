package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.CondicionAplicable;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;

public class AgregarMetodologia {

	public void agregar(String nombreMetodologia, List<CondicionAplicable> condicionesAgregadas){
		validaciones(nombreMetodologia,condicionesAgregadas);
		crearYAgregarAlRepositorio(nombreMetodologia, condicionesAgregadas);
	}

	private void crearYAgregarAlRepositorio(String nombreMetodologia, List<CondicionAplicable> condicionesAgregadas) {
		Metodologia nuevaMetodologia = new Metodologia(nombreMetodologia, condicionesAgregadas);
		RepositorioMetodologias.instance().agregarMetodologia(nuevaMetodologia);
	}
	
	private void validaciones(String nombreMetodologia,List<CondicionAplicable> condicionesAgregadas){
		validarNombre(nombreMetodologia);
		validarQueHayaAlgunaCondicion(condicionesAgregadas);
	}
	
	private void validarNombre(String nombreMetodologia) {
		if (nombreMetodologia.isEmpty())
			throw new RuntimeException("No se ingresó un nombre para la metodologia");
	}

	private void validarQueHayaAlgunaCondicion(List<CondicionAplicable> condicionesAgregadas) {
		if (noHayCondicionesAgregadas(condicionesAgregadas))
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

	public boolean noHayCondicionesAgregadas(List<CondicionAplicable> condicionesAgregadas) {
		return condicionesAgregadas.size() == 0;
	}

}