package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Has_recorded {
	private int artist_id;
	private int recording_id;

	/**
	 * 
	 * @param artist_id
	 * @param recording_id
	 */
	public Has_recorded(int artist_id, int recording_id) {
		this.artist_id = artist_id;
		this.recording_id = recording_id;
	}

	/**
	 * 
	 * @return
	 */
	public int getArtist_id() {
		return artist_id;
	}

	/**
	 * 
	 * @param artist_id
	 */
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}

	/**
	 * 
	 * @return
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
}
