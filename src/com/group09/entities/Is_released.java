package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Is_released {
	private int medium_id;
	private int release_id;

	/**
	 * 
	 * @param medium_id
	 * @param release_id
	 */
	public Is_released(int medium_id, int release_id) {
		this.medium_id = medium_id;
		this.release_id = release_id;
	}

	/**
	 * 
	 * @return medium_id
	 */
	public int getMedium_id() {
		return medium_id;
	}

	/**
	 * 
	 * @param medium_id
	 */
	public void setMedium_id(int medium_id) {
		this.medium_id = medium_id;
	}

	/**
	 * 
	 * @return release_id
	 */
	public int getRelease_id() {
		return release_id;
	}

	/**
	 * 
	 * @param release_id
	 */
	public void setRelease_id(int release_id) {
		this.release_id = release_id;
	}
}
