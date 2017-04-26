package test;
import domain.*;
import domain.repositorios.RepositorioEmpresas;
import junit.framework.Assert;
import util.LevantaArchivo;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class empresasTest{
	
	public static List<Empresa> datosDePrueba(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Empresa unaEmpresa = new Empresa();
		Periodo periodo1 = new Periodo(new LocalDate(2017,07,01), new LocalDate(2017,12,31));
		Periodo periodo2 = new Periodo(new LocalDate(2017,01,01), new LocalDate(2017,06,30));
		cuentas.add(agregarCuentaDePrueba("ROE", (float) 102, periodo1));
		cuentas.add(agregarCuentaDePrueba("ROA", (float) 90, periodo2));
		
		unaEmpresa.setNombre("Google");
		unaEmpresa.setCuentas(cuentas);
		empresas.add(unaEmpresa);
		
		return empresas;
	}
	
	public static Cuenta agregarCuentaDePrueba(String nombre, Float balance, Periodo periodo){
		Cuenta unaCuenta = new Cuenta();
		unaCuenta.setNombre(nombre);
		unaCuenta.setBalance(balance);
		//unaCuenta.setPeriodo(periodo);
		return unaCuenta;
	}
	
	@Before
	public void init(){
		try{
		RepositorioEmpresas.getInstance().agregarEmpresa(LevantaArchivo.cargarArchivo("src/test/resources/Empresa1.json"));
		}
		catch(IOException e){e.printStackTrace();}
	}
	
	@Test
	public void seCargoCocaColaTest(){
		assertEquals(1, RepositorioEmpresas.getInstance().getEmpresasCargadas().size());
	}
	
	@Test
	public void cocaColaTieneDosCuentas(){
		assertEquals(2, RepositorioEmpresas.getInstance().getEmpresasCargadas().get(0).getCuentas().size());
	}
	
	
	
}