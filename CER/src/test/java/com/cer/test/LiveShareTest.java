package com.cer.test;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.persistent.CompanySymbol;
import com.cer.persistent.LiveData;
import com.cer.services.EquityService;
import com.cer.util.LiveStatus;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = false)
@Transactional
public class LiveShareTest {
	protected final Logger logger = LoggerFactory.getLogger(LiveShareTest.class);
	
	@Autowired
	private EquityService equityService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private IgniteConfiguration ignite_cfg;
	
	@Test
	public void getApacheCacheTest()
	{
		logger.info("Welcome to hell");
		if(ignite_cfg != null)
		{
			Ignite ignite = Ignition.start(ignite_cfg);
			IgniteCache cach=ignite.getOrCreateCache("myCacheName");
			for(int i=0;i< 1001;i++)
			{
				cach.put(i, Integer.toString(i));
			}
			
			for(int i=0;i< 1001;i++)
			{
				System.out.println("Cache get:"+ cach.get(i));

			}
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ignite.close();
		}
	}
	
	@Test
	public void getAllSymbols()
	{
		logger.info("getAllSymbols start ");
		List<CompanySymbol> list= equityService.getCompanySymbol();
		Assert.assertNotNull(list);
		if(list != null && list.size() > 0)
		{
			logger.info(""+ list.size());
		}
		
		logger.info("getAllSymbols end ");
	}
	
	@Test
	public void getAllLiveUpdates()
	{
		logger.info("getAllLiveUpdates start ");
		List<CompanySymbol> list= equityService.getCompanySymbol();
		Assert.assertNotNull(list);
		if(list != null && list.size() > 0)
		{
			for(CompanySymbol symbol:list)
			{
				LiveStatus live = new LiveStatus(symbol,equityService);
				//Thread thread=new Thread(live,symbol.getName());
				//thread.start();
				
				taskExecutor.execute(live);
			}
			//LiveStatus live = new LiveStatus(list.get(0),equityService);
			//taskExecutor.execute(live);
			
			for (;;) {
				int count = taskExecutor.getActiveCount();
				logger.info("Active Threads : " + count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (count == 0) {
					taskExecutor.shutdown();
					break;
				}
			}
		}
		
		logger.info("getAllLiveUpdates end ");
	}
	
	
	@Test
	public void testString()
	{
		String str="{\"futLink\":\"\",\"otherSeries\":[\"EQ\"],\"lastUpdateTime\":\"23-SEP-2016 15:59:59\",\"tradedDate\":\"23SEP2016\",\"data\":[{\"extremeLossMargin\":\"5.00\",\"cm_ffm\":\"52.71\",\"bcStartDate\":\"16-SEP-16\",\"change\":\"-0.45\",\"buyQuantity3\":\"-\",\"sellPrice1\":\"-\",\"buyQuantity4\":\"-\",\"sellPrice2\":\"-\",\"priceBand\":\"20\",\"buyQuantity1\":\"2,091\",\"deliveryQuantity\":\"1,10,076\",\"buyQuantity2\":\"-\",\"sellPrice5\":\"-\",\"quantityTraded\":\"1,35,931\",\"buyQuantity5\":\"-\",\"sellPrice3\":\"-\",\"sellPrice4\":\"-\",\"open\":\"38.10\",\"low52\":\"24.50\",\"securityVar\":\"10.35\",\"marketType\":\"N\",\"pricebandupper\":\"46.70\",\"totalTradedValue\":\"52.28\",\"faceValue\":\"5.00\",\"ndStartDate\":\"-\",\"previousClose\":\"38.95\",\"symbol\":\"20MICRONS\",\"varMargin\":\"25.98\",\"lastPrice\":\"38.50\",\"pChange\":\"-1.16\",\"adhocMargin\":\"-\",\"companyName\":\"20 Microns Limited\",\"averagePrice\":\"38.46\",\"secDate\":\"23SEP2016\",\"series\":\"EQ\",\"isinCode\":\"INE144J01027\",\"indexVar\":\"15.00\",\"pricebandlower\":\"31.20\",\"totalBuyQuantity\":\"2,091\",\"high52\":\"45.00\",\"purpose\":\"ANNUAL GENERAL MEETING\",\"cm_adj_low_dt\":\"23-SEP-15\",\"closePrice\":\"38.30\",\"isExDateFlag\":false,\"recordDate\":\"-\",\"cm_adj_high_dt\":\"12-AUG-16\",\"totalSellQuantity\":\"-\",\"dayHigh\":\"39.60\",\"exDate\":\"14-SEP-16\",\"sellQuantity5\":\"-\",\"bcEndDate\":\"23-SEP-16\",\"css_status_desc\":\"Listed\",\"ndEndDate\":\"-\",\"sellQuantity2\":\"-\",\"sellQuantity1\":\"-\",\"buyPrice1\":\"38.30\",\"sellQuantity4\":\"-\",\"buyPrice2\":\"-\",\"sellQuantity3\":\"-\",\"applicableMargin\":\"30.98\",\"buyPrice4\":\"-\",\"buyPrice3\":\"-\",\"buyPrice5\":\"-\",\"dayLow\":\"37.85\",\"deliveryToTradedQuantity\":\"80.98\",\"totalTradedVolume\":\"1,35,931\"}],\"optLink\":\"\"}";
		try{
		Gson gson = new Gson();
		LiveData data = gson.fromJson(str, LiveData.class);
		logger.info(data.toString());
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
