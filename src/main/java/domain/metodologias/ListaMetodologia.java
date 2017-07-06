package domain.metodologias;

import java.util.List;

import domain.Empresa;
import domain.repositorios.RepositorioEmpresas;

public class ListaMetodologia extends Metodologia {

	@Override
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo) {
		return listaEmpresas;
	}

}
