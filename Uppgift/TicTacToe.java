package Uppgift ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
    	//Codereview: declared playerPos so it can be used in try catch
    	int playerPos = 0;
    	//Codereview: Declared scanner outside while, does not need a new scanner every cycle
    	Scanner scan = new Scanner(System.in);
    	
    	
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);

	    while(true) {
	        
	        System.out.println("Enter your placement (1-9):");
	        
	        playerPos = getUserInput(scan);
	        
	        if (gameEngine(gameBoard, playerPos, "player").length() > 0) break;
	        if (gameEngine(gameBoard, 0, "cpu").length() > 0) break;
	
	    }

    }
    
    
    //Codereview:take care of user input with recursive call
    private static int getUserInput(Scanner scan) {
    	String res = scan.next();
    	int returnval = 0;
    	
    	if (res.matches("[0-9]"))
    		returnval = Integer.parseInt(res);
    	else {
    		System.out.println("Input needs to be a number");
    		returnval = getUserInput(scan);
    	}
    	
    	return returnval;
    }
    

    private static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    private static String checkWinner() {

    	//Codereview: added Integer type in the list
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8,9);
        List<Integer> leftCol = Arrays.asList(1, 2, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winConditions = new ArrayList<List<Integer>>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(cross1);
        winConditions.add(cross2);

        for(List<Integer> l : winConditions) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins! Sorry :-(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT";
            }
        }

        return "";
    }
	
  
    //Codereview:might be easier to read if there is a method for taking care of the inputs and checking winner 
    private static String gameEngine(char[][] gameBoard, int pos, String user) {
    	Random rand = null;
    	int cpuPos = 0;
    	
    	if (user.equals("cpu")) {
            rand = new Random();
            cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, user);
            printGameBoard(gameBoard);
        }else {
        	placePiece(gameBoard, pos, user);
        }
    	
    	
        String result = checkWinner();
        if (result.length() > 0) {
        	if (user.equals("player"))
        		printGameBoard(gameBoard);
        	System.out.println(result);
        	return result;
        }
        
        return "";
        
    }
}
