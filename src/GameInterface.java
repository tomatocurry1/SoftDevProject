import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameInterface {
	
	public static Tile[][] grid;
	private static String info;
	
	// initializes the game board and players
	public static void gameInit(){
		
		//creates new players and assigns their colors
		TurnManager.plst[0] = new Player(1, 0.5f,0.0f,0.8f);
		TurnManager.plst[1] = new Player(2, 0.8f,0.4f,0.f);
		TurnManager.plst[2] = new Player(3, 0.0f,0.5f,0.8f);
		TurnManager.plst[3] = new Player(4, 0.8f,0.5f,0.8f);
		
		//sets the current player to be the first player
		TurnManager.setCurrentPlayer(TurnManager.plst[0]);
		
		//makes matrix of tiles
		grid = new Tile[14][10];
		
		//places the terrain and resources on the board
		for(int i=0; i<14;i++){
			for(int j=0; j<10;j++){
				grid[i][j] = new Tile(Terrain.GRASSLANDS, null,i,j);
				if((j==5)||(j==4)){
					grid[i][j].setTerrain(Terrain.WATER);
				}
				
				if ((j==3  && (i>4 && i<9)) || (j==6 && (i>4 && i<9))) 
					grid[i][j].setTerrain(Terrain.HILLS);
				
				if (((i>=0 && i<4) || (i<= 13 && i>10)) && (j==2 || j ==7))
					grid[i][j].setTerrain(Terrain.HILLS);
				
				if ((j==0 || j==9) && (i==11 ||i == 10 || i == 2 || i == 3))
					grid[i][j].setTerrain(Terrain.HILLS);
				
				if ((j==1 || j==8) && (i ==0 || i == 13))
					grid[i][j].setTerrain(Terrain.HILLS);
				
				if ((j==0 || j==9) && (i ==0 || i == 13))
					grid[i][j].setTerrain(Terrain.HILLS);
				
				if ((j==1 || j==8) && (i==5 || i==8)) 
					grid[i][j].setTerrain(Terrain.HILLS);
					
				if ((j==4 || j==5 || j ==3 || j == 6)  && (i == 4 || i == 9))
					grid[i][j].setTerrain(Terrain.ROADS);

				if ((j == 1 || j == 0 || j == 8 || j == 9) && (i==1 || i==12))
					grid[i][j].setTerrain(Terrain.ROADS);
				
				if ((i==2 || i==3 || i==10 || i==11) && (j==1 || j==8))
					grid[i][j].setTerrain(Terrain.ROADS);
				
				if ((i==3 || i==4 || i==9 || i==10) && (j==2 || j==7))
					grid[i][j].setTerrain(Terrain.ROADS);
				
				if (((j==1 || j == 2) && (i==6 || i == 7)) || ((j == 7 || j ==8) && (i == 6 || i == 7))) {
					grid[i][j].setTerrain(Terrain.MOUNTAINS);
				}
				
				if  ((i==1 || i ==12) && (j==2 || j == 7)) 
					grid[i][j].setResource(new Steel());
				
				if ((i==5 || i==8) && (j==3 || j ==6))
					grid[i][j].setResource(new Steel());
				
				if ((j==0 || j ==9) && (i == 5 || i == 8)) 
					grid[i][j].setResource(new Oil());
				
				
				
				
			}
		}
		//puts units on board for each player
		grid[1][1].setUnit(new TankDefault(TurnManager.plst[0]));
		grid[1][8].setUnit(new TankDefault(TurnManager.plst[1]));
		grid[12][8].setUnit(new TankDefault(TurnManager.plst[2]));
		grid[12][1].setUnit(new TankDefault(TurnManager.plst[3]));

		
		//puts bases on the board for each player and assigns the owner of the base
		grid[0][0].setBuilding(new City(false));
		grid[0][0].getBuilding().setOwner(TurnManager.plst[0]);
		grid[0][9].setBuilding(new City(false));
		grid[0][9].getBuilding().setOwner(TurnManager.plst[1]);
		grid[13][9].setBuilding(new City(false));
		grid[13][9].getBuilding().setOwner(TurnManager.plst[2]);
		grid[13][0].setBuilding(new City(false));
		grid[13][0].getBuilding().setOwner(TurnManager.plst[3]);
		grid[5][2].setBuilding(new City(true));
		grid[8][2].setBuilding(new City(true));
		grid[5][7].setBuilding(new City(true));
		grid[8][7].setBuilding(new City(true));
		
	}

	private Tile lastTile = null;
	private Tile justClickedTile = null;
	private static int xCord;
	private static int yCord;
	private double posX;
	private double posY;

	// The window handle
	private long window;
	
	GLFWMouseButtonCallback mouseCallback;

	public void run() {
		
		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(1280, 720, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});
		
		glfwSetMouseButtonCallback(window, mouseCallback = new GLFWMouseButtonCallback() {
		    @Override
		    public void invoke(long wind, int button, int action, int mods) {
		    	
		    	if (action == GLFW_RELEASE) {
		    	posX = getCursorPosX(window);
		    	posY = getCursorPosY(window);
		    	
		    	if (posY > 40 && posX < 952) { //if mouse clicked on the board
			    	
			    	xCord = getXCord(posX);
		    		yCord = getYCord(posY);
		    		lastTile = justClickedTile;
			    	justClickedTile = GameInterface.grid[xCord][yCord];
		    		
			    	if (button == GLFW_MOUSE_BUTTON_1) {
				    	
			    		//prints the coordinates of the tile that was clicked
				    	System.out.println("xCord: " + xCord + ", yCord: " + yCord);
				    	
			
				    	if (justClickedTile.getUnit() != null) {
				    		//System.out.println("Oil: " + justClickedTile.getUnit().getOwner().getOil());
				    		//System.out.println("Steel: " + justClickedTile.getUnit().getOwner().getSteel());
				    		System.out.println("Has Unit! Attack: " + justClickedTile.getUnit().getAttack());
				    		System.out.println("Movement points: " + justClickedTile.getUnit().getMovementPts());
				    	}
				    	else
				    		System.out.println("No Unit");
				    	//determines if the current player can move their unit on lastTile to the justClickedTile
				    	if ((lastTile != justClickedTile) && lastTile != null && lastTile.getUnit() != null &&  
				    		UnitManager.isValidMove(lastTile, justClickedTile) ) {
				    		
				    		if (UnitManager.isAttackValid(lastTile, justClickedTile)) {
				    			UnitManager.attack(lastTile, justClickedTile);
				    			}
				    		else if (justClickedTile.getUnit() == null){
				    			UnitManager.moveUnit(lastTile, justClickedTile);
				    		}
				    		lastTile = null;
				    		justClickedTile = null;
				    	}
			    	}
			    	//right clicking displays the terrain, resource, and building????
			    	else if (button == GLFW_MOUSE_BUTTON_2) {
			    		System.out.println("Terrain is: " + justClickedTile.getTerrain().toString());
				    	if (justClickedTile.getBuilding() == null)
				    		System.out.println("No Building");
				    	else
				    		System.out.println("Has Building: " + justClickedTile.getBuilding().toString());
				    	if (justClickedTile.getResource() == null)
				    		System.out.println("No Resource");
				    	else
				    		System.out.println("Has Resource: " + justClickedTile.getResource().toString());
			    	}
		    	
		    	}
		    	//if "end turn" button clicked
		    	if(posX > 68*14+30 && posX < 1280-30 && posY < 720-20 && posY > 720-150){
		    		System.out.println("End Turn button clicked");
		    		TurnManager.endTurn();
		    		justClickedTile = null;
		    		lastTile = null;
		    	}
		   
		    	if (posX > 952 + 40 && posX < 952 + 140 && posY > 260 && posY < 330) {
		    		System.out.println("Trying to buy Infantry");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum())
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Infantry")){
		    				System.out.println("can buy Infantry");
		    				UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Infantry");
		    			}
		    	}
		    	else if (posX > 952 + 40 && posX < 952 + 140 && posY > 370 && posY < 440) {
		    		System.out.println("Trying to buy Tank");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum())
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Tank")){
		    				System.out.println("can buy Tank");
		    				UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Tank");
		    			}
		    	}
		    	else if (posX > 952 + 40 && posX < 952 + 140 && posY > 480 && posY < 550) {
		    		System.out.println("Trying to buy Aircraft");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum())
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Aircraft")){
		    				System.out.println("can buy Aircraft");
		    				UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Aircraft");
		    			}
		    	}
		    }
		    }
		}); 

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
		
		gameInit();
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1280, 0, 720, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			 
		    // set the color of the quad (R,G,B)
		    
		    GL11.glColor3f(0.1f,0.5f,1.0f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(952,0);
			GL11.glVertex2f(1280,0);
			GL11.glVertex2f(1280,720);
			GL11.glVertex2f(952,720);
		    GL11.glEnd();
		    
		    GL11.glColor3f(0.0f,0.5f,0.7f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(0,720-40);
			GL11.glVertex2f(1280,720-40);
			GL11.glVertex2f(1280,720);
			GL11.glVertex2f(0,720);
		    GL11.glEnd();
		    
		    GL11.glColor3f(0.8f,0.0f,0.5f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(0,0);
			GL11.glVertex2f(68*14,0);
			GL11.glVertex2f(68*14,720-40);
			GL11.glVertex2f(0,720-40);
		    GL11.glEnd();
		    
		    drawInfantryButton(false);
		    drawTankButton(false);
		    drawAircraftButton(false);
		    drawAntiInfantryButton(false);
		    drawAntiTankButton(false);
		    drawAntiAircraftButton(false);
		    
		    for(int i=0; i<10; i++){
		    	for(int j = 0; j<14; j++){
		    	
				    //draws the terrain on the board
		    		if (GameInterface.grid[j][i].getTerrain() != null)
		    			drawTerrain(i, j, GameInterface.grid[j][i].getTerrain());
		
				    //draws the units on the board
				    if(GameInterface.grid[j][i].getUnit()!=null){
				    	drawUnit(i, j, GameInterface.grid[j][i].getUnit());
				    }
				    
				    //draws the buildings on board
				    if(GameInterface.grid[j][i].getBuilding() != null) {
				    	drawBuilding(j, i, GameInterface.grid[j][i].getBuilding());
				    }
				    
				    //draws the resource on the board
				    if (GameInterface.grid[j][i].getResource() != null) {
				    	drawResource(i, j, GameInterface.grid[j][i].getResource());
				    }
		    	}
		    }
		    
		    //draws the boarders of the tiles
		    for (int i = 1; i < 14; i++) {
			    GL11.glColor3f(0.0f,0.0f,0.0f);
			    GL11.glBegin(GL11.GL_LINES);
			    GL11.glLineWidth((float)2.5); 
				GL11.glVertex2f(68*i,680);
				GL11.glVertex2f(68*i,0);
			    GL11.glEnd();
		    }
		    
		    for (int i = 1; i < 10; i++) {
			    GL11.glColor3f(0.0f,0.0f,0.0f);
			    GL11.glBegin(GL11.GL_LINES);
			    GL11.glLineWidth((float)2.5); 
				GL11.glVertex2f(0,68*i);
				GL11.glVertex2f(952,68*i);
			    GL11.glEnd();
		    }

		    //highlights the tile that is just clicked
		    if (justClickedTile != null) {
		    	
		    	GL11.glColor3f(1.0f, 0.0f, 0.0f);
		    	GL11.glBegin(GL11.GL_LINES);
		    	GL11.glLineWidth((float)2);
		    	GL11.glVertex2f(68*xCord, 68*yCord);
		    	GL11.glVertex2f(68*xCord + 68, 68*yCord);
		    	GL11.glVertex2f(68*xCord, 68*yCord + 68);
		    	GL11.glVertex2f(68*xCord + 68, 68*yCord + 68);
		    	GL11.glVertex2f(68*xCord, 68*yCord);
		    	GL11.glVertex2f(68*xCord, 68*yCord + 68);
		    	GL11.glVertex2f(68*xCord + 68, 68*yCord);
		    	GL11.glVertex2f(68*xCord + 68, 68*yCord + 68);
		    	GL11.glEnd();
		    }
		    
		    //draws the end turn button
			GL11.glColor3f(0.8f,0.2f,0.6f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(68*14+30,20);
			GL11.glVertex2f(1280-30,20);
			GL11.glVertex2f(1280-30,150);
			GL11.glVertex2f(68*14+30,150);
		    GL11.glEnd(); 
		    
		    if(justClickedTile!=null)
		    	if(justClickedTile.getUnit()!=null)
		    Text.drawString("Health: "+justClickedTile.getUnit().getHealth()+"\n Movement: "+justClickedTile.getUnit().getMovementPts() +"\n Attack: "+justClickedTile.getUnit().getAttack(), 130f, 80f, 30f, 1.5f);
		    
		    if (justClickedTile != null && justClickedTile.getBuilding() != null && justClickedTile.getBuilding().getOwner() != null && justClickedTile.getBuilding().getOwner().equals(TurnManager.getCurrentPlayer())) {
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Infantry")) {
		    		drawInfantryButton(true);
		    		drawAntiInfantryButton(true);
				    drawAntiTankButton(true);
				    drawAntiAircraftButton(true);
		    	}
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Tank")) {
		    		drawTankButton(true);
		    		drawAntiInfantryButton(true);
				    drawAntiTankButton(true);
				    drawAntiAircraftButton(true);
		    	}
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Aircraft")) {
		    		drawAircraftButton(true);
		    		drawAntiInfantryButton(true);
				    drawAntiTankButton(true);
				    drawAntiAircraftButton(true);
		    	}
		    }
		    
		    Text.drawString("END TURN", 52.5f, 4.5f, 80f, 5f);
 
		    renderPlayerInfo();

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		    
		}
	}
	
	//returns the x position (pixel)
	private static double getCursorPosX(long windowID) {
		DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(windowID, posX, null);
	    return posX.get(0);
	}
	
	//returns the y position (pixel)
	private static double getCursorPosY(long windowID) {
		DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(windowID, null, posY);
	    return posY.get(0);
	}
	
	//returns the x coordinate on the board (tile)
	private static int getXCord(double value) {
		//xCord = (int) ((value / 68));
		return (int) ((value / 68));
	}
	
	//returns the y coordinate on the board (tile)
	private static int getYCord(double value) {
		//yCord = (int) (10 - ((value - 40) / 68));
		return (int) (10 - ((value - 40) / 68));
	}

	
	private static void drawBuilding(int i, int j, City b) {
		if (b.getOwner() == null) {
			drawCircle(i, j, 15, null);
		}
		if (!b.isCity())
			drawCircle(i, j, 25, b.getOwner());
		else
			drawCircle(i, j, 15, b.getOwner());
	}
	
	// draws a circle on the tile with radius r with player p's color
	private static void drawCircle(int xCord, int yCord, int radius, Player p){
	    glBegin(GL_TRIANGLE_FAN);
	    // if the building doesn't have an owner, make it grey
	    if (p == null)
	    	GL11.glColor3f(0.5f, 0.5f, 0.5f);
	    else
	    	GL11.glColor3f(p.getRed(),p.getGreen(),p.getBlue());
		glVertex2f(xCord*68+34, yCord*68+34); // center of circle
		for(int i = 0; i <= 20;i++) { 
		GL11.glVertex2f((float)
		            ((xCord*68+34) + (radius * Math.cos(i *  (2*Math.PI / 20)))), 
			    (float)((yCord*68+34) + (radius * Math.sin(i * 2*Math.PI / 20)))
			);
		
		}
		GL11.glEnd();
	}
	
	//draws the terrain on the board
	private static void drawTerrain(int i, int j, Terrain t){
		if(t==Terrain.GRASSLANDS)
			GL11.glColor3f(0.0f,0.8f,0.2f);
		else if(t==Terrain.WATER)
			GL11.glColor3f(0.0f,0.2f,0.8f);
		else if (t == Terrain.MOUNTAINS)
			GL11.glColor3f(0.2f, 0.2f, 0.2f);
		else if (t == Terrain.ROADS)
			GL11.glColor3f(1.0f, 1.0f, 0.2f);
		else if (t == Terrain.HILLS)
			GL11.glColor3f(0.8f, .0f, 0.2f);
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(68*j,68*i);
		GL11.glVertex2f(68*(j+1),68*i);
		GL11.glVertex2f(68*(j+1),68*(i+1));
		GL11.glVertex2f(68*j,68*(i+1));
	    GL11.glEnd();
	}
	
	private static void drawUnit(int i, int j, Unit u) {
    	Player p = u.getOwner();
    	GL11.glColor3f(p.getRed(), p.getGreen(), p.getBlue());
    	if(u instanceof AircraftDefault){
	    	GL11.glBegin(GL11.GL_TRIANGLES);
	    	GL11.glVertex2f(68*j+30,68*i+38);
			GL11.glVertex2f(68*j+20,68*i+20);
			GL11.glVertex2f(68*j+40,68*i+20);
    	}
    	if(u instanceof InfantryDefault){
        	GL11.glBegin(GL11.GL_QUADS);
        	GL11.glVertex2f(68*j+20,68*i+40);
    		GL11.glVertex2f(68*j+20,68*i+20);
    		GL11.glVertex2f(68*j+40,68*i+20);
    		GL11.glVertex2f(68*j+40,68*i+40);
    	}
    	
    	if(u instanceof TankDefault){
        	GL11.glBegin(GL11.GL_QUADS);
        	GL11.glVertex2f(68*j+10,68*i+40);
    		GL11.glVertex2f(68*j+20,68*i+20);
    		GL11.glVertex2f(68*j+40,68*i+20);
    		GL11.glVertex2f(68*j+30,68*i+40);
    	}
    	
	    GL11.glEnd();
	}
	
	private static void drawResource(int i, int j, Resource r) {
		drawMainResource(i, j, r);
		drawOwnedResource(i, j, r);
	}
	
	private static void drawOwnedResource(int i, int j, Resource r) {
		if (r.getOwner() != null) {
			Player p = r.getOwner();
			GL11.glColor3f(p.getRed(), p.getGreen(), p.getBlue());
			GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f((float)(68*(j+.1)),(float)(68*(i +.1)));
			GL11.glVertex2f((float)(68*(j+0.2)), (float)(68*(i+.1)));
			GL11.glVertex2f((float)(68*(j+0.2)),(float)(68*(i+0.2)));
			GL11.glVertex2f((float)(68*(j+.1)),(float)(68*(i+0.2)));
		    GL11.glEnd();
		}
	}
	
	private static void drawMainResource(int i, int j, Resource r) {
		if (r.toString() == "Oil")
    		GL11.glColor3f(0.0f,0.0f,0.0f);
		else 
			GL11.glColor3f(1.0f,1.0f,1.0f);
		
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(68*j,68*i);
		GL11.glVertex2f((float)(68*(j+0.3)), (float)(68*i));
		GL11.glVertex2f((float)(68*(j+0.3)),(float)(68*(i+0.3)));
		GL11.glVertex2f((float)(68*j),(float)(68*(i+0.3)));
	    GL11.glEnd();
	}
	
	private static void renderPlayerInfo(){
	    for(int i = 0; i < TurnManager.plst.length; i++){
	    	Player player = TurnManager.plst[i];
		    Text.drawString("Victory:"+player.getVictoryPoints()+" Oil:"+player.getOil()+" Steel:"+player.getSteel()+" Credit:"+player.getCredits(), 6f+i*42.5f, 93.5f, 30f, 1.5f);
		    
		    
			GL11.glColor3f(player.getRed(),player.getGreen(),player.getBlue());
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(10+i*320, 720-10);
			GL11.glVertex2f(30+i*320,720-10);
			GL11.glVertex2f(30+i*320,720-30);
			GL11.glVertex2f(10+i*320,720-30);
		    GL11.glEnd();
	    }
	}
	
	private static void drawInfantryButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 260);
		GL11.glVertex2f(952 + 140,720 - 260);
		GL11.glVertex2f(952 + 140,720 - 330);
		GL11.glVertex2f(952 + 40,720 - 330);
	    GL11.glEnd();
	    Text.drawString("Infantry", 100.5f, 42.5f, 40f, 1.5f);
	}
	
	private static void drawTankButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 370);
		GL11.glVertex2f(952 + 140,720 - 370);
		GL11.glVertex2f(952 + 140,720 - 440);
		GL11.glVertex2f(952 + 40,720 - 440);
	    GL11.glEnd();
	    Text.drawString("Tank", 100.5f, 31.5f, 40f, 1.5f);
	}
	
	private static void drawAircraftButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 480);
		GL11.glVertex2f(952 + 140,720 - 480);
		GL11.glVertex2f(952 + 140,720 - 550);
		GL11.glVertex2f(952 + 40,720 - 550);
	    GL11.glEnd();
	    Text.drawString("Aircraft", 100.5f, 20.5f, 40f, 1.5f);
	}
	
	private static void drawAntiInfantryButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 160,720 - 260);
		GL11.glVertex2f(952 + 260,720 - 260);
		GL11.glVertex2f(952 + 260,720 - 330);
		GL11.glVertex2f(952 + 160,720 - 330);
	    GL11.glEnd();
	    Text.drawString("AntiInfantry", 110.5f, 40.5f, 40f, 1.5f);
	}
	
	private static void drawAntiTankButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 170,720 - 370);
		GL11.glVertex2f(952 + 270,720 - 370);
		GL11.glVertex2f(952 + 270,720 - 440);
		GL11.glVertex2f(952 + 170,720 - 440);
	    GL11.glEnd();
	    Text.drawString("AntiTank", 110.5f, 30.5f, 40f, 1.5f);
	}
	
	private static void drawAntiAircraftButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 170,720 - 480);
		GL11.glVertex2f(952 + 270,720 - 480);
		GL11.glVertex2f(952 + 270,720 - 550);
		GL11.glVertex2f(952 + 170,720 - 550);
	    GL11.glEnd();
	    Text.drawString("AntiAircraft", 110.5f, 20.5f, 40f, 1.5f);
	}
	
	public static void main(String[] args) {
		new GameInterface().run();
	}
}
