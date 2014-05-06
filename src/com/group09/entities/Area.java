package com.group09.entities;

/**
 * 
 * @author Group 09
 * 
 */
public class Area {
	private int area_id;
	private String name;
	private String type_of_area;

	/**
	 * 
	 * @param area_id
	 * @param name
	 * @param type_of_area
	 */
	public Area(int area_id, String name, String type_of_area) {
		this.area_id = area_id;
		this.name = name;
		this.type_of_area = type_of_area;
	}

	/**
	 * 
	 * @return area_id
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
	 * @return type_of_area
	 */
	public String getType_of_area() {
		return type_of_area;
	}

	/**
	 * 
	 * @param type_of_area
	 */
	public void setType_of_area(String type_of_area) {
		this.type_of_area = type_of_area;
	}
}
