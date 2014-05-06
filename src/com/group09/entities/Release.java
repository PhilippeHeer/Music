package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Release {
	private int release_id;
	private String name;

	/**
	 * 
	 * @param release_id
	 * @param name
	 */
	public Release(int release_id, String name) {
		this.release_id = release_id;
		this.name = name;
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

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
