package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Recording {
	private int recording_id;
	private String name;
	private int length;

	/**
	 * 
	 * @param recording_id
	 * @param name
	 * @param length
	 */
	public Recording(int recording_id, String name, int length) {
		this.recording_id = recording_id;
		this.name = name;
		this.length = length;
	}

	/**
	 * 
	 * @return recording_id
	 */
	public int getRecording_id() {
		return recording_id;
	}

	/**
	 * 
	 * @param recording_id
	 */
	public void setRecording_id(int recording_id) {
		this.recording_id = recording_id;
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

	/**
	 * 
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
}
