package game;

import java.util.ArrayList;

import fixtures.Fixture;
import fixtures.Room;

public class Player {
	private Room currentRoom;

	public Room getCurrentRoom() { return currentRoom; }
	public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }
	
	public void move(String direction) {
		// TODO Auto-generated method stub
		if (currentRoom.exitExists(direction)) {
			Room newRoom = currentRoom.getExit(direction);
			currentRoom = newRoom;
		} else {
			System.out.println("That direction doesn't have an exit.");
		}
		this.observe();
	}
	
	public void observe() {
		this.observe(currentRoom);
	}
	
	public void observe(Fixture f) {
		System.out.println("You're observing " + f.getName());
		System.out.println("\t" + f.getLongDescription());
		if (f.getName() == "Exit") {Main.endGame(); }
		if (f instanceof Room) {
			System.out.println("--- Exits ---");
			((Room) f).printExits();
		}
		System.out.println("---   ---");
	}
	public void observe(ArrayList<Fixture> fixtureList) {
		// TODO Auto-generated method stub
		
	}
	
	
}
