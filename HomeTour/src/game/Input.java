package game;

//import java.util.ArrayList;
import java.util.Scanner;

//import fixtures.Appliance;
//import fixtures.Furniture;

public class Input {
	private Scanner scanner = new Scanner(System.in);
	
	public void closeScanner() { scanner.close(); }
	
	public String[] collectInput() {		
		System.out.print("What would you like to do? ");
		String[] userInput = scanner.nextLine().split(" ");
		
		if (userInput[0].equalsIgnoreCase("exit")) {
			return new String[] {"exit", ""};
		}
		if (userInput.length != 2) {
			userInput = new String[] {"", ""};
		}
		
		return userInput;
		//TODO catch single word entries here or parseInput?
	}
	
	public void parse(String[] userInput, Player player, RoomManager manager) { //resolve input
		//switch on command {go, move, inspect, pickup, use, sit}
		switch (userInput[0]) {
			default:
				System.out.println("I didn't understand that command.");
				this.printInstructions();
				break;
				
			case "exit":
				boolean isAnExitRoom = manager.isAHomeExit(player.getCurrentRoom());
				if (isAnExitRoom) {
					Main.endGame();
				} else {
					System.out.println("Please navigate to a room with an exit.");
				}
				break;
				
			case "go":
			case "move":
				player.move(userInput[1]);
				break;
				
			case "inspect":
				//TODO complete inspect functionality.
				System.out.println("This functionality is not complete.");
				switch (userInput[1]) {
					default:
						System.out.println("I don't understand what you want to look at.");
						break;
					case "room":
						player.observe();
//						ArrayList<Furniture> roomFurniture = player.getCurrentRoom().getFurniture();
//						ArrayList<Appliance> roomAppliances = player.getCurrentRoom().getAppliances();
//						player.observe(roomFurniture);
//						player.observe(roomAppliances);
						break;
					case "furniture":
						player.getCurrentRoom().getFurniture();
						break;
					case "appliances":
						player.getCurrentRoom().getAppliances();
						
				}
			case "pickup":
			case "use":
			case "sit":
				System.out.println("Clairvoyance can only get you so far...");
				//These commands have not been implemented.
		}
	}
	
	public void printInstructions() {
		System.out.println("--- These are instructions. ---");
		System.out.println("You can 'go' or 'move' in a cardinal direction.");
		//Incomplete impementation.
		System.out.println("You can 'inspect' rooms, furniture, and appliances.");
//		System.out.println("You can try to 'pickup' items."); 
//		System.out.println("You can try to 'sit' on furniture.");
//		System.out.println("You can try to 'use' appliances and furniture.");
		System.out.println("You can try to 'exit'.");
		System.out.println("---   ---\n");
	}
}