package org.scad.s4n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * This Class implements each order the "Su corrientazo a domicilio". It contains two attributes: 
 * instruction list and valid     
 * @author Dixon Medina
 *
 */

public class Order {

	private final List<Instruction> instructions;
	private boolean valid;

	public Order(List<Instruction> instructions) {
		this.valid = !instructions.isEmpty();
		this.instructions = Collections.unmodifiableList(instructions);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<Instruction> getInstructions() {
		return this.instructions;
	}

	/**
	 * This method adds a new instruction in a instruction list 
	 * @param newInstruction new  instruction
	 * @return return a new order not to let the mutability of the object.      
	 */
	public Order addNewInstruction(Instruction newInstruction) {
		List<Instruction> instructions = new ArrayList<>(getInstructions());
		instructions.add(newInstruction);
		return new Order(instructions);
	}

	/**
	 * This method return a new order from a string instruction. 
	 * @param instructionStr string instruction. 
	 * @return return a new order. 
	 */	
	public static Order valueOf(String instructionStr) {
		List<Instruction> instructions = new ArrayList<>();

		if (!instructionStr.trim().isEmpty())
			Arrays.asList(instructionStr.split("")).stream().forEach(instr -> {
				if (Instruction.valueOf(instr.charAt(0)).isPresent())
					instructions.add(Instruction.valueOf(instr.charAt(0)).get());
			});

		return new Order(instructions);

	}
	
	/**
	 * This method converts the object order to string. 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{valid=" + (valid?"S":"N") + ", instructions=[" );
		instructions.stream().forEach(instr -> sb.append(instr.getValue() + ","));
		return sb.append("]}").toString();
	}
}
