
public class TurnManager {

	private int arrayPointer = 0;
	private static Player currentPlayer = new Player();
	public static Player plst[] = new Player[4];
	
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void setCurrentPlayer(Player p){
		currentPlayer = p;
	}
	
	public void endTurn() {
		
		if (arrayPointer == 3) {
			arrayPointer = 0;
		}
		else
			arrayPointer++;
		
		currentPlayer = plst[arrayPointer];
	}
}
