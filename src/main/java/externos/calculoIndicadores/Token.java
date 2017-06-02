package externos.calculoIndicadores;

import domain.Empresa;

public interface Token {
	public double calcularValor(Empresa empresa, String periodo);
}
