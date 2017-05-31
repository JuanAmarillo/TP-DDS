package domain.repositorios;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Indicador;

public class Repositorio<T> {
	
	private List<T> elementosCargados;
	private Repositorio<T> instance = null;
	
	public Repositorio<T> instance() {
		if (instance==null){
			elementosCargados = new ArrayList<T>();
			instance = new Repositorio<T>();
		}
		return instance;
		
	}
	
}
