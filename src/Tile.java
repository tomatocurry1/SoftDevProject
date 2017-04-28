public class Tile {
	
	Terrain terrain;
	Resource resource;
	Unit unit;
	City building;
	int xCord;
	int yCord;
	
	
	public Tile (Terrain t, Resource r, int X, int Y) {  // resource can be either a traditional resource or a building
		this.setTerrain(t);
		this.setResource(r);
		this.setUnit(null);
		xCord = X;
		yCord = Y;
	}
	
	public int getX() {
		return xCord;
	}
	
	public int getY() {
		return yCord;
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
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public City getBuilding() {
		return building;
	}
	
	public void setBuilding(City c) {
		building = c;
	}
}
