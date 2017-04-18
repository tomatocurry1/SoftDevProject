
public class Base extends City {

	private Player owner;
	private int victoryPointWorth;
	
	
	public Base() {
		victoryPointWorth = 2;
	}
	
	public int getVictoryPointWorth() {
		return victoryPointWorth;
	}
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player p) {
		owner = p;
	}
	
	@Override
	public String toString() {
		return "Base";
	}

}
