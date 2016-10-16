package com.cer.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name= "company_symbol")
public class CompanySymbol {
	
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="id")
	private Long companySymbolId;
	
	@Column(name="symbol", nullable = false)
	private String symbol;
	
	@Column(name="name", nullable = true)
	private String name;

	@Column(name="series" )
	private String series;
	
	public Long getCompanySymbolId() {
		return companySymbolId;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public void setCompanySymbolId(Long companySymbolId) {
		this.companySymbolId = companySymbolId;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
	
	
}
