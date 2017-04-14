
public class TankAntiTank extends TankDefault {
	
	public TankAntiTank(Player p) {
		super(p);
		
		this.setTankAMultiplier(1.5);
		InfantryAntiTank.setSpecialAttack(getTankAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (isTank(u)) {
			return true;
		}
		else
			return false;
	}

}
