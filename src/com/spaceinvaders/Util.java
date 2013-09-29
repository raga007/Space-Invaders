package com.spaceinvaders;

/**
 * Utility Class
 * 
 *
 */
public class Util {
	
	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message The message to display on failure
	 */
	public static void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}
}
