package unitTests.fixtureEmpresas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Cuenta;
import domain.Empresa;

public class PreparadorDeEmpresas {

	public static List<Empresa> prepararEmpresas() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(prepararEmpresa("Coca-Cola", 2500.0, 10000.0, 1890));
		empresas.add(prepararEmpresa("Sorny", 1000.0, 10.0, 2014));
		empresas.add(prepararEmpresa("MagnetBox", -2000.0, 3000.0, 2010));
		empresas.add(prepararEmpresa("Pepsi-Co", 1000.0, 2000.0, 1900));
		empresas.add(prepararEmpresa("Panaphonics", 1000.0, 5000000.0, 2015));

		return empresas;
	}

	private static Empresa prepararEmpresa(String nombre, Double pasivo, Double activo, int anioFundacion) {
		Empresa empresa = new Empresa(nombre);
		Set<Cuenta> cuentas = new HashSet<>();
		Cuenta cuentita = new Cuenta("PasivoTotal", "pascuas", pasivo);
		Cuenta cuentitaBis = new Cuenta("ActivoTotal", "pascuas", activo);
		cuentas.add(cuentita);
		cuentas.add(cuentitaBis);
		empresa.setCuentas(cuentas);
		empresa.setAnioFundacion(anioFundacion);
		return empresa;
	}

}