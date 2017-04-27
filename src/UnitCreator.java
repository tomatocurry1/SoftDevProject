
public class UnitCreator {
	
	public static boolean canCreateUnit(Player p, String type) {
		if(p.getNum()!=TurnManager.getCurrentPlayer().getNum())
			return false;
		switch(type){
			case "Tank":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>=TankDefault.getCreditCost())
						return true;
			break;
			case "TankAT":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>=TankDefault.getCreditCost())
						return true;
			break;
			case "TankAA":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>=TankDefault.getCreditCost())
						return true;
			break;
			case "TankSiege":
				if(p.getSteel()-p.getSteelUsed()>0)
					if(p.getCredits()>=TankDefault.getCreditCost())
						return true;
			break;
			case "Aircraft":
				if(p.getOil()-p.getOilUsed()>0)
					if(p.getCredits()>=AircraftDefault.getCreditCost())
						return true;
			break;
			case "AircraftAT":
				if(p.getOil()-p.getOilUsed()>0)
					if(p.getCredits()>=AircraftDefault.getCreditCost())
						return true;
			break;
			case "AircraftAA":
				if(p.getOil()-p.getOilUsed()>0)
					if(p.getCredits()>=AircraftDefault.getCreditCost())
						return true;
			break;
			case "AircraftSiege":
				if(p.getOil()-p.getOilUsed()>0)
					if(p.getCredits()>=AircraftDefault.getCreditCost())
						return true;
			break;
			case "Infantry":
				if(p.getCredits()>=InfantryDefault.getCreditCost())
					return true;
			break;
			case "InfantryAT":
				if(p.getCredits()>=InfantryDefault.getCreditCost())
					return true;
			break;
			case "InfantryAA":
				if(p.getCredits()>=InfantryDefault.getCreditCost())
					return true;
			break;
			case "InfantrySiege":
				if(p.getCredits()>=InfantryDefault.getCreditCost())
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
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "InfantryAT":
					GameInterface.grid[x][y].setUnit(new InfantryAntiTank(p));
					p.subtractCredits(InfantryDefault.getCreditCost());
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "InfantryAA":
					GameInterface.grid[x][y].setUnit(new InfantryAntiAir(p));
					p.subtractCredits(InfantryDefault.getCreditCost());
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "InfantrySiege":
					GameInterface.grid[x][y].setUnit(new InfantrySiege(p));
					p.subtractCredits(InfantryDefault.getCreditCost());
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "Tank":
					GameInterface.grid[x][y].setUnit(new TankDefault(p));
					p.subtractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "TankAT":
					GameInterface.grid[x][y].setUnit(new TankAntiTank(p));
					p.subtractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "TankAA":
					GameInterface.grid[x][y].setUnit(new TankAntiAir(p));
					p.subtractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "TankSiege":
					GameInterface.grid[x][y].setUnit(new TankSiege(p));
					p.subtractCredits(TankDefault.getCreditCost());
					p.setSteelUsed(p.getSteelUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "Aircraft":
					GameInterface.grid[x][y].setUnit(new AircraftDefault(p));
					p.subtractCredits(AircraftDefault.getCreditCost());
					p.setOilUsed(p.getOilUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "AircraftAT":
					GameInterface.grid[x][y].setUnit(new AircraftAntiTank(p));
					p.subtractCredits(AircraftDefault.getCreditCost());
					p.setOilUsed(p.getOilUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "AircraftAA":
					GameInterface.grid[x][y].setUnit(new AircraftAntiAir(p));
					p.subtractCredits(AircraftDefault.getCreditCost());
					p.setOilUsed(p.getOilUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				case "AircraftSiege":
					GameInterface.grid[x][y].setUnit(new AircraftSiege(p));
					p.subtractCredits(AircraftDefault.getCreditCost());
					p.setOilUsed(p.getOilUsed()+1);
					p.setUnitsControlled(TurnManager.getCurrentPlayer().getUnitsControlled() + 1);
				break;
				
			}
			GameInterface.grid[x][y].getUnit().setMovementPts(0);
			if (GameInterface.grid[x][y].getUnit() instanceof AircraftDefault)
				p.addAircraft(GameInterface.grid[x][y].getUnit());
			else if (GameInterface.grid[x][y].getUnit() instanceof TankDefault)
				p.addTank(GameInterface.grid[x][y].getUnit());
		}
			
	}
	
}
