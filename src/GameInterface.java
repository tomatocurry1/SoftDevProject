import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

public class GameInterface {
	
	public static Tile[][] grid;
	private static String info;
	private static int spritenum;
	private static int spriteinfantry;
	private static int spriteheli;
	private static int spritecity;
	private static int spritegrass;
	private static int spriteroad;
	private static int spritemountain;
	private static int spritehill;
	private static int spriteoil;
	private static int spritesteel;
	private static int spritebase;
	private static int spritewinning;
	
	
	private Tile lastTile = null;
	private Tile justClickedTile = null;
    private boolean displayTileInfo = false;
	private static int xCord;
	private static int yCord;
	private double posX;
	private double posY;
	private boolean buyingTank = false;
	private boolean buyingInfantry = false;
	private boolean buyingAircraft = false;
	static boolean nextScreen = false;
	static int numplayers = 4;

	// The window handle
	private long window;
	
	GLFWMouseButtonCallback mouseCallback;
	
public void run() {
		
		init();
		prescreen();
		gameInit();
		mainLoop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public void prescreen(){
		int panda = TextureLoader.glLoadPNG("img/panda.png");
			
		int title = TextureLoader.glLoadPNG("img/panda_title.png");
		
//			int fontset = TextureLoader.glLoadLinearPNG("img/fontset.png");
		
		
		glfwSetMouseButtonCallback(window, mouseCallback = new GLFWMouseButtonCallback() {
		    @Override
		    public void invoke(long wind, int button, int action, int mods) {
		    	double posX=-1;
		    	double posY=-1;
		    	if (action == GLFW_RELEASE) {
			    	posX = 1280 - getCursorPosX(window);
			    	posY = 720 - getCursorPosY(window);
			    	if (button == GLFW_MOUSE_BUTTON_1) {
			    		if(posX > 1280/2 - 240/2 && posX < 1280/2 + 240/2 && posY > 720/2 - 80/2 && posY < 720/2 + 80/2){
			    			numplayers = 2;
			    			nextScreen = true;
			    		}
			    		
			    		if(posX > 1280/2 - 240/2 && posX < 1280/2 + 240/2 && posY > 720/2 - 80/2 - 100 && posY < 720/2 + 80/2 - 100){
			    			numplayers = 3;
			    			nextScreen = true;
			    		}
			    		
			    		if(posX > 1280/2 - 240/2 && posX < 1280/2 + 240/2 && posY > 720/2 - 80/2 - 200 && posY < 720/2 + 80/2 - 200){
			    			numplayers = 4;
			    			nextScreen = true;
			    		}
			    		
			    	}
			    
		    	}
		    }
		});
		
		
		while(!glfwWindowShouldClose(window) && !nextScreen){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
		    GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			GL11.glColor3f(1.f,1.f,1.f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, panda);
	    	GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0,1);
		    GL11.glVertex2f(0,0);
		    
		    GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,720);
			
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(1280,720);
			
			GL11.glTexCoord2f(1,1);
		    GL11.glVertex2f(1280,0);
		    GL11.glEnd();
		    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		    
		    GL11.glBindTexture(GL11.GL_TEXTURE_2D, title);
	    	GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0,1);
		    GL11.glVertex2f(0,0+200);
		    
