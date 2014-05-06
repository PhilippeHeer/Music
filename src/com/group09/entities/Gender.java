package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Gender {
	private int gender_id;
	private String name;

	/**
	 * 
	 * @return gender_id
	 */
	public int getID() {
		return gender_id;
	}

	/**
	 * 
	 * @param iD
	 * @param name
	 */
	public Gender(int iD, String name) {
		this.gender_id = iD;
		this.name = name;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		gender_id = iD;
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
