
public class MapLoader {
	
	static final int[] maps = {0,0,1};
	
	public static void load(int playernum){
		load(playernum, (int)(Math.random()*maps[playernum-2]));
	}
	
	public static void load(int playernum, int mapnum){
		switch(playernum){
			case 2:
				load2P(mapnum);
				break;
			case 3:
				load3P(mapnum);
				break;
			case 4:
				load4P(mapnum);
				break;
			default: 
		}
	}
	
	public static void load2P(int mapnum){
		switch(mapnum){
			case 0:
		}
	}
	
	public static void load3P(int mapnum){
		switch(mapnum){
			case 0:
		}
	}
	
	public static void load4P(int mapnum){
		switch(mapnum){
			case 0:
				//creates new players and assigns their colors
				TurnManager.plst[0] = new Player(1, 0.5f,0.0f,0.8f);
				TurnManager.plst[1] = new Player(2, 0.8f,0.4f,0.f);
				TurnManager.plst[2] = new Player(3, 0.0f,0.5f,0.8f);
				TurnManager.plst[3] = new Player(4, 0.8f,0.5f,0.8f);
				
				//sets the current player to be the first player
				TurnManager.setCurrentPlayer(TurnManager.plst[0]);
				
				//makes matrix of tiles
				GameInterface.grid = new Tile[14][10];
				
				//places the terrain and resources on the board
				for(int i=0; i<14;i++){
					for(int j=0; j<10;j++){
						GameInterface.grid[i][j] = new Tile(Terrain.GRASSLANDS, null,i,j);
						if((j==5)||(j==4)){
							GameInterface.grid[i][j].setTerrain(Terrain.WATER);
						}
						
						if ((j==3  && (i>4 && i<9)) || (j==6 && (i>4 && i<9))) 
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						
						if (((i>=0 && i<4) || (i<= 13 && i>10)) && (j==2 || j ==7))
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						
						if ((j==0 || j==9) && (i==11 ||i == 10 || i == 2 || i == 3))
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						
						if ((j==1 || j==8) && (i ==0 || i == 13))
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						
						if ((j==0 || j==9) && (i ==0 || i == 13))
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						
						if ((j==1 || j==8) && (i==5 || i==8)) 
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
							
						if ((j==4 || j==5 || j ==3 || j == 6)  && (i == 4 || i == 9))
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);

						if ((j == 1 || j == 0 || j == 8 || j == 9) && (i==1 || i==12))
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
						
						if ((i==2 || i==3 || i==10 || i==11) && (j==1 || j==8))
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
						
						if ((i==3 || i==4 || i==9 || i==10) && (j==2 || j==7))
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
						
						if (((j==1 || j == 2) && (i==6 || i == 7)) || ((j == 7 || j ==8) && (i == 6 || i == 7))) {
							GameInterface.grid[i][j].setTerrain(Terrain.MOUNTAINS);
						}
						
						if  ((i==1 || i ==12) && (j==2 || j == 7)) 
							GameInterface.grid[i][j].setResource(new Steel());
						
						if ((i==5 || i==8) && (j==3 || j ==6))
							GameInterface.grid[i][j].setResource(new Steel());
						
						if ((j==0 || j ==9) && (i == 5 || i == 8)) 
							GameInterface.grid[i][j].setResource(new Oil());
						
						
						
						
					}
				}
				//puts units on board for each player
				GameInterface.grid[1][0].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[13][8].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[13][0].setUnit(new InfantryDefault(TurnManager.plst[1]));
				
				
				GameInterface.grid[1][1].setUnit(new InfantryDefault(TurnManager.plst[0]));
				GameInterface.grid[1][8].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[12][8].setUnit(new InfantryDefault(TurnManager.plst[2]));
				GameInterface.grid[12][1].setUnit(new InfantryDefault(TurnManager.plst[3]));

				
				//puts bases on the board for each player and assigns the owner of the base
				GameInterface.grid[0][0].setBuilding(new Base());
				GameInterface.grid[0][0].getBuilding().setOwner(TurnManager.plst[0]);
				GameInterface.grid[0][9].setBuilding(new Base());
				GameInterface.grid[0][9].getBuilding().setOwner(TurnManager.plst[1]);
				GameInterface.grid[13][9].setBuilding(new Base());
				GameInterface.grid[13][9].getBuilding().setOwner(TurnManager.plst[2]);
				GameInterface.grid[13][0].setBuilding(new Base());
				GameInterface.grid[13][0].getBuilding().setOwner(TurnManager.plst[3]);
				GameInterface.grid[5][2].setBuilding(new City());
				GameInterface.grid[8][2].setBuilding(new City());
				GameInterface.grid[5][7].setBuilding(new City());
				GameInterface.grid[8][7].setBuilding(new City());
				break;
				
		}
	}
}