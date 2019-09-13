import java.util.ArrayList;
import java.util.Scanner;

public class program {
	
	//private static String[][] chessboard = new String[8][8];
	private static int[][] matrix = new int[8][8];
	
	private static String[] letters= {"a","b","c","d","e","f","g","h"};

	public static void main(String[] args) {
		System.out.println("The 8 queen problem by Elliot and Simon N.");
		/*
		for(int i=0;i<chessboard.length;i++) {
			for(int x=0;x<chessboard.length;x++) {
				chessboard[i][x]="[ ]";
			}
		}*/
		/*
		Scanner myObj = new Scanner(System.in);
		System.out.print("Place chesspiece: ");
		String position = myObj.nextLine();
		
		placeQueen(position);
		*/
		
		String position = "c3";
		int xC=0;
		for(int i=0;i<letters.length;i++) {
			if(position.substring(0,1).equals(letters[i])) {
				xC=i;
			}
		}		
		int yC = Integer.parseInt(position.substring(1,2))-1;
		
		matrix=placeQueen(xC,yC,matrix);
		printBoard(matrix);
		System.out.println("org");
		
		ArrayList<int[]> freePos = findAllFree(matrix);

		
		ArrayList<int[][]> listOfBoards = placePieces(freePos,matrix);
		/*
		for(int[][] a: listOfBoards) {
			printBoard(a);
			System.out.println();
		}*/
		for(int[][] a:listOfBoards) {
			ArrayList<int[][]> aC = placePieces(freePos,matrix);
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
		int[][] copyBoard = board;
		for(int[] a:list) {
			copyBoard=board;
			copyBoard=placeQueen(a[0],a[1],copyBoard);
			//printBoard(copyBoard);
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
			if((8-numOfQueens)<=numOfZeros) {
				listOfBoards.add(copyBoard);
			}
			//System.out.println("new piece");
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
