
public class UnitCreator {
	
	public static boolean canCreateUnit(Player p, String type) {
		if(p.getNum()!=TurnManager.getCurrentPlayer().getNum())
			return false;
		switch(type){
			case "Tank":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>TankDefault.getCreditCost())
						return true;
			break;
			case "Aircraft":
				if(p.getOil()-p.getOilUsed()>0)
					if(p.getCredits()>AircraftDefault.getCreditCost())
						return true;
			break;
			case "Infantry":
				if(p.getCredits()>InfantryDefault.getCreditCost())
					return true;
			break;
		
		}
		return false;
	}
	
	public static void createUnit(Player p, int x, int y, String type) {
		if(canCreateUnit(p,type)){
			switch(type){
				case "Infantry":
					GameInterface.grid[x][y].setUnit(new InfantryDefault(p));
					p.subtractCredits(InfantryDefault.getCreditCost());
				break;
				case "Tank":
					GameInterface.grid[x][y].setUnit(new TankDefault(p));
					p.subtractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
				break;
				case "Aircraft":
					GameInterface.grid[x][y].setUnit(new AircraftDefault(p));
					p.subtractCredits(AircraftDefault.getCreditCost());
					p.setOilUsed(p.getOilUsed()+1);
				break;
				
			}
		}
			
	}
	
}
