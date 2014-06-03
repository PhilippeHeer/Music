package com.group09.time;

/**
 * 
 * @author Philippe Heer
 *
 */
abstract public class Timing {
	private static long counter = 0;

	/**
	 * 
	 */
	public static void startCounter() {
		counter = System.currentTimeMillis();
	}

	/**
	 * 
	 */
	public static void stopCounter() {
		System.out.println(System.currentTimeMillis() - counter);
	}
}
