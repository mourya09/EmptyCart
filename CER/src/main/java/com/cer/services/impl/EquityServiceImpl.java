/**
 * 
 */
package com.cer.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cer.dao.CERDao;
import com.cer.persistent.CompanySymbol;
import com.cer.persistent.LiveShares;
import com.cer.services.EquityService;

/**
 * @author NEX6UYU
 *
 */
 @Service("equityService")
public class EquityServiceImpl implements EquityService {

	 protected final Logger logger = LoggerFactory.getLogger(EquityServiceImpl.class);
	 
	 @Autowired
	 private CERDao cerDao;
	 
	 /* (non-Javadoc)
	 * @see com.cer.services.EquityService#saveShareLive(com.cer.persistent.LiveShares)
	 */
	 @Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean saveShareLive(LiveShares liveShare) {
		logger.info("saveShareLive start ");
		Boolean result = false;
		if (liveShare != null && liveShare.getSymbolid().getCompanySymbolId() != null) {
			try {
				List<CompanySymbol> list = cerDao.find("from CompanySymbol where companySymbolId=?",
						liveShare.getSymbolid().getCompanySymbolId());
				if (list != null && list.size() > 0) {
					CompanySymbol temp = list.get(0);
					if(liveShare.getCompanyName() != null && ! "".equalsIgnoreCase(liveShare.getCompanyName()) && ( 
							temp.getName() == null || "".equalsIgnoreCase(temp.getName())))
					{
						temp.setName(liveShare.getCompanyName());
						cerDao.update(temp);
					}
					liveShare.setSymbolid(temp);
					cerDao.save(liveShare);
					result = true;
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		logger.info("saveShareLive end");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.EquityService#getCompanySymbol()
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<CompanySymbol> getCompanySymbol() {
		logger.info("getCompanySymbol start ");
		List<CompanySymbol> list= cerDao.find("from CompanySymbol order by symbol");
		
		logger.info("getCompanySymbol end ");
		return list;
	}

}
