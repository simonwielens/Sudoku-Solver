package ie.simo.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverUtilTest {
	
	private Integer[][] completedTable = 	{{ 5,3,4, 6,7,8, 9,1,2},
									         { 6,7,2, 1,9,5, 3,4,8},
									         { 1,9,8, 3,4,2, 5,6,7},
									         
									         { 8,5,9, 7,6,1, 4,2,3}, 
									         { 4,2,6, 8,5,3, 7,9,1},
									         { 7,1,3, 9,2,4, 8,5,6}, 
									         
									         { 9,6,1, 5,3,7, 2,8,4},
									         { 2,8,7, 4,1,9, 6,3,5}, 
									         { 3,4,5, 2,8,6, 1,7,9}};


	@Test
	public void testGetSquare00() {
		Integer[][] expected = {{ 5,3,4 },
							{ 6,7,2 },
							{ 1,9,8 }};
		Integer [][] result = SolverUtil.getSquare(completedTable, 0, 0);
		
		SolverUtil.printArray(result);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetSquare10() {
		Integer[][] expected = {{ 8,5,9 },
							{ 4,2,6 },
							{ 7,1,3 }};

		Integer [][] result = SolverUtil.getSquare(completedTable, 1, 0);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}

	
	@Test
	public void testGetSquare20() {
		Integer[][] expected = {{ 9,6,1 },
					        { 2,8,7 }, 
					        { 3,4,5 }};

		Integer [][] result = SolverUtil.getSquare(completedTable, 2, 0);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}

	
	@Test
	public void testGetSquare01() {
		Integer[][] expected = 	{{ 6,7,8 },
					         { 1,9,5 },
					         { 3,4,2 }};

		Integer [][] result = SolverUtil.getSquare(completedTable, 0, 1);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}

	@Test
	public void testGetSquare02() {
		Integer[][] expected = 	{{ 9,1,2 },
						         { 3,4,8 },
						         { 5,6,7 }};

		Integer [][] result = SolverUtil.getSquare(completedTable, 0, 2);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}

	@Test
	public void testGetSquare11() {
		Integer[][] expected = {{ 7,6,1 }, 
			         			{ 8,5,3 },
			         			{ 9,2,4 }}; 
		
		Integer [][] result = SolverUtil.getSquare(completedTable, 1, 1);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}

	@Test
	public void testGetSquare22() {
		Integer[][] expected = {{ 2,8,4 },
							{ 6,3,5 },
							{ 1,7,9 }};

		Integer [][] result = SolverUtil.getSquare(completedTable, 2, 2);
		
		SolverUtil.printArray(result);
		
		assertEquals(expected, result);
	}


}
