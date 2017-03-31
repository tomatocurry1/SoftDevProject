import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Test;

public class MovementTest {
	
	GameInterface a = new GameInterface();
	
	@Test
	public void testMovementOptimization() {
		GameInterface.gameInit();
		UnitManager.setup(GameInterface.grid[1][1].getUnit());
		assertEquals(1.5, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[3][2]), 0.01);
		assertEquals(0.0, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[1][3]), 0.01);
		assertEquals(0.5, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[2][0]), 0.01);
		assertEquals(-1, UnitManager.shortestPath(GameInterface.grid[1][1], GameInterface.grid[8][0]), 0.01);
		assertEquals(-1, UnitManager.shortestPath(GameInterface.grid[1][8], GameInterface.grid[8][0]), 0.01);
		assertEquals(1, UnitManager.shortestPath(GameInterface.grid[1][8], GameInterface.grid[0][8]), 0.01);
		assertEquals(-1, UnitManager.shortestPath(GameInterface.grid[12][1], GameInterface.grid[13][2]), 0.01);
		assertEquals(1, UnitManager.shortestPath(GameInterface.grid[12][1], GameInterface.grid[13][1]), 0.01);
	}
	
	@Test
	public void testUnitMovement() {
		GameInterface.gameInit();
		assertTrue(GameInterface.grid[2][0].getUnit() == null);
		UnitManager.moveUnit(GameInterface.grid[1][1], GameInterface.grid[2][0]);
		assertTrue(GameInterface.grid[2][0].getUnit() != null);
	}
	
	@Test
	public void testCurrentPlayerInteraction() {
		GameInterface.gameInit();
		//current player attempts to move own unit
		assertTrue(UnitManager.isValidMove(GameInterface.grid[1][1], GameInterface.grid[3][2]));
		//current player attempts to move other player's unit
		assertFalse(UnitManager.isValidMove(GameInterface.grid[1][8], GameInterface.grid[1][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][8], GameInterface.grid[12][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][1], GameInterface.grid[12][1]));
	}
	
	@Test
	public void testAttack() {
		GameInterface.gameInit();
		try {
			assertTrue(TurnManager.getCurrentPlayer().getNum() == 1);
			assertTrue(GameInterface.grid[1][1].getUnit() != null);
			assertTrue(GameInterface.grid[4][4].getUnit() == null);
			UnitManager.moveUnit(GameInterface.grid[1][1], GameInterface.grid[4][4]);
			assertTrue(GameInterface.grid[1][1].getUnit() == null);
			assertTrue(GameInterface.grid[4][4].getUnit() != null);
			TurnManager.endTurn();
			assertTrue(TurnManager.getCurrentPlayer().getNum() == 2);
			assertTrue(GameInterface.grid[1][8].getUnit() != null);
			assertTrue(GameInterface.grid[4][5].getUnit() == null);
			UnitManager.moveUnit(GameInterface.grid[1][8], GameInterface.grid[4][5]);
			assertTrue(GameInterface.grid[1][8].getUnit() == null);
			assertTrue(GameInterface.grid[4][5].getUnit() != null);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(GameInterface.grid[4][4], GameInterface.grid[4][5]));
			UnitManager.attack(GameInterface.grid[4][4], GameInterface.grid[4][5]);
			assertTrue(GameInterface.grid[4][4].getUnit() != null);
			assertTrue(GameInterface.grid[4][4].getUnit().getOwner().getNum() == 1);
			assertTrue(GameInterface.grid[4][5].getUnit() != null);
			assertTrue(GameInterface.grid[4][5].getUnit().getOwner().getNum() == 2);
			assertEquals(4, GameInterface.grid[4][4].getUnit().getAttack());
			assertEquals(6, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(10, GameInterface.grid[4][4].getUnit().getHealth());
			assertEquals(0.0, GameInterface.grid[4][4].getUnit().getMovementPts(), 0.01);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(GameInterface.grid[4][4], GameInterface.grid[4][5]));
			UnitManager.attack(GameInterface.grid[4][4], GameInterface.grid[4][5]);
			assertTrue(GameInterface.grid[4][4].getUnit() != null);
			assertTrue(GameInterface.grid[4][4].getUnit().getOwner().getNum() == 1);
			assertTrue(GameInterface.grid[4][5].getUnit() != null);
			assertTrue(GameInterface.grid[4][5].getUnit().getOwner().getNum() == 2);
			assertEquals(4, GameInterface.grid[4][4].getUnit().getAttack());
			assertEquals(2, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(10, GameInterface.grid[4][4].getUnit().getHealth());
			assertEquals(0.0, GameInterface.grid[4][4].getUnit().getMovementPts(), 0.01);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(GameInterface.grid[4][4], GameInterface.grid[4][5]));
			UnitManager.attack(GameInterface.grid[4][4], GameInterface.grid[4][5]);
			assertTrue(GameInterface.grid[4][4].getUnit() == null);
			assertTrue(GameInterface.grid[4][5].getUnit() != null);
			assertTrue(GameInterface.grid[4][5].getUnit().getOwner().getNum() == 1);
			assertEquals(4, GameInterface.grid[4][5].getUnit().getAttack());
			assertEquals(10, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(0.0, GameInterface.grid[4][5].getUnit().getMovementPts(), 0.01);
			
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[4][5], GameInterface.grid[12][1]));
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[4][5], GameInterface.grid[3][5]));
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[12][1], GameInterface.grid[3][5]));
		}
		catch(Exception e) {
			fail("Attacking unit failed");
		}
	}

	/*@Test
	public void testMouseClicks() {
		GameInterface a = new GameInterface();
		a.run();
		try {
		Robot robot = new Robot();
		robot.mouseMove(720-100, 100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(720-100, 150);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        assertTrue(GameInterface.grid[2][1].getUnit() != null);
		}
		catch (Exception e) {
			fail("Failed for some reason");
		}
	}*/
}
