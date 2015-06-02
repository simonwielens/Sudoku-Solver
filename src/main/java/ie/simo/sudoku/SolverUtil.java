package ie.simo.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SolverUtil {
	public static Integer[] getColumn(Integer [][] table, int index) {
		Integer [] col = new Integer[9];
		for(int i = 0;  i < 9; i++) {
			col[i] = table[i][index];
		}
		
		return col;
	}
	
	public static Integer [][] getSquare(Integer [][] table, int row, int col) {
		Integer [][] square = new Integer[3][3];
		for(int i = 0;  i < 3; i++) {
			for(int j = 0;  j < 3; j++) {
				square[i][j] = table[(row * 3) + i][(col * 3) + j];
			}
		}
		
		return square;
	}
	
	public static boolean checkValid(Integer [] row) {
		Set<Integer> found = new HashSet<Integer>();
		int count = 0;
		for(int i = 0; i < 9; i++){
			if(row[i] > 0){
				count++;
				found.add(row[i]);
			}
		}
		return found.size() == count;
	}
	
	public static void printArray(Integer [][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("\n");
		}
		
		System.out.println("========================");
	}
}
