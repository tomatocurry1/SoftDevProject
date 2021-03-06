
public class AircraftAntiAir extends AircraftDefault {
	
	public AircraftAntiAir(Player p) {
		super(p);
		
		this.setAircraftAMultiplier(1.5);
		setSpecialAttack(getAircraftAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (isAircraft(u)) {
			return true;
		}
		else
			return false;
	}
	@Override
	public String toString(){
		return super.toString() + " (Anti-Air)";
	}
}
