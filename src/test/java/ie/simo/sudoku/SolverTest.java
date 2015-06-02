package ie.simo.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {
	
	
	private Integer[][] easyTable ={{0,7,2, 0,3,6, 4,0,0},
		        					{0,9,0, 7,0,0, 0,3,5},
		        					{0,0,0, 1,8,0, 0,0,2},
							        
		        					{2,0,6, 0,0,0, 0,9,0},
							        {3,0,5, 0,9,0, 6,0,8},
							        {0,4,0, 0,0,0, 5,0,1},
							        
							        {7,0,0, 0,2,3, 0,0,0},
							        {1,5,0, 0,0,4, 0,8,0},
							        {0,0,8, 6,1,0, 9,7,0}};

//	        new int[]{8, 7, 2, 5, 3, 6, 4, 1, 9},
//	        new int[]{6, 9, 1, 7, 4, 2, 8, 3, 5},
//	        new int[]{5, 3, 4, 1, 8, 9, 7, 6, 2},
//	        new int[]{2, 8, 6, 4, 5, 1, 3, 9, 7},
//	        new int[]{3, 1, 5, 2, 9, 7, 6, 4, 8},
//	        new int[]{9, 4, 7, 3, 6, 8, 5, 2, 1},
//	        new int[]{7, 6, 9, 8, 2, 3, 1, 5, 4},
//	        new int[]{1, 5, 3, 9, 7, 4, 2, 8, 6},
//	        new int[]{4, 2, 8, 6, 1, 5, 9, 7, 3}),};
	
	private Integer [][] mediumTable = {{0,0,0, 9,0,7, 0,0,0},
								        {9,0,0, 0,0,0, 0,0,8},
								        {0,3,0, 4,0,5, 0,2,0},
								        
								        {3,0,7, 0,4,0, 2,0,6},
								        {0,0,0, 5,0,9, 0,0,0},
								        {8,0,9, 0,2,0, 1,0,3},
								        
								        {0,7,0, 6,0,4, 0,3,0},
								        {2,0,0, 0,0,0, 0,0,9},
								        {0,0,0, 1,0,2, 0,0,0}};

//	        new int[]{4, 8, 2, 9, 1, 7, 3, 6, 5},
//	        new int[]{9, 1, 5, 2, 6, 3, 4, 7, 8},
//	        new int[]{7, 3, 6, 4, 8, 5, 9, 2, 1},
//	        new int[]{3, 5, 7, 8, 4, 1, 2, 9, 6},
//	        new int[]{6, 2, 1, 5, 3, 9, 8, 4, 7},
//	        new int[]{8, 4, 9, 7, 2, 6, 1, 5, 3},
//	        new int[]{1, 7, 8, 6, 9, 4, 5, 3, 2},
//	        new int[]{2, 6, 4, 3, 5, 8, 7, 1, 9},
//	        new int[]{5, 9, 3, 1, 7, 2, 6, 8, 4});
	
	
	// From http://zonkedyak.blogspot.fr/2006/11/worlds-hardest-sudoku-puzzle-al.html
	private Integer [][] reallyHardTable = {{1,0,0, 0,0,7, 0,9,0},
								            {0,3,0, 0,2,0, 0,0,8},
								            {0,0,9, 6,0,0, 5,0,0},
								            
								            {0,0,5, 3,0,0, 9,0,0},
								            {0,1,0, 0,8,0, 0,0,2},
								            {6,0,0, 0,0,4, 0,0,0},
								            
								            {3,0,0, 0,0,0, 0,1,0},
								            {0,4,0, 0,0,0, 0,0,7},
								            {0,0,7, 0,0,0, 3,0,0}};

//		            new int[]{1, 6, 2, 8, 5, 7, 4, 9, 3},
//		            new int[]{5, 3, 4, 1, 2, 9, 6, 7, 8},
//		            new int[]{7, 8, 9, 6, 4, 3, 5, 2, 1},
//		            new int[]{4, 7, 5, 3, 1, 2, 9, 8, 6},
//		            new int[]{9, 1, 3, 5, 8, 6, 7, 4, 2},
//		            new int[]{6, 2, 8, 7, 9, 4, 1, 3, 5},
//		            new int[]{3, 5, 6, 4, 7, 8, 2, 1, 9},
//		            new int[]{2, 4, 1, 9, 3, 5, 8, 6, 7},
//		            new int[]{8, 9, 7, 2, 6, 1, 3, 5, 4}),
	
	@Test
	public void testValidTable() {
		Integer[][] completedTable = 	{{ 5,3,4, 6,7,8, 9,1,2},
							         { 6,7,2, 1,9,5, 3,4,8},
							         { 1,9,8, 3,4,2, 5,6,7},
							         
							         { 8,5,9, 7,6,1, 4,2,3}, 
							         { 4,2,6, 8,5,3, 7,9,1},
							         { 7,1,3, 9,2,4, 8,5,6}, 
							         
							         { 9,6,1, 5,3,7, 2,8,4},
							         { 2,8,7, 4,1,9, 6,3,5}, 
							         { 3,4,5, 2,8,6, 1,7,9}};
		
		assertTrue(Solver.isValid(completedTable));
		
		
	}
	
	@Test
	public void testValidIncompleteTable() {
		Integer[][] incompleteTable ={{ 5,3,4, 6,7,8, 9,1,2},
							         { 6,7,2, 1,0,5, 3,4,8},
							         { 1,9,8, 3,4,2, 5,6,7},
							         
							         { 8,5,9, 7,6,1, 4,2,3}, 
							         { 4,2,6, 8,5,3, 7,9,1},
							         { 7,1,3, 9,2,4, 8,5,6}, 
							         
							         { 9,6,1, 5,3,7, 2,8,4},
							         { 2,8,7, 4,1,9, 6,3,5}, 
							         { 3,4,5, 2,8,6, 1,7,9}};
		
		assertTrue(Solver.isValid(incompleteTable));
		
		
	}
	
	@Test
	public void testInvalidTableRowsCols() {
		Integer[][] completedTable = {{ 5,3,4, 6,7,8, 9,1,9},
							         { 6,7,2, 1,9,5, 3,4,8},
							         { 1,9,8, 3,4,2, 5,6,7},
							         
							         { 8,5,9, 7,6,1, 4,2,3}, 
							         { 4,2,6, 8,5,3, 7,9,1},
							         { 7,1,3, 9,2,4, 8,5,6}, 
							         
							         { 9,6,1, 5,3,7, 2,8,4},
							         { 2,8,7, 4,1,9, 6,3,5}, 
							         { 3,4,5, 2,8,6, 1,7,9}};

		assertFalse(Solver.isValid(completedTable));
	}
	
	
	@Test
	public void testInvalidTableBox() {
		Integer[][] completedTable = 	{{ 6,7,2, 1,9,5, 3,4,8},
							         { 6,7,2, 1,9,5, 3,4,8},
							         { 1,9,8, 3,4,2, 5,6,7},
							         
							         { 8,5,9, 7,6,1, 4,2,3}, 
							         { 4,2,6, 8,5,3, 7,9,1},
							         { 7,1,3, 9,2,4, 8,5,6}, 
							         
							         { 9,6,1, 5,3,7, 2,8,4},
							         { 2,8,7, 4,1,9, 6,3,5}, 
							         { 3,4,5, 2,8,6, 1,7,9}};
					
		assertFalse(Solver.isValid(completedTable));
	}
	
	@Test
	public void solveOneMissingNumber() {
		
		Integer[][] table = {{ 0,3,4, 6,7,8, 9,1,2},
					         { 6,7,2, 1,9,5, 3,4,8},
					         { 1,9,8, 3,4,2, 5,6,7},
					         
					         { 8,5,9, 7,6,1, 4,2,3}, 
					         { 4,2,6, 8,5,3, 7,9,1},
					         { 7,1,3, 9,2,4, 8,5,6}, 
					         
					         { 9,6,1, 5,3,7, 2,8,4},
					         { 2,8,7, 4,1,9, 6,3,5}, 
					         { 3,4,5, 2,8,6, 1,7,9}};
		
		Integer[][] solved = Solver.solve(table);
		
		assertEquals(5, (int)solved[0][0]);
		
	}
	
	@Test
	public void solveEasyTable() {
		Integer [][] solved = Solver.solve(easyTable);
		SolverUtil.printArray(solved);
		assertTrue(Solver.isValid(solved));
	}
	
	@Test
	public void solveMediumTable() {
		Integer [][] solved = Solver.solve(mediumTable);
		SolverUtil.printArray(solved);
		assertTrue(Solver.isValid(solved));
	}
	
	@Test
	public void solveReallyHardTable() {
		Integer [][] solved = Solver.solve(reallyHardTable);
		SolverUtil.printArray(solved);
		assertTrue(Solver.isValid(solved));
	}
	
	
}
