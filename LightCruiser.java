
public class LightCruiser extends Ship{

	LightCruiser(){
		this.setLength(5); 
		this.setHit(new boolean[5]);
	}
	
	@Override
	String getShipType() {
		return "light cruiser"; 
	}

}
