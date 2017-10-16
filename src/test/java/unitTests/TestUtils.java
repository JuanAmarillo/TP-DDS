package unitTests;
import java.util.Arrays;
import java.util.LinkedList;

public class TestUtils {
	public static LinkedList<String> convertirALista(String[] strings) {
		return new LinkedList<String>(Arrays.asList(strings));
	}
}
