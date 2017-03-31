
public class Player {

	private int playerNum;
	private int victoryPoints;
	private int oil;
	private int steel;
	private int oilUsed;
	private int steelUsed;
	private int credits;
	private float red;
	private float green;
	private float blue;
	
	public Player(int num) {
		playerNum = num;
	}
	
	public Player(int num, float r, float g, float b) {
		playerNum = num;
		red = r;
		green = g;
		blue = b;
	}
	
	public int getNum() {
		return playerNum;
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
	
	public void substractCredits(int value){
		credits -= value;
	}
	
	public int getCredits() {
		return credits;
	}

	public int getSteelUsed() {
		return steelUsed;
	}

	public void setSteelUsed(int steelUsed) {
		this.steelUsed = steelUsed;
	}

	public int getOilUsed() {
		return oilUsed;
	}

	public void setOilUsed(int oilUsed) {
		this.oilUsed = oilUsed;
	}
}
