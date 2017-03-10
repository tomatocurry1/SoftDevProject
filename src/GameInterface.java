
public class GameInterface {
	
	public static Tile[][] grid;
	private static Player plst[] = new Player[4];
	
	public static void init(){
		
		plst[0] = new Player();
		
		grid = new Tile[10][14];
		
		for(int i=0; i<10;i++){
			for(int j=0; j<14;j++){
				grid[i][j] = new Tile(Terrain.GRASSLANDS, null);
				if((j==5&&i<5)||(j==6&&i>=5)){
					grid[i][j].setTerrain(Terrain.WATER);
				}

			}
		}
		grid[2][4].setUnit(new Unit(plst[0]));
		
	}
	
	void loop(){
		
	}
	
	void displayTile(Tile tile, int x, int y){
		
	}
	
	Tile findTile(int x, int y){
		
		return null;
	}
}
