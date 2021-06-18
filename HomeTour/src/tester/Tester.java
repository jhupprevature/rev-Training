package tester;

import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
		String input = "room";
		String[] array = {"room", "exit"};
		String array0 = array[0];
		
		String[] userInput = {"", ""};
		
		System.out.print("What would you like to do? ");
		userInput = "go living room".split(" ");
		
		if (userInput.length > 2) {
			String command = userInput[0];
			String secondInput = "";
			for (int i = 1; i <= userInput.length-1; i++) {
				System.out.println("i=" + i + "input=" + userInput[i]);
				secondInput = secondInput.concat(userInput[i] + " ");
				System.out.println(secondInput);
			}
			userInput = new String[] {command, secondInput.trim()};
		}
		System.out.println(Arrays.toString(userInput));
		// {"go", "living", "room"}

	}

}
