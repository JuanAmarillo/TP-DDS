package ui.vm.cargarMetodologiaVM.factories;

import java.util.List;

import domain.condiciones.Condicion;
import domain.login.Authenticator;
import domain.login.Usuario;
import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;
import domain.repositorios.RepositorioUsuarios;
import persistencia.TransactionManager;

public class AgregarMetodologia {

	public void agregar(String nombreMetodologia, List<Condicion> condicionesAgregadas) {
		validaciones(nombreMetodologia, condicionesAgregadas);
		crearYAgregarAlRepositorio(nombreMetodologia, condicionesAgregadas);
	}

	private void crearYAgregarAlRepositorio(String nombreMetodologia, List<Condicion> condicionesAgregadas) {
		Metodologia nuevaMetodologia = new Metodologia(nombreMetodologia, condicionesAgregadas);
		TransactionManager.instance().crearTransaccion();
		Usuario usuario = RepositorioUsuarios.instance().encontrarUsuario("login").get();
		usuario.agregarMetodologia(nuevaMetodologia);
		TransactionManager.instance().cerrarTransaccion();
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
		if (noHayCondicionesAgregadas(condicionesAgregadas))
			throw new RuntimeException("No se seleccionó ninguna condición");
	}

	public boolean noHayCondicionesAgregadas(List<Condicion> condicionesAgregadas) {
		return condicionesAgregadas.size() == 0;
	}

}