package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Artist {
	private int artist_id;
	private String name;
	private int type_id;
	private int gender_id;
	private int area_id;

	/**
	 * 
	 * @param artist_id
	 * @param name
	 * @param type_id
	 * @param gender_id
	 * @param area_id
	 */
	public Artist(int artist_id, String name, int type_id, int gender_id,
			int area_id) {
		this.artist_id = artist_id;
		this.name = name;
		this.type_id = type_id;
		this.gender_id = gender_id;
		this.area_id = area_id;
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
	 * @return
	 */
	public int getGender_id() {
		return gender_id;
	}

	/**
	 * 
	 * @param gender_id
	 */
	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}

	/**
	 * 
	 * @return
	 */
	public int getArea_id() {
		return area_id;
	}

	/**
	 * 
	 * @param area_id
	 */
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
}
