
public class UnitManager {
	
	public static boolean isMoveValid(Tile tile1, Tile tile2) {
		Unit temp = tile1.getUnit();
		System.out.println(temp.getMovementPts());
		if ((Math.abs(tile2.getX() - tile1.getX()) + Math.abs(tile2.getY() - tile1.getY())) <= temp.getMovementPts()) {
			return true;
		}
		else
			return false;
		
	}

	public static void moveUnit(Tile tile1, Tile tile2) {
		Unit temp = tile1.getUnit();
		temp.setMovementPts(temp.getMovementPts() - (Math.abs(tile2.getX() - tile1.getX()) + Math.abs(tile2.getY() - tile1.getY())));
		tile1.setUnit(null);	
		tile2.setUnit(temp);
		Player p = tile2.getUnit().getOwner();
		//if tile has a resource
		if (tile2.getResource() != null)  {
			Resource r = tile2.getResource();
			if (r.toString().equals("Steel")) {
				if (r.getOwner() != null)
					r.getOwner().setSteel(r.getOwner().getSteel() - 1);
				p.setSteel(p.getSteel() + 1);
			}
			else {
				if (r.getOwner() != null)
					r.getOwner().setOil(r.getOwner().getOil() - 1);
				p.setOil(p.getOil() + 1);
			}
			tile2.getResource().setOwner(p);
		}
		if (tile2.getBuilding() != null)
			tile2.getBuilding().setOwner(p);
	}
	
	public static void attack(Tile tile1, Tile tile2) {
		Unit unit1 = tile1.getUnit();
		Unit unit2 = tile2.getUnit();
		unit2.decreaseHealth(unit1.getAttack());
		if (unit2.getHealth() <= 0) {
			moveUnit(tile1, tile2);
		}
		System.out.println("Remaining Health: " + unit2.getHealth());
	}
}
