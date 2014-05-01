package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Genre {
	private int ID;
	private int count;
	private String name;

	/**
	 * 
	 * @param ID
	 * @param count
	 * @param name
	 */
	public Genre(int iD, int count, String name) {
		this.ID = iD;
		this.count = count;
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		this.ID = iD;
	}

	/**
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 
	 * @return
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
