import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

public class GameInterface {
	

	/*
	// this section gets the coordinates for the point clicked
	private static double getCursorPosX(long windowID) {
		DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(windowID, posX, null);
	    return posX.get(0);
	}
	
	private static double getCursorPosY(long windowID) {
		DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(windowID, null, posY);
	    return posY.get(0);
	}
	
	private static int getXCord(double value) {
		return (int) ((value / 68) + 1);
	}
	
	private static int getYCord(double value) {
		return (int) (11 - ((value - 40) / 68));
	}
	// end of the get point clicked section
	*/
	

	


	public static Tile[][] grid;
	private static Player plst[] = new Player[4];
	
	// initializes the game board and players
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
