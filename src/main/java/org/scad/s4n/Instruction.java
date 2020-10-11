package org.scad.s4n;

import java.util.Optional;

/**
 * This enum contains each instruction that processes each order 
 * @author Dixon Medina
 *
 */
public enum Instruction {
	FORWARD('A'), LEFT('I'), RIGHT('D');

	private char value;

	private Instruction(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}
	
	
	/**
	 * This static method returns a optional instruction than can be valid or invalid  
	 * @param value char with instruction
	 * @return optional instruction 
	 */
	static public Optional<Instruction> valueOf(char value) {
		for(Instruction instruction: Instruction.values()) {
			if(instruction.getValue() == value ) 				
				return Optional.of(instruction);							
		}
		return Optional.empty();
	}

}
