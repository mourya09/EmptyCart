/**
 * 
 */
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

/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "\"Item\"")
public class Item   implements Serializable{

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
}
