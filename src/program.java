import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class program {
	
	//private static String[][] chessboard = new String[8][8];
	private static int[][] matrix = new int[8][8];
	
	private static String[] letters= {"a","b","c","d","e","f","g","h"};
	
	private static ArrayList<int[][]> possibleSolutions = new ArrayList<int[][]>();
	
	static int numOfPrints=0;

	public static void main(String[] args) {
		System.out.println("The 8 queen problem by Elliot and Simon N.\nThe Queens' positions is marked by a 2");
		
		
		Scanner myObj = new Scanner(System.in);
		System.out.print("Place chesspiece: ");
		String position = myObj.nextLine();
		myObj.close();
		System.out.println();
		
		Boolean validLetter = false;
		int xC=0;
		for(int i=0;i<letters.length;i++) {
			if(position.substring(0,1).equals(letters[i])) {
				xC=i;
				validLetter=true;
			}
		}
		if(!validLetter) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		int yC=0;
		try {
			yC = Integer.parseInt(position.substring(1,2))-1;
			if(yC>8) {
				System.out.println("Invalid input");
				System.exit(0);
			}
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		
		//Places first Queen.
		matrix=placeQueen(xC,yC,matrix);
		
		//Finds all open positions with the first Queen placed.
		ArrayList<int[]> freePos = findAllFree(matrix);
		//Places a second Queen on all possible positions.
		ArrayList<int[][]> listOfBoards = placePieces(freePos,matrix);

		//Iterates thru all possible boards.
		recursiveFunction(listOfBoards);
		
		//Prints the solutions.
		for(int[][] a: possibleSolutions) {
			printBoard(a);
			System.out.println();
		}
		
		System.out.println("Found solutions: "+possibleSolutions.size());

	}
	private static void recursiveFunction(ArrayList<int[][]> listOfBoards) {
		
				
		for(int[][] a:listOfBoards) {
			ArrayList<int[][]> aC = placePieces(findAllFree(a),a);
			recursiveFunction(aC);
		}

	}
	private static int[][] placeHorizontal(int x, int y, int[][] board) {
		for(int i=0;i<8;i++) {

			board[i][y]=1;
		}
		return board;
	}
	private static int[][] placeVertical(int x, int y, int[][] board) {
		for(int i=0;i<8;i++) {

			board[x][i]=1;
		}
		return board;
	}
	private static int[][] placeAscending(int xStart, int yStart, int[][] board) {
		int y=yStart;
		for(int x=xStart;x<8;x++) {
			if(y>7) {
				break;
			}

			board[x][y]=1;
			y++;
		}
		y=yStart;
		for(int x=xStart;x>-1;x--) {
			if(y<0) {
				break;
			}

			board[x][y]=1;
			y--;
		}
		return board;

	}
	private static int[][] placeDescending(int xStart, int yStart, int[][] board) {
		
		int y=yStart;
		for(int x=xStart;x<8;x++) {
			if(y<0) {
				break;
			}

			board[x][y]=1;
			y--;
		}
		y=yStart;
		for(int x=xStart;x>-1;x--) {
			if(y>7) {
				break;
			}

			board[x][y]=1;
			y++;
		}
		return board;

	}
	private static ArrayList<int[][]> placePieces(ArrayList<int[]> list, int[][] board){
		
		ArrayList<int[][]> listOfBoards = new ArrayList<int[][]>();
		int[][] copyBoard;
		
		for(int[] a:list) {
			copyBoard = new int[8][8];
			
			for(int xC=0;xC<8;xC++) {
				for(int yC=0;yC<8;yC++) {
					copyBoard[xC][yC]=board[xC][yC];
				}
			}
			copyBoard=placeQueen(a[0],a[1],copyBoard);
			int numOfQueens=0;
			int numOfZeros=0;
			for(int x=0;x<8;x++) {
				for(int y=0;y<8;y++) {
					if(copyBoard[x][y]==2) {
						numOfQueens++;
					}
					if(copyBoard[x][y]==0) {
						numOfZeros++;
					}
				}
			}
			if(numOfQueens==8) {
				boolean exists=false;
				if(possibleSolutions.size()>0) {
					for(int[][] d:possibleSolutions) {
						if(Arrays.deepEquals(d, copyBoard)) {
							exists=true;
						}
					}
					if(!exists) {
						possibleSolutions.add(copyBoard);
					}		
				}
				else {
					possibleSolutions.add(copyBoard);
				}
			}
			else if((8-numOfQueens)<=numOfZeros) {
				listOfBoards.add(copyBoard);
			}
		}
		
		return listOfBoards;
	}
	private static int[][] placeQueen(int x,int y, int[][] board) {
		int[][] copyBoard = new int[8][8];
		
		for(int xC=0;xC<8;xC++) {
			for(int yC=0;yC<8;yC++) {
				copyBoard[xC][yC]=board[xC][yC];
			}
		}
		
		placeHorizontal(x,y,copyBoard);
		placeVertical(x,y,copyBoard);
		
		placeAscending(x,y,copyBoard);
		placeDescending(x,y,copyBoard);
		
		copyBoard[x][y]=2;
		return copyBoard;
	}
	public static void printBoard(int[][] board) {
		for(int y=0;y<board.length;y++) {
			for(int x=0;x<board.length;x++) {
				System.out.print(board[x][7-y]+" ");
			}
			System.out.println();
		}
		
		numOfPrints++;
	}
	public static ArrayList<int[]> findAllFree(int[][] board) {
		
		ArrayList<int[]> result = new ArrayList<int[]>();
		
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				if(board[x][y]==0) {
					int[] coord = {x,y};
					result.add(coord);
				}
			}
		}
	
		return result;
	}

	

}
