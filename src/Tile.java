
	public class Tile {
	
	Terrain terrain;
	Resource resource;
	Unit unit;
	City building;
	
	public Tile (Terrain t, Resource r) {  // resource can be either a traditional resource or a building
		this.setTerrain(t);
		this.setResource(r);
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public void setResource(Resource r) {
		resource = r;
	}
	
	
	public Terrain getTerrain() {
		return  terrain;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit u) {
		unit = u;
	}
	
	public void setTerrain(Terrain t) {
		terrain = t;
	}
}

