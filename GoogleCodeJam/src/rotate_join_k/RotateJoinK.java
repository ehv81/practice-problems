package rotate_join_k;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Google Code Jam Problem: Rotate	
 * https://code.google.com/codejam/contest/544101/dashboard#s=p0
 */

public class RotateJoinK {

	public static void main(String[] args) {
		BufferedReader r;
		FileWriter fw;
		String[] buffer;
		String line;

		String inputFilename = "input.txt";
		String outputFilename = "output.txt";

		int n; //board size
		int k; //target number
		char[][] board;

		int numberOfCases;
		int caseNumber = 1;

		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of cases is %d%n",numberOfCases);

			while( caseNumber <= numberOfCases ){
				buffer = r.readLine().split(" ");
				n = Integer.parseInt(buffer[0]);
				k = Integer.parseInt(buffer[1]);

				board = new char[n][n];

				//initialize the board
				for (int i = 0; i < n; i++) {
					line = r.readLine();
					for (int j = 0; j < n; j++) {
						board[i][j] = line.charAt(j);
					}
				}

				rotateBoard(board);
				applyGravity(board);

				fw.write("Case #" + caseNumber + ": " + andTheWinnerIs(board, k) + "\n");

				caseNumber++;
			}
			r.close();
			fw.close();
			System.out.printf("File %s successfully processed and results written to %s%n",
					inputFilename, outputFilename);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	static void rotateBoard(char[][] board) {
		int n = board.length;
		if (n > 1) {
			char tmp;
			//perform rotation as composition of two reflections:
			//transposition
			for (int i = 0; i < n-1; i++) {
				for (int j = i+1; j < n; j++) {
					tmp = board[i][j];
					board[i][j] = board[j][i];
					board[j][i] = tmp;
				}
			}
			//horizontal reflection
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n/2; j++) {
					tmp = board[i][j];
					board[i][j] = board[i][n-j-1];
					board[i][n-j-1] = tmp;
				}
			}
		}
	}

	static void applyGravity(char[][] board) {
		int n = board.length;
		if (n > 1) {
			char[] tmp;
			int l;

			//for each row
			for (int j = 0; j < n; j++) {
				//filter out the non empty chars and create a temporary row that
				//contains the initial row after application of gravity
				tmp = createDottedCharArray(n);
				l = n-1;
				for (int i = n-1; i >= 0; i--) {
					if (board[i][j] != '.') {
						tmp[l] = board[i][j];
						l--;
					}
				}
				//update the board
				for (int i = 0; i < n; i++)
					board[i][j] = tmp[i];
			}
		}
	}

	static String andTheWinnerIs(char[][] board, int k) {
		boolean redWins = hasKInARow(board,k,'R');
		boolean blueWins = hasKInARow(board,k,'B');

		if (redWins && blueWins)
			return "Both";
		else if (redWins)
			return "Red";
		else if (blueWins)
			return "Blue";
		else return "Neither";
	}

	static boolean hasKInARow(char[][] board, int k, char target) {
		int n = board.length;
		int sequenceLength;
		boolean sequenceContinues;

		if (k > n)
			throw new RuntimeException("K is bigger than N");
		if (k == 0)
			return true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == target) {
					//if horizontal-right k-row exists
					if (j < n-k+1) {
						sequenceLength = 1;
						sequenceContinues = true;
						while (sequenceContinues == true) {
							if (sequenceLength == k)
								return true;
							if (board[i][j+sequenceLength] == target) {
								sequenceLength++;
							} else sequenceContinues = false;
						}
					}
					//if vertical-down k-row exists
					if (i < n-k+1) {
						sequenceLength = 1;
						sequenceContinues = true;
						while (sequenceContinues == true) {
							if (sequenceLength == k)
								return true;
							if (board[i+sequenceLength][j] == target) {
								sequenceLength++;
							} else sequenceContinues = false;
						}
					}
					//if diagonal up-right k-row exists
					if ((j < n-k+1) && (i > k-2)) {
						sequenceLength = 1;
						sequenceContinues = true;
						while (sequenceContinues == true) {
							if (sequenceLength == k)
								return true;
							if (board[i-sequenceLength][j+sequenceLength] == target) {
								sequenceLength++;
							} else sequenceContinues = false;
						}
					}
					//if diagonal down-right k-row exists
					if ((j < n-k+1) && (i < n-k+1)) {
						sequenceLength = 1;
						sequenceContinues = true;
						while (sequenceContinues == true) {
							if (sequenceLength == k)
								return true;
							if (board[i+sequenceLength][j+sequenceLength] == target) {
								sequenceLength++;
							} else sequenceContinues = false;
						}
					}
				}
			}
		}

		return false;
	}

	static void printBoard(char[][] board) {
		System.out.println();
		for (int i = 0; i < board.length; i++)
			System.out.println(board[i]);
	}

	static char[] createDottedCharArray(int n) {
		char[] array = new char[n];
		for (int i = 0; i < n; i++)
			array[i] = '.';
		return array;
	}
}
