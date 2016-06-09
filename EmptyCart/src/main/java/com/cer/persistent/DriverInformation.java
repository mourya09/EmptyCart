/**
 * 
 */
package com.cer.persistent;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "\"DriverInformation\"")
public class DriverInformation  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8508073744773598882L;

	@Id
    @SequenceGenerator(name="DriverInformation_ID_seq", sequenceName="\"DriverInformation_ID_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DriverInformation_ID_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false, insertable = false)
	  /*@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "BIGSERIAL  NOT NULL",insertable = false, updatable = false)*/
	private Long driverId;
	
	@Column(name="\"Name\"")
	private String name;
	
	
	@Column(name="\"License\"")
	private String license;
	
	@Column(name="\"Address\"")
	private String address;
	
	@Column(name="\"IdType1\"")
	private String idType1;
	
	@Column(name="\"IDType2\"")
	private String iDType2;
	
	@Column(name="\"OnPayRoll\"")
	private String onPayRoll;
	
	@Column(name="\"LicenseValidity\"")
	private Date licenseValidity;

	public Long getDriverId() {
		return driverId;
	}

	public String getName() {
		return name;
	}

	public String getLicense() {
		return license;
	}

	public String getAddress() {
		return address;
	}

	public String getIdType1() {
		return idType1;
	}

	public String getiDType2() {
		return iDType2;
	}

	public String getOnPayRoll() {
		return onPayRoll;
	}

	public Date getLicenseValidity() {
		return licenseValidity;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setIdType1(String idType1) {
		this.idType1 = idType1;
	}

	public void setiDType2(String iDType2) {
		this.iDType2 = iDType2;
	}

	public void setOnPayRoll(String onPayRoll) {
		this.onPayRoll = onPayRoll;
	}

	public void setLicenseValidity(Date licenseValidity) {
		this.licenseValidity = licenseValidity;
	}
	
	
}
