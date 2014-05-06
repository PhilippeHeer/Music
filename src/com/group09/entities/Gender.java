package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Gender {
	private int Gender_id;
	private String Name;

	/**
	 * 
	 * @return
	 */
	public int getID() {
		return Gender_id;
	}

	/**
	 * 
	 * @param iD
	 * @param name
	 */
	public Gender(int iD, String name) {
		this.Gender_id = iD;
		this.Name = name;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		Gender_id = iD;
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
}
