
public class UnitManager {

	public static void moveUnit(Tile tile1, Tile tile2, Unit u) {
		
		tile2.setUnit(u);
		tile1.setUnit(null);	
	}
	
	
	

}
