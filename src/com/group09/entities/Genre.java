package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Genre {
	private int genre_id;
	private String name;
	private int count;

	/**
	 * 
	 * @param genre_id
	 * @param name
	 * @param count
	 */
	public Genre(int iD, String name, int count) {
		this.genre_id = iD;
		this.name = name;
		this.count = count;
	}

	/**
	 * 
	 * @return
	 */
	public int getID() {
		return genre_id;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		this.genre_id = iD;
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
}
