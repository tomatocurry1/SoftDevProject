
public class UnitCreator {
	
	public static boolean canCreateUnit(Player p, String type) {
		switch(type){
			case "Tank":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>TankDefault.getCreditCost())
						return true;
			break;
		
		}
		return false;
	}
	
	public static void createUnit(Player p, int x, int y, String type) {
		if(canCreateUnit(p,type)){
			switch(type){
				case "Tank":
					GameInterface.grid[x][y].setUnit(new TankDefault(p));
					p.substractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
				break;
				
			}
		}
			
	}
	
}
