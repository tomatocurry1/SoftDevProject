
public class AircraftDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 1;
	private double roadMultiplier = 1;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 1;
	private int oilCost = 1;
	private int steelCost = 0;
	private int creditCost = 700;
	public AircraftDefault(Player p) {
		super(p);
		
		setHealth(10);
		setAttack(4);
		setMovementPts(2);
	}

	public double getMultiplier(Terrain t) {
		if (t == Terrain.GRASSLANDS)
			return grasslandMultiplier;
		else if (t == Terrain.HILLS)
			return hillMultiplier;
		else if (t == Terrain.MOUNTAINS)
			return mountainMultiplier;
		else if (t == Terrain.ROADS) 
			return roadMultiplier;
		else 
			return waterMultiplier;
	}
	public int getOilCost() {
		return oilCost;
	}
	
	public int getSteelCost() {
		return steelCost;
	}
	
	public int getCreditCost() {
		return creditCost;
	}
}

