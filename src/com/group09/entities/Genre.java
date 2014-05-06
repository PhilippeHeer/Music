package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Genre {
	private int Genre_id;
	private String Name;
	private int Count;

	/**
	 * 
	 * @param Genre_id
	 * @param name
	 * @param count
	 */
	public Genre(int iD, String name, int count) {
		this.Genre_id = iD;
		this.Name = name;
		this.Count = count;
	}

	/**
	 * 
	 * @return
	 */
	public int getID() {
		return Genre_id;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		this.Genre_id = iD;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getCount() {
		return Count;
	}

	/**
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.Count = count;
	}
}
