import java.util.*;

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
	private int endGameCounter = 0;
	private int citiesControlled = 1;
	private int unitsControlled = 1;
	private Stack<Unit> tankStack = new Stack<Unit>();
	private Stack<Unit> aircraftStack = new Stack<Unit>();
	
	public Player(int num) {
		playerNum = num;
	}
	
	public Player(int num, float r, float g, float b) {
		playerNum = num;
		red = r;
		green = g;
		blue = b;
	}
	
	public void setCitiesControlled (int n) {
		citiesControlled = n;
	}
	
	public int getCitiesControlled () {
		return citiesControlled;
	}
	
	public void setUnitsControlled (int n) {
		unitsControlled = n;
	}
	
	public int getUnitsControlled () {
		return unitsControlled;
	}
	
	public boolean isEliminated() {
		if ((this.getUnitsControlled() + this.getCitiesControlled()) <= 0) {
			return true;
		}
		else 
			return false;
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
	
	public void setEndGameCounter(int n) {
		endGameCounter = n;
	}
	
	public int getEndGameCounter() {
		return endGameCounter;
	}
	
	
	public void addCredits(int value) {
		credits = credits + value;
	}
	
	public void subtractCredits(int value){
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
	
	public void addTank(Unit t) {
		tankStack.push(t);
	}
	
	public int numOfTanks() {
		return tankStack.size();
	}
	
	public Unit getLastTank() {
		return tankStack.pop();
	}
	
	public void addAircraft(Unit t) {
		aircraftStack.push(t);
	}
	
	public int numOfAircrafts() {
		return aircraftStack.size();
	}
	
	public Unit getLastAircraft() {
		return aircraftStack.pop();
	}
	
	/*public void setLastAircraft(Unit a) {
		this.lastAircraft = a;
	}
	
	public Unit getLastAircraft() {
		return lastAircraft;
	}
	
	public void setLastTank(Unit t) {
		this.lastTank = t;
	}
	
	public Unit getLastTank() {
		return lastTank;
	}*/
}
