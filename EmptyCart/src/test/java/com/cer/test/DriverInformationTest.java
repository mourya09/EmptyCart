package com.cer.test;

import java.sql.Date;
import java.util.Calendar;

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

import com.cer.persistent.DriverInformation;
import com.cer.services.DriverService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GreatMart.xml" })
@TransactionConfiguration(transactionManager = "gmTransactionManager", defaultRollback = false)
@Transactional
public class DriverInformationTest {

	protected final Logger logger = LoggerFactory.getLogger(DriverInformationTest.class);
	
	@Autowired
	private DriverService driverService;
	
	
	@Test
	public void addDriver()
	{
		DriverInformation di = new DriverInformation();
		di.setAddress("A-501, Gaur Global Village, Crossing Republic, Ghaziabad, UP - 201016");
		di.setName("Driver1");
		di.setIdType1("Aadhar");
		di.setiDType2("PAN Card");
		di.setLicense("License1");
		di.setLicenseValidity(new Date(Calendar.getInstance().getTime().getTime()));
		boolean result = driverService.addDriverInformation(di);
		Assert.assertTrue("Unabe to Add Driver Information ", result);
		
		
	}
	
}
