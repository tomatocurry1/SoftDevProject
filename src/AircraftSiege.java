
public class AircraftSiege extends AircraftDefault {
	
	public AircraftSiege(Player p) {
		super(p);
		
		this.setCityAMultiplier(1.5);
		InfantryAntiTank.setSpecialAttack(getCityAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (getEnemyOnCity()) {
			return true;
		}
		else
			return false;
	}

}
