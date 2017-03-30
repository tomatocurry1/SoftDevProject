
public class UnitCreator {
	
	public static boolean canCreateUnit(Player p) {
		if (p.getOil() > 0 && p.getSteel() > 0)
			return true;
		return false;
	}
	
	public static void createUnit(Player p) {
		if (p.getNum() == 1) 
			GameInterface.grid[1][1].setUnit(new Unit(p));
		else if (p.getNum() == 2)
			GameInterface.grid[1][8].setUnit(new Unit(p));
		else if (p.getNum() == 3) 
			GameInterface.grid[12][8].setUnit(new Unit(p));
		else if (p.getNum() == 4)
			GameInterface.grid[12][1].setUnit(new Unit(p));
	}
	
}
