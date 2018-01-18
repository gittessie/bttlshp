import java.util.*;

public class Ocean {
	Ship[][] ships = new Ship[20][20]; //grid of ship objects 
	int shotsFired; //count of how many shots the user has taken in the game
	int hitCount; //count of how many shots hit a ship, including duplicate hits 
	int shipsSunk; //number of ships that have been sunk in the current game out of 13 
	
	Ocean() {
		shotsFired = 0; 
		hitCount = 0; 
		shipsSunk = 0; 
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				ships[i][j] = new EmptySea(); 
			}
		}
	}
	
	public int getShotsFired() {
		return shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}
		
	public Ship[][] getShipArray() {
		return ships;
	}
	
	public void setShipArray(Ship[][] ships) {
		this.ships = ships; 
	}

	/**
	 * Randomly places all 13 ships on the board/ ocean
	 */
	void placeAllShipsRandomly() {
		Ship[] shipsToPlace = new Ship[13];
		
		//ship 1 - Battleship
		Ship ship1 = new BattleShip();
		shipsToPlace[0] = ship1; 
		//ship 2 - Battle Cruiser
		Ship ship2 = new BattleCruiser();
		shipsToPlace[1] = ship2;
		//ship 3 - Cruiser
		Ship ship3 = new Cruiser();
		shipsToPlace[2] = ship3;
		//ship 4 - Cruiser
		Ship ship4 = new Cruiser();
		shipsToPlace[3] = ship4;
		//ship 5 - Light Cruiser
		Ship ship5 = new LightCruiser();
		shipsToPlace[4] = ship5;
		//ship 6 - Light Cruiser
		Ship ship6 = new LightCruiser();
		shipsToPlace[5] = ship6;
		//ship 7 - Destroyer
		Ship ship7 = new Destroyer();
		shipsToPlace[6] = ship7;
		//ship 8 - Destroyer
		Ship ship8 = new Destroyer();
		shipsToPlace[7] = ship8;
		//ship 9 - Destroyer
		Ship ship9 = new Destroyer();
		shipsToPlace[8] = ship9;
		//ship 10 - Submarine
		Ship ship10 = new Submarine();
		shipsToPlace[9] = ship10;
		//ship 11 - Submarine
		Ship ship11 = new Submarine();
		shipsToPlace[10] = ship11;
		//ship 12 - Submarine
		Ship ship12 = new Submarine();
		shipsToPlace[11] = ship12;
		//ship 13 - Submarine
		Ship ship13 = new Submarine();
		shipsToPlace[12] = ship13;
		
		Random rand = new Random(System.currentTimeMillis());
		//for each ship starting from largest to smallest 
		for(int i = 0; i < 13; i++) {
			while(true) {
				//generate random row and col int
				int row = rand.nextInt(21);
				int col = rand.nextInt(21); 
				//if (okToPlaceShipAt(row, col, true, ocean){
				if(shipsToPlace[i].okToPlaceShipAt(row, col, true, this)){
					// placeShipAt(row, col, true, ocean) } & break 
					shipsToPlace[i].placeShipAt(row, col, true, this);
					//System.out.println("ship " + (i+1) + " placed at "+ row + ", " +col);
					break; 
				}
				// else if (okToPlaceShipAt(row, col, false, ocean){
				else if(shipsToPlace[i].okToPlaceShipAt(row, col, false, this)) {
					// placeShipAt(row, col, false, ocean) } & break out of random col int loop
					shipsToPlace[i].placeShipAt(row, col, false, this); 
					//System.out.println("ship " + i + " placed at "+ row + ", " +col);
					break; 
				}
				//else generate a new random row col combo 
			}
		}
	}
	
	/**
	 * Checks whether a given coordinate is occupied by a ship 
	 * @param row  
	 * @param col 
	 * @return true if spot contains ship, false otherwise
	 */
	boolean isOccupied(int row, int col) {
		if(ships[row][col].getShipType().equals("empty")) {
			return false; 
		}
		return true; 
	}
	/** 
	 * Checks if a ship is hit and updates shotsFired and hitCount 
	 * @param row
	 * @param col
	 * @return true if a non-sunken ship was hit, false otherwise 
	 */
	boolean shootAt(int row, int col) {
		shotsFired += 1; 
		if (ships[row][col].shootAt(row,col)) {
			hitCount += 1; 
			if(ships[row][col].isSunk()) {
				shipsSunk += 1; 
			}
			return true; 
		} 
		return false; 
	}

	/**
	 * Checks whether player sunk all 13 ships
	 * @return -true if all ships sunk, false otherwise 
	 */
	boolean isGameOver() {
		if(shipsSunk == 13) {
			return true;
		}
		return false; 
	}
	
	/**
	 * Prints the ocean with "S" indicating a location that was fired at and a ship was hit, "-" indicating a spot that was shot at with nothing there, 
	 * "x" for locations hit containing a sunken ship , and "." indicating a location that has yet to be fired at 
	 */
	void print() {
		System.out.println("-\t0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19");
		for(int i = 0; i < 20; i++) {
			System.out.print(i+"\t");
			for(int j = 0; j < 20; j++) {
				//System.out.print(ships[i][j]+" ");
				if(ships[i][j].toString().equals(".")) {
					if(ships[i][j].getHit()[0]) {
						System.out.print("-  ");
					}
					else {
						System.out.print(".  ");
					}
				} 
				else if(ships[i][j].toString().equals("x")) {
					System.out.print("x  ");
				}
				else if(ships[i][j].toString().equals("S")) {
					if(ships[i][j].hitAtSpot(i, j)) {
						System.out.print("S  "); 
					}
					else {
						System.out.print(".  ");
					}
				}
				else{
					System.out.print(ships[i][j]+"  ");
				}
			}
			System.out.println("");
		}
	}
	
}
