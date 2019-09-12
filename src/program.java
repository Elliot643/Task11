import java.util.Scanner;

public class program {
	
	private static String[][] chessboard = new String[8][8];
	
	private static int[] numbers= {1,2,3,4,5,6,7,8};
	private static char[] alphabet= {'a','b','c','d','e','f','g','h'};

	public static void main(String[] args) {
		System.out.println("The 8 queen problem by Elliot and Simon N.");
		
		for(int i=0;i<chessboard.length;i++) {
			for(int x=0;x<chessboard.length;x++) {
				chessboard[i][x]="[ ]";
			}
		}
		Scanner myObj = new Scanner(System.in);
		System.out.println("Place chesspiece");
		String position = myObj.nextLine();
		
		int[] coordinate = placeQueen(position);
		
		
		printBoard();

	}
	private static void placeQueen(String position) {
		for(int i=0;i<numbers.length;i++) {
			
		}
		int x = numbers[];
		int y = Integer.parseInt(position.substring(1,2));
		
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
