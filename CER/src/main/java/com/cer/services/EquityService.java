package com.cer.services;

import java.util.List;

import com.cer.persistent.CompanySymbol;
import com.cer.persistent.LiveShares;

public interface EquityService {

	public Boolean saveShareLive(LiveShares liveShare);
	public List<CompanySymbol> getCompanySymbol();
	
}
