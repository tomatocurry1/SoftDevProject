
public class InfantryAntiTank extends InfantryDefault {
	
		
	public InfantryAntiTank(Player p) {
		super(p);
		
		this.setTankAMultiplier(1.5);
		setSpecialAttack(getTankAMultiplier() * getAttack());
	}
	
	public boolean useSpecial(Unit u) {
		if (isTank(u)) {
			System.out.println(getSpecialAttack());			
			System.out.println(getTankAMultiplier() * getAttack());
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
