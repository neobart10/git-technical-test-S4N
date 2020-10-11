package config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

public class ScadS4NGetPropertyValues {	
	
	private InputStream inputStream;
	private TreeMap<String, String> configurations = new TreeMap<String, String>();
 
	public TreeMap<String, String> getPropValues() {
 		
		try {
			Properties properties = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");				
			}
			
			properties.entrySet().forEach(entry -> configurations.put(entry.getKey().toString() , entry.getValue().toString()));			
			
			inputStream.close();
			
		} catch (Exception e) {
			System.out.println("Error loading properties Exception: " + e);
		} finally {
			
		}
		
		return configurations;
	}
}
