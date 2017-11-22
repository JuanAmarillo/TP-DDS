package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public static String readProperty(String propertyName) {
		Properties defaultProps = new Properties();
		FileInputStream in;
		String resultado = "";
		try {
			in = new FileInputStream("SystemConfiguration");
			defaultProps.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultado = defaultProps.getProperty(propertyName);
		return resultado;
	}
}
  