package org.scad.s4n;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.ScadS4NReadConfigMain;

/**
 * The class Dispatcher Simulates The Dispatcher of the “Su corrientazo a domicilio”. 
 * it loads the order lists and assigns them to each Drone. After that,
 * it sends the Drones Simultaneously.
 * @author Dixon Medina
 *
 */


public class Dispatcher {
	
	private final int NDRONES = ScadS4NReadConfigMain.getnDrones();
	private List<Drone> drones;

	public Dispatcher() {
		drones = new ArrayList<>(NDRONES);
		drones = Collections.unmodifiableList(drones);
	}
	
	/**
	 * This method calls initDrones and sendDrones.
	 *   	
	 */
	public void init() {
		initDrones();
		sendDrones();
	}

	/**
	 * This method initializes the Drones depending on the quantity configured 
	 * and It loads the order lists to each Drone calling the initOrdersByDrone method.
	 */
	public void initDrones() {
		for (int id = 1; id <= NDRONES; id++) {
			Drone newDrone = new Drone(id, initOrdersByDrone(id));
			addNewDrone(newDrone);			
		}
	}

	/**
	 * This method reads the file that belongs to a Drone 
	 * with its corresponding id and from this reading creates the order lists.  
	 * @param id  this parameter represents the id of the Drone 
	 * @return returns that order list of the Drone with the id that receives from parameter    
	 */
	private List<Order> initOrdersByDrone(int id) {
		
		String fileName =  ScadS4NReadConfigMain.getPathIn()+String.format("in%02d.txt", id);		
		List<Order> orders = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String instructionStr;

			while ((instructionStr = br.readLine()) != null) {
				Order newOrder = Order.valueOf(instructionStr);
				orders.add(newOrder);
			}			
			br.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Error opening path[" + fileName+ "]");
		}
		return orders;		
	}

	
	/**
	 * This method iterates each Drone and starts them   
	 */
	private void sendDrones() {
		for (Drone drone : drones) {
			drone.start();
		}
	}
	
	
	public void addNewDrone(Drone newDrone ) {
		List<Drone> drones = new ArrayList<>(getDrones());
		drones.add(newDrone);
		this.drones = drones; 
	}
	
	public List<Drone> getDrones() {
		return drones;
	}

	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

	public int getNDRONES() {
		return NDRONES;
	}

}
