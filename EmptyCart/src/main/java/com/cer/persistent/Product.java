/**
 * 
 */
package com.cer.persistent;

import java.io.Serializable;
import javax.persistence.Transient;
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
@Table(name= "\"Product\"")
public class Product   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2001935585306041129L;
	@Id
    @SequenceGenerator(name="WarehouseItems_itemid_seq", sequenceName="\"WarehouseItems_itemid_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="WarehouseItems_itemid_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false)
	private Long id;
	
	@Column(name="\"Name\"")
	private String name;
	
	@Column(name="\"BrandName\"")
	private String brandName;
	
	@Column(name="\"Weight\"")
	private Double weight;
	
	@Column(name="\"Quantity\"")
	private Long quantity;
	
	@Column(name="price")
	private Double price;
	
	
	@Column(name="sku")
	private String sku;

	@Column(name="upc")
	private String upc;

	@Column(name="merchant_commodity_id")
	private String merchant_commodity_id;

	@Column(name="hs_code")
	private String hs_code;

	@Column(name="description")
	private String description;

	@Column(name="url")
	private String url;

	@Column(name="sizeid")
	private String sizeid;

	@Column(name="length")
	private Double length;

	@Column(name="width")
	private Double width;

	@Column(name="height")
	private Double height;

	@Column(name="volume")
	private String volume;

	@Column(name="pictures")
	private String pictures;

	@Column(name="extension")
	private String extension;
	
	
	@Transient
	private String lattitude;


	@Transient
	private String longitude;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrandName() {
		return brandName;
	}

	public Double getWeight() {
		return weight;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public String getUpc() {
		return upc;
	}

	public String getMerchant_commodity_id() {
		return merchant_commodity_id;
	}

	public String getHs_code() {
		return hs_code;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getSizeid() {
		return sizeid;
	}

	public Double getLength() {
		return length;
	}

	public Double getWidth() {
		return width;
	}

	public Double getHeight() {
		return height;
	}

	public String getVolume() {
		return volume;
	}

	public String getPictures() {
		return pictures;
	}

	public String getExtension() {
		return extension;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public void setMerchant_commodity_id(String merchant_commodity_id) {
		this.merchant_commodity_id = merchant_commodity_id;
	}

	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSizeid(String sizeid) {
		this.sizeid = sizeid;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getLattitude() {
		return lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
