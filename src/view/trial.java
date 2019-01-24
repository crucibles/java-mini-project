package view;

import java.util.Scanner;

public class trial {
	public static void main (String[] args) {
	    Scanner scnr = new Scanner(System.in);
	    String userString = getUserString(scnr);  
	    System.out.println("\nCurrent Text: " + userString);
	}

	public static String getUserString(Scanner keyboard) { 
	    System.out.println("Enter Initial Text: ");
	    String input = "";
	    String line;
	    while (keyboard.hasNextLine()) {
	        line = keyboard.nextLine();
	        if (line.isEmpty()) {
	            break;
	        }
	        input += line + "\n";
	    }
	    return input;
	}
}
