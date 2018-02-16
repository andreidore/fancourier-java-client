package com.github.andreidore.fancourierclient;

public class City {

	private String name;

	private String agency;

	private int distance;

	public City(String name, String agency, int distance) {
		this.name = name;
		this.agency = agency;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public String getAgency() {
		return agency;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", agency=" + agency + ", distance=" + distance + "]";
	}

}
