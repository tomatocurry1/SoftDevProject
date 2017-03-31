	
public class Unit {

	private Player owner;
	private int health;
	private int attack;
	private double movementPts;
	private double tankAMultiplier;
	private double aircraftAMultiplier;
	private double cityAMultiplier;
	private double tankDMultiplier;
	private double aircraftDMultiplier;
	private double infantryDMultiplier;
	private double originalMovementPts;

	
	public Unit(Player p) {
		owner = p;
	}
	
	public double getMultiplier(Terrain t) {
		return 100000.0;
	}
	
	public Unit(Player p, int h, int a) {
		owner = p;
		health = h;
		attack = a;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public double getMovementPts() {
		return movementPts;
	}

	public void setMovementPts(double movementPts) {
		this.movementPts = movementPts;
	}
	
	public void setOriginalMovementPts(double origPts) {
		originalMovementPts = origPts;
	}
	
	
	public void resetMovePts() {
		this.setMovementPts(originalMovementPts);
	}

	public double getTankAMultiplier() {
		return tankAMultiplier;
	}

	public void setTankAMultiplier(double tankAMultiplier) {
		this.tankAMultiplier = tankAMultiplier;
	}

	public double getAircraftAMultiplier() {
		return aircraftAMultiplier;
	}

	public void setAircraftAMultiplier(double aircraftAMultiplier) {
		this.aircraftAMultiplier = aircraftAMultiplier;
	}

	public double getCityAMultiplier() {
		return cityAMultiplier;
	}

	public void setCityAMultiplier(double cityAMultiplier) {
		this.cityAMultiplier = cityAMultiplier;
	}

	public double getTankDMultiplier() {
		return tankDMultiplier;
	}

	public void setTankDMultiplier(double tankDMultiplier) {
		this.tankDMultiplier = tankDMultiplier;
	}

	public double getAircraftDMultiplier() {
		return aircraftDMultiplier;
	}

	public void setAircraftDMultiplier(double aircraftDMultiplier) {
		this.aircraftDMultiplier = aircraftDMultiplier;
	}

	public double getInfantryDMultiplier() {
		return infantryDMultiplier;
	}

	public void setInfantryDMultiplier(double infantryDMultiplier) {
		this.infantryDMultiplier = infantryDMultiplier;
	}
	
	public void decreaseHealth(int value) {
		health = health - value;
	}
}
