public class Tile {
	
	Terrain terrain;
	Resource resource;
	Unit unit;
	City building;
	
	public Tile () {  // resource can be either a traditional resource or a building
		this.setTerrain(Terrain.GRASSLANDS);
		this.setResource(null);
		this.setUnit(null);
	}
	
	public Resource getResource() {
		return resource;
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
	
	public void setResource(Resource r){
		resource = r;
	}
}
