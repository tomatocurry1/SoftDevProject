import static org.junit.Assert.*;

import org.junit.Test;

public class MovementTest {
	
	GameInterface a = new GameInterface();
	
	@Test
	public void testMovementOptimization() {
		a.init();
		UnitManager.setup(GameInterface.grid[1][1].getUnit());
		assertEquals(1.5, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[3][2]), 0.01);
	}
	
	@Test
	public void testCurrentPlayerInteraction() {
		a.init();
		assertTrue(UnitManager.isValidMove(GameInterface.grid[1][1], GameInterface.grid[3][2]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[1][8], GameInterface.grid[1][9]));
	}

}
