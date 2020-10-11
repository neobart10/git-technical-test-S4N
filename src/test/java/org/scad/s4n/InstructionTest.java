package org.scad.s4n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.scad.s4n.Instruction;

public class InstructionTest {

	@Test
	public void isInstructionTest() {		
		assertEquals(Instruction.valueOf((Instruction.FORWARD.getValue())).isPresent(), true);
		assertEquals(Instruction.valueOf((Instruction.FORWARD.getValue())).isPresent(), true);
		assertEquals(Instruction.valueOf((Instruction.FORWARD.getValue())).isPresent(), true);
				
		assertEquals(Instruction.valueOf('A').isPresent(), true);
		assertEquals(Instruction.valueOf('I').isPresent(), true);
		assertEquals(Instruction.valueOf('D').isPresent(), true);				
	}
	
	@Test
	public void isInstructionFailTest() {		
		assertEquals(Instruction.valueOf('Z').isPresent(), false);
		assertEquals(Instruction.valueOf('5').isPresent(), false);		
		assertEquals(Instruction.valueOf('.').isPresent(), false);
	}
	
	
}
