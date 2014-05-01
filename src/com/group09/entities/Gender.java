package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public abstract class Gender {
	private int ID;
	private String name;

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
	 * @param name
	 */
	public Gender(int iD, String name) {
		this.ID = iD;
		this.name = name;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
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
