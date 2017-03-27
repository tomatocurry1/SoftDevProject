
public class Player {

	private int victoryPoints;
	private int oil;
	private int steel;
	private int credits;
	private float red;
	private float green;
	private float blue;
	
	public Player() {
		super();
	}
	
	public Player(float r, float g, float b) {
		red = r;
		green = g;
		blue = b;
	}
	
	public void setColors(float r, float g, float b) {
		red = r;
		green = g;
		blue = b;
	}
	
	public float getRed() {
		return red;
	}
	
	public float getGreen() {
		return green;
	}
	
	public float getBlue() {
		return blue;
	}
	
	public void setOil(int value) {
		oil = value;
	}
	
	public int getOil() {
		return oil;
	}
	
	public void setSteel(int value) {
		steel = value;
	}
	
	public int getSteel() {
		return steel;
	}
	
	public void setVictoryPoints(int value) {
		victoryPoints = value;
	}
	
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	public void addCredits(int value) {
		credits = credits + value;
	}
	
	public int getCredits() {
		return credits;
	}
}
