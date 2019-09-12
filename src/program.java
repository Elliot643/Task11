import java.util.Scanner;

public class program {
	
	private static String[][] chessboard = new String[8][8];
	private static int[][] matrix = new int[8][8];
	
	private static String[] letters= {"a","b","c","d","e","f","g","h"};

	public static void main(String[] args) {
		System.out.println("The 8 queen problem by Elliot and Simon N.");
		
		for(int i=0;i<chessboard.length;i++) {
			for(int x=0;x<chessboard.length;x++) {
				chessboard[i][x]="[ ]";
			}
		}
		
		Scanner myObj = new Scanner(System.in);
		System.out.print("Place chesspiece: ");
		String position = myObj.nextLine();
		
		placeQueen(position);
		
		
		printBoard();

	}
	private static void placeQueen(String position) {
		int x=0;
		for(int i=0;i<letters.length;i++) {
			if(position.substring(0,1).equals(letters[i])) {
				x=i;
			}
		}
		
		int y = 8-Integer.parseInt(position.substring(1,2));
		
		chessboard[y][x]="[X]";
		
	}
	public static void printBoard() {
		for(int i=0;i<chessboard.length;i++) {
			for(int x=0;x<chessboard.length;x++) {
				System.out.print(chessboard[i][x]);
			}
			System.out.println();
		}
	}

}
