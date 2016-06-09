package com.cer.persistent;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;


@Entity
@Table(name= "\"WareHouse\"")
/*@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllWarehouse5",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 5* 1609.34",
                        resultClass=WareHouse.class
    ),@NamedNativeQuery(
            name    =   "getAllWarehouse10",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 10* 1609.34",
                        resultClass=WareHouse.class
    ),
    @NamedNativeQuery(
            name    =   "getAllWarehouse15",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 15* 1609.34",
                        resultClass=WareHouse.class
    ),
    @NamedNativeQuery(
            name    =   "getAllWarehouse25",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 25* 1609.34",
                        resultClass=WareHouse.class
    ),
    @NamedNativeQuery(
            name    =   "getAllWarehouse50",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 50* 1609.34",
                        resultClass=WareHouse.class
    ),
    @NamedNativeQuery(
            name    =   "getAllWarehouse75",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 75* 1609.34",
                        resultClass=WareHouse.class
    ),
    @NamedNativeQuery(
            name    =   "getAllWarehouse100",
            query   =   "SELECT * FROM public.\"WareHouse\" WHERE ST_Distance_Sphere(\"servingArea\", ST_MakePoint(?, ?)) <= 100* 1609.34",
                        resultClass=WareHouse.class
    )
    
    
})*/

public class WareHouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3208112424205063043L;

	@Id
    @SequenceGenerator(name="WareHouse_ID_seq", sequenceName="\"WareHouse_ID_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="WareHouse_ID_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="typeofstorage")
	private String typeofstorage = "DRY";
	
	
	@Column(name="\"servingArea\"",columnDefinition="geometry(Polygon,4326)")
	private Polygon servingArea;

	@Column(name="location",columnDefinition="geometry(Point,4326)")
	private Point location;

	
	@Transient
	private String servingAreaJson;
	
	@Transient
	private String locationJson;
	
	@Column(name="country")
	private String country;
	
	@Column(name="city")
	private String city;
	
	@Column(name="area")
	private String area;
	
	@Column(name="\"PLZ\"")
	private String PLZ;
	
	@Column(name="street")
	private String street;
	@Column(name="house_no")
	private String house_no;
	
	@Column(name="\"POI\"")
	private String POI;
	
	@Column(name="type")
	private String type;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="poi_dist")
	private String poi_dist;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="formatted_address")
	private String formatted_address;
	
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getAddress() {
		return address;
	}


	public String getTypeofstorage() {
		return typeofstorage;
	}


	public Polygon getServingArea() {
		return servingArea;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setTypeofstorage(String typeofstorage) {
		this.typeofstorage = typeofstorage;
	}


	public void setServingArea(Polygon servingArea) {
		this.servingArea = servingArea;
	}


	public Point getLocation() {
		return location;
	}


	public void setLocation(Point location) {
		this.location = location;
	}


	public String getServingAreaJson() {
		return servingAreaJson;
	}


	public String getLocationJson() {
		return locationJson;
	}


	public void setServingAreaJson(String servingAreaJson) {
		this.servingAreaJson = servingAreaJson;
	}


	public void setLocationJson(String locationJson) {
		this.locationJson = locationJson;
	}


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
