package com.algorithm.conways.model;

public class Cell {

	/**
	 * X coordinate of the cell instance.
	 */
	private int coordinateX;
	
	/**
	 * Y coordinate of the cell instance.
	 */
	private int coordinateY;
	
	/**
	 * Current state of the cell instance.
	 */
	private String state;
	
	private int aliveNeighbourCount = 0;

	public Cell() {
	}
	
	public Cell(int x, int y, String state) {
		this.coordinateX = x;
		this.coordinateY = y;
		this.state = state;
	}
	
	public int getCoordinateX() {
		return coordinateX;
	}
	
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public int getCoordinateY() {
		return coordinateY;
	}
	
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getAliveNeighbourCount() {
		return aliveNeighbourCount;
	}

	public void setAliveNeighbourCount(int aliveNeighbourCount) {
		this.aliveNeighbourCount = aliveNeighbourCount;
	}
}
