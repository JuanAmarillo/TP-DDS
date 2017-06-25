package archivos;

import domain.*;
import domain.repositorios.RepositorioEmpresas;


public class LevantaArchivoEmpresa extends FileLoader<Empresa> {
	
	public LevantaArchivoEmpresa(String filepath) {
		super(filepath,Empresa.class,RepositorioEmpresas.instance());
	}
	
}
