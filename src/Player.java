
public class Player {

	private int victoryPoints;
	private int oil;
	private int steel;
	private int credits;
	
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
