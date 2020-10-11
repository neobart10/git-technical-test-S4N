package org.scad.s4n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardinalPointsClass {
  
	@Test
	public void getCardinalPointTest() {
		assertEquals(CardinalPoints.valueOf("Norte"), CardinalPoints.Norte);
		assertEquals(CardinalPoints.valueOf("Sur"), CardinalPoints.Sur);
		assertEquals(CardinalPoints.valueOf("Oriente"), CardinalPoints.Oriente);
		assertEquals(CardinalPoints.valueOf("Occidente"), CardinalPoints.Occidente);
	}
}
