package com.cer.persistent;

import java.io.Serializable;

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

import com.vividsolutions.jts.geom.Polygon;



@Entity
@Table(name= "\"RouteTrackSystem\"")
public class RouteTrackSystem   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2165284741910731987L;
	@Id
    @SequenceGenerator(name="RouteTrackSystem_RouteId_seq", sequenceName="\"RouteTrackSystem_RouteId_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RouteTrackSystem_RouteId_seq")
    @Basic(optional = false)
    @Column(name = "\"RouteId\"", updatable=false)
	private Long id;
	
	
	@Column(name="\"ReFuelType\"")
	private String reFuelType;
	
	@Column(name="\"RefuelCost\"",precision=2, length=7)
	private Double refuelCost;
	
	@Column(name="\"TollTax\"",precision=2, length=7)
	private Double tollTax;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="\"driverid\"")
	private DriverInformation driverid;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="\"truckid\"")
	private TruckInformation truckId;
	
	
	@Column(name="\"Route\"")
	private Polygon route;

	@Transient
	private String routeGeojson;
	
	@Transient
	private Integer projection;

	public Long getId() {
		return id;
	}


	public String getReFuelType() {
		return reFuelType;
	}


	public Double getRefuelCost() {
		return refuelCost;
	}


	public Double getTollTax() {
		return tollTax;
	}


	public DriverInformation getDriverid() {
		return driverid;
	}


	public TruckInformation getTruckId() {
		return truckId;
	}


	public Polygon getRoute() {
		return route;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setReFuelType(String reFuelType) {
		this.reFuelType = reFuelType;
	}


	public void setRefuelCost(Double refuelCost) {
		this.refuelCost = refuelCost;
	}


	public void setTollTax(Double tollTax) {
		this.tollTax = tollTax;
	}


	public void setDriverid(DriverInformation driverid) {
		this.driverid = driverid;
	}


	public void setTruckId(TruckInformation truckId) {
		this.truckId = truckId;
	}


	public void setRoute(Polygon route) {
		this.route = route;
	}


	public String getRouteGeojson() {
		return routeGeojson;
	}


	public void setRouteGeojson(String routeGeojson) {
		this.routeGeojson = routeGeojson;
	}


	public Integer getProjection() {
		return projection;
	}


	public void setProjection(Integer projection) {
		this.projection = projection;
	}
}
