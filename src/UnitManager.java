import java.util.*;

public class UnitManager {
	
	public static double[][] terrainMultiplierArray = new double[14][10];
	
	public static boolean isPossibleMove(Tile tile1, Tile tile2) {
		Unit temp = tile1.getUnit();
		if ((Math.abs(tile2.getX() - tile1.getX()) + Math.abs(tile2.getY() - tile1.getY())) <= temp.getMovementPts() * 2) {
			return true;
		}
		else
			return false;
		
	}
	
	public static boolean isPossibleMove(Tile tile1, Tile tile2, double points) {
		if ((Math.abs(tile2.getX() - tile1.getX()) + Math.abs(tile2.getY() - tile1.getY())) <= 2 * points) {
			return true;
		}
		else
			return false;
		
	}
	
	public static boolean isValidMove(Tile tile1, Tile tile2) {
		if ((tile1.getUnit().getOwner() == TurnManager.getCurrentPlayer())) {
			setup(tile1.getUnit());
			if (shortestPath(tile1, tile2) >= 0)
				return true;
		}
		return false;
	}
	
	
	
	public static boolean isAttackValid (Tile tile1, Tile tile2) {
		if (tile2.getUnit() != null && (tile1.getUnit().getOwner().equals(TurnManager.getCurrentPlayer())) && !tile2.getUnit().getOwner().equals(TurnManager.getCurrentPlayer())) {
	
			if ((Math.abs(tile1.getX() - tile2.getX()) + Math.abs(tile1.getY() - tile2.getY())) > 1) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	public static void moveUnit(Tile tile1, Tile tile2) {
		Unit temp = tile1.getUnit();
		if (Unit.isInfantry(temp)) { 
			if (tile2.getBuilding() != null) 
				((InfantryDefault)temp).setIsOnCity(true);
			
			else
				((InfantryDefault) temp).setIsOnCity(false);
		}
		//deducts movement points
		//temp.setMovementPts(temp.getMovementPts() - (Math.abs(tile2.getX() - tile1.getX()) + Math.abs(tile2.getY() - tile1.getY())));
		temp.setMovementPts(shortestPath(tile1, tile2));
		tile1.setUnit(null);	
		tile2.setUnit(temp);
	}
	
	public static void attack(Tile tile1, Tile tile2) {
		Unit unit1 = tile1.getUnit();
		Unit unit2 = tile2.getUnit();
		
		if (tile2.getBuilding() != null) 
			Unit.setEnemyOnCity(true); 
		else
			Unit.setEnemyOnCity(false);
		
		// might need to add an "or" to this part for infantry in cities (siege)
		if (unit1.useSpecial(unit2)) {
			unit2.decreaseHealth((int)unit1.getSpecialAttack());
		}
		else {
			unit2.decreaseHealth((int)unit1.getAttack());
		}
		//if unit is destroyed
		if (unit2.getHealth() <= 0) {
			moveUnit(tile1, tile2);
			tile2.getUnit().setMovementPts(0.0);
		}
		else
			tile1.getUnit().setMovementPts(0.0);
		System.out.println("Remaining Health: " + unit2.getHealth());
	}

		public static void setup(Unit u) {
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 10; j++) {
					terrainMultiplierArray[i][j] = u.getMultiplier(GameInterface.grid[i][j].getTerrain());
					if (GameInterface.grid[i][j].getUnit() != null && !GameInterface.grid[i][j].getUnit().getOwner().equals(TurnManager.getCurrentPlayer())) {
						terrainMultiplierArray[i][j] = 50000;
					}
				}
			}
		}
		
		//returns the number of movement points left after movement from tile1 to tile2
		public static double shortestPath(Tile tile1, Tile tile2) {
			double[][] move = new double [14][10];
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 10; j++) {
					move[i][j] = -1;
				}
			}
			Tile active;
			//sets the starting tile movementPoints on the move matrix
			move[tile1.getX()][tile1.getY()] = GameInterface.grid[tile1.getX()][tile1.getY()].getUnit().getMovementPts();
			//makes new queue
			LinkedList<Tile> q = new LinkedList<Tile>();
			
			//adds the first tile to the queue of potential tiles
			q.add(GameInterface.grid[tile1.getX()][tile1.getY()]);
			
			while (!q.isEmpty()) {
				active = q.poll();
				int activeX = active.getX();
				int activeY = active.getY();
				double points = move[active.getX()][active.getY()];
				if (UnitManager.isPossibleMove(active, tile2, move[activeX][activeY])) {
					if (activeY != 9) {
						if (points - terrainMultiplierArray[activeX][activeY + 1] > move[activeX][activeY + 1]) {
							move[activeX][activeY + 1] = points - terrainMultiplierArray[activeX][activeY + 1];
							q.add(GameInterface.grid[activeX][activeY + 1]);
						}
					}
					if (activeY != 0) {
						if (points - terrainMultiplierArray[activeX][activeY - 1] > move[activeX][activeY - 1]) {
							move[activeX][activeY - 1] = points - terrainMultiplierArray[activeX][activeY - 1];
							q.add(GameInterface.grid[activeX][activeY - 1]);
						}
					}
					if (activeX != 13) {
						if (points - terrainMultiplierArray[activeX + 1][activeY] > move[activeX + 1][activeY]) {
							move[activeX + 1][activeY] = points - terrainMultiplierArray[activeX + 1][activeY];
							q.add(GameInterface.grid[activeX + 1][activeY]);
						}
					}
					if (activeX != 0) {
						if (points - terrainMultiplierArray[activeX - 1][activeY] > move[activeX - 1][activeY]) {
							move[activeX - 1][activeY] = points - terrainMultiplierArray[activeX- 1][activeY];
							q.add(GameInterface.grid[activeX - 1][activeY]);
						}
					}
				}
			}
			return move[tile2.getX()][tile2.getY()];
		}
		
	}


