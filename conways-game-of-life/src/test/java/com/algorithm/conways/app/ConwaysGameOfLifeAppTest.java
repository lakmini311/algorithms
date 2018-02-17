package com.algorithm.conways.app;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConwaysGameOfLifeAppTest {
	
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

	/**
	 * Test single cell alive scenario.
	 */
	@Test
	public void testSingleAliveCell() {
		String data = "[[1, 1]]";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		ConwaysGameOfLifeApp.main(null);
		assertEquals("1: []".replaceAll("\\s", ""), output.toString().replaceAll("\\n|\\s", ""));

	}
	
	/**
	 * Test six cells alive scenario.
	 */
	@Test
	public void testSixAliveCells() {
		try {
			String data = "[[5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6]]";
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			
			ConwaysGameOfLifeApp.main(null);
			String expectedOutput = readFile("src/test/resources/SixAliveCellsInput.txt");
			
			assertEquals(expectedOutput.replaceAll("\\n|\\s", ""), output.toString().replaceAll("\\s", ""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read expected output from file specified.
	 * @param path	String 	The file path of the expected output.
	 * @return		String	The content of the file as a String.
	 * @throws IOException	Thrown when an issues occurred during file read.
	 */
	private String readFile(String path) throws IOException {
		byte[] pathArr = Files.readAllBytes(Paths.get(path));
		return new String(pathArr);
	}

	/**
	 * Execute before each test case execution.
	 */
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(output));
	}

	/**
	 * Execute after each test case execution.
	 */
	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	}

}
