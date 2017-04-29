package util;

import domain.*;
import domain.repositorios.RepositorioEmpresas;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class LevantaArchivo {
	
	public Empresa cargarArchivo(String filepath) throws IOException {
		Empresa aDevolver = getEmpresaDelArchivo(filepath);
		verifyEmpresa(aDevolver);
		return aDevolver;
	}

	public Empresa getEmpresaDelArchivo(String filepath) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Empresa aDevolver = mapper.readValue(new File(filepath), Empresa.class);
		return aDevolver;
	}
	
	public void verifyEmpresa(Empresa empresaLeida) {
		Empresa yaEstaCargada = RepositorioEmpresas.getInstance().obtenerEmpresaYaCargada(empresaLeida);
		if(yaEstaCargada.getNombre() == null) {
			RepositorioEmpresas.getInstance().agregarEmpresa(empresaLeida);
		}
		else if (!yaEstaCargada.getCuentas().containsAll(empresaLeida.getCuentas())) {
			yaEstaCargada.getCuentas().addAll(empresaLeida.getCuentas());
		}			
	}
}
