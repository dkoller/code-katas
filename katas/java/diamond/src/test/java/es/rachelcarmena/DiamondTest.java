package es.rachelcarmena;

import org.junit.*;
import static org.junit.Assert.*;

public class DiamondTest {

	@Test
	public void getDiamondFromA() {
		String[] diamond = new Diamond('A').getRepresentation();
		assertEquals("A", diamond[0]);
	}

	@Test
	public void getDiamondFromB() {
		String[] diamond = new Diamond('B').getRepresentation();
		assertEquals(" A ", diamond[0]);
		assertEquals("B B", diamond[1]);
		assertEquals(" A ", diamond[2]);
	}

	@Test
	public void getDiamondFromC() {
		String[] diamond = new Diamond('C').getRepresentation();
		assertEquals("  A  ", diamond[0]);
		assertEquals(" B B ", diamond[1]);
		assertEquals("C   C", diamond[2]);
		assertEquals(" B B ", diamond[3]);
		assertEquals("  A  ", diamond[4]);
	}

	@Test
	public void getDiamondFromD() {
		String[] diamond = new Diamond('D').getRepresentation();
		assertEquals("   A   ", diamond[0]);
		assertEquals("  B B  ", diamond[1]);
		assertEquals(" C   C ", diamond[2]);
		assertEquals("D     D", diamond[3]);
		assertEquals(" C   C ", diamond[4]);
		assertEquals("  B B  ", diamond[5]);
		assertEquals("   A   ", diamond[6]);
	}
}
