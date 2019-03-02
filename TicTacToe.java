import java.util.InputMismatchException;
import java.util.Scanner;
public class TicTacToe {
	Scanner scanner = new Scanner(System.in).useDelimiter("\r\n");
	private char[][] board;
	private char currentPlayer;
	private int randomNumber = 1 + (int)(Math.random() * 100);
	private int row = getRow();
	private int col = getCol();
	
	public static void main(String[]args) {

		TicTacToe game = new TicTacToe();
		game.go();

	}

	//This is where the game runs
	public void go() {
		boolean isThereAWinner = false;
		System.out.println("Player " + currentPlayer + " is up first.\n");
		printBoard();
		while(!isThereAWinner) {
			
			placeMarker(getRow(),getCol());
			printBoard();
			checkForWin();
			if(checkForWin()) {
				System.out.println("Player " + currentPlayer + " wins!");
				isThereAWinner = true;
				scanner.close();
				break;
			}
			changePlayer();
			System.out.println("Player " + currentPlayer + " is up.\n");
		}
	}

	//construct a TicTacToe Game
	public TicTacToe(){
		board = new char[3][3];
		currentPlayer = firstSelection(randomNumber);
		initializeBoard();
	}

	//Initialize game board
	public void initializeBoard() {
		//loop through the rows
		for(int row = 0; row < 3; row++) {
			//loop through the columns
			for(int col = 0; col < 3; col++) {
				board[row][col] = '-';
			}
		}
	}

	//Change player mark
	public void changePlayer() {
		if(currentPlayer == 'x') 
			currentPlayer = 'o';
		else
			currentPlayer = 'x';
	}	

	//Print game board
	public void printBoard() {
		int x = 1;
		System.out.println("         COLUMNS");
		System.out.println("        1   2   3");
		System.out.println("      -------------");
		for(int row = 0; row < 3; row++) {		
			System.out.print("ROW " + x + " ");
			System.out.print("| ");		
			for(int col = 0; col < 3; col++) {
				System.out.print(board[row][col] + " | ");		
			}
			System.out.println();
			System.out.println("      -------------");
			x++;
		}
		System.out.println();
	}

	//Print current player
	public void currentTurn() {
		System.out.println("The current player is " + currentPlayer);
	}

	//Places a marker
	public boolean placeMarker(int row, int col) {
		if((row >= 0) && (row < 3)){
			if((col >= 0) && (col < 3)) {
				if(board[row][col] == '-') {
					board[row][col] = currentPlayer;
					return true;
				}
			}
		}
		return false;
	}

	//Is the game board full?
	public boolean isBoardFull() {
		boolean isFull = true;
		//loop through the rows
		for(int row = 0; row < 3; row++) {
			//loop through the columns
			for(int col = 0; col < 3; col++) {
				if(board[row][col] == '-')
					isFull = false;
			}
		}
		return isFull;
	}

	//Check if three values are the same
	private boolean checkRowCol(char col1, char col2, char col3) {
		return ((col1 != '-') && (col1 == col2) && (col2 == col3));

	}

	//Check Rows for win
	private boolean checkRows() {
		for(int i = 0; i < 3; i++) {
			if(checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	//Check Columns for win
	private boolean checkCol() {
		for(int i = 0; i < 3; i++) {
			if(checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	//Check diagonals for win'
	private boolean checkDiagonals() {
		return((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));	
	}

	//Check for win
	public boolean checkForWin() {
		return(checkRows() || checkCol() || checkDiagonals());
	}

	//Randomly select who goes first
	public char firstSelection(int randomNum) {
		//randomNumber = 1 + (int)(Math.random() * 100);
		if(randomNumber % 2 == 0) 
			return 'x';

		return 'o';
	}

	//Get User Input
	private int getRow() {
		boolean isValidInput = false;
		int row = -1;

		while(!isValidInput) {
			try {
				System.out.println("Enter a row 1-3 ");
				row = scanner.nextInt() - 1;
				checkNumber(row);
				isValidInput = true;
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid Input");
				scanner.next();
			}
			catch(tooLowException ex) {
				System.out.println("Too low, please enter a row 1-3");
				scanner.next();
			}
			catch(tooHighException ex) {
				System.out.println("Too high, please enter a row 1-3");
				scanner.next();
			}
		}

		return row;
	}
	
	private int getCol() {
		boolean isValidInput = false;
		int col = -1;

		while(!isValidInput) {
			try {
				System.out.println("Enter a col 1-3 ");
				col = scanner.nextInt() - 1;
				checkNumber(col);
				isValidInput = true;
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid Input");
				scanner.next();
			}
			catch(tooLowException ex) {
				System.out.println("Too low, please enter a col 1-3");
				scanner.next();
			}
			catch(tooHighException ex) {
				System.out.println("Too high, please enter a col 1-3");
				scanner.next();
			}
		}

		return col;
	}
	
	//Exceptions
	public static void checkNumber(int input) throws tooLowException, tooHighException{
		if(input < 0) 
			throw new tooLowException();
		else if(input > 2) 
			throw new tooHighException();	
	}

	
}	

class tooHighException extends Exception{}
class tooLowException extends Exception{}















































