/**
 * 
 */
package com.cer.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.persistent.Currency;
import com.cer.persistent.UserDetails;
import com.cer.services.CERService;
import com.cer.services.UserDetailsService;

/**
 * @author Praveen Kumar
 * It test all the Currency Exchange Rate related scenarios.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = true)
@Transactional
public class CurrencyTest {
	
	protected final Logger logger = LoggerFactory.getLogger(CurrencyTest.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CERService cerService;
	@Test
	public void addCurrency()
	{
		logger.info("addCurrency start ");
		UserDetailsServiceTest userTest=new UserDetailsServiceTest();
		userTest.testService1();
		UserDetails u=new UserDetails();
		u.setFirstName("Praveen1");
		u.setLastName("Kumar1");
		u.setEmailID("mourya.09@gmail.com");
		u.setCreationDate(new Date());
		u.setLastModifiedDate(new Date());
		u.setId(1L);
		Currency cur=new Currency();
		cur.setCode("USD");
		cur.setCurrencyName("US Dollar");
		cur.setCreationDate(new Date());
		cur.setCurrencyShortName("USD");
		cur.setCurrencySymbol("$");
		cur.setLastModifiedDate(new Date());
		cur.setCurrencyImageUrl("usd.png");
		Boolean result = cerService.addCurrency(cur, u);
		if(result)
		{
			logger.info("Currency Added Successfully ");
		}else
		{
			Assert.fail();
		}
		logger.info("addCurrency end ");
	}
	
	@Test
	public void majeTest()
 {
		String str = "SELECT MAP_FUNCTION_NAME FROM MAP_FUNCTIONS WHERE ID IN ( SELECT DISTINCT OBIEEGROUP_MAPFUNCTIONS.MAP_FUNCTIONS_ID FROM OBIEEGROUP_MAPFUNCTIONS WHERE OBIEE_GROUP_NAME =   (SELECT USERGROUP FROM OBIEEUSER WHERE LOWER(USERNAME) = LOWER('#')   )) ORDER BY MAP_FUNCTION_NAME ASC";
		System.out.println(str.replace("#", "First One"));
		// System.out.println(str.replace("?", "Second One"));
	}
	
	@Test
	public void majeeTest()
	{
		final ArrayList<Integer> list= new ArrayList<Integer>();
		for(int i =0; i < 1000000; i++)
		{
			list.add(i);
		}
		System.out.println(list.size() + " ");
	}
}
