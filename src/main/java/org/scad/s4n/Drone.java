package org.scad.s4n;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import config.ScadS4NReadConfigMain;

/**
 * This Drone Class represents each Drone of “Su corrientazo a domicilio”. 
 * It has the following parameters: id, one order list and the position where the Drone 
 * it is located. With these attributes it iterates the orders and reports the final position. 
 * So the class extends from the Thread class with the purpose the instances of this class execute the process simultaneously. 
 * 
 * @author Dixon Medina
 *
 */
public class Drone extends Thread {

	private int id;
	private List<Order> orders;
	private Position position;

	public Drone(int id, List<Order> orders) {
		this.id = id;
		this.orders = Collections.unmodifiableList(orders);
		this.position = new Position(0, 0, CardinalPoints.Norte);	
	}
	
	/**
	 * This method iterates the order list and executes each one all of the instructions 
	 * that the order contains. This way the class can modify the drone position.  
	 * It validates the limits of the neighborhood, the quantity of the orders for each route 
	 * and reports each of the orders.  
	 * At the end of each route, the drone returns to the initial position.
	 * 
	 */
	@Override
	public void run() {

		int ordersDelivered = 0;
		initReport();
		
		for (Order order : orders) {
			if (order.isValid()) {
				
				if (ordersDelivered == 0) {					
					toReturn();
					ordersDelivered = ScadS4NReadConfigMain.getLimitOrders();					
				}
				
				for (Instruction instruction : order.getInstructions()) {
					Optional<Position> newPosition = process(instruction);

					if (newPosition.isPresent()) {
						this.position = newPosition.get();
					} else {
						report("Order exceeded delivery limit.");
					}
				}

				if (position.isValid()) {
					ordersDelivered--;
					report(position.toString());
				} else {
					report("Order exceeded delivery limit.");
				}

			} else 				
				report("Order invalid.");
		}		

	}

	/**
	 * This method returns the drone to the initial position.  
	 */
	public void toReturn() {
		position = new Position(0, 0, CardinalPoints.Norte);
	}

	private void initReport() {
		String fileName =  ScadS4NReadConfigMain.getPathOut()+String.format("out%02d.txt", id);
		String str = "== Reporte de entregas ==";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(str + '\n');
			writer.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Error opening path[" + fileName+ "]");
		}				
	}
	
	
	/**
	 * This method reports each message with help of the writeReport method.   
	 * @param message message to report
	 */
	private void report(String message) {
		if (ScadS4NReadConfigMain.getDebug())
			System.out.println("Drone #" + id + " -> " + message);
		writeReport(message);
	}

	
	/**
	 * this method writes the message in the file the belongs to the Drone   
	 * @param message
	 */
	public void writeReport(String message) {
		String fileName =  ScadS4NReadConfigMain.getPathOut()+String.format("out%02d.txt", id);		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.append(message + '\n');
			writer.close();
		} catch (IOException e) {
			System.out.println("Error opening path[" + fileName+ "]");
			//e.printStackTrace();
		}		
	}

	/**
	 * This method processes the instruction and calls other methods that execute them.   
	 * @param instruction instruction to execute. 
	 * @return returns new position.
	 */
	private Optional<Position> process(Instruction instruction) {

		switch (instruction) {
		case FORWARD:
			moveForward();
			break;
		case LEFT:
			turnLeft();
			break;
		case RIGHT:
			turnRight();
			break;
		}

		return Optional.of(position);
	}


	/**
	 * This method changes the drone position moving forward.   
	 */
	private void moveForward() {
		switch (position.getDirection()) {
		case Norte:
			this.position = this.position.setY(this.position.getY() + 1);
			break;
		case Oriente:
			this.position = this.position.setX(this.position.getX() + 1);
			break;
		case Occidente:
			this.position = this.position.setX(this.position.getX() - 1);
			break;
		case Sur:
			this.position = this.position.setY(this.position.getY() - 1);
			break;
		}
	}

	/**
	 * This method changes the drone direction to the left.
	 */
	private void turnLeft() {
		switch (position.getDirection()) {
		case Norte:
			this.position = this.position.setDirection(CardinalPoints.Occidente);
			break;
		case Oriente:
			this.position = this.position.setDirection(CardinalPoints.Norte);
			break;
		case Occidente:
			this.position = this.position.setDirection(CardinalPoints.Sur);
			break;
		case Sur:
			this.position = this.position.setDirection(CardinalPoints.Oriente);
			break;
		}
	}

	/**
	 * This method changes the drone direction to the right.
	 */
	private void turnRight() {
		switch (position.getDirection()) {
		case Norte:
			this.position = this.position.setDirection(CardinalPoints.Oriente);
			break;
		case Oriente:
			this.position = this.position.setDirection(CardinalPoints.Sur);
			break;
		case Occidente:
			this.position = this.position.setDirection(CardinalPoints.Norte);
			break;
		case Sur:
			this.position = this.position.setDirection(CardinalPoints.Occidente);
			break;
		}
	}

	public int getIdDrone() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	

}
