
public class TurnManager {

	public static int arrayPointer = 0;
	private static Player currentPlayer = new Player();
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
	
	public static void endTurn() {
		resetMovement();
		cyclePlayers();
	}
}

