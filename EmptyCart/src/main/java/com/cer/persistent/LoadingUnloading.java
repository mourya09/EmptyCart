/**
 * 
 */
package com.cer.persistent;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vividsolutions.jts.geom.Point;

/**
 * @author Praveen Kumar
 *
 */

@Entity
@Table(name= "\"LoadingInformation\"")
public class LoadingUnloading implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6894381334567514679L;
	@Id
    @SequenceGenerator(name="LoadingInformation_id_seq", sequenceName="\"LoadingInformation_id_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LoadingInformation_id_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false)
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="truckid")
	private TruckInformation truckId;
	
	@Column(name="\"Cost\"")
	private Double cost;
	
	
	@Column(name="\"type\"")
	private String type;
	
	
	@Column(name="\"starttime\"")
	private Timestamp starttime;
	
	
	@Column(name="\"endtime\"")
	private Timestamp endtime;

	@Column(name="location",columnDefinition="geometry(Point,4326)")
	private Point location;
	
	
	@Transient
	private String locationJson;

	public Long getId() {
		return id;
	}


	public TruckInformation getTruckId() {
		return truckId;
	}


	public Double getCost() {
		return cost;
	}


	public String getType() {
		return type;
	}


	public Timestamp getStarttime() {
		return starttime;
	}


	public Timestamp getEndtime() {
		return endtime;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setTruckId(TruckInformation truckId) {
		this.truckId = truckId;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}


	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}


	public Point getLocation() {
		return location;
	}


	public String getLocationJson() {
		return locationJson;
	}


	public void setLocation(Point location) {
		this.location = location;
	}


	public void setLocationJson(String locationJson) {
		this.locationJson = locationJson;
	}
	
}
