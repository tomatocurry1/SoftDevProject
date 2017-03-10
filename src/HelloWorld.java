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
public class HelloWorld {

	private Tile lastTile = null;
	private Tile justClickedTile = null;
	private static int xCord;
	private static int yCord;
	private double posX;
	private double posY;

	private Tile[][] grid;

	
	// The window handle
	private long window;
	
	GLFWMouseButtonCallback mouseCallback;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		
		// This is just to have something to run
		/*Player testPlayer = new Player();
		Unit unit1 = new Unit(testPlayer);
		grid[5][3].setUnit(unit1);
*/
		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
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
		    	if (action == GLFW_RELEASE && button == GLFW_MOUSE_BUTTON_1) {
			    	posX = getCursorPosX(window);
			    	posY = getCursorPosY(window);
			    	xCord = HelloWorld.getXCord(posX);
		    		yCord = HelloWorld.getYCord(posY);
			    	if (posY > 40 && posX < 952) {
				    	//System.out.println("clicked: " + posX + ", "+ posY);
				    	System.out.println("xCord: " + xCord + ", yCord: " + yCord);

				    	lastTile = justClickedTile;
				    	justClickedTile = GameInterface.grid[xCord][yCord];
				    	
				    	if (justClickedTile.getUnit() == null)
				    		System.out.println("No unit");
				    	else
				    		System.out.println("Yes unit");
				    	System.out.println("Terrain is: " + justClickedTile.getTerrain().toString());
				    	if ((lastTile != justClickedTile) && lastTile != null && lastTile.getUnit() != null) {
				    		UnitManager.moveUnit(lastTile, justClickedTile);
				    		lastTile = null;
				    		justClickedTile = null;
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
		
		GameInterface.init();
		grid = GameInterface.grid;
		
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
			 
		    // set the color of the quad (R,G,B,A)
		    GL11.glColor3f(0.5f,0.5f,1.0f);
	 
		    // draw quad
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(100,100);
			GL11.glVertex2f(100+200,100);
			GL11.glVertex2f(100+200,100+200);
			GL11.glVertex2f(100,100+200);
		    GL11.glEnd();
		    
		    GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(300,300);
			GL11.glVertex2f(300+200,300);
			GL11.glVertex2f(300+200,300+200);
			GL11.glVertex2f(300,300+200);
		    GL11.glEnd();
		    
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
		    
		    
		    
		    for(int i=0; i<10; i++){
		    	for(int j = 0; j<14; j++){
				    //GL11.glColor3f(i*(float)(1./10),0.0f,j*(float)(1./14));
		    		if(GameInterface.grid[j][i].getTerrain()==Terrain.GRASSLANDS)
		    			GL11.glColor3f(0.0f,0.8f,0.2f);
		    		if(GameInterface.grid[j][i].getTerrain()==Terrain.WATER)
		    			GL11.glColor3f(0.0f,0.2f,0.8f);
				    
				    GL11.glBegin(GL11.GL_QUADS);
				    GL11.glVertex2f(68*j,68*i);
					GL11.glVertex2f(68*(j+1),68*i);
					GL11.glVertex2f(68*(j+1),68*(i+1));
					GL11.glVertex2f(68*j,68*(i+1));
				    GL11.glEnd();
				    
				    if(GameInterface.grid[j][i].getUnit()!=null){
				    	GL11.glColor3f(0.5f,0.0f,0.8f);
					    
					    GL11.glBegin(GL11.GL_TRIANGLES);
					    GL11.glVertex2f(68*j+30,68*i+38);
						GL11.glVertex2f(68*j+20,68*i+20);
						GL11.glVertex2f(68*j+40,68*i+20);
					    GL11.glEnd();
				    }
				   
		    	}
		    }
		    
		    
		    
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
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}
	
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
		//xCord = (int) ((value / 68));
		return (int) ((value / 68));
	}
	
	private static int getYCord(double value) {
		//yCord = (int) (10 - ((value - 40) / 68));
		return (int) (10 - ((value - 40) / 68));
	}

	/*if (grid[xCord][yCord] != null) {
		Unit unitSelected = justClickedTile.getUnit();
		justClickedTile = grid[xCord][yCord];	
		xCord = getXCord(posX);
		yCord = getYCord(posY);
		
		
		if (justClickedTile.getUnit() == null) {
			lastTile = justClickedTile;
			justClickedTile = 
			UnitManager.moveUnit(lastTile, justClickedTile);
		}
		
	}*/

	private void makeBoard() {
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 10; j++ ) {
				grid[i][j] = new Tile();
			}
		}
	}
	
	public static void main(String[] args) {
		new HelloWorld().run();
	}
}



