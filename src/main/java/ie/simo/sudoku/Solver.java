package ie.simo.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solver {
	
	private static Map<Integer, List<Integer>> adjacentRows = new HashMap<>();
	
	private static final int MAX_ITERATIONS = 1500;
	
	static{
		adjacentRows.put(0, Arrays.asList(new Integer[]{1,2}));
		adjacentRows.put(1, Arrays.asList(new Integer[]{0,2}));
		adjacentRows.put(2, Arrays.asList(new Integer[]{0,1}));
		adjacentRows.put(3, Arrays.asList(new Integer[]{4,5}));
		adjacentRows.put(4, Arrays.asList(new Integer[]{3,5}));
		adjacentRows.put(5, Arrays.asList(new Integer[]{3,4}));
		adjacentRows.put(6, Arrays.asList(new Integer[]{7,8}));
		adjacentRows.put(7, Arrays.asList(new Integer[]{6,8}));
		adjacentRows.put(8, Arrays.asList(new Integer[]{6,7}));
	}
	
	public static Integer [][] solve(Integer [][] table) {
		int count = 0;
		while(!isComplete(table) && count < MAX_ITERATIONS){
			
			count++;
			for(int row = 0; row < 9; row++){
				for(int col = 0; col < 9; col++){
					
					if(table[row][col] == 0) {
						
						List<Integer> potentialMatches = new LinkedList<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
						
						for(Integer i : SolverUtil.getColumn(table, col)){
							if(i > 0) { 
								potentialMatches.remove(i);
							}
						}
						
						for(Integer i : table[row]){
							if(i > 0) { 
								potentialMatches.remove(i);
							}
						}
						
						Integer [][] thisSquare = SolverUtil.getSquare(table, getContainingSquare(row), getContainingSquare(col));
						for(int a = 0; a < 3;  a++){
							for(int b = 0; b < 3;  b++){
								if(thisSquare[a][b] > 0) {
									potentialMatches.remove((Integer)thisSquare[a][b]);
								}
							}
						}
						
						if(potentialMatches.size() == 1){
							table[row][col] = potentialMatches.get(0);
							SolverUtil.printArray(table);
						} else if (potentialMatches.size() > 1){
							table = checkSquaresRowsAndCols(table, row, col, potentialMatches);
						}
					}
				}
			}
		}
		
		if(!isValid(table)) {
			System.err.println("INVALID TABLE, SHITE");	
		}
		SolverUtil.printArray(table);
		return table;
	}

	private static Integer[][] checkSquaresRowsAndCols(Integer[][] table, int row,
			int col, List<Integer> potentialMatches) {
		
		//SolverUtil.printArray(table);
		
		List<Integer[]> rows = new ArrayList<>();
		List<Integer[]> columns = new ArrayList<>();
		
		for(Integer column : adjacentRows.get(col)){
			columns.add(SolverUtil.getColumn(table, column));
		}
		
		for(Integer adjRow : adjacentRows.get(row)){
			rows.add(table[adjRow]);
		}
		
		for(Integer match : potentialMatches) {
			boolean foundInAllRows = true;
			boolean foundInAllCols = true;
			
			for(Integer[] rowToTest: rows) {
				if(!containsMatch(rowToTest, match)) {
					foundInAllRows = false;
				}
			}
			
			for(Integer[] rowToTest: columns) {
				if(!containsMatch(rowToTest, match)) {
					foundInAllCols = false;
				}
			}
			if(foundInAllRows && foundInAllCols){
				table[row][col] = match;
				SolverUtil.printArray(table);
				break;
			} else if(foundInAllCols) {
				
				//which	square is it testing and what are the options			
//				StringBuilder matches = new StringBuilder();
//				for(int option : potentialMatches)
//				{
//					matches.append(option + " ");
//				}
//				
//				System.out.println("["+ row + "][" + col + "] : " + matches.toString());
				
				// Arrays.asList returns a fixed size array, so does not support
				// removing elems - hence this cast to a linked list
				List<Integer> relevantRows = new LinkedList<Integer>(adjacentRows.get(row));
				
				Iterator<Integer> iter = relevantRows.listIterator();
				
				while(iter.hasNext()) {
					int rowToCheck = iter.next();
					if(table[rowToCheck][col] != 0) {
						iter.remove();
					} else if(Arrays.asList(table[rowToCheck]).contains(match)) {
						iter.remove();
					}
				}
				
				if(relevantRows.size() == 0) {
					table[row][col] = match;
				}
		
			} else if(foundInAllRows) {
				// Arrays.asList returns a fixed size array, so does not support
				// removing elems - hence this cast to a linked list
				List<Integer> relevantCols = new LinkedList<Integer>(adjacentRows.get(col));

				Iterator<Integer> iter = relevantCols.iterator();
				
				while(iter.hasNext()) {
					int colToCheck = iter.next();
					if(table[row][colToCheck] != 0) {
						iter.remove();
					} else if(Arrays.asList(SolverUtil.getColumn(table, colToCheck)).contains(match)) {
						iter.remove();
					}
				}
				
				if(relevantCols.size() == 0) {
					table[row][col] = match;
				}
			}
			
		}
		
		return table;
	}

	private static boolean containsMatch(Integer[] rowToTest, Integer match) {
		return Arrays.asList(rowToTest).contains(match);
	}

	private static int getContainingSquare(int index) {
		if(index > 5) {
			return 2;
		} else if(index > 2) {
			return 1;
		} else {
			return 0;
		}
	}

	private static boolean isComplete(Integer[][] table) {
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				if(table[row][col] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static boolean isValid(Integer[][] table) {
		return checkRows(table) && checkColumns(table) && checkSquares(table);
	}

	private static boolean checkRows(Integer[][] table) {
		boolean result = true;
		for (int i = 0; i < 9; i++) {
			result &= SolverUtil.checkValid(table[i]);
		}

		return result;
	}

	private static boolean checkColumns(Integer[][] table) {
		boolean result = true;
		for (int i = 0; i < 9; i++) {
			result &= SolverUtil.checkValid(SolverUtil.getColumn(table, i));
		}

		return result;

	}

	private static boolean checkSquares(Integer[][] table) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				validateSquare(SolverUtil.getSquare(table, row, col));
			}
		}
		return true;
	}

	private static boolean validateSquare(Integer[][] square) {
		Set<Integer> found = new HashSet<Integer>();
		int count = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (square[row][col] > 0) {
					count++;
					found.add(square[row][col]);
				}
			}
		}
		return found.size() == count;
	}
}
