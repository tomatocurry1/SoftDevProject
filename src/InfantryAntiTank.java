
public class InfantryAntiTank extends InfantryDefault {
	
	
	
	public InfantryAntiTank(Player p) {
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
