
public class InfantryDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 1;
	private double roadMultiplier = 1;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 500000;
	private int oilCost = 0;
	private int steelCost = 0;
	private int creditCost = 200;
	
	public InfantryDefault(Player p) {
		super(p);
		
		setHealth(6);
		setAttack(2);
		setMovementPts(2);
		setOriginalMovementPts(2);
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


