
public class TankDefault extends Unit {

	private double grasslandMultiplier = 1;
	private double hillMultiplier = 2;
	private double roadMultiplier = .5;
	private double mountainMultiplier = 500000;
	private double waterMultiplier = 500000;
	
	public TankDefault(Player p) {
		super(p);
		
		setHealth(10);
		setAttack(4);
		setMovementPts(3);
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