package com.algorithm.conways.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.algorithm.conways.model.Cell;

/**
 * This application class contains the logic for execution of 
 * "Conway's Game of Life".
 * 
 * @author Anuradha Karunamuni
 */
public class ConwaysGameOfLifeApp {
	
	/**
	 * The constant "ALIVE".
	 */
	private static final String CONST_ALIVE = "ALIVE";
	/**
	 * The constant "DEAD"
	 */
	private static final String CONST_DEAD = "DEAD";
	
	/**
	 * The size of the matrix grid along X axis.
	 */
	private static final int matrixSizeXAxis = 200;
	/**
	 * The size of the matrix grid along Y axis.
	 */
	private static final int matrixSizeYAxis = 200;
	
	/**
	 * The matrix grid in the form of a 2D array.
	 */
	private static Cell[][] matrix;
	/**
	 * Counter used to print the output.
	 */
	private static int outputCount = 1;

	/**
	 * Main method handling the logic initiation and execution.
	 * 
	 * @param args	String[]
	 * @throws InterruptedException
	 */
	public static void main(String[] args) {
		
		// Read the input.
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String input = sc.nextLine();
			
			// Initialize the matrix grid;
			init();
			// Prepare the initial grid based on the input.
			// Print an error message if the input is invalid.
			if(!prepareInputMatrix(input)) {
				System.out.println("[ERROR] Invaid input.");
			}
			// Initiate "Conway's Game of Life" execution.
			runConwaysGameofLife();
			
		}
		sc.close();
	}
	
	/**
	 * Prepare the matrix grid based on the initial input "ALIVE" coordinates.
	 * 
	 * @param input	String	Initial "ALIVE" coordinates.
	 * @return	
	 */
	private static boolean prepareInputMatrix(String input) {

		boolean isValid = true;

		if (input != null && input.length() > 0) {

			String tempInput = input.substring(1, (input.length() - 1));
			tempInput = tempInput.replace(" ", "");
			String[] tempInputArr = tempInput.split("\\[|,\\[|\\]");
			
			// For each "ALIVE" coordinate in the input.
			for(String cellCoordinate : tempInputArr) {

				String[] coordinates = cellCoordinate.split(",");
				
				if(coordinates[0].length() > 0 && coordinates[1].length() > 0) {
					int xCoordinate = Integer.parseInt(coordinates[0]);
					int yCoordinate = Integer.parseInt(coordinates[1]);
					
					for(int x = 0; x < matrix.length; x++) {
						for(int y = 0; y < matrix[x].length; y++) {
							
							Cell cell = matrix[x][y];
							if((cell.getCoordinateX() == xCoordinate) && (cell.getCoordinateY() == yCoordinate)) {
								// Set status as "ALIVE".
								cell.setState(CONST_ALIVE);
							}
						}
					}
				}
			}
			// Set the "ALIVE" neighbor count for each cell in the initial grid. 
			setAliveNeighborsCount();
			
		} else {
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * Run "Conway's Game f Life" logic recursively.
	 */
	private static void runConwaysGameofLife() {
		
		List<String> outputArr = new ArrayList<String>();
		
		// Traverse along the X axis.
		for(int x = 0; x < matrix.length; x++) {
			// Traverse along the Y axis.
			for(int y = 0; y < matrix[x].length; y++) {
				
				Cell currentCell = matrix[x][y];
				String state = currentCell.getState();
				int aliveNeghboursCount = currentCell.getAliveNeighbourCount();
				
				// Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
				// Any live cell with two or three live neighbors lives on to the next generation.
				// Any live cell with more than three live neighbors dies, as if by overpopulation.
				if(state.equals(CONST_ALIVE) && (aliveNeghboursCount < 2 || aliveNeghboursCount > 3)) {
					currentCell.setState(CONST_DEAD);
				
				//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
				} else if(state.equals(CONST_DEAD) && aliveNeghboursCount == 3) {
					currentCell.setState(CONST_ALIVE);
					
				}
				// Collect the "ALIVE" cell coordinates to output.
				if(currentCell.getState().equals(CONST_ALIVE)) {
					outputArr.add("[" + currentCell.getCoordinateX() + ", " + currentCell.getCoordinateY() + "]");
				}
			}
		}
		// Set the new counts for "ALIVE" neighbors for each cell.
		setAliveNeighborsCount();
		// Print the "ALIVE" coordinates of current stage.
		System.out.println(outputCount + ": " + outputArr);
		outputCount++;
		
		// Execute until 100 outputs are printed.
		if(outputCount <= 100 && outputArr.size() > 2) {
			runConwaysGameofLife();
			
		} else {
			return;
		}
		
	}

	/**
	 * Set the "ALIVE" neighbor count for each cell on the grid 
	 * considering all 8 adjacent cell states.
	 *  
	 */
	private static void setAliveNeighborsCount() {

		// Traverse along the X axis.
		for (int x = 0; x < matrix.length; x++) {
			// Traverse along the Y axis.
			for (int y = 0; y < matrix[x].length; y++) {
				
				Cell currentCell = matrix[x][y];
				currentCell.setAliveNeighbourCount(0);
				
				// Check bottom left diagonal neoghbor.
				if(x > 0 && y > 0) {
					Cell bottomLeftDiagonalNeighbour = matrix[x - 1][y - 1];
					if (bottomLeftDiagonalNeighbour != null && bottomLeftDiagonalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check bottom vertical neighbor.
				if(y > 0) {
					Cell bottomVerticalNeighbour = matrix[x][y - 1];
					if (bottomVerticalNeighbour != null && bottomVerticalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check bottom right diagonal neighbor.
				if(x < (matrixSizeXAxis -1) && (y > 0)) {
					Cell bottomRightDiagonalNeighbour = matrix[x + 1][y - 1];
					if (bottomRightDiagonalNeighbour != null
							&& bottomRightDiagonalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check left horizontal neighbor.
				if(x > 0) {
					Cell leftHorizontalNeighbour = matrix[x - 1][y];
					if (leftHorizontalNeighbour != null && leftHorizontalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check right horizontal neighbor.
				if(x < (matrixSizeXAxis - 1)) {
					Cell rightHorizontalNeigbour = matrix[x + 1][y];
					if (rightHorizontalNeigbour != null && rightHorizontalNeigbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check top left left diagonal neighbor.
				if((x > 0) && y < (matrixSizeYAxis - 1)) {
					Cell topLeftDiagonalNeigbour = matrix[x - 1][y + 1];
					if (topLeftDiagonalNeigbour != null && topLeftDiagonalNeigbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check top vertical neighbor.
				if(y < (matrixSizeYAxis - 1)) {
					Cell topVerticalNeighbour = matrix[x][y + 1];
					if (topVerticalNeighbour != null && topVerticalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
				// Check right diagonal neighbor.
				if((x < (matrixSizeXAxis - 1)) && (y < (matrixSizeYAxis - 1))) {
					Cell topRightDiagonalNeighbour = matrix[x + 1][y + 1];
					if (topRightDiagonalNeighbour != null && topRightDiagonalNeighbour.getState().equals(CONST_ALIVE)) {
						currentCell.setAliveNeighbourCount(currentCell.getAliveNeighbourCount() + 1);
					}
				}
			}
		}
	}
	
	
	/**
	 * Prepare initial matrix with all cells in "DEAD" state.
	 */
	private static void init() {

		// Create the matrix grid.
		matrix = new Cell[matrixSizeXAxis][matrixSizeYAxis];
		
		// Set each cell state to "DEAD" for the initial grid.
		for(int coordinateX = 0; coordinateX < matrix.length; coordinateX++) {
			for(int coordinateY = 0; coordinateY < matrix[coordinateX].length; coordinateY++) {
				matrix[coordinateX][coordinateY] = new Cell(coordinateX, coordinateY, CONST_DEAD);
			}
		}
	}

}
