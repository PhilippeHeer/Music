package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Is_track_on {
	private int recording_id;
	private int medium_id;
	private int position;

	/**
	 * 
	 * @param recording_id
	 * @param medium_id
	 * @param position
	 */
	public Is_track_on(int recording_id, int medium_id, int position) {
		this.recording_id = recording_id;
		this.medium_id = medium_id;
		this.position = position;
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
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
