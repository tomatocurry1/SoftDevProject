	
public class Unit {

	private double specialAttack = 0;
	private static boolean enemyOnCity;
	private Player owner;
	private int health;
	private int attack;
	private double movementPts;
	private double tankAMultiplier;
	private double aircraftAMultiplier;
	private double cityAMultiplier;
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

	public  void setHealth(int d) {
		health = d;
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
	public double getOriginalMovementPts() {
		return originalMovementPts;
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
	

	public void decreaseHealth(int d) {
		this.setHealth(health - d);
}
	
	public static boolean isInfantry(Unit u) {
		if (u.getOriginalMovementPts() == 2) 
			return true;
		else
			return false;	
	}
	public static boolean isTank(Unit u) {
		if (u.getOriginalMovementPts() == 3) 
			return true;
		else
			return false;	
	}
	public static boolean isAircraft(Unit u) {
		if (u.getOriginalMovementPts() == 5) 
			return true;
		else
			return false;
	}
	
	public boolean useSpecial(Unit u) {
		return false;
	}
	
	public  double getSpecialAttack(Unit u) {
		return specialAttack ;
	}

	public  double getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(double specialAttack) {
		this.specialAttack = specialAttack;
	}

	public static void setEnemyOnCity(boolean b) {
		enemyOnCity = true;
		
	}
	
	public static boolean getEnemyOnCity() {
		return enemyOnCity;
		
	}

	
}
