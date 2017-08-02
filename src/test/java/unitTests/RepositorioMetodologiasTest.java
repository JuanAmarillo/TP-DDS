package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.metodologias.Metodologia;
import domain.repositorios.RepositorioMetodologias;

public class RepositorioMetodologiasTest {
	
	Metodologia metodologia = new Metodologia("Segun cantidad de patos",null);
	
	@Test
	public void SePuedeCargarUnaMetodologiaTest(){
		
		RepositorioMetodologias.instance().agregarMetodologia(metodologia);
		assertEquals(1, RepositorioMetodologias.instance().getMetodologiasCargadas().size());
	}

	@Test
	public void SePuedeBuscarPorNombreTest(){
		assertEquals(Metodologia.class,RepositorioMetodologias.instance().buscarMetodologia("Segun cantidad de patos").get().getClass());
		assertEquals(true, RepositorioMetodologias.instance().buscarMetodologia("Segun cantidad de patos").isPresent());
	}
	
}
