package domain.metodologias;

import java.util.List;

import domain.Empresa;

public interface Metodologia {
	
	public List<Empresa> aplicarMetodologia(List<Empresa> listaEmpresas, String periodo);
	
}
