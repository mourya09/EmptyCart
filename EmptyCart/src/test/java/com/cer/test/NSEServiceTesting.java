package com.cer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.model.JuniorNiftyStockWatch;
import com.cer.services.NSEService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:EmptyCart.xml" })
@TransactionConfiguration(transactionManager = "gmTransactionManager", defaultRollback = false)
@Transactional
public class NSEServiceTesting {
	protected final Logger logger = LoggerFactory.getLogger(NSEServiceTesting.class);
	
	@Autowired
	private NSEService nseService;
	
	
	@Test
	public void testNifty()
	{
		logger.info("testNifty start");
		
		JuniorNiftyStockWatch result = nseService.getNSEPreparedFifty();
		Assert.assertNotNull(result);
		
		logger.info("testNifty end ");
	}
}
