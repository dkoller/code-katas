package es.rachelcarmena;

import org.junit.*;
import static org.junit.Assert.*;

public class DiamondTest {

	@Test
	public void getDiamondFromA() {
		Object[] diamond = new Diamond('A').getDiamond();
		assertEquals("A", diamond[0]);
	}

	@Test
	public void getDiamondFromB() {
		Object[] diamond = new Diamond('B').getDiamond();
		assertEquals(" A ", diamond[0]);
		assertEquals("B B", diamond[1]);
		assertEquals(" A ", diamond[2]);
	}

	@Test
	public void getDiamondFromC() {
		Object[] diamond = new Diamond('C').getDiamond();
		assertEquals("  A  ", diamond[0]);
		assertEquals(" B B ", diamond[1]);
		assertEquals("C   C", diamond[2]);
		assertEquals(" B B ", diamond[3]);
		assertEquals("  A  ", diamond[4]);
	}

	@Test
	public void getDiamondFromD() {
		Object[] diamond = new Diamond('D').getDiamond();
		assertEquals("   A   ", diamond[0]);
		assertEquals("  B B  ", diamond[1]);
		assertEquals(" C   C ", diamond[2]);
		assertEquals("D     D", diamond[3]);
		assertEquals(" C   C ", diamond[4]);
		assertEquals("  B B  ", diamond[5]);
		assertEquals("   A   ", diamond[6]);
	}
}
