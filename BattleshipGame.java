import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattleshipGame {

	public static void main(String[] args) {
		
		Ocean ocean = new Ocean(); 
		ocean.placeAllShipsRandomly();
		
		System.out.println("Now playing Battleship!");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			ocean.print();
			if(ocean.isGameOver()) {
				System.out.println("Congrats! You sunk all the ships! It took you " + ocean.getHitCount() + " shots.");
				scanner.close(); 
				break; 
			}
			
			System.out.println("Take your next 5 shots or type QUIT to exit the game.");
			System.out.println("Enter the x and y coordinates of the next 5 spots where you'd like to shoot.");
			System.out.println("The input format should look something like this: x1 y1 x2 y2 x3 y3 x4 y4 x5 y5");
			
			String userInput = scanner.nextLine();
			
			if (userInput.equals("QUIT")){
				System.out.println("Exiting Game.");
				scanner.close();
				break;
			}
			
			Pattern p = Pattern.compile("-?\\d+");
			Matcher m = p.matcher(userInput); 
			ArrayList<Integer> inputVals = new ArrayList<>();
			while(m.find()) {
				inputVals.add(Integer.parseInt(m.group()));
			}
			if(inputVals.size() == 10) {
			
				boolean invalidCoord = false; 
				for(int i=0; i < 10; i++) {
					if(inputVals.get(i) < 0 || inputVals.get(i) > 19) {
						invalidCoord = true; 
					}
				}
				
				if(invalidCoord) {
					System.out.println("Invalid coordinate(s) provided. All values should be from 0-19. Try again.");
				}
				else {
					for(int j = 0; j < 10; j +=2) {
						int xInput = inputVals.get(j);
						int yInput = inputVals.get(j+1);
						ocean.shootAt(xInput, yInput);
						if(ocean.getShipArray()[xInput][yInput].isSunk()) {
							System.out.println("Yay! You sunk a " + ocean.getShipArray()[xInput][yInput].getShipType()+".");
							System.out.println((13 - ocean.getShipsSunk())+" ship(s) to go."); 
						}
					}
				}
			}
			else {
				System.out.println("Invalid Input.");
			}
		}
		
		
	}

}
