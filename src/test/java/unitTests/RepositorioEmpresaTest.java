package unitTests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.repositorios.RepositorioEmpresas;;

public class RepositorioEmpresaTest {

	private RepositorioEmpresas repositorio;
	
	@Before
	public void init(){
		repositorio = new RepositorioEmpresas();
	}
}
