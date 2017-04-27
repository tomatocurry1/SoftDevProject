
public class MapLoader {
	
	static final int[] maps = {1,1,1};
	
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
				System.err.println("Player number out of bounds (2-4)");
		}
	}
	
	public static void load2P(int mapnum){
		
		//creates new players and assigns their colors
		TurnManager.plst[0] = new Player(1, 0.5f,0.0f,0.8f);
		TurnManager.plst[1] = new Player(2, 0.8f,0.4f,0.f);
				
		//sets the current player to be the first player
		TurnManager.setCurrentPlayer(TurnManager.plst[0]);
		
		switch(mapnum){
			case 0:
				//makes matrix of tiles
				GameInterface.grid = new Tile[14][10];
				
				
				//places the terrain and resources on the board
				for(int i=0; i<14;i++){
					for(int j=0; j<10;j++){
						GameInterface.grid[i][j] = new Tile(Terrain.GRASSLANDS, null,i,j);
						
						if (i <=9 && i >= 4 && j >= 3 && j <= 6)
							GameInterface.grid[i][j].setTerrain(Terrain.WATER);
						if ((i == 10 || i == 3) &&(j == 4 || j ==5))
							GameInterface.grid[i][j].setTerrain(Terrain.WATER);
						if (j <= 5 && j >= 4 && (i <=2 || (i >= 11)))
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						if (i <= 7 && i >= 6 && (j <= 1 || j >= 8))
							GameInterface.grid[i][j].setTerrain(Terrain.MOUNTAINS);
						if (j <= 6 && j >= 3 && (i == 5 || i == 8))
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
					}
				}	
				
				GameInterface.grid[0][9].setBuilding(new Base());
				GameInterface.grid[0][9].getBuilding().setOwner(TurnManager.plst[0]);
				GameInterface.grid[13][0].setBuilding(new Base());
				GameInterface.grid[13][0].getBuilding().setOwner(TurnManager.plst[1]);
				GameInterface.grid[13][9].setBuilding(new City());
				GameInterface.grid[0][0].setBuilding(new City());
				GameInterface.grid[1][1].setResource(new Oil());
				GameInterface.grid[12][8].setResource(new Oil());
				GameInterface.grid[1][8].setResource(new Steel());
				GameInterface.grid[12][1].setResource(new Steel());
				GameInterface.grid[6][2].setBuilding(new City());
				GameInterface.grid[7][7].setBuilding(new City());
				
				GameInterface.grid[0][9].setUnit(new InfantryDefault(TurnManager.plst[0]));
				GameInterface.grid[13][0].setUnit(new InfantryDefault(TurnManager.plst[1]));
				break;
				
			default:
				System.err.println("Map number out of bounds " + maps[0]);	
		}
	}
	
	public static void load3P(int mapnum){
		
		//creates new players and assigns their colors
		TurnManager.plst[0] = new Player(1, 0.5f,0.0f,0.8f);
		TurnManager.plst[1] = new Player(2, 0.8f,0.4f,0.f);
		TurnManager.plst[2] = new Player(3, 0.0f,0.5f,0.8f);
		
		//sets the current player to be the first player
		TurnManager.setCurrentPlayer(TurnManager.plst[0]);
			
		switch(mapnum){
			case 0:

				
				//makes matrix of tiles
				GameInterface.grid = new Tile[14][10];
				
				
				//places the terrain and resources on the board
				for(int i=0; i<14;i++){
					for(int j=0; j<10;j++){
						GameInterface.grid[i][j] = new Tile(Terrain.WATER, null,i,j);
						
						if((j==9 || j== 8) && ((i >= 0 && i <= 5) || (i >=8 && i <= 13))){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						
						if((j==7 || j== 6) && ((i >= 0 && i <= 4) || (i >=9 && i <= 13))){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						
						if(j==5  && ((i >= 0 && i <= 3) || (i >=10 && i <= 13))){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						
						if(j==0  && (i >= 2 && i <= 11)){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						if(j==1  && (i >= 3 && i <= 10)){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						if(j==2  && (i >= 4 && i <= 9)){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						if(j==3  && (i >= 5 && i <= 8)){
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
						
						if(j==8 && i == 11){
							GameInterface.grid[i][j].setResource(new Oil());
						}
						
						if (j==0 && i==5) {
							GameInterface.grid[i][j].setResource(new Oil());
						}
	
						if (j==8 && i==2) {
							GameInterface.grid[i][j].setResource(new Oil());
						}
				
						if(j==1  && (i == 0 || i == 13)){
							GameInterface.grid[i][j].setResource(new Oil());
							GameInterface.grid[i][j].setTerrain(Terrain.GRASSLANDS);
						}
												
						if( j == 6 && i == 6){
							GameInterface.grid[i][j].setResource(new Oil());
						}
						
						if( j == 5 && i == 7){
							GameInterface.grid[i][j].setResource(new Oil());
						}
						
						if(j==6 && i == 1){
							GameInterface.grid[i][j].setResource(new Steel());
						}
						
						if(j==3 && i == 7){
							GameInterface.grid[i][j].setResource(new Steel());
						}
						
						if(j==9 && i == 9){
							GameInterface.grid[i][j].setResource(new Steel());
						}
						
						if((j==4 )&& (i == 7 || i == 6)){
							GameInterface.grid[i][j].setTerrain(Terrain.MOUNTAINS);
						}
						
						if(j==3 && (i == 4 || i == 5 || i == 9 || i == 8)){
							GameInterface.grid[i][j].setTerrain(Terrain.MOUNTAINS);
						}
						
						if(j==2 && (i == 0 || i == 1 || i == 12 || i == 13)){
							GameInterface.grid[i][j].setTerrain(Terrain.MOUNTAINS);
						}

						if ((i==3 && (j >= 2 && j <= 5))) {
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
						}
						if ((i==10 && (j >= 2 && j <= 5))) {
							GameInterface.grid[i][j].setTerrain(Terrain.ROADS);
						}
						
						if ((j==8 && (i >= 6 && i <= 7))) {
							GameInterface.grid[i][j].setTerrain(Terrain.HILLS);
						}
						
						
												
					}
				}	
				
				GameInterface.grid[3][9].setBuilding(new Base());
				GameInterface.grid[3][9].getBuilding().setOwner(TurnManager.plst[0]);
				GameInterface.grid[12][7].setBuilding(new Base());
				GameInterface.grid[12][7].getBuilding().setOwner(TurnManager.plst[1]);
				GameInterface.grid[4][1].setBuilding(new Base());
				GameInterface.grid[4][1].getBuilding().setOwner(TurnManager.plst[2]);
				
				GameInterface.grid[2][5].setBuilding(new City());
				GameInterface.grid[9][1].setBuilding(new City());
				GameInterface.grid[11][5].setBuilding(new City());
				
						
				GameInterface.grid[3][9].setUnit(new InfantryDefault(TurnManager.plst[0]));
				GameInterface.grid[12][7].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[4][1].setUnit(new InfantryDefault(TurnManager.plst[2]));
				break;
				
			default:
				System.err.println("Map number out of bounds " + maps[1]);

		}
		
	}
	
	public static void load4P(int mapnum){
		
		//creates new players and assigns their colors
		TurnManager.plst[0] = new Player(1, 0.5f,0.0f,0.8f);
		TurnManager.plst[1] = new Player(2, 0.8f,0.4f,0.f);
		TurnManager.plst[2] = new Player(3, 0.0f,0.5f,0.8f);
		TurnManager.plst[3] = new Player(4, 0.8f,0.5f,0.8f);
		
		//sets the current player to be the first player
		TurnManager.setCurrentPlayer(TurnManager.plst[0]);
		TurnManager.arrayPointer = 0;
		
		switch(mapnum){
			case 0:
			
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
				/*GameInterface.grid[1][0].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[13][8].setUnit(new InfantryDefault(TurnManager.plst[1]));
				GameInterface.grid[13][0].setUnit(new InfantryDefault(TurnManager.plst[1]));
				
				*/
				
				
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
				
			default:
				System.err.println("Map number out of bounds " + maps[2]);
		}
					
					
			
	}
}
