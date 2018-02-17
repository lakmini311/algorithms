***********************************************************************************
							Conway's Game of Life
***********************************************************************************

Prerequisites
=============

1) Git needs to be installed to clone the source code from GitHub. (E.g.: Version 2.16.1)
 
2) Java Version jdk_1.8.0_144 or higher needs to be installed. (The program was tested with only jdk_1.8.0_144)

3) To build the source code, Maven version 3.5.0 or higher needs to be installed. (The source code was tested only with 3.5.0)

4) Need to have a terminal to execute the commands. (E.g.: Windows Command Prompt, Ubuntu Terminal)


Obtain the source code
========================

1) The source code is available under the following GitHub repository;
		https://github.com/lakmini311/algorithms
		
2) Open the terminal of choice and navigate to the file path the source code needs to be checked out on the local machine.

3) Type the following command to clone the source code;
		> git clone https://github.com/lakmini311/algorithms.git


Build the source code
=====================

1) Navigate to the root folder of the project "algorithms/conways-game-of-life".

2) Type the following command to build the source code;
		> mvn install
		
3) Make sure there are no build issues.

Run the program
===============

1) Navigate to "algorithms/conways-game-of-life/target" on the terminal.

2) Execute following command to execute the program;
		> java -jar conways-game-of-life-1.0.0-jar-with-dependencies.jar
		
3) Enter input (Initial "ALIVE" cell coordinates);
		Input Syntax:	[[<x_coordinate1>, <y_coordinate1>], [<x_coordinate2>, <y_coordinate2>], ...]
		E.g.: [[5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6]]
		
4) Observe the output displaying the first 100 or less states of the grid.