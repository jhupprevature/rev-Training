package game;

public class Main {
	private static RoomManager manager = new RoomManager();
	private static Player player = new Player();
	private static Input handler = new Input();
	private static boolean isRunning = true;

	public static void main(String[] args) {
		manager.init();
		player.setCurrentRoom(manager.getStartingRoom());
		String[] input;
		
		handler.printInstructions();
		player.observe();
		
		while (isRunning) {
			if (!isRunning) { continue; }
			input = handler.collectInput();
			handler.parse(input,player, manager);
		}
		
		System.out.println("The simulation is complete.");
		handler.closeScanner();
	}
	
	public static void endGame() {
		isRunning = false;
	}
}
