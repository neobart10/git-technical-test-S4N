package org.scad.s4n;

import config.ScadS4NReadConfigMain;

/**
 * This class is used to inform and modify the position of the Drone
 * @author Dixon Medina
 *
 */
public class Position {
	
	private int x;
	private int y;
	private CardinalPoints direction; 
	
	public Position(int x, int y, CardinalPoints direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public Position setX(int x) {
		return new Position(x, y, direction);
	}

	public int getY() {
		return y;
	}

	public Position setY(int y) {
		return new Position(x, y, direction);		
	}

	public CardinalPoints getDirection() {
		return direction;
	}

	public Position setDirection(CardinalPoints direction) {
		return new Position(x, y, direction);		
	}	
	
	/**
	 * This method validates that the position of Drone does not exceed the configuration.
	 * @return true or false if position is valid
	 */
	public boolean isValid() {
		return Math.abs(x) <= ScadS4NReadConfigMain.getLimitStreets() && Math.abs(y) <= ScadS4NReadConfigMain.getLimitStreets(); 
	}
	
	/**
	 * @return returns the current position and direction
	 */
	@Override
	public String toString() {		
		return "(" + x + ", " + y + ") dirección " + direction.name();		
	}
}
