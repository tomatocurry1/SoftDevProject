
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
		if (tile2.getResource() != null) 
			tile2.getResource().setOwner(tile2.getUnit().getOwner());
		if (tile2.getBuilding() != null)
			tile2.getBuilding().setOwner(tile2.getUnit().getOwner());
	}
	
	
	
	
	

}
