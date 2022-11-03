package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	private static final String propertyFilePath= "src/test/resources/config.properties";

	public static Properties readProperties() throws IOException {
		Properties properties = new Properties();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties.load(reader);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
		finally {
			reader.close();
		}
		return properties;
	}

	public String getApplicationUrl() {
		try{
			String url = PropertyUtils.readProperties().getProperty("url");
			if(url != null) return url;
			else throw new RuntimeException("url not specified in the Configuration.properties file.");
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException when reading properties");
		}

	}

}
