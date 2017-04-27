import static org.junit.Assert.*;

import org.junit.Test;

public class TurnManagerTest {

	@Test
	public void elimiatePlayers() {
		TurnManager.plst = new Player[4];
		MapLoader.load(4);
		
		TurnManager.getCurrentPlayer().addCredits(4000);
		UnitCreator.createUnit(TurnManager.getCurrentPlayer(), 13, 8, "Infantry");
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		
		GameInterface.grid[12][8].setUnit(null);
		for(int i=0; i<4; i++)
			TurnManager.endTurn();
		if(TurnManager.getCurrentPlayer().getNum() != 1)
			assert(false);
		for(int i=0; i<3; i++)
			TurnManager.endTurn();
		if(TurnManager.getCurrentPlayer().getNum() != 1)
			assert(false);
	}

}
