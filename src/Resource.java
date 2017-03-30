
public class Resource {

	private int price;
	private Player owner;
	
	public Resource(String resourceType) {
		if (resourceType.equals("Steel"))
			price = 100;
		else if (resourceType.equals("Oil"))
			price = 200;
		else
			throw new IllegalArgumentException("Must enter 'Steel' or 'Oil'");
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setOwner(Player p) {
		owner = p;
	}
	
	public Player getPlayer() {
		return owner;
	}
	
	@Override
	public String toString() {
		if (price == 100)
			return "Steel";
		else
			return "Oil";
	}
}
