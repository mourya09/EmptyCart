package com.cer.vo;

import java.io.Serializable;

public class ReverseGeocodeAddress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4502179070658911907L;
	
	private String country;
	private String city;
	private String area;
	private String PLZ;
	private String street;
	private String house_no;
	private String POI;
	private String type;
	private String phone;
	private String poi_dist;
	private String lng;
	private String lat;
	private String formatted_address;
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
	public String getPLZ() {
		return PLZ;
	}
	public String getStreet() {
		return street;
	}
	public String getHouse_no() {
		return house_no;
	}
	public String getPOI() {
		return POI;
	}
	public String getType() {
		return type;
	}
	public String getPhone() {
		return phone;
	}
	public String getPoi_dist() {
		return poi_dist;
	}
	public String getLng() {
		return lng;
	}
	public String getLat() {
		return lat;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setPLZ(String pLZ) {
		PLZ = pLZ;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public void setPOI(String pOI) {
		POI = pOI;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPoi_dist(String poi_dist) {
		this.poi_dist = poi_dist;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
}
