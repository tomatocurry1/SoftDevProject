
	public class Tile {
	
	Terrain terrain;
	Resource resource;
	Unit unit;
	City building;
	
	Resource getResource() {
		return resource;
	}
	
	
	Terrain getTerrain() {
		return  terrain;
	}
	
	Unit getUnit() {
		return unit;
	}
	
	void setUnit(Unit u) {
		unit = u;
	}
	
	void setTerrain(Terrain t) {
		terrain = t;
	}
}

