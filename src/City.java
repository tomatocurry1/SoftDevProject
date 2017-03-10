
public class City {
	
	private Player owner;
	
	private final boolean isCity;
	
	public City(boolean isCity) {
		this.isCity = isCity;
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player p) {
		owner = p;
	}
	
	public boolean isCity() {
		return isCity;
	}
	
	@Override
	public String toString() {
		if (isCity)
			return "City";
		else
			return "Base";
	}
}
