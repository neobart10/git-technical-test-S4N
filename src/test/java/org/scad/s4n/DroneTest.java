package org.scad.s4n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import config.ScadS4NReadConfigMain;

public class DroneTest {
	
	@Test
	public void createDroneTest() {
		Drone drone = new Drone(1, new ArrayList<Order>());
		assertEquals(drone.getIdDrone(), 1);
		Position position = new Position(0, 0, CardinalPoints.Norte);
		assertEquals(drone.getPosition().toString(), position.toString());
	}
	
	@Test
	public void toReturnDroneTest() {
		Drone drone = new Drone(1, new ArrayList<Order>());
		Position position = new Position(0, 0, CardinalPoints.Norte);
		
		assertEquals(drone.getPosition().toString(), position.toString());
		
		drone.setPosition(new Position(1, 2, CardinalPoints.Occidente));
		assertTrue("No son iguales", !drone.getPosition().toString().equals(position.toString()));
		
		drone.toReturn();
		assertEquals(drone.getPosition().toString(), position.toString());
		
	}
	
	@Test
	public void runDroneFailTest() throws InterruptedException {
		ScadS4NReadConfigMain.load();
		
		String instructionOrder1 = "AAAAIAA";
		String instructionOrder2 = "DDDAIAD";
		String instructionOrder3 = "AAIADAD";
				
		Order order1 = Order.valueOf(instructionOrder1);
		Order order2 = Order.valueOf(instructionOrder2);
		Order order3 = Order.valueOf(instructionOrder3);
		
		
		
		List<Order> orders = Arrays.asList(order1, order2, order3);
				
		Drone drone = new Drone(55, orders);
		
		assertEquals(3, drone.getOrders().size());
		
		drone.start();
		Thread.sleep(1000);
		
		String fileName =  ScadS4NReadConfigMain.getPathOut()+String.format("out%02d.txt", 55);		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			assertEquals(br.readLine(), "== Reporte de entregas ==");
			String report1 = br.readLine();			
			assertEquals(report1.equals("(-2, 4) dirección Norte"), false);
			
			String report2 = br.readLine();
			assertEquals(report2.equals("(-3, 3) dirección Sur"), false);
			
			String report3 = br.readLine();
			assertEquals(report3.equals("(0, 0) dirección Occidente"), true);
			
			br.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void runDroneTest() throws InterruptedException {
		ScadS4NReadConfigMain.load();
		
		String instructionOrder1 = "AAAAIAA";
		String instructionOrder2 = "DDDAIAD";
		String instructionOrder3 = "AAIADAD";
				
		Order order1 = Order.valueOf(instructionOrder1);
		Order order2 = Order.valueOf(instructionOrder2);
		Order order3 = Order.valueOf(instructionOrder3);
		
		
		
		List<Order> orders = Arrays.asList(order1, order2, order3);
				
		Drone drone = new Drone(60, orders);
		
		assertEquals(3, drone.getOrders().size());
		
		drone.start();
		Thread.sleep(1000);
		
		String fileName =  ScadS4NReadConfigMain.getPathOut()+String.format("out%02d.txt", 60);		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			assertEquals(br.readLine(), "== Reporte de entregas ==");
			String report1 = br.readLine();			
			assertEquals(report1, "(-2, 4) dirección Occidente");			
			
			String report2 = br.readLine();
			assertEquals(report2, "(-1, 3) dirección Sur");
			
			String report3 = br.readLine();
			assertEquals(report3, "(0, 0) dirección Occidente");
						
			
			br.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
	
	

}
