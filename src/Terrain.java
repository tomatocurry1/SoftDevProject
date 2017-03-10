
public enum Terrain {

	GRASSLANDS(1.0, "Grasslands"),
	HILLS(1.5, "Hills"),
	MOUNTAINS(2.0, "Mountains"),
	ROADS(0.5, "Roads"),
	WATER(1.0, "Water");
	
	private double modifier;
	
	private String name;
	
	private Terrain (double value, String s) {
		modifier = value;
		name = s;
	}
	
	public double getModifier() {
		return modifier;
	}
	
	public String getName() {
		return name;
	}
	
}
