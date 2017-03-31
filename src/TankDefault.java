
public class TankDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 2;
	private double roadMultiplier = .5;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 500000;
	private int oilCost = 0;
	private int steelCost = 1;
	private static int creditCost = 500;
	
	public TankDefault(Player p) {
		super(p);
		
		setHealth(10);
		setAttack(4);
		setMovementPts(3);
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
	
	public static int getCreditCost() {
		return creditCost;
	}
	
}
