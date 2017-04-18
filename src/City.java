
public class City {
	
	private Player owner;
	private int victoryPointWorth;
	
	
	public City() {
		victoryPointWorth = 1;
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
		return "City";
	}
}
