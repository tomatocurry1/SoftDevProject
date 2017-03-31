
public class InfantryDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 1;
	private double roadMultiplier = 1;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 500000;
	
	public InfantryDefault(Player p) {
		super(p);
		
		setHealth(6);
		setAttack(2);
		setMovementPts(2);
	}

	public double getGrasslandMultipier() {
		return grasslandMultiplier;
	}
	
	public double getHillMultipier() {
		return hillMultiplier;
	}
	
	public double getRoadMultipier() {
		return roadMultiplier;
	}
	
	public double getMountainMultipier() {
		return mountainMultiplier;
	}
	
	public double getWaterMultipier() {
		return waterMultiplier;
	}
}


