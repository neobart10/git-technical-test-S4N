package org.scad.s4n;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class OrderTest {

	@Test
	public void CreateOrderTest() {
		Order order1 = new Order(new ArrayList<Instruction>()); 
		assertEquals(order1.isValid(), false);

		Order order2 = order1.addNewInstruction(Instruction.FORWARD);
		assertEquals(order2.isValid(), true);
	}
	
	@Test
	public void getInstructionsTest() {
		Order order1 = new Order(new ArrayList<Instruction>());
		assertEquals("{valid=N, instructions=[]}", order1.toString());
		
		Order order2 = order1.addNewInstruction(Instruction.FORWARD);
		assertEquals("{valid=S, instructions=[A,]}", order2.toString());
	}
	
	
	@Test
	public void isValidTest() {
		Order order1 = Order.valueOf("AAAAIAA");
		assertEquals(order1.isValid(), true);

		Order order2 = Order.valueOf("");
		assertEquals(order2.isValid(), false);

		Order order3 = Order.valueOf("A");
		assertEquals(order3.isValid(), true);

		Order order4 = Order.valueOf("Z");		
		assertEquals(order4.isValid(), false);
	}

}
