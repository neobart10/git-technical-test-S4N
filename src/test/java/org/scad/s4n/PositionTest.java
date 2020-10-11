package org.scad.s4n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import config.ScadS4NReadConfigMain;

public class PositionTest {
  
	@Test
	public void CreatePositionTest() {
		Position position = new Position(0, 0, CardinalPoints.Occidente);		
		assertEquals(position.toString(), "(0, 0) dirección Occidente");
				
		position.setDirection(CardinalPoints.Norte);
		assertEquals(position.toString(), "(0, 0) dirección Occidente");
		
		position = position.setDirection(CardinalPoints.Norte);
		assertEquals(position.toString(), "(0, 0) dirección Norte");
	}
	
	@Test 
	public void isValidPositionTest() {
		
		ScadS4NReadConfigMain.load();		
		
		Position position = new Position(0, 0, CardinalPoints.Occidente);		
		assertEquals(position.isValid(), true);
		
		position = position.setX(10);		
		assertEquals(position.isValid(), true);
		
		position = position.setX(12);		
		assertEquals(position.isValid(), false);
		
		position = position.setY(-12);		
		assertEquals(position.isValid(), false);
	}
  
}

