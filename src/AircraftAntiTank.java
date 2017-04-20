
public class AircraftAntiTank extends AircraftDefault {

	public AircraftAntiTank(Player p) {
		super(p);
		
		this.setTankAMultiplier(1.5);
		setSpecialAttack(getTankAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (isTank(u)) {
			return true;
		}
		else
			return false;
	}
	@Override
	public String toString(){
		return super.toString() + " (Anti-Tank)";
	}
}
