
public class AircraftDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 1;
	private double roadMultiplier = 1;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 1;
		
	public AircraftDefault(Player p) {
		super(p);
		
		setHealth(10);
		setAttack(4);
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

