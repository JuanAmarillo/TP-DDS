package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.condiciones.Condicion;
import domain.repositorios.RepositorioEmpresas;

public class ListaMetodologia extends Metodologia {

	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return listaEmpresas;
	}

	@Override
	public Condicion getCondicionAAplicar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metodologia getCadenaCondiciones() {
		// TODO Auto-generated method stub
		return null;
	}

}
