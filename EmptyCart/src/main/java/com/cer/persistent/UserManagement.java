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

@Entity
@Table(name= "\"User_Mgmt\"")
public class UserManagement implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -1297852517356177398L;

	
	
	@Id
    @SequenceGenerator(name="User_Mgmt_ID_seq", sequenceName="\"User_Mgmt_ID_seq\"", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="User_Mgmt_ID_seq")
    @Basic(optional = false)
    @Column(name = "\"ID\"", updatable=false,insertable=false)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="\"createdDate\"")
	private Date createdDate;
	
	
	@Column(name="\"displayName\"")
	private String displayName;


	public Long getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
