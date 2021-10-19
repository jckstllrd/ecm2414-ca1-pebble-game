package PebbleGame;

import java.util.Scanner;

public class UserInterface {
    
    Scanner sc = new Scanner(System.in);

    private String getInput() {
        String input = sc.nextLine();
        return input;
        
    }

    public void displayTitleMessage() {
        System.out.println("Welcome to the PebbleGame!!\nYou will be asked to enter the number of players.\nand then for the location of three files in turn contaiing comma separated integer values for the pebble weights.\nThe integer values must be strictly positive.\nTh game will then be simulated, and utput written to files in this directory.");
    }

    public int askNumPlayers() {
        System.out.println("Please enter the number of players:");
        String numPlayers = getInput();
        return Integer.parseInt(numPlayers);
    }

    public String askBagLocation(int bagNumber) {
        System.out.printf("Please enter location of bag number %d to load:", bagNumber);
        String bagLocation = getInput();
        return bagLocation;
    }
}
