package org.scad.s4n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import config.ScadS4NReadConfigMain;

public class DispatcherTest {

	@Test
	public void createDispathcer() {
		ScadS4NReadConfigMain.load();
		Dispatcher dispatcher = new Dispatcher();

		assertEquals(dispatcher.getNDRONES(), ScadS4NReadConfigMain.getnDrones());
		assertEquals(dispatcher.getDrones().size(), 0);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void createADrone() {
		ScadS4NReadConfigMain.load();
		Dispatcher dispatcher = new Dispatcher();

		assertEquals(0, dispatcher.getDrones().size());

		Drone drone1 = new Drone(1, new ArrayList<Order>());

		dispatcher.getDrones().add(drone1);

		assertEquals(1, dispatcher.getDrones().size());
	}

	@Test
	public void initDronesTest() {
		ScadS4NReadConfigMain.load();
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.initDrones();

		assertEquals(ScadS4NReadConfigMain.getnDrones(), dispatcher.getDrones().size());
		int nDrones = ScadS4NReadConfigMain.getnDrones();

		for (int id = 1; id <= nDrones; id++) {

			String fileName = ScadS4NReadConfigMain.getPathIn() + String.format("in%02d.txt", id);

			assertTrue(Files.exists(Paths.get(fileName)));
		}
	}
		
	@Test
	public void validInitDronesNotExistOrderFileTest() {
		ScadS4NReadConfigMain.load();
		Dispatcher dispatcher = new Dispatcher();				
		dispatcher.init();
		assertEquals(ScadS4NReadConfigMain.getnDrones(), dispatcher.getDrones().size());
	}

	@Test
	public void validSendDrones() {
		ScadS4NReadConfigMain.load();

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.init();

		int nDrones = ScadS4NReadConfigMain.getnDrones();

		for (int id = 1; id <= nDrones; id++) {

			String fileName = ScadS4NReadConfigMain.getPathOut() + String.format("out%02d.txt", id);

			assertTrue(Files.exists(Paths.get(fileName)));
		}

	}

}
