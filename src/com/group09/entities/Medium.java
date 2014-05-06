package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Medium {
	private int medium_id;
	private String format;

	/**
	 * 
	 * @param medium_id
	 * @param format
	 */
	public Medium(int medium_id, String format) {
		this.medium_id = medium_id;
		this.format = format;
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
	 * @return format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
}
