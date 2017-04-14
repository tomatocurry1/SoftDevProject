
public class TankAntiAir extends TankDefault {
	
	public TankAntiAir(Player p) {
		super(p);
		
		this.setAircraftAMultiplier(1.5);
		InfantryAntiTank.setSpecialAttack(getAircraftAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (isAircraft(u)) {
			return true;
		}
		else
			return false;
	}

}
