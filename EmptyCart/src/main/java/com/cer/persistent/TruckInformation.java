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
@Entity
@Table(name= "\"TruckInformation\"")
public class TruckInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6141672948708152098L;
	@Id
    @SequenceGenerator(name="TruckInformation_ID_seq", sequenceName="\"TruckInformation_ID_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TruckInformation_ID_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false)
	private Long id;
	
	@Column(name="\"Name\"")
	private String name;
	
	
	@Column(name="\"TruckNumber\"")
	private String truckNumber;
	
	
	@Column(name="\"Capacity\"")
	private Long capacity;
	
	
	@Column(name="\"Permit\"")
	private String Permit;
	
	@Column(name="\"Type\"")
	private String Type;
	
	
	@Column(name="\"InsurancePolicyNumber\"")
	private Long InsurancePolicyNumber;
	
	@Column(name="\"CapacityType\"")
	private String CapacityType = "Kg";
	
	@Column(name="\"Ownership\"")
	private String Ownership = "Company";

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public Long getCapacity() {
		return capacity;
	}

	public String getPermit() {
		return Permit;
	}

	public String getType() {
		return Type;
	}

	public Long getInsurancePolicyNumber() {
		return InsurancePolicyNumber;
	}

	public String getCapacityType() {
		return CapacityType;
	}

	public String getOwnership() {
		return Ownership;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public void setPermit(String permit) {
		Permit = permit;
	}

	public void setType(String type) {
		Type = type;
	}

	public void setInsurancePolicyNumber(Long insurancePolicyNumber) {
		InsurancePolicyNumber = insurancePolicyNumber;
	}

	public void setCapacityType(String capacityType) {
		CapacityType = capacityType;
	}

	public void setOwnership(String ownership) {
		Ownership = ownership;
	}
}
