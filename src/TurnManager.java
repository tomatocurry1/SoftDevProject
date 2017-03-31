
public class TurnManager {

	public static int arrayPointer = 0;
	private static Player currentPlayer;
	public static Player plst[] = new Player[4];
	
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(Player p){
		currentPlayer = p;
	}
	
	public static void cyclePlayers() {
		if (arrayPointer == 3) {
			arrayPointer = 0;
		}
		else
			arrayPointer++;
		currentPlayer = plst[arrayPointer];
		System.out.println(currentPlayer);
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
					if(checkCaptured.getUnit()!=null)
						if(checkCaptured.getResource().getOwner() == null || checkCaptured.getUnit().getOwner().getNum() != checkCaptured.getResource().getOwner().getNum()){
							
							Player attacker = checkCaptured.getUnit().getOwner();
							checkCaptured.getResource().setOwner(attacker);
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
						temp.getBuilding().getOwner().setVictoryPoints(temp.getBuilding().getOwner().getVictoryPoints()+1);
						if(temp.getBuilding().getOwner()!=null)
							temp.getBuilding().getOwner().addCredits(250);
					}
				}
			}
	}
	
	public static void endTurn() {
		resetMovement();
		calculateResources();
		cyclePlayers();
		
	}
}

