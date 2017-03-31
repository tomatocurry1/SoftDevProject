
public class UnitCreator {
	
	public static boolean canCreateUnit(Player p, String type) {
		if (p.getOil() > 0 && p.getSteel() > 0)
			return true;
		return false;
	}
	
	public static void createUnit(Player p, int x, int y, String type) {
		if(canCreateUnit(p,type)){
			switch(type){
				case "Tank":
					GameInterface.grid[x][y].setUnit(new TankDefault(p));
				break;
				
			}
		}
			
	}
	
}
