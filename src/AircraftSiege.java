
public class AircraftSiege extends AircraftDefault {
	
	public AircraftSiege(Player p) {
		super(p);
		
		this.setCityAMultiplier(2);
		setSpecialAttack(getCityAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (getEnemyOnCity()) {
			return true;
		}
		else
			return false;
	}

	@Override
	public String toString(){
		return super.toString() + " (Siege)";
	}
}
