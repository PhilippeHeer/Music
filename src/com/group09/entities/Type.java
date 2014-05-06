package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Type {
	private int type_id;
	private String name;

	/**
	 * 
	 * @param type_id
	 * @param name
	 */
	public Type(int type_id, String name) {
		this.type_id = type_id;
		this.name = name;
	}

	/**
	 * 
	 * @return type_id
	 */
	public int getType_id() {
		return type_id;
	}

	/**
	 * 
	 * @param type_id
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
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
