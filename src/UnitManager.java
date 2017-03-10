
public class UnitManager {

	public static void moveUnit(Tile tile1, Tile tile2) {
		tile2.setUnit(tile1.getUnit());
		tile1.setUnit(null);	
	}
	
	
	

}