		    GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,720+200);
			
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(1280,720+200);
			
			GL11.glTexCoord2f(1,1);
		    GL11.glVertex2f(1280,0+200);
		    GL11.glEnd();
		    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			
			GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(1280/2 - 240/2 ,720/2 - 80/2);
			GL11.glVertex2f(1280/2 + 240/2,720/2 - 80/2);
			GL11.glVertex2f(1280/2 + 240/2,720/2 + 80/2);
			GL11.glVertex2f(1280/2 - 240/2,720/2 + 80/2);
		    GL11.glEnd();
		   
		    
		    GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(1280/2 - 240/2 ,720/2 - 80/2 - 100);
			GL11.glVertex2f(1280/2 + 240/2,720/2 - 80/2 - 100);
			GL11.glVertex2f(1280/2 + 240/2,720/2 + 80/2 - 100);
			GL11.glVertex2f(1280/2 - 240/2,720/2 + 80/2 - 100);
		    GL11.glEnd();
		    
		    GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(1280/2 - 240/2 ,720/2 - 80/2 - 200);
			GL11.glVertex2f(1280/2 + 240/2,720/2 - 80/2 - 200);
			GL11.glVertex2f(1280/2 + 240/2,720/2 + 80/2 - 200);
			GL11.glVertex2f(1280/2 - 240/2,720/2 + 80/2 - 200);
		    GL11.glEnd();
			
		    Font.drawString("TWO PLAYER", 550, 348, 2f, 2.5f);
		    Font.drawString("THREE PLAYER", 543, 248, 2f, 2.5f);
		    Font.drawString("FOUR PLAYER", 550, 150, 2f, 2.5f);
			
		    //Font.drawString("qwertyuiopasdfghjklzxcvbnm 1234567890", 10, 10, 1.f, 1.5f);
		    
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
		
		nextScreen = false;
		
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
		window = glfwCreateWindow(1280, 720, "Banda", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
	
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
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
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL_TEXTURE_RECTANGLE_ARB);
		// Set the clear color
		glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1280, 0, 720, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//GL11.glTexEnvf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Font.init();
	}

	// initializes the game board and players
	public void gameInit(){
		
		TurnManager.plst = new Player[numplayers];
		MapLoader.load(numplayers);
		
		
		glfwSetMouseButtonCallback(window, mouseCallback = new GLFWMouseButtonCallback() {
		    @Override
		    public void invoke(long wind, int button, int action, int mods) {
		    	
		    	if (action == GLFW_RELEASE) {
		    	posX = getCursorPosX(window);
		    	posY = getCursorPosY(window);
		    	System.out.println((1280-posX) + ", " + (720-posY));
		    	
		    	displayTileInfo = false;
		    	
		    	if (posY > 40 && posX < 952) { //if mouse clicked on the board
		    		buyingInfantry = false;
					buyingTank = false;
					buyingAircraft = false;
			    	xCord = getXCord(posX);
		    		yCord = getYCord(posY);
		    		lastTile = justClickedTile;
			    	justClickedTile = GameInterface.grid[xCord][yCord];
		    		
			    	if (button == GLFW_MOUSE_BUTTON_1) {
				    	
			    		//prints the coordinates of the tile that was clicked
				    	System.out.println("xCord: " + xCord + ", yCord: " + yCord);
				    	
			
				    	if (justClickedTile.getUnit() != null) {
				    	
				    	}
				    	else
				    		System.out.println("No Unit");
				    	//determines if the current player can move their unit on lastTile to the justClickedTile
				    	if ((lastTile != justClickedTile) && lastTile != null && lastTile.getUnit() != null &&  
				    		UnitManager.isValidMove(lastTile, justClickedTile) ) {
				    		
					    		if (justClickedTile.getUnit() == null){
					    			UnitManager.moveUnit(lastTile, justClickedTile);
				    		}
				    		lastTile = null;
				    		justClickedTile = null;
				    	}
				    	if ((lastTile != justClickedTile) && lastTile != null && lastTile.getUnit() != null) {
				    		
				    		if (UnitManager.isAttackValid(lastTile, justClickedTile)) {
				    			UnitManager.attack(lastTile, justClickedTile);
				    			}
				    		
			    		lastTile = null;
			    		justClickedTile = null;
			    	}
			    	}
			    	//right clicking displays the terrain, resource, and building????
			    	else if (button == GLFW_MOUSE_BUTTON_2) {
			    		displayTileInfo = true;
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
		    		if(!TurnManager.endGame())
		    			TurnManager.endTurn();
		    		justClickedTile = null;
		    		lastTile = null;
		    		buyingInfantry = false;
		    		buyingTank = false;
		    		buyingAircraft = false;
		    	}
		   
		    	//selecting the generic type of unit
		    	if (posX > 952 + 40 && posX < 952 + 165 && posY > 260 && posY < 330) {
		    		System.out.println("Trying to buy Infantry");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getUnit() == null && justClickedTile.getBuilding().getOwner() != null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()) {
		    			buyingAircraft = false;
    					buyingTank = false;
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Infantry")){
		    				System.out.println("can buy Infantry");
		    				buyingInfantry = true;
		    				
		    				//UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Infantry");
		    			}
		    		}
		    	}
		    	else if (posX > 952 + 40 && posX < 952 + 165 && posY > 370 && posY < 440) {
		    		System.out.println("Trying to buy Tank");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getUnit() == null && justClickedTile.getBuilding().getOwner() != null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()) {
		    			buyingInfantry = false;
    					buyingAircraft = false;
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Tank")){
		    				System.out.println("can buy Tank");
		    				buyingTank = true;
		    				
		    				//UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Tank");
		    			}
		    		}
		    	}
		    	else if (posX > 952 + 40 && posX < 952 + 165 && posY > 480 && posY < 550) {
		    		System.out.println("Trying to buy Aircraft");
		    		
		    		if(justClickedTile != null && justClickedTile.getBuilding()!=null && justClickedTile.getBuilding().getOwner() != null && justClickedTile.getBuilding().getOwner().getNum() == TurnManager.getCurrentPlayer().getNum()) {
		    			buyingInfantry = false;
    					buyingTank = false;
		    			if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Aircraft")){
		    				System.out.println("can buy Aircraft");
		    				buyingAircraft = true;
		    				
		    				//UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "Aircraft");
		    			}
		    		}
		    	}
		    	
		    	//selecting the specialized unit
		    	if (buyingInfantry || buyingAircraft || buyingTank) {
			    	if (posX > 952 + 170 && posX < 952 + 295 && posY > 260 && posY < 330) {
			    		if (buyingInfantry)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "InfantrySiege");
			    		else if (buyingTank)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "TankSiege");
			    		else if (buyingAircraft)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "AircraftSiege");
			    		buyingInfantry = false;
    					buyingTank = false;
    					buyingAircraft = false;
			    	}
			    	else if (posX > 952 + 170 && posX < 952 + 295 && posY > 370 && posY < 440) {
			    		if (buyingInfantry)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "InfantryAT");
			    		else if (buyingTank)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "TankAT");
			    		else if (buyingAircraft)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "AircraftAT");
			    		buyingInfantry = false;
    					buyingTank = false;
    					buyingAircraft = false;
			    	}
			    	else if (posX > 952 + 170 && posX < 952 + 295 && posY > 480 && posY < 550) {
			    		if (buyingInfantry)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "InfantryAA");
			    		else if (buyingTank)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "TankAA");
			    		else if (buyingAircraft)
			    			UnitCreator.createUnit(TurnManager.getCurrentPlayer(), justClickedTile.getX(), justClickedTile.getY(), "AircraftAA");
			    		buyingInfantry = false;
    					buyingTank = false;
    					buyingAircraft = false;
			    	}
		    	}
		    	
		    	
		    }
		    }
		}); 
		
		if(spritenum == 0)
			spritenum = TextureLoader.glLoadPNG("img/cooltankbro.png");
		
		if(spriteinfantry == 0)
			spriteinfantry = TextureLoader.glLoadPNG("img/infantrybro.png");
		
		if(spriteheli == 0)
			spriteheli = TextureLoader.glLoadPNG("img/heli.png");
		
		if(spritecity == 0)
			spritecity = TextureLoader.glLoadPNG("img/city.png");
		
		if(spritegrass == 0)
			spritegrass = TextureLoader.glLoadPNG("img/grass3.png");
		
		if(spriteroad == 0)
			spriteroad = TextureLoader.glLoadPNG("img/road.png");

		if(spritemountain == 0)
			spritemountain = TextureLoader.glLoadPNG("img/mountain.png");
		
		if(spritehill == 0)
			spritehill = TextureLoader.glLoadPNG("img/hills.png");
		
		if(spriteoil == 0)
			spriteoil = TextureLoader.glLoadPNG("img/oil.png");
		
		if(spritesteel == 0)
			spritesteel = TextureLoader.glLoadPNG("img/steel2.png");
		
		if(spritebase == 0)
			spritebase = TextureLoader.glLoadPNG("img/fort.png");
		if(spritewinning == 0)
			spritewinning = TextureLoader.glLoadPNG("img/winning.png");
		
	}

	
		
		
	
	private void mainLoop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		//GL11.glEnable(ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			 
		    GL11.glColor3f(0.f,0.f,0f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(0 ,0);
			GL11.glVertex2f(1280,0);
			GL11.glVertex2f(1280,720);
			GL11.glVertex2f(0,720);
		    GL11.glEnd();
			
		    // set the color of the quad (R,G,B)
		    
		    GL11.glColor3f(0.f,0.7f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(952,0);
			GL11.glVertex2f(1280,0);
			GL11.glVertex2f(1280,720);
			GL11.glVertex2f(952,720);
		    GL11.glEnd();
		    
		    GL11.glColor3f(0.4f,0.4f,0.4f);
//		    GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(952,0);
			GL11.glVertex2f(952+5,0);
			GL11.glVertex2f(952+5,720);
			GL11.glVertex2f(952,720);
		    GL11.glEnd();
		    
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(1280-5,0);
			GL11.glVertex2f(1280,0);
			GL11.glVertex2f(1280,720);
			GL11.glVertex2f(1280-5,720);
		    GL11.glEnd();
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(952,720-40);
			GL11.glVertex2f(1280,720-40);
			GL11.glVertex2f(1280,720-40-5);
			GL11.glVertex2f(952,720-40-5);
		    GL11.glEnd();
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(952,0);
			GL11.glVertex2f(1280,0);
			GL11.glVertex2f(1280,5);
			GL11.glVertex2f(952,5);
		    GL11.glEnd();
		    
		    GL11.glColor3f(0.7f,0.3f,0.3f);
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
		    if (buyingAircraft || buyingInfantry || buyingTank) {
			    drawAntiInfantryButton(true);
			    drawAntiTankButton(true);
			    drawAntiAircraftButton(true);
		    }
		    
		    for(int i=0; i<10; i++){
		    	for(int j = 0; j<14; j++){
		    	
				    //draws the terrain on the board
		    		if (GameInterface.grid[j][i].getTerrain() != null)
		    			drawTerrain(i, j, GameInterface.grid[j][i].getTerrain());
		
				    
				    
				    //draws the buildings on board
				    if(GameInterface.grid[j][i].getBuilding() != null) {
				    	drawBuilding(i, j, GameInterface.grid[j][i].getBuilding());
				    }
				    
				    //draws the resource on the board
				    if (GameInterface.grid[j][i].getResource() != null) {
				    	drawResource(i, j, GameInterface.grid[j][i].getResource());
				    }
				    
				    //draws the units on the board
				    if(GameInterface.grid[j][i].getUnit()!=null){
				    	drawUnit(i, j, GameInterface.grid[j][i].getUnit());
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
			GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(68*14+30,20);
			GL11.glVertex2f(1280-30,20);
			GL11.glVertex2f(1280-30,150);
			GL11.glVertex2f(68*14+30,150);
		    GL11.glEnd(); 
		    
		    GL11.glColor3f(0.7f,0.3f,0.3f);
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(68*14+30,500);
			GL11.glVertex2f(1280-30,500);
			GL11.glVertex2f(1280-30,650);
			GL11.glVertex2f(68*14+30,650);
		    GL11.glEnd();
		   
		    
		    if(displayTileInfo){
        	    if(justClickedTile!=null){
        	    	
        	    	Font.drawString(justClickedTile.terrain.getName(), 1010+10f, 620f, 1.5f, 1.5f);
        	    	String str = "";
        	    	switch(justClickedTile.terrain){
        	    		case WATER:
        	    			Font.drawString("Land Units can't cross water", 1010+10f, 620-20f, .9f, 1.5f);
        	    			break;
        	    		case GRASSLANDS:
        	    			Font.drawString("Plain looking grasslands", 1010+10f, 620-20f, .9f, 1.5f);
        	    			break;
        	    		case HILLS:
        	    			Font.drawString("Tanks have hard time corssing\nTanks - 2 times movement cost", 1010+10f, 620-20f, .9f, 1.5f);
        	    			break;
        	    		case MOUNTAINS:
        	    			Font.drawString("This looks impassable", 1010+10f, 620-20f, .9f, 1.5f);
        	    			break;
        	    		case ROADS:
        	    			Font.drawString("Nice and smooth\nTanks - Half Movement cost", 1010+10f, 620-20f, .9f, 1.5f);
        	    			break;
        	    			
        	    	}
        	    	
        	    	
        	    	if(justClickedTile.getResource()!=null){
        	    		Font.drawString(justClickedTile.getResource().toString(), 1010+10f, 620-60f, 1.5f, 1.5f);
        	    		if(justClickedTile.getResource() instanceof Oil)
        	    			Font.drawString("Used for making aircrafts", 1010+10f, 620-20-60f, .9f, 1.5f);
        	    		else if(justClickedTile.getResource() instanceof Steel)
    	    			Font.drawString("Used for making tanks", 1010+10f, 620-20-60f, .9f, 1.5f);
        	    	}
        	    	else if(justClickedTile.getBuilding()!=null){
        	    		Font.drawString(justClickedTile.getBuilding().toString(), 1010+10f, 620-60f, 1.5f, 1.5f);
        	    		if(justClickedTile.getBuilding() instanceof Base)
        	    			Font.drawString("2 Victory Point", 1010+10f, 620-20-60f, .9f, 1.5f);
        	    		else
        	    			Font.drawString("1 Victory Point", 1010+10f, 620-20-60f, .9f, 1.5f);
        	    	}
        	    
        	    	
        	    }
		    }
		    if(!displayTileInfo)
		    if(justClickedTile!=null)
		    	if(justClickedTile.getUnit()!=null){
		    		Font.drawString("Health: "+justClickedTile.getUnit().getHealth()+"\nMovement: "+justClickedTile.getUnit().getMovementPts() +"\nAttack: "+justClickedTile.getUnit().getAttack(), 1010+20f, 600-10f, 1.5f, 1.5f);
		        	Player p = justClickedTile.getUnit().getOwner();
		        	GL11.glColor3f(p.getRed(), p.getGreen(), p.getBlue());
		        		if(justClickedTile.getUnit() instanceof InfantryDefault)
		        			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteinfantry);
		        		else if(justClickedTile.getUnit() instanceof AircraftDefault)
		        			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteheli);
		        		else if(justClickedTile.getUnit() instanceof TankDefault)
		        			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritenum);
		        		
		            	GL11.glBegin(GL11.GL_QUADS);
		        	    GL11.glTexCoord2f(0,1);
		        	    GL11.glVertex2f(1000-10,600+20);
		        	    
		        	    GL11.glTexCoord2f(0,0);
		        	    //GL11.glTexCoord2f(0,32);
		        		GL11.glVertex2f(1000-10,620+20);
		        		
		        		GL11.glTexCoord2f(1,0);
		        		//GL11.glTexCoord2f(32,32);
		        		GL11.glVertex2f(1020-10, 620+20);
		        		
		        		GL11.glTexCoord2f(1,1);
		        		//GL11.glTexCoord2f(32,0);
		        	    GL11.glVertex2f(1020-10, 600+20);
		        	    
		        	    GL11.glEnd();
		        	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		        	    
		        	    Font.drawString(justClickedTile.getUnit().toString(), 1010+10f, 620f, 1.5f, 1.5f);
		    	}
		    	
		    if (justClickedTile != null && justClickedTile.getBuilding() != null && justClickedTile.getUnit() == null && justClickedTile.getBuilding().getOwner() != null && justClickedTile.getBuilding().getOwner().equals(TurnManager.getCurrentPlayer())) {
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Infantry")) {
		    		drawInfantryButton(true);
		    	}
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Tank")) {
		    		drawTankButton(true);
		    	}
		    	if (UnitCreator.canCreateUnit(TurnManager.getCurrentPlayer(), "Aircraft")) {
		    		drawAircraftButton(true);
		    	}
		    }
		    
		    Font.drawString("END TURN", 1050f, 70f, 2.0f, 5f);
 
		    renderPlayerInfo();
		    renderCurrentPlayerOutline();
		    if (TurnManager.endGame() == true) {
		    	//GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritewinning);
		    	GL11.glColor3f(.0f,.0f,.0f);
			    GL11.glBegin(GL11.GL_QUADS);
			    //GL11.glTexCoord2f(0,1);
			    GL11.glVertex2f(800,600);
			    //GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(800,200);
				//GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(200,200);
				//GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(200,600);
			    GL11.glEnd();
			    
			    Font.drawString("Player " + (((TurnManager.getCurrentPlayer().getPlayerNum()-1 + TurnManager.plst.length-1)%TurnManager.plst.length)+1) + " wins" , 200, 400, 2.f, 0);
			    //GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		    }
		    
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
		if(b.getOwner() != null){
			Player p = b.getOwner();
	    	GL11.glColor3f(p.getRed(), p.getGreen(), p.getBlue());
	    }else
	    	GL11.glColor3f(0.5f, 0.5f, 0.5f);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritecity);
		if(b instanceof Base)
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritebase);
		else
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritecity);
    	GL11.glBegin(GL11.GL_QUADS);
	    GL11.glTexCoord2f(0,1);
	    GL11.glVertex2f(68*j,68*i);
	    
	    GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(68*j,68*(i+1));
		
		GL11.glTexCoord2f(1,0);
		GL11.glVertex2f(68*(j+1),68*(i+1));
		
		GL11.glTexCoord2f(1,1);
	    GL11.glVertex2f(68*(j+1),68*i);
	    GL11.glEnd();
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	    
	}
	
	// draws a circle on the tile with radius r with player p's color
	private static void drawCircle(int xCord, int yCord, int radius, Player p){
	    glBegin(GL_TRIANGLE_FAN);
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
		if(t==Terrain.GRASSLANDS){
			GL11.glColor3f(1.0f,1.0f,1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritegrass);
		}
		else if(t==Terrain.WATER)
			GL11.glColor3f(0.0f,0.2f,0.8f);
		else if (t == Terrain.MOUNTAINS){
			GL11.glColor3f(1.0f,1.0f,1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritemountain);
		
		}
		else if (t == Terrain.ROADS){
			GL11.glColor3f(0.8f,.8f,.8f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteroad);
		
		}
		else if (t == Terrain.HILLS){
			GL11.glColor3f(1.0f,1.0f,1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritehill);
		}
		
		
		//GL11.glColor3f(.0f,.0f,1.0f);
		
		//System.out.println(spritenum);
		
		//GL11.glBindTexture(ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB, spritenum);
		
		GL11.glBegin(GL11.GL_QUADS);
	    
	    GL11.glTexCoord2f(0,1);
	    GL11.glVertex2f(68*j,68*i);
	    
	    GL11.glTexCoord2f(0,0);
	    //GL11.glTexCoord2f(0,32);
		GL11.glVertex2f(68*j,68*(i+1));
		
		GL11.glTexCoord2f(1,0);
		//GL11.glTexCoord2f(32,32);
		GL11.glVertex2f(68*(j+1),68*(i+1));
		
		GL11.glTexCoord2f(1,1);
		//GL11.glTexCoord2f(32,0);
	    GL11.glVertex2f(68*(j+1),68*i);
	    
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		//GL11.glBindTexture(ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB, 0);
	}
	
	private static void drawUnit(int i, int j, Unit u) {
    	Player p = u.getOwner();
    	GL11.glColor3f(p.getRed(), p.getGreen(), p.getBlue());
    	if(u.getMovementPts()==0)
    		GL11.glColor3f(170f/255f-.3f*(1.f-p.getRed()), 175f/255f-.3f*(1.f-p.getGreen()), 185f/255f-.3f*(1.f-p.getBlue()));
    	if(u instanceof AircraftDefault){
    		GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteheli);
        	GL11.glBegin(GL11.GL_QUADS);
    	    GL11.glTexCoord2f(0,1);
    	    GL11.glVertex2f(68*j,68*i);
    	    
    	    GL11.glTexCoord2f(0,0);
    	    //GL11.glTexCoord2f(0,32);
    		GL11.glVertex2f(68*j,68*(i+1));
    		
    		GL11.glTexCoord2f(1,0);
    		//GL11.glTexCoord2f(32,32);
    		GL11.glVertex2f(68*(j+1),68*(i+1));
    		
    		GL11.glTexCoord2f(1,1);
    		//GL11.glTexCoord2f(32,0);
    	    GL11.glVertex2f(68*(j+1),68*i);
    	}
    	if(u instanceof InfantryDefault){
    		GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteinfantry);
        	GL11.glBegin(GL11.GL_QUADS);
    	    GL11.glTexCoord2f(0,1);
    	    GL11.glVertex2f(68*j,68*i);
    	    
    	    GL11.glTexCoord2f(0,0);
    	    //GL11.glTexCoord2f(0,32);
    		GL11.glVertex2f(68*j,68*(i+1));
    		
    		GL11.glTexCoord2f(1,0);
    		//GL11.glTexCoord2f(32,32);
    		GL11.glVertex2f(68*(j+1),68*(i+1));
    		
    		GL11.glTexCoord2f(1,1);
    		//GL11.glTexCoord2f(32,0);
    	    GL11.glVertex2f(68*(j+1),68*i);
    	}
    	
    	if(u instanceof TankDefault){
    		
    		GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritenum);
    		GL11.glBegin(GL11.GL_QUADS);
    	    GL11.glTexCoord2f(0,1);
    	    GL11.glVertex2f(68*j,68*i);
    	    
    	    GL11.glTexCoord2f(0,0);
    	    //GL11.glTexCoord2f(0,32);
    		GL11.glVertex2f(68*j,68*(i+1));
    		
    		GL11.glTexCoord2f(1,0);
    		//GL11.glTexCoord2f(32,32);
    		GL11.glVertex2f(68*(j+1),68*(i+1));
    		
    		GL11.glTexCoord2f(1,1);
    		//GL11.glTexCoord2f(32,0);
    	    GL11.glVertex2f(68*(j+1),68*i);
    		
    	}
    	
	    GL11.glEnd();
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	private static void drawResource(int i, int j, Resource r) {
		if (r.toString().equals("Oil")){
			GL11.glColor3f(1.0f,1.0f,1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spriteoil);
		}
		if (r.toString().equals("Steel")){
			GL11.glColor3f(1.0f,1.0f,1.0f);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, spritesteel);
		}
		
		
		//GL11.glColor3f(.0f,.0f,1.0f);
		
		//System.out.println(spritenum);
		
		//GL11.glBindTexture(ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB, spritenum);
		
		GL11.glBegin(GL11.GL_QUADS);
	    
	    GL11.glTexCoord2f(0,1);
	    GL11.glVertex2f(68*j,68*i);
	    
	    GL11.glTexCoord2f(0,0);
	    //GL11.glTexCoord2f(0,32);
		GL11.glVertex2f(68*j,68*(i+1));
		
		GL11.glTexCoord2f(1,0);
		//GL11.glTexCoord2f(32,32);
		GL11.glVertex2f(68*(j+1),68*(i+1));
		
		GL11.glTexCoord2f(1,1);
		//GL11.glTexCoord2f(32,0);
	    GL11.glVertex2f(68*(j+1),68*i);
	    
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
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
	    for(int i = 0; i < numplayers; i++){
	    	Player player = TurnManager.plst[i];
		    Font.drawString("Victory:"+player.getVictoryPoints()+" Oil:"+player.getOil()+" Steel:"+player.getSteel()+" Credit:"+player.getCredits(), (i+1)*40f+i*280f, 695f, 1.f, 1.5f);
			GL11.glColor3f(player.getRed(),player.getGreen(),player.getBlue());
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(10+i*320, 720-10);
			GL11.glVertex2f(30+i*320,720-10);
			GL11.glVertex2f(30+i*320,720-30);
			GL11.glVertex2f(10+i*320,720-30);
		    GL11.glEnd();
	    }
	}
	
	private static void renderCurrentPlayerOutline() {
		int n = TurnManager.getCurrentPlayer().getPlayerNum()-1;
		
		GL11.glColor3f(1.0f, 1.0f, 0.0f);
    	GL11.glBegin(GL11.GL_LINES);
    	GL11.glLineWidth((float)10);
    	
    	GL11.glVertex2f(5+n*320, 720-5);
		GL11.glVertex2f(310+n*320,720-5);
		
		GL11.glVertex2f(310+n*320,720-35);
		GL11.glVertex2f(5+n*320,720-35);
		
		GL11.glVertex2f(5+n*320, 720-5);
		GL11.glVertex2f(5+n*320, 720-35);
		
		GL11.glVertex2f(310+n*320,720-35);
		GL11.glVertex2f(310+n*320,720-5);
		
    	GL11.glEnd();
	}

		
	private static void drawInfantryButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(.8f,.8f,0.3f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 260);
		GL11.glVertex2f(952 + 165,720 - 260);
		GL11.glVertex2f(952 + 165,720 - 330);
		GL11.glVertex2f(952 + 40,720 - 330);
	    GL11.glEnd();
	    Font.drawString("Infantry", 1280-275f, 417f, 1.5f, 1.0f);
	}
	
	private static void drawTankButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(.8f,.8f,0.3f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 370);
		GL11.glVertex2f(952 + 165,720 - 370);
		GL11.glVertex2f(952 + 165,720 - 440);
		GL11.glVertex2f(952 + 40,720 - 440);
	    GL11.glEnd();
	    Font.drawString("Tank", 1280-250f, 417-110f, 1.5f, 1.5f);
	}
	
	private static void drawAircraftButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(.8f,.8f,0.3f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 40,720 - 480);
		GL11.glVertex2f(952 + 165,720 - 480);
		GL11.glVertex2f(952 + 165,720 - 550);
		GL11.glVertex2f(952 + 40,720 - 550);
	    GL11.glEnd();
	    Font.drawString("Aircraft", 1280-275f, 417-220f, 1.5f, 1.5f);
	}
	
	private static void drawAntiInfantryButton(boolean canBuy) {
		if (canBuy) {
			GL11.glColor3f(0.8f,0.2f,0.6f);
		}
		else { 
			GL11.glColor3f(0.4f, 0.4f, 0.4f);
		}
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(952 + 170,720 - 260);
		GL11.glVertex2f(952 + 295,720 - 260);
		GL11.glVertex2f(952 + 295,720 - 330);
		GL11.glVertex2f(952 + 170,720 - 330);
	    GL11.glEnd();
	    Font.drawString("Siege", 1280-125f, 417f, 1.5f, 1.5f);
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
		GL11.glVertex2f(952 + 295,720 - 370);
		GL11.glVertex2f(952 + 295,720 - 440);
		GL11.glVertex2f(952 + 170,720 - 440);
	    GL11.glEnd();
	    Font.drawString("Anti-Tank", 1280-150f, 417-110f, 1.5f, 1.5f);
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
		GL11.glVertex2f(952 + 295,720 - 480);
		GL11.glVertex2f(952 + 295,720 - 550);
		GL11.glVertex2f(952 + 170,720 - 550);
	    GL11.glEnd();
	    Font.drawString("Anti-Air", 1280-150f, 417-220f, 1.5f, 1.5f);
	}
	
	public static void main(String[] args) {
		new GameInterface().run();
		//GL11.glDeleteTextures(spritenum);
	}
}
