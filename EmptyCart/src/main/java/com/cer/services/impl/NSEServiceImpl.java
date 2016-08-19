package com.cer.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.model.JuniorNiftyStockWatch;
import com.cer.model.NSELiveStockWatch;
import com.cer.services.NSEService;
import com.cer.util.PropertyConfigurer;
import com.google.gson.Gson;

@Service("nseService")
public class NSEServiceImpl implements NSEService {

	@Autowired
	private PropertyConfigurer propertyConfigurer;
	protected final Logger logger = LoggerFactory.getLogger(NSEServiceImpl.class);
	
	private JuniorNiftyStockWatch getData(String searchUrl)
	{
		logger.info("getData start for " + searchUrl);
		JuniorNiftyStockWatch result = null;

		HttpsURLConnection hc = null;
		try {
			URL url = new URL(searchUrl);
			hc = (HttpsURLConnection) url.openConnection();
			hc.setRequestProperty("User-Agent",	"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			hc.setRequestProperty("Accept", "*/*");
			hc.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			hc.setRequestProperty("Cookie", "pointer=2; sym1=SBIN; sym2=VEDL");
			hc.setRequestProperty("Host", "www.nseindia.com");
			hc.setRequestProperty("Referer",					"https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
			hc.setRequestProperty("X-Requested-With", "XMLHttpRequest");			
			
			InputStream ins = hc.getInputStream();
		    InputStreamReader isr = new InputStreamReader(ins);
		    BufferedReader in = new BufferedReader(isr);

		    String inputLine;
		    StringBuffer strBuff= new StringBuffer();
		    while ((inputLine = in.readLine()) != null)
		    {
		    	strBuff.append(inputLine);
		    }

		    in.close();
		    inputLine = strBuff.toString();
		    if(inputLine != null && !"".equalsIgnoreCase(inputLine))
		    {
		    	Gson gson = new Gson();
		    	result = gson.fromJson(inputLine, JuniorNiftyStockWatch.class);
		    }
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			hc.disconnect();
		}
		logger.info("getData end for " + searchUrl);

		return result;

	}
	
	
	
	public JuniorNiftyStockWatch getNSEPreparedFifty() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("PREPARED_NIFTY_50");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNextFifty() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("PREPARED_NIFTY_NEXT50");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedMidcapFifty() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("PREPARED_NIFTY_MIDCAP50");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyAuto() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyBank() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyEnergy() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyFinancialServices() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyFMCG() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyIT() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyMedia() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyMetal() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyPharma() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyPSUBank() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyRealty() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}

	public JuniorNiftyStockWatch getNSEPreparedNiftyPrivateBank() {
		logger.info("getNSEPreparedFifty start ");
		String str = propertyConfigurer.getProperty("");
		JuniorNiftyStockWatch result = getData(str);
		logger.info("getNSEPreparedFifty end ");
		return result;
	}
	
	public NSELiveStockWatch getNSELiveDataForASymbol(String series, String symbol)
	{
		//symbol=VEDL&series=EQ
		String searchUrl = propertyConfigurer.getProperty("NSE_SYMBOL_VISE_CHANGE")+ "?symbol=" + symbol + "&series="+series;;
		
		logger.info("getData start for " + searchUrl);
		NSELiveStockWatch result = null;
		
		HttpsURLConnection hc = null;
		try {
			URL url = new URL(searchUrl);
			hc = (HttpsURLConnection) url.openConnection();
			hc.setRequestProperty("User-Agent",	"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			hc.setRequestProperty("Accept", "*/*");
			hc.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			hc.setRequestProperty("Cookie", "pointer=2; sym1=SBIN; sym2=VEDL");
			hc.setRequestProperty("Host", "www.nseindia.com");
			hc.setRequestProperty("Referer",					"https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
			hc.setRequestProperty("X-Requested-With", "XMLHttpRequest");	
			
			
			InputStream ins = hc.getInputStream();
		    InputStreamReader isr = new InputStreamReader(ins);
		    BufferedReader in = new BufferedReader(isr);

		    String inputLine;
		    StringBuffer strBuff= new StringBuffer();
		    while ((inputLine = in.readLine()) != null)
		    {
		    	strBuff.append(inputLine);
		    }

		    in.close();
		    inputLine = strBuff.toString();
		    if(inputLine != null && !"".equalsIgnoreCase(inputLine))
		    {
		    	Gson gson = new Gson();
		    	result = gson.fromJson(inputLine, NSELiveStockWatch.class);
		    }
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			hc.disconnect();
		}
		logger.info("getData end for " + searchUrl);

		return result;
	}

}
