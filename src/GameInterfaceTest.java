import static org.junit.Assert.*;

import org.junit.Test;

public class GameInterfaceTest {
	
	@Test
	public void testMovementOptimization() {
		GameInterface a = new GameInterface();
		a.init();
		UnitManager.setup(GameInterface.grid[1][1].getUnit());
		assertEquals(2, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[3][2]), 0.01);
		
	}

}
