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
@Entity
@Table(name= "\"SellerCatalog\"")
public class SellerCatalog implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98757160390421719L;
	@Id
    @SequenceGenerator(name="WarehouseItems_id_seq", sequenceName="\"WarehouseItems_id_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="WarehouseItems_id_seq")
    @Basic(optional = false)
    @Column(name = "id", updatable=false)
	private Long id;
	
	

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="itemid")
	private Item itemid;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="whid")
	private Seller whid;


	public Long getId() {
		return id;
	}


	public Item getItemid() {
		return itemid;
	}


	public Seller getWhid() {
		return whid;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setItemid(Item itemid) {
		this.itemid = itemid;
	}


	public void setWhid(Seller whid) {
		this.whid = whid;
	}
}
