package domain.condiciones.OperadoresCondicion;

import javax.persistence.AttributeConverter;

public class OperadorConverter implements AttributeConverter<OperadorCondicion, String> {

	@Override
	public String convertToDatabaseColumn(OperadorCondicion operador) {
		return operador.getNombre();
	}

	@Override
	public OperadorCondicion convertToEntityAttribute(String nombreOperador) {
		if(nombreOperador.equals(">")) {
			return new Mayor();
		}
		return new Menor();
	}
}
