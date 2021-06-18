package game;

import fixtures.Room;
import java.util.ArrayList;

public class RoomManager {
	private Room startingRoom;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Room> homeExits = new ArrayList<Room>();
	
	/**
	 * Instantiates each <code>Room</code> into an {@link ArrayList} and configures the 
	 * <code>startingRoom</code>.
	 */
	public void init(){
		/*
		 * Instantiate each room and add it to the ArrayList. 
		 * Establish starting room.
		 */
		Room frontPorch = new Room("Front Porch", "A covered porch with a waist-high railing.",
				"A covered brick porch. It has a white wooden railing, but only one hand rail.");
				/*"The brick porch frames a triple wide window and the front door. It's wide "
				 *+ "enough for a pair of chairs and a small side table. The wooden railing is "
				 *+ "painted white to match the two support columns. The brick steps to the "
				 *+ "front lawn are directly across from the door. There's only one hand rail, "
				 *+ "made of twisted iron, but the sidewalk stain says there used to be two.");
				 */
		rooms.add(frontPorch);
		startingRoom = frontPorch;
		
		Room livingRoom = new Room("Living Room", "An extra wide, carpeted living room.", 
				"This living room has a beam running across the ceiling where a wall once "
				+ "stood.");
		rooms.add(livingRoom);
		
		Room office = new Room("Office", "A carpeted side room with a chair rail.", 
				"This room is very open. There's a sliding glass door on one side and a half "
				+ "wall on the other.");
		rooms.add(office);
		
		Room backPorch = new Room("Back Porch", "A covered, enclosed porch.", 
				"It's a screened in brick porch with a ceiling fan.");
		rooms.add(backPorch);
		
		Room kitchen = new Room("Kitchen", "An older kitchen with a window AC.", 
				"There are counter tops along the opposite sides of the kitchen, and a "
				+ "dining table in the middle.");
		rooms.add(kitchen);
		
		Room hallway = new Room("Hallway", "A narrow carpeted hallway.", 
				"This hallway wouldn't be so narrow if the low bookcase wasn't running its "
				+ "length.");
		rooms.add(hallway);
		
		Room halfBathroom = new Room("Half Bathroom", "A bathroom that doubles as a laundry "
				+ "room.", "It's painted burnt orange. There are cabinets above the washer "
						+ "and dryer.");
		rooms.add(halfBathroom);
		
		Room bathroom = new Room("Bathroom", "A cramped bathroom with a porcelain tub.", 
				"The doors to this navy blue bathroom have just enough room to open and not "
				+ "bump into each other.");
		rooms.add(bathroom);
		
		Room masterBedroom = new Room("Master Bedroom", "A large bedroom with a window AC.", 
				"This large room is painted with Dallas Cowboys colors. Why?");
		rooms.add(masterBedroom);
		
		Room backHallway = new Room("Back Hallway", "An enclosed hallway.", 
				"There's a door separating the bedrooms from the rest of the house. It's a "
				+ "safe shelter during hurricane season.");
		rooms.add(backHallway);
		
		Room backBedroom = new Room("Back Bedroom", "A bright bedroom with a window AC.", 
				"This bedroom is slightly smaller than the master. The sun hits two faces of "
				+ "the room.");
		rooms.add(backBedroom);
		
		Room frontBedroom = new Room("Front Bedroom", "A small bedroom.", 
				"This bedroom has windows on the front of the house. It got really warm after "
				+ "the large pine tree was removed.");
		rooms.add(frontBedroom);
		
		Room carport = new Room("Carport", "A cement pad wide enough for two vehicles.",
				"Only one of the two spots is covered. That section is also painted.");
		rooms.add(carport);
		
		Room exit = new Room("Exit", "This will exit the Home Tour.",
				"Congratulations on completing the Home Tour.");
		
		/*
		 * Establish exits for each room.
		 */
		frontPorch.setExits(new String[] {"north", "south"},
				new Room[] {livingRoom, exit});
		livingRoom.setExits(new Room[]{kitchen, hallway, frontPorch, office});
		office.setExits(new Room[]{backPorch, kitchen, livingRoom, carport});
		backPorch.setExits(new String[] {"south", "north"},
				new Room[] {office, exit});
		kitchen.setExits(new String[] {"south", "west"},
				new Room[] {livingRoom, office});
		hallway.setExits(new String[] {"north", "east", "west"},
				new Room[] {halfBathroom, backHallway, livingRoom});
		halfBathroom.setExits(new String[] {"north", "south"},
				new Room[] {bathroom, hallway});
		bathroom.setExits(new String[] {"east", "south"},
				new Room[] {masterBedroom, halfBathroom});
		masterBedroom.setExits(new String[] {"south", "west"},
				new Room[] {backHallway, bathroom});
		backHallway.setExits(new Room[] {masterBedroom, backBedroom, frontBedroom, hallway});
		backBedroom.setExit("north", backHallway);
		frontBedroom.setExit("north", backHallway);
		carport.setExits(new String[] {"east", "south"},
				new Room[] {office, exit});
		
		/*
		 * Establish homeExits
		 */
		homeExits.add(frontPorch);
		homeExits.add(backPorch);
		homeExits.add(carport);
		homeExits.add(exit); //Should not need, just in case.
		
		/*
		 * Define furniture in each room.
		 * Use a furniture manager.
		 */
//		frontPorch
//		livingRoom.setFurniture(new ArrayList<Furniture> {longCouch, loveSeat, nalasCouch, dvdCollection, coffeeTable, endTable, trophyShelves});
//		office.setFurniture(new ArrayList<Furniture> {masterDesk, studentDesk, studyDesk, fileCabinet};
//		backPorch
//		kitchen.setFurniture(new ArrayList<Furniture> {pantryCabinets, diningTable, diningCabinets, sinkCabinet}; 
//		hallway.setFurniture(new ArrayList<Furniture> {bookcase};
//		halfBathroom.setFurniture(new ArrayList<Furniture> {linenCabinets, medicineCabinet, sinkCabinet, laundryBaskets};
//		bathroom.setFurniture(new ArrayList<Furniture> {medicineCabinet, sinkCabinet};
//		masterBedroom.setFurniture(new ArrayList<Furniture> {nightStand, bed, dresser, closet};
//		backHallway
//		backBedroom.setFurniture(new ArrayList<Furniture> {bed, closet, gamingChair, nightStand, dresser, studentDesk};
//		frontBedroom.setFurniture(new ArrayList<Furniture> {studentDesk, futon, dresser, closet, dvdCollection, litterBox};
//		carport
		
		/*
		 * Define appliances in each room.
		 * Use an appliance manager.
		 */
//		frontPorch
//		livingRoom.setAppliances(new ArrayList<Appliances> {entertainmentCenter, gamingCenter};
//		office.setAppliances(new ArrayList<Appliances> {masterComputer, studentComputer, printer, router};
//		backPorch.setAppliances(new ArrayList<Appliances> {propaneGrill, treadmill};
//		kitchen.setAppliances(new ArrayList<Appliances> {refrigerator, deepFreezer, microwave, sink, oven, stoveTop};
//		hallway
//		halfBathroom.setAppliances(new ArrayList<Appliances> {washer, dryer, toilet, sink};
//		bathroom.setAppliances(new ArrayList<Appliances> {shower, bathTub, toilet, sink};
//		masterBedroom.setAppliances(new ArrayList<Appliances> {entertainmentCenter}
//		backHallway
//		backBedroom.setAppliances(new ArrayList<Appliances> {gamingCenter}
//		frontBedroom.setAppliances(new ArrayList<Appliances> {entertainmentCenter}
//		carport.setAppliances(new ArrayList<Appliances> {silveradoExtendedCab};
	}


	public Room getStartingRoom() {	return startingRoom; }
	public void setStartingRoom(Room startingRoom) { this.startingRoom = startingRoom; }

	public ArrayList<Room> getRooms() { return rooms; }
	public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms; }
	
	public boolean isAHomeExit(Room room) { 
		if (homeExits.contains(room)) { return true; } 
		else { return false; }
	}
}
