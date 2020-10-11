package org.scad.s4n;

import config.ScadS4NReadConfigMain;


/**
 * This is main class of the application. it is in charge of the configuration and executes the Dispatcher.            
 * @author Dixon Medina
 *
 */
public class MainScad {
	
	/**
	 * Main method of the application.
	 * @param args
	 */
	public static void main(String[] args) {

		ScadS4NReadConfigMain.load();
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.init();

	}
}
