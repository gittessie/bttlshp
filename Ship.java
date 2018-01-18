
public abstract class Ship {

	private int bowRow; //the row coordinate of the front of the ship
	private int bowColumn; //the column coordinate of the front of the ship 
	private int length; //the length of the ship
	private boolean horizontal; //true if ship is placed horizontally (takes up a single row), false if vertical 
	private boolean[] hit; //keep track of where the ship has been hit from front to back
	
	public int getBowRow() {
		return bowRow;
	}
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int len) {
		this.length = len;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean[] getHit() {
		return hit;
	}
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	abstract String getShipType(); 
	
	/**
	 * Checks whether the ship can be placed in the selected position without overlapping or touching other ships 
	 * @param row
	 * @param col 
	 * @param horiz - whether the ship is being placed vertically or horizontally
	 * @param ocean - the ocean to place the ship in 
	 * @return true if ship can be legally placed in the spot, false otherwise 
	 */
	boolean okToPlaceShipAt(int row, int col, boolean horiz, Ocean ocean) {
		Ship[][] ships = ocean.getShipArray(); 
		
		//check for invalid coordinates 
		if(row > 19 || row < 0 || col > 19 || col < 0) {
			return false; 
		}
		
		if(horiz) {
			if(col + length > 20) {
				return false; 
			}
			for(int i = col; i < length + col; i++) {
				//check this square
				if(ships[row][i].getShipType() != "empty") {
					return false; 
				}
				//check square above
				if(row != 0) {
					if(ships[row-1][i].getShipType() != "empty") {
						return false; 
					}
				}
				//check square below
				if(row != 19) {
					if(ships[row+1][i].getShipType() != "empty") {
						return false; 
					}
				}
				//check square to right
				if(i != 19) {
					if(ships[row][i+1].getShipType() != "empty") {
						return false; 
					}
					
					//check square up right
					if(row != 0) {
						if(ships[row-1][i+1].getShipType() != "empty") {
							return false; 
						}
					}
					//check square bottom right
					if(row != 19) {
						if(ships[row+1][i+1].getShipType() != "empty") {
							return false; 
						}
					}
				}
				
				//check square to left
				if(i != 0) {
					if(ships[row][i-1].getShipType() != "empty") {
						return false; 
					}
					
					//check square up left
					if(row != 0) {
						if(ships[row-1][i-1].getShipType() != "empty") {
							return false; 
						}
					}
					//check square bottom left
					if(row != 19) {
						if(ships[row+1][i-1].getShipType() != "empty") {
							return false; 
						}
					}
				}			
			}
		}
		else { //vertical 
			if(row + length > 20) {
				return false; 
			}
			for(int i = row; i < length + row; i++) {
				//check this square
				if(ships[i][col].getShipType() != "empty") {
					return false; 
				}
				//check square above
				if(i != 0) {
					if(ships[i-1][col].getShipType() != "empty") {
						return false; 
					}
				}
				//check square below
				if(i != 19) {
					if(ships[i+1][col].getShipType() != "empty") {
						return false; 
					}
				}
				//check square to right
				if(col != 19) {
					if(ships[i][col+1].getShipType() != "empty") {
						return false; 
					}
					
					//check square up right
					if(i != 0) {
						if(ships[i-1][col+1].getShipType() != "empty") {
							return false; 
						}
					}
					//check square bottom right
					if(i != 19) {
						if(ships[i+1][col+1].getShipType() != "empty") {
							return false; 
						}
					}
				}
				
				//check square to left
				if(col != 0) {
					if(ships[i][col-1].getShipType() != "empty") {
						return false; 
					}
					
					//check square up left
					if(i != 0) {
						if(ships[i-1][col-1].getShipType() != "empty") {
							return false; 
						}
					}
					//check square bottom left
					if(i != 19) {
						if(ships[i+1][col-1].getShipType() != "empty") {
							return false; 
						}
					}
				}			
			}
		}
		return true; 
	}
	
	/**
	 * Places the bow of a ship at a given location 
	 * @param row
	 * @param col
	 * @param horiz - whether the ship is being placed vertically or horizontally
	 * @param ocean - the ocean to place the ship in
	 */
	void placeShipAt(int row, int col, boolean horiz, Ocean ocean) {
		bowRow = row; 
		bowColumn = col; 
		horizontal = horiz; 
		Ship[][] oceanArray = ocean.getShipArray(); 
		if(horiz) {
			for(int i = col; i < length + col; i++) {
				oceanArray[row][i] = this; 
			}
		} 
		else {
			for(int i = row; i < length + row; i++) {
				oceanArray[i][col] = this; 
			}
		}
		ocean.setShipArray(oceanArray);
	}
	
	/**
	 * Marks the corresponding part of the ship as hit if occupies the given location 
	 * @param row
	 * @param col
	 * @return true if ship was hit, false otherwise 
	 */
	boolean shootAt(int row, int col) {
		if(horizontal) {
			if(row == bowRow && col >= bowColumn && col < bowColumn + length) {
				if(isSunk()) {
					return false; 
				}
				hit[col - bowColumn] = true; 
				return true; 
			}
		}
		else {
			if(col == bowColumn && row >= bowRow && row < bowRow + length) {
				if(isSunk()) {
					return false; 
				}
				hit[row- bowRow] = true; 
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Checks if a ship is hit at a specific coordinate 
	 * @param row
	 * @param col
	 * @return
	 */
	boolean hitAtSpot(int row, int col) {
		int index; 
		if(horizontal) {
			index = col- bowColumn; 
		}
		else {
			index = row- bowRow; 
		}
		return hit[index]; 
	}
	
	/**
	 * Checks if all parts of the ship have been hit 
	 * @return true if ship is sunk, false otherwise 
	 */
	boolean isSunk() {
		for(int i = 0; i < length; i++) {
			if(hit[i] == false) {
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * @return "x" if ship has been sunk, "S" otherwise 
	 */
	@Override
	public String toString() {
		if(isSunk()) {
			return "x"; 
		} 
		return "S"; 
	}
	
}
