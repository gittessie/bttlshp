
public class Submarine extends Ship{

	Submarine(){
		this.setLength(3); 
		this.setHit(new boolean[3]);
	}
	
	@Override
	String getShipType() {
		return "submarine"; 
	}

	

}
