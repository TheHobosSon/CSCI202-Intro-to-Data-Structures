package edu.unca.csci202;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Gameboard {
	
	public Cell[][] board;
	public String field;
	public int[][] locations;
	public boolean completed;
	public boolean failed;
	public int turnNumber = 0;
	
	public Gameboard() {
		board = new Cell[8][8];
		locations = new int[10][2];
		field = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = new Cell();
			}
		}
		mineLocations();
		completed = false;
		failed = false;
	}
	
	public String showBoard() {
		field = "";
		int[] arr = new int[2];
		for(int i = 0; i < board.length; i++) {
			if(i > 0) {
				field += "\n";
			}
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].found && board[i][j].isMine) {
					board[i][j].setMine("M");
				}else if(board[i][j].found && board[i][j].isMine == false){
					arr[0] = i;
					arr[1] = j;
					board[i][j].setMine(calculateMines(arr));
				}else if(board[i][j].isMine && board[j][i].hidden == false) {
					board[i][j].setMine("M");
				}else {
					board[i][j].setMine("-");
				}
				field += board[i][j].getMine() + " ";
			}
		}
		return field;
	}
	
	public int[][] mineLocations() {
		Random rand = new Random();
		int row;
		int column;
		int totalMines = 0;
		while(totalMines < 10) {
			row = rand.nextInt(8);
			column = rand.nextInt(8);
			if(board[row][column].isMine == false){
				locations[totalMines][0] = row;
				locations[totalMines][1] = column;
				board[row][column].isMine = true;
				totalMines ++;
			}
		}
		return locations;
	}
	
	public String calculateMines(int[] arr) {
		int count = 0;
		String result = "";
		int surroundingSpaces = 0;
		if(arr[0] == 0 || arr[0] == 7) {
			if(arr[1] == 0 || arr[1] == 7) {
				surroundingSpaces = 4;
			}else {
				surroundingSpaces = 6;
			}
		}else if(arr[1] == 0 || arr[1] == 7) {
			surroundingSpaces = 6;
		}else {
			surroundingSpaces = 9;
		}
		outerloop:
		for(int i = 1; i < 3; i++) {
			for(int j = 1; j < 3; j++) {
				if(count == surroundingSpaces) {
					break outerloop;
				}else if(board[i + (arr[0]-1)][j + (arr[1]-1)].isMine) {
					count++;
				}
			}
		}
		result = String.valueOf(count);
		return result;
	}
	
	public void playerGuess() {
		Scanner scan = new Scanner(System.in);
		int[] guess = new int[2];
		String peek;
		String answer;
		while(completed == false) {
			System.out.println(showBoard());
			for(int j = 0; j < board.length; j++) {
				for(int k = 0; k < board[j].length; k++) {
					if(board[j][k].found == false) {
						board[j][k].hidden = true;
					}else {
						board[j][k].hidden = false;
					}
				}
			}
			if (turnNumber == 0) {
				System.out.print("Would you like to peek? (y/n) ");
				turnNumber++;
				peek = scan.next();
				if(peek.toLowerCase().equals("y")) {
					for(int j = 0; j < board.length; j++) {
						for(int k = 0; k < board[j].length; k++) {
							board[j][k].hidden = false;
						}
					}
				}
			}else {
				System.out.print("Enter Row Number: ");
				guess[0] = scan.nextInt();
				System.out.print("Enter Column Number: ");
				guess[1] = scan.nextInt();
				System.out.print("Does row " + guess[0] + " and column " + guess[1] + " contain a mine? (y/n) ");
				answer = scan.next();
				if(board[guess[0] - 1][guess[1] - 1].isMine && answer.toLowerCase().equals("n")) {
					failed = true;
					completed = true;
					System.out.println("You have failed.");
				}else if(board[guess[0] - 1][guess[1] - 1].isMine == false && answer.toLowerCase().equals("y")){
					failed = true;
					completed = true;
					System.out.println("You have failed.");
				}else if(board[guess[0] - 1][guess[1] - 1].isMine && answer.toLowerCase().equals("y")){
					board[guess[0] - 1][guess[1] - 1].found = true;
				}else if(board[guess[0] - 1][guess[1] - 1].isMine == false && answer.toLowerCase().equals("n")) {
					board[guess[0] - 1][guess[1] - 1].found = true;
					
				}
			}	
		}
		
	}
	
	public void run() {
		playerGuess();
//		System.out.println(showBoard());
//		int[] mineTest = new int[2];
//		mineTest[0] = 1;
//		mineTest[1] = 0;
//		System.out.println(calculateMines(mineTest));
		
	}

}
