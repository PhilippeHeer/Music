package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Is_Genre {
	private int artist_id;
	private int genre_id;

	/**
	 * 
	 * @param artist_id
	 * @param genre_id
	 */
	public Is_Genre(int artist_id, int genre_id) {
		super();
		this.artist_id = artist_id;
		this.genre_id = genre_id;
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
	public int getGenre_id() {
		return genre_id;
	}

	/**
	 * 
	 * @param genre_id
	 */
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
}
