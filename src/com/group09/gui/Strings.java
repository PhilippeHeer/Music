package com.group09.gui;

/**
 * 
 * @author Group 09
 * 
 */
public abstract class Strings {

	/**
	 * 
	 */
	public static final String ADD_ROW = "Add row to table";

	/**
	 * 
	 */
	public static final String ADD_ROW_TOOLTIP = "Add row to table";

	/**
	 * 
	 */
	public static final String DELETE_ROW = "Delete row to table";
	
	/**
	 * 
	 */
	public static final String DELETE_ROW_TOOLTIP = "Delete row to table";
	/**
	 * 
	 */
	public static final String SEARCH_TABLE = "Search table";

	/**
	 * 
	 */
	public static final String SEARCH_TABLE_TOOL_TIP = "Serach table";

	/**
	 * 
	 */
	public static final String NAMES[] = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" };

	/**
	 * 
	 */
	public static String NAMES_TOOL_TIPS[] = {
			"Print the names of artists from Switzerland.",
			"Print the names of areas with the highest number male artists, female artists and groups, as well as the number of artists of each type in each of the three areas.",
			"List the names of 10 groups with the most recorded tracks.",
			"List the names of 10 groups with the most releases.",
			"Print the name of a female artist associated with the most genres.",
			"List all cities which have more female than male artists.",
			"List the releases with the highest number of tracks.",
			"For each area that has more than 30 artists, list the male artist, the female artist and the group with the most tracks recorded.",
			"List the name of the release with the most tracks.",
			"American metal group Metallica is asking its fans to choose the setlist for its upcoming concert in Switzerland. Assuming that the Metallica fans will choose the songs that have appeared on the highest number of mediums, list the top 25 songs.",
			"For each of the 10 genres with the most artists, list the most popular female artist.",
			"List all genres that have no female artists, all genres that have no male artists and all genres that have no groups.",
			"For each area with more than 10 groups, list the 5 male artists that have recorded the highest number of tracks.",
			"List the 10 groups with the highest number of tracks that appear on compilations.",
			"List the top 10 releases with the most collaborations, i.e., releases where one artist is performing all songs and the highest number of different guest artists contribute to the album.",
			"List the release which is associated with the most mediums. If there are more than one such release, list all such releases.",
			"List the most popular genre among the groups which are associated with at least 3 genres.",
			"List the 5 most popular song titles along with the number of songs that share such title.",
			"List the top 10 artists according to their track to release ratio.",
			"The concert hit index is a measure of probability that the artist can attract enough fans to fill a football stadium. We define the “hit artist” as one that has more than 10 songs that appear on more than 100 mediums and measure \"hit ability\" as the average number of mediums that a top 10 song appears on. List all “hit artists” according to their \"hit ability\"."		
	};
	
	/**
	 * 
	 */
	public static final String TABLE_NAMES[] = {
		"Type",
		"Gender",
		"Genre",
		"Area",
		"Artist",
		"is_genre",
		"Recording",
		"has_recorded",
		"Medium",
		"Release",
		"is_track_on",
		"is_released",
	};
}
