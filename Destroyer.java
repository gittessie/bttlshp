
public class Destroyer extends Ship {

	Destroyer(){
		this.setLength(4); 
		this.setHit(new boolean[4]);
	}
	
	@Override
	String getShipType() {
		return "destroyer"; 
	}

}
