package main;

import java.util.Scanner;
import java.lang.Math;

public class Converter {

	public static void main(String[] args) {
		int menuSelection = 0;
		Scanner scanner = new Scanner(System.in);
		
		String[] mainMenu = {"Please select an option:",
				"1. Currency",
				"2. Temperature",
				"3. Weight",
				"4. Quit"};
		
		while (menuSelection != mainMenu.length-1) {
			System.out.println("Welcome to the Main Menu.");
			for (String line : mainMenu) {System.out.println(line);}
			
			menuSelection = Integer.parseInt(scanner.nextLine());
			System.out.println();
			
			switch (menuSelection) {
				default:
					System.out.println("That's not a menu option.");
					System.out.println("Please enter a number.");
					menuSelection = 0; //Reset selection to try again.
					break;
				case 1:
					currencyMenu(scanner);
					break;
				case 2:
					tempMenu(scanner);
					break;
				case 3:
					weightMenu(scanner);
					break;
				case 4:
					System.out.println("Thank you for using the Converter.");
					System.out.println("Goodbye.");
			}	
		}
		
		scanner.close();
	}
	
	protected static boolean isInArray(String str, String[] arr) {
		for (String s : arr) {if (s.equals(str)) {return true;}}
		return false;
	}
		
	protected static int getIndex(String str, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (str.toUpperCase().equals(arr[i].toUpperCase())) {return i;}
		}
		return -1; //This will eventually result in an IndexOutOfBoundsException.
	}
	
	protected static double round(double value, int decimals) {
		return Math.round(value * Math.pow(10, decimals)) / Math.pow(10, decimals);
	}
	
	private static void menuError(String direction) {
		System.out.println("That's not a menu option.");
		System.out.println(direction);
		System.out.println("Use 'X' to return to the Main Menu.");
		System.out.println();
	}
	
	private static void currencyMenu(Scanner scan) {
		String selection = "";
		String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY"};
		float[][] tradeTable = {
				{    1F, 0.826F, 109.703F, 0.709F, 1.299F, 1.217F, 0.899F, 6.398F}, //USD
				{1.211F,     1F, 132.817F, 0.858F, 1.572F, 1.473F, 1.088F, 7.747F}, //EUR
				{0.009117F, 0.007530F, 1F, 0.006460F, 0.011833F, 0.011090F, 0.008193F, 0.058331F}, //JPY
				{1.411F, 1.165F, 154.759F,     1F, 1.831F, 1.716F, 1.268F, 9.028F}, //GBP
				{0.770F, 0.636F,  84.504F, 0.546F,     1F, 0.937F, 0.692F, 4.930F}, //AUD
				{0.822F, 0.679F,  90.178F, 0.583F, 1.067F,     1F, 0.739F, 5.262F}, //CAD
				{1.113F, 0.919F, 122.057F, 0.788F, 1.444F, 1.353F,     1F, 7.121F}, //CHF
				{0.1563F, 0.1291F, 17.1393F, 0.1107F, 0.2028F, 0.1900F, 0.1404F, 1F}, //CNY
		}; // Data collected from X-rates.com @ approximately 06/11/21 16:00.
		//TODO (Maybe?) Confirm reliability of rounding errors.
		
		while (!selection.equals("X")) {
			System.out.println("Current Menu: Currency Converter.");
			System.out.println("Use 'X' to return to the Main Menu. Or press Enter.");
			selection = scan.nextLine().toUpperCase();
			if (selection.equals("X")) {continue;}
			
			System.out.println("The following currencies can be converted.");
			for (String str: currencies) {System.out.print(str + " ");}
			System.out.println();
			
			System.out.println("Which of the above currencies do you have? ");
			selection = scan.nextLine().toUpperCase();
			if (isInArray(selection,currencies)) {
				int originCurrency = getIndex(selection,currencies);
				
				System.out.println("How much of " + currencies[originCurrency] + " do you have? ");
				double originAmount = round(Double.parseDouble(scan.nextLine()),2);
				
				System.out.println("Which of the above currencies do you want? ");
				selection = scan.nextLine().toUpperCase();
				if(isInArray(selection,currencies)) {
					int finalCurrency = getIndex(selection,currencies);
					
					double finalAmount = round(originAmount * tradeTable[originCurrency][finalCurrency], 2);
					System.out.println(originAmount + currencies[originCurrency] + " is " + finalAmount + currencies[finalCurrency]);
					System.out.println("The conversion is complete.");
					System.out.println();
					continue;
				}
			}
			
			menuError("Type the currency code.");
			if (selection.equals("X")) {continue;}
			selection = ""; //Reset selection to try again.
		}		
	}
	
	private static void tempMenu(Scanner scan) {
		String selection = "";
		String[] scales = {"F", "C", "K"};
		float[][][] convertTable = { //Formula is {Add, Multiply, Add}
				{{0,1,0}, {-32, (float)(5.0/9.0), 0}, {-32, (float)(5.0/9.0), 237.15F}}, //Fahrenheit
				{{0, (float)(9.0/5.0), 32}, {0,1,0}, {273.15F, 1, 0}}, //Celsius
				{{-273.15F, (float)(9.0/5.0), 32}, {-273.15F, 1, 0}, {0,1,0}}  //Kelvin
		};
		
		while (!selection.equals("X")) {
			System.out.println("Current Menu: Temperature Converter.");
			System.out.println("Use 'X' to return to the Main Menu. Or press Enter.");
			selection = scan.nextLine().toUpperCase();
			if (selection.equals("X")) {continue;}
			
			System.out.println("The following currencies can be converted.");
			for (String s: scales) {System.out.print(s + " ");}
			System.out.println();
			
			System.out.println("Which of the above temperatures do you have? ");
			selection = scan.nextLine().toUpperCase();
			if (isInArray(selection,scales)) {
				int originTemp = getIndex(selection,scales);
				
				System.out.println("What is your temperature in " + scales[originTemp] + "? ");
				double originAmount = round(Double.parseDouble(scan.nextLine()),2);
				
				System.out.println("Which of the above temperatures do you want? ");
				selection = scan.nextLine().toUpperCase();
				if(isInArray(selection,scales)) {
					int finalTemp = getIndex(selection,scales);
					float[] conversion = convertTable[originTemp][finalTemp];
					
					double finalAmount = round((originAmount + conversion[0]) * conversion[1] + conversion[2], 2);
					System.out.println(originAmount + scales[originTemp] + " is " + finalAmount + scales[finalTemp]);
					System.out.println("The conversion is complete.");
					System.out.println();
					continue;
				}
			}
			
			menuError("Type the temperature code.");
			if (selection.equals("X")) {continue;}
			selection = ""; //Reset selection to try again.
		}
	}
	
	private static void weightMenu(Scanner scan) {
		String selection = "";
		
		String[] weights = {"LBS", "KG"};
		float[][] convertTable = {
			{      1, 0.4535F}, //LBS
			{2.2046F,       1}  //KG
		};
		
		while (!selection.equals("X")) {
			System.out.println("Current Menu: Weight Converter.");
			System.out.println("Use 'X' to return to the Main Menu. Or press Enter.");
			selection = scan.nextLine().toUpperCase();
			if (selection.equals("X")) {continue;}
			
			System.out.println("The following weights can be converted.");
			for (String str: weights) {System.out.print(str + " ");}
			System.out.println();
			
			System.out.println("Which of the above weights do you have? ");
			selection = scan.nextLine().toUpperCase();
			if (isInArray(selection,weights)) {
				int originWeight = getIndex(selection,weights);
				
				System.out.println("How much of " + weights[originWeight] + " do you have? ");
				double originAmount = round(Double.parseDouble(scan.nextLine()),2);
				
				System.out.println("Which of the above weights do you want? ");
				selection = scan.nextLine().toUpperCase();
				if(isInArray(selection,weights)) {
					int finalWeight = getIndex(selection,weights);
					
					double finalAmount = round(originAmount * convertTable[originWeight][finalWeight], 2);
					System.out.println(originAmount + weights[originWeight] + " is " + finalAmount + weights[finalWeight]);
					System.out.println("The conversion is complete.");
					System.out.println();
					continue;
				}
			}
		
			menuError("Type the weight code.");
			if (selection.equals("X")) {continue;}
			selection = ""; //Reset selection to try again.
		}
	}
}
