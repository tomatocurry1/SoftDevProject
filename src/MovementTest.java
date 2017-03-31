import static org.junit.Assert.*;

import org.junit.Test;

public class MovementTest {
	
	GameInterface a = new GameInterface();
	
	@Test
	public void testMovementOptimization() {
		a.init();
		UnitManager.setup(GameInterface.grid[1][1].getUnit());
		assertEquals(1.5, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[3][2]), 0.01);
		assertEquals(0.0, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[1][3]), 0.01);
		assertEquals(2.5, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[1][0]), 0.01);
	}
	
	@Test
	public void testCurrentPlayerInteraction() {
		a.init();
		//current player attempts to move own unit
		assertTrue(UnitManager.isValidMove(GameInterface.grid[1][1], GameInterface.grid[3][2]));
		//current player attempts to move other player's unit
		assertFalse(UnitManager.isValidMove(GameInterface.grid[1][8], GameInterface.grid[1][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][8], GameInterface.grid[12][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][1], GameInterface.grid[12][1]));
	}

}
