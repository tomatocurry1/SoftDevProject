import static org.junit.Assert.*;

import org.junit.Test;

public class UnitCreatorTest {

	@Test
	public void createAircraftEnough(){
		GameInterface.gameInit();
		//tile with oil
		GameInterface.grid[4][3].getResource().setOwner(TurnManager.getCurrentPlayer());
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		int credit = TurnManager.getCurrentPlayer().getCredits();
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
		GameInterface.gameInit();
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
	public void createTankEnough(){
		GameInterface.gameInit();
		//tile with steel
		GameInterface.grid[7][6].getResource().setOwner(TurnManager.getCurrentPlayer());
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
		GameInterface.gameInit();
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
		GameInterface.gameInit();
		//tile with steel
		GameInterface.grid[7][6].getResource().setOwner(TurnManager.plst[0]);
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

}
