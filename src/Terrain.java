
public enum Terrain {

	GRASSLANDS(1.0),
	HILLS(1.5),
	MOUNTAINS(2.0),
	ROADS(0.5),
	WATER(1.0);
	
	private double modifier;
	
	private Terrain (double value) {
		modifier = value;
	}
	
	public double getModifier() {
		return modifier;
	}
	
}
