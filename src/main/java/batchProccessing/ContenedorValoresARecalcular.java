package batchProccessing;

import java.util.ArrayList;
import java.util.List;

import domain.Empresa;

public class ContenedorValoresARecalcular {
	
	private static ContenedorValoresARecalcular instance = null;
	private List<EmpresaPeriodoARecalcular> aRecalcular = new ArrayList<EmpresaPeriodoARecalcular>();
	

	public static ContenedorValoresARecalcular instance() {
		if (noHayInstanciaCargada())
			cargarNuevaInstancia();
		return instance;
	}
	
	public static void cargarNuevaInstancia() {
		instance = new ContenedorValoresARecalcular();
	}

	private static boolean noHayInstanciaCargada() {
		return instance == null;
	}
	
	public List<EmpresaPeriodoARecalcular> getList() {
		return aRecalcular;
	}
	
	public int cantidadDeValoresARecalcular() {
		aRecalcular.forEach(it -> System.out.println(it.getEmpresa().getNombre() + " ACA " + it.getPeriodo()));
		return aRecalcular.size();
	}
	
	public  void agregarEmpresaPeriodo(Empresa empresa, String periodo) {
		aRecalcular.add(new EmpresaPeriodoARecalcular(empresa, periodo));
	}
}
