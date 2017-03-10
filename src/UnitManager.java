
public class UnitManager {

	public static void moveUnit(Tile tile1, Tile tile2) {
		Unit temp = tile1.getUnit();
		tile1.setUnit(null);	
		tile2.setUnit(temp);

	}
	
	
	

}
