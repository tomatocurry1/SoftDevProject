import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Test;

public class MovementTest {
	
	GameInterface a = new GameInterface();
	
	@Test
	public void testMovementOptimization() {
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		GameInterface.grid[1][1].setUnit(new TankDefault(TurnManager.getCurrentPlayer()));
		GameInterface.grid[1][8].setUnit(new TankDefault(TurnManager.getCurrentPlayer()));
		GameInterface.grid[12][1].setUnit(new TankDefault(TurnManager.getCurrentPlayer()));
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
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		assertTrue(GameInterface.grid[2][0].getUnit() == null);
		UnitManager.moveUnit(GameInterface.grid[1][1], GameInterface.grid[2][0]);
		assertTrue(GameInterface.grid[2][0].getUnit() != null);
	}
	
	@Test
	public void testCurrentPlayerInteraction() {
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//current player attempts to move own unit
		assertTrue(UnitManager.isValidMove(GameInterface.grid[1][1], GameInterface.grid[2][2]));
		//current player attempts to move other player's unit
		assertFalse(UnitManager.isValidMove(GameInterface.grid[1][8], GameInterface.grid[1][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][8], GameInterface.grid[12][9]));
		assertFalse(UnitManager.isValidMove(GameInterface.grid[12][1], GameInterface.grid[12][1]));
	}
	
