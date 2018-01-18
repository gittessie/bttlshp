
public class BattleCruiser extends Ship{

	BattleCruiser(){
		this.setLength(7); 
		this.setHit(new boolean[7]);
	}
	
	@Override
	String getShipType() {
		return "battlecruiser"; 
	}

}
