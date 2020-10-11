package config;

import java.util.TreeMap;

/**
 * @author dmedina
 * 
 */

public class ScadS4NReadConfigMain {
	
	private final static int DEFAULTLIMITSTREETS = 10;
	private final static int DEFAULTLIMITORDERS = 3;
	private final static int DEFAULTTOTALDRONES = 20;
	private final static boolean DEBUG = false;
  
	private static TreeMap<String, String> configurations; 
		
	public static void load() {
		ScadS4NGetPropertyValues properties = new ScadS4NGetPropertyValues();
		configurations = properties.getPropValues();
	}
	
	public static int getLimitStreets() {		
		return configurations.containsKey("limitStreets")?Integer.parseInt(configurations.get("limitStreets")):DEFAULTLIMITSTREETS;
	}
	
	public static int getLimitOrders() {		
		return configurations.containsKey("limitOrders")?Integer.parseInt(configurations.get("limitOrders")):DEFAULTLIMITORDERS;
	}
	
	public static int getnDrones() {		
		return configurations.containsKey("drones")?Integer.parseInt(configurations.get("drones")):DEFAULTTOTALDRONES;
	}
	
	public static boolean getDebug() {
		return configurations.containsKey("debug")?configurations.get("debug").charAt(0)=='Y':DEBUG;
	}
	
	public static String getPathIn() {
		return configurations.containsKey("pathIN")?configurations.get("pathIN"):"";
	}
	
	public static String getPathOut() {
		return configurations.containsKey("pathOUT")?configurations.get("pathOUT"):"";
	}
}
