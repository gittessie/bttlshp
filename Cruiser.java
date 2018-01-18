
public class Cruiser extends Ship{

	Cruiser(){
		this.setLength(6); 
		this.setHit(new boolean[6]);
	}
	
	@Override
	String getShipType() {
		return "cruiser";
	}

}
