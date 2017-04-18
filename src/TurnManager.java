import org.lwjgl.opengl.GL11;

public class TurnManager {

	public static int arrayPointer = 0;
	private static Player currentPlayer;
	public static Player plst[] = new Player[4];
	private static int pntLmt = 8;
	private static int numOfPlayers = 4;

	
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(Player p){
		currentPlayer = p;
	}
	
	public static void cyclePlayers() {
		if (arrayPointer == (numOfPlayers -1)) {
			arrayPointer = 0;
		}
		else
			arrayPointer++;
		currentPlayer = plst[arrayPointer];
	}
	
	public static void  resetMovement() {
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 10; j++) {
				
				if (GameInterface.grid[i][j].getUnit() != null) {
					GameInterface.grid[i][j].getUnit().resetMovePts();
				}
			}
		}
	}
	
	public static void changeCapturedOwnership(){
		for(int i = 0; i < GameInterface.grid.length; i++)
			for(int j = 0; j < GameInterface.grid[0].length; j++){
				Tile checkCaptured = GameInterface.grid[i][j];
				//Building captured?
				if(checkCaptured.getBuilding()!=null){
					if(checkCaptured.getUnit()!=null)
						if(checkCaptured.getBuilding().getOwner() == null ||checkCaptured.getUnit().getOwner().getNum() != checkCaptured.getBuilding().getOwner().getNum()){
							
							Player attacker = checkCaptured.getUnit().getOwner();
							checkCaptured.getBuilding().setOwner(attacker);
						}
				}
				
				//Resource captured?
				if(checkCaptured.getResource()!=null){
					if(checkCaptured.getUnit()!=null) {
						if(checkCaptured.getResource().getOwner() == null) {
							Player attacker = checkCaptured.getUnit().getOwner();
							checkCaptured.getResource().setOwner(attacker);
						}
						else if (checkCaptured.getUnit().getOwner().getNum() != checkCaptured.getResource().getOwner().getNum()){
							Player attacker = checkCaptured.getUnit().getOwner();
							checkCaptured.getResource().setOwner(attacker);
						}
					}
				}
			}
	}
	
	public static void calculateResources(){
		changeCapturedOwnership();
		
		for(int k = 0; k < plst.length; k ++){
			plst[k].setOil(0);
			plst[k].setSteel(0);
			plst[k].setVictoryPoints(0);
			plst[k].setOilUsed(0);
			plst[k].setSteelUsed(0);
		}
		
		
		for(int i = 0; i < GameInterface.grid.length; i++)
			for(int j = 0; j < GameInterface.grid[0].length; j++){
				Tile temp = GameInterface.grid[i][j];
				
				if(temp.getResource() != null){
					if(temp.getResource().toString().equals("Steel")){
						if(temp.getResource().getOwner()!= null)
							temp.getResource().getOwner().setSteel(temp.getResource().getOwner().getSteel()+1);
					}else if(temp.getResource().toString().equals("Oil")){
						if(temp.getResource().getOwner()!= null)
							temp.getResource().getOwner().setOil(temp.getResource().getOwner().getOil()+1);
					}
				}
				
				
				
				if(temp.getBuilding()!=null){
					if(temp.getBuilding().getOwner()!=null){
						temp.getBuilding().getOwner().setVictoryPoints(temp.getBuilding().getOwner().getVictoryPoints()+temp.getBuilding().getVictoryPointWorth());
						if(temp.getBuilding().getOwner()!=null)
							temp.getBuilding().getOwner().addCredits(250);
					}
				}
				
				if(temp.getUnit() != null){
					if(temp.getUnit() instanceof AircraftDefault){
						Player owner = temp.getUnit().getOwner();
						owner.setOilUsed(owner.getOilUsed()+1);
					}
					if(temp.getUnit() instanceof TankDefault){
						Player owner = temp.getUnit().getOwner();
						owner.setSteelUsed(owner.getSteelUsed()+1);
					}
				}
			}
	}
	
	private static void checkResourceUnitTransfer() {
		for (int i = 0; i < plst.length; i++ ) {
			while (plst[i].getOil() < plst[i].getOilUsed()) {
				deleteAircraft(plst[i]);
			}
			while (plst[i].getSteel() < plst[i].getSteelUsed()) {
				deleteTank(plst[i]);
			}
		}
	}
	
	private static void deleteTank(Player p) {
		boolean found = false;
		int i = 0;
		while (!found && i < GameInterface.grid.length) {
			int j = 0;
			while (!found && j < GameInterface.grid[i].length) {
				if (GameInterface.grid[i][j].getUnit() != null && GameInterface.grid[i][j].getUnit().getOwner().equals(p) && (GameInterface.grid[i][j].getUnit() instanceof TankDefault)) {
					GameInterface.grid[i][j].setUnit(null);
					p.setSteelUsed(p.getSteelUsed() - 1);
					found = true;
				}
				j++;
			}
			i++;
		}
		}
	
	
	private static void deleteAircraft(Player p) {
		boolean found = false;
		int i = 0;
		while (!found && i < GameInterface.grid.length) {
			int j = 0;
			while (!found && j < GameInterface.grid[i].length) {
				if (GameInterface.grid[i][j].getUnit() != null && GameInterface.grid[i][j].getUnit().getOwner().equals(p) && (GameInterface.grid[i][j].getUnit() instanceof AircraftDefault)) {
					GameInterface.grid[i][j].setUnit(null);
					p.setOilUsed(p.getOilUsed() - 1);
					found = true;
					}
				j++;
				}	
				i++;
			}
		}
	
	public static void pointLimit() {
		
			if (currentPlayer.getVictoryPoints() >= pntLmt) {
				currentPlayer.setEndGameCounter(currentPlayer.getEndGameCounter() + 1);
			}
				else
					currentPlayer.setEndGameCounter(0);
	}
	
	public static boolean endGame() {
		for (int i = 0; i < numOfPlayers; i++) {
			if (plst[i].getEndGameCounter() >= 2) {
				return true; 
			}
		}
		return false;
	}
	
	public static void endTurn() {
		resetMovement();
		calculateResources();
		pointLimit();

		checkResourceUnitTransfer();

		cyclePlayers();
		endGame();
		System.out.println(endGame());
	}
}

