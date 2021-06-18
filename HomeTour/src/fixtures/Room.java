package fixtures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Room extends Fixture {
	private HashMap<String, Room> exits = new HashMap<String, Room>();
	private ArrayList<Furniture> furniture = new ArrayList<Furniture>();
	private ArrayList<Appliance> appliances = new ArrayList<Appliance>();
	
	/**
	 * Constructs a Room object with four unknown exits.
	 * 
	 * @param name		the name of the Room
	 * @param shortDesc a sentence to describe the Room
	 * @param longDesc  a paragraph to describe the Room
	 */
	public Room(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
	}
	
	public HashMap<String,Room> getExits() { return exits; }
	
	public Room getExit(String direction) {
		return exits.get(direction);
	}
	
//	public Room getExit (String name) {
//		for (int i = 0; i < exits.size(); i++) {
//			String exitName = exits.get(i).name;
//			if (exitName.equalsIgnoreCase(name)) {
//				return exits.get(i);
//			}
//		}
//		
//		//HOPEFULLY, this is never printed.
//		System.out.println("No exit with that name.");
//		return null;
//	}
	
	public void setExit(String direction, Room room) {
		exits.put(direction, room);		
	}
	
	public void setExits(Room[] rooms) {
		String[] directions = {"north", "east", "south", "west"};
		this.setExits(directions, rooms);
	}
	
	public void setExits(String[] directions, Room[] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			exits.put(directions[i], rooms[i]);
		}
	}
	
	public boolean exitExists(String direction) {
		return exits.containsKey(direction);
	}

	public void printExits() {
		for (Entry<String, Room> entry : this.exits.entrySet()) {
			System.out.println("The " + entry.getValue().getName() + 
					" is " + entry.getKey() + ".");
			System.out.println("\t"+entry.getValue().getShortDescription());
		}
	}

	/**
	 * @return the furniture
	 */
	public ArrayList<Furniture> getFurniture() {
		if (furniture.isEmpty()) {
			System.out.println("There is no furniture in this room.");
		}
		return furniture;
	}

	/**
	 * @param furniture the furniture to set
	 */
	public void setFurniture(ArrayList<Furniture> furniture) {
		this.furniture = furniture;
	}

	/**
	 * @return the appliances
	 */
	public ArrayList<Appliance> getAppliances() {
		if (appliances.isEmpty()) {
			System.out.println("There are no appliances in this room.");
		}
		return appliances;
	}

	/**
	 * @param appliances the appliances to set
	 */
	public void setAppliances(ArrayList<Appliance> appliances) {
		this.appliances = appliances;
	}

}