	@Test
	public void checkCreditsForCities() {
		try {
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			assertTrue(a[5][2].getBuilding().getOwner() == null);
			UnitManager.moveUnit(a[1][1], a[5][2]);
			assertEquals(0, TurnManager.plst[0].getCredits());
			assertEquals(0, TurnManager.plst[1].getCredits());
			TurnManager.endTurn();
			assertTrue(a[5][2].getBuilding().getOwner().equals(TurnManager.plst[0]));
			assertEquals(500, TurnManager.plst[0].getCredits());
			assertEquals(250, TurnManager.plst[1].getCredits());
			TurnManager.endTurn();
			assertEquals(500, TurnManager.plst[1].getCredits());
			assertEquals(1000, TurnManager.plst[0].getCredits());
		}
		catch (Exception e) {
			fail("Credits are not allocated correctly");
		}
	}
	@Test
	public void resourceOwnership() {
		try	{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			a[1][3].setUnit(new InfantryDefault(TurnManager.plst[1]));
			assertTrue(a[1][2].getResource() != null);
			assertTrue(a[1][2].getResource().getOwner() == null);
			assertTrue(UnitManager.isValidMove(a[1][1], a[1][2]));
			UnitManager.moveUnit(a[1][1], a[1][2]);
			assertTrue(a[1][2].getResource() != null);
			assertTrue(a[1][2].getResource().getOwner() == null);
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			assertTrue(UnitManager.isValidMove(a[1][2], a[2][2]));
			UnitManager.moveUnit(a[1][2], a[2][2]);
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			assertTrue(UnitManager.isValidMove(a[1][3], a[1][2]));
			UnitManager.moveUnit(a[1][3], a[1][2]);
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[0]));
			TurnManager.endTurn();
			assertTrue(a[1][2].getResource().getOwner().equals(TurnManager.plst[1]));
		}
		catch (Exception e) {
			fail("Incorrect transfer of resource ownership");
		}
	}
	
	@Test
	public void testTankDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "Tank");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][3].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][3]);
			TurnManager.endTurn();
			assertEquals(2, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete tank");
		}
	}
	
	@Test
	public void testTankSiegeDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "TankSiege");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][3].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][3]);
			TurnManager.endTurn();
			assertEquals(2, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete tank");
		}
	}
	@Test
	public void testTankATDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "TankAT");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][3].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][3]);
			TurnManager.endTurn();
			assertEquals(2, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete tank");
		}
	}
	
	@Test
	public void testTankAADeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "TankAA");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][3].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][3]);
			TurnManager.endTurn();
			assertEquals(2, a[5][3].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete tank");
		}
	}
	
	@Test
	public void testAircraftDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][0]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "Aircraft");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][0].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][0]);
			TurnManager.endTurn();
			assertEquals(2, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete aircraft");
		}
	}
	
	@Test
	public void testAircraftSiegeDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][0]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "AircraftSiege");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][0].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][0]);
			TurnManager.endTurn();
			assertEquals(2, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete aircraft");
		}
	}
	
	@Test
	public void testAircraftATDeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][0]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "AircraftAT");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][0].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][0]);
			TurnManager.endTurn();
			assertEquals(2, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete aircraft");
		}
	}
	
	@Test
	public void testAircraftAADeletion() {
		try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			TurnManager.plst[0].addCredits(10000);
			UnitManager.moveUnit(a[1][1], a[5][0]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[0], 4, 4, "AircraftAA");
			assertEquals(1, TurnManager.getCurrentPlayer().getPlayerNum());
			assertEquals(1, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[5][0].getUnit() != null);
			assertTrue(a[4][4].getUnit() != null);
			UnitManager.moveUnit(a[1][8], a[5][0]);
			TurnManager.endTurn();
			assertEquals(2, a[5][0].getResource().getOwner().getPlayerNum());
			assertTrue(a[4][4].getUnit() == null);
			
		}
		catch (Exception e) {
			fail("did not correctly delete aircraft");
		}
	}
	
	
	@Test
	public void testAttack() {
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		try {
			assertTrue(TurnManager.getCurrentPlayer().getNum() == 1);
			assertTrue(GameInterface.grid[1][1].getUnit() != null);
			assertTrue(GameInterface.grid[4][4].getUnit() == null);
			UnitManager.moveUnit(GameInterface.grid[1][1], GameInterface.grid[4][4]);
			assertTrue(GameInterface.grid[1][1].getUnit() == null);
			assertTrue(GameInterface.grid[4][4].getUnit() != null);
			assertEquals(1, TurnManager.plst[1].getUnitsControlled());
			assertEquals(1, TurnManager.plst[1].getCitiesControlled());
			assertTrue(TurnManager.getCurrentPlayer().getNum() == 1);
			TurnManager.endTurn();
			assertEquals(2, TurnManager.getCurrentPlayer().getNum());
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
			assertEquals(2, GameInterface.grid[4][4].getUnit().getAttack());
			assertEquals(4, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(6, GameInterface.grid[4][4].getUnit().getHealth());
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
			assertEquals(2, GameInterface.grid[4][4].getUnit().getAttack());
			assertEquals(2, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(6, GameInterface.grid[4][4].getUnit().getHealth());
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
			assertEquals(2, GameInterface.grid[4][5].getUnit().getAttack());
			assertEquals(6, GameInterface.grid[4][5].getUnit().getHealth());
			assertEquals(0.0, GameInterface.grid[4][5].getUnit().getMovementPts(), 0.01);
			
		
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[4][5], GameInterface.grid[12][1]));
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[4][5], GameInterface.grid[3][5]));
			assertFalse(UnitManager.isAttackValid(GameInterface.grid[12][1], GameInterface.grid[3][5]));
		}
		catch(Exception e) {
			fail("Attacking unit failed");
		}
	}
	
	@Test
	public void createAircraftEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//tile with oil
		GameInterface.grid[5][0].getResource().setOwner(TurnManager.getCurrentPlayer());
		
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		TurnManager.getCurrentPlayer().addCredits(9000);
		int credit = TurnManager.getCurrentPlayer().getCredits();
		System.out.println(GameInterface.grid[5][0].getResource());
		System.out.println(TurnManager.getCurrentPlayer().getOil());
		System.out.println(UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(),"Aircraft"));
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 3, 2, "Aircraft");
		try{
			if(GameInterface.grid[3][2].getUnit() instanceof AircraftDefault){
				if(GameInterface.grid[3][2].getUnit().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()){
					if(TurnManager.getCurrentPlayer().getCredits()+AircraftDefault.getCreditCost() == credit){
						assert(true);
					}
				}
			}
			else{
				fail("Failed at making aircraft");
			}
		}catch(Exception e){
			fail("Error thrown while making aircraft");
		}
		
	}
	
	@Test
	public void createAircraftNotEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//tile with oil
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 4, 2, "Aircraft");
		try{
			if(GameInterface.grid[4][2].getUnit() instanceof AircraftDefault){
				fail("Not supposed to make an aircraft");
			}
			else{
				
			}
		}catch(Exception e){
			fail("Error thrown while making aircraft");
		}
		assert(true);
	}
	
	@Test
	public void createInfantryEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		int credit = TurnManager.getCurrentPlayer().getCredits();
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 3, 2, "Infantry");
		try{
			if(GameInterface.grid[3][2].getUnit() instanceof InfantryDefault){
				if(GameInterface.grid[3][2].getUnit().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()){
					if(TurnManager.getCurrentPlayer().getCredits()+InfantryDefault.getCreditCost() == credit){
						assert(true);
					}
				}
			}
			else{
				fail("Failed at making infantry");
			}
		}catch(Exception e){
			fail("Error thrown while making infantry");
		}
		
	}
	
	@Test
	public void createInfantryNotEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		TurnManager.getCurrentPlayer().subtractCredits(TurnManager.getCurrentPlayer().getCredits());
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 2, 2, "Infantry");
		try{
			if(GameInterface.grid[2][2].getUnit() instanceof InfantryDefault){
				fail("Not supposed to make a infantry");
			}
			else{
				
			}
		}catch(Exception e){
			fail("Error thrown while making infantry");
		}
		assert(true);
	}
	
	@Test
	public void createTankEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//2 tiles with steel
		GameInterface.grid[1][2].getResource().setOwner(TurnManager.getCurrentPlayer());
		GameInterface.grid[1][7].getResource().setOwner(TurnManager.getCurrentPlayer());
		TurnManager.getCurrentPlayer().addCredits(3000);
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		int credit = TurnManager.getCurrentPlayer().getCredits();
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 3, 2, "Tank");
		try{
			if(GameInterface.grid[3][2].getUnit() instanceof TankDefault){
				if(GameInterface.grid[3][2].getUnit().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()){
					if(TurnManager.getCurrentPlayer().getCredits()+TankDefault.getCreditCost() == credit){
						assert(true);
					}
				}
			}
			else{
				fail("Failed at making tank");
			}
		}catch(Exception e){
			fail("Error thrown while making tank");
		}
		
	}
	
	@Test
	public void createTankNotEnough(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//tile with steel
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 2, 2, "Tank");
		try{
			if(GameInterface.grid[2][2].getUnit() instanceof TankDefault){
				fail("Not supposed to make a tank");
			}
			else{
				
			}
		}catch(Exception e){
			fail("Error thrown while making tank");
		}
		assert(true);
	}
	
	
	@Test
	public void createWrongTurn(){
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		//tile with steel
		GameInterface.grid[1][2].getResource().setOwner(TurnManager.plst[0]);
		for(int i=0; i<5; i++)
			TurnManager.endTurn();
		int credit = TurnManager.plst[0].getCredits();
		UnitCreator.createUnit(TurnManager.plst[0], 3, 2, "Tank");
		try{
			if(GameInterface.grid[3][2].getUnit() instanceof TankDefault){
				if(GameInterface.grid[3][2].getUnit().getOwner().getNum() == TurnManager.plst[0].getNum()){
					if(TurnManager.plst[0].getCredits()+TankDefault.getCreditCost() == credit){
						fail("made unit when it was not the player's turn");
					}
				}
			}
			
		}catch(Exception e){
			fail("Error thrown while making tank");
		}
		assert(true);
		
	}

	
	/*
	@Test
	public void createUnits() {
		//try{
			TurnManager.plst = new Player[4];
			MapLoader.load(4);
			Tile[][] a = GameInterface.grid;
			for (int i = 0; i < 4; i++) {
				TurnManager.plst[i].addCredits(20000);
			}
			a[1][2].getResource().setOwner(TurnManager.plst[0]);
			a[1][7].getResource().setOwner(TurnManager.plst[1]);
			a[5][3].getResource().setOwner(TurnManager.plst[0]);
			a[8][0].getResource().setOwner(TurnManager.plst[2]);
			a[8][9].getResource().setOwner(TurnManager.plst[3]);
			a[5][0].getResource().setOwner(TurnManager.plst[2]);
			
			a[1][2].setUnit(new InfantryDefault((TurnManager.plst[0])));
			a[1][7].setUnit(new InfantryDefault((TurnManager.plst[1])));
			a[5][3].setUnit(new InfantryDefault((TurnManager.plst[0])));
			a[8][0].setUnit(new InfantryDefault((TurnManager.plst[2])));
			a[8][9].setUnit(new InfantryDefault((TurnManager.plst[3])));
			a[5][0].setUnit(new InfantryDefault((TurnManager.plst[2])));
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			
			UnitCreator.createUnit(TurnManager.plst[0], 3, 2, "TankAA");
			UnitCreator.createUnit(TurnManager.plst[0], 2, 2, "TankAT");
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[1], 1, 2, "TankSiege");
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[2], 2, 0, "AircraftAA");
			UnitCreator.createUnit(TurnManager.plst[2], 3, 3, "AircraftAT");
			TurnManager.endTurn();
			UnitCreator.createUnit(TurnManager.plst[3], 1, 0, "AircraftSiege");
			UnitManager.attack(a[2][2], a[1][2]);
			UnitManager.attack(a[1][2], a[1][1]);
			UnitManager.attack(a[1][0], a[1][1]);
			UnitManager.attack(a[2][0], a[1][0]);
			UnitManager.attack(a[3][3], a[3][2]);
			UnitManager.attack(a[3][2], a[3][3]);*/
	//	}
		//catch (Exception e) {
			//fail("Failure in creating all units");
		//}
	
	
	
	
	/*@Test
	public void destroyUnits() {
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		Tile[][] a = GameInterface.grid;
		Unit attacker;
		try {
			TurnManager.getCurrentPlayer().setSteel(1);
			TurnManager.plst[1].setSteel(1);
			TurnManager.plst[2].setOil(1);
			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 1, 1, "Tank");
			UnitCreator.createUnit(TurnManager.plst[1], 1, 2, "Tank");
			UnitCreator.createUnit(TurnManager.plst[2], 1, 3, "Aircraft");
			attacker = a[1][1].getUnit();
			//assertTrue(UnitManager.isAttackValid(a[1][1], a[1][2]));
			UnitManager.attack(a[1][1], a[1][2]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(a[1][1], a[1][2]));
			UnitManager.attack(a[1][1], a[1][2]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(a[1][1], a[1][2]));
			UnitManager.attack(a[1][1], a[1][2]);
			assertTrue(a[1][1].getUnit().equals(null));
			assertTrue(attacker.equals(a[1][2]));
			assertTrue(UnitManager.isAttackValid(a[1][2], a[1][3]));
			UnitManager.attack(a[1][2], a[1][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(UnitManager.isAttackValid(a[1][2], a[1][3]));
			UnitManager.attack(a[1][2], a[1][3]);
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			TurnManager.endTurn();
			assertTrue(a[1][2].getUnit().equals(null));
			assertTrue(attacker.equals(a[1][3]));
		}
		catch (Exception e) {
			fail("Destroying unit failed");
		}
	}

	
	
	
	@Test
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
	
	
	@Test
	public void testLoadingGame() {
		GameInterface a = new GameInterface();
		a.run();
	}
	
}
