
public class EmptySea extends Ship{
	
	EmptySea(){
		this.setLength(1); 
		this.setHit(new boolean[1]);
	}
	
	/**
	 * not actually a ship, represents empty space 
	 */
	@Override
	String getShipType() {
		return "empty"; 
	}
	
	/**
	 * no successful shots possible for sea
	 */
	@Override
	boolean shootAt(int row, int col) {
		boolean[] newHit = new boolean[1]; 
		newHit[0] = true; 
		this.setHit(newHit); 
		return false; 
	}
	
	/**
	 * water can't sink
	 */
	@Override
	boolean isSunk() {
		return false; 
	}
	
	/**
	 * @return "." for empty water not hit, "-" for hit water
	 */
	@Override
	public String toString() {
		if(this.getHit()[0]) {
			return "-"; 
		}
		return "."; 
	}

}
