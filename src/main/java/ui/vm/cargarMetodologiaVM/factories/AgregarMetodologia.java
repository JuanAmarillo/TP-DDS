package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;

public class AgregarMetodologia {

	public void agregar(String nombreMetodologia, List<Condicion> condicionesAgregadas) {
		validaciones(nombreMetodologia, condicionesAgregadas);
		crearYAgregarAlRepositorio(nombreMetodologia, condicionesAgregadas);
	}

	private void crearYAgregarAlRepositorio(String nombreMetodologia, List<Condicion> condicionesAgregadas) {
		Metodologia nuevaMetodologia = new Metodologia(nombreMetodologia, condicionesAgregadas);
		RepositorioMetodologias.instance().agregarMetodologia(nuevaMetodologia);
	}

	private void validaciones(String nombreMetodologia, List<Condicion> condicionesAgregadas) {
		validarNombre(nombreMetodologia);
		validarQueHayaAlgunaCondicion(condicionesAgregadas);
	}

	private void validarNombre(String nombreMetodologia) {
		if (nombreMetodologia.isEmpty())
			throw new RuntimeException("No se ingresó un nombre para la metodologia");
	}

	private void validarQueHayaAlgunaCondicion(List<Condicion> condicionesAgregadas) {
		if (condicionesAgregadas.size() == 0)
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

}