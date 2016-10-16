/**
 * 
 */
package com.cer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cer.persistent.CompanySymbol;
import com.cer.persistent.LiveData;
import com.cer.persistent.LiveShares;
import com.cer.services.EquityService;
import com.google.gson.Gson;

/**
 * @author NEX6UYU
 *
 */
public class LiveStatus implements Runnable {

	private CompanySymbol symbol;
	protected final Logger logger = LoggerFactory.getLogger(LiveStatus.class);
	private static final SimpleDateFormat dt = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");	
	
	private static final SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMM-yy");
	private static final SimpleDateFormat dt2 = new SimpleDateFormat("ddMMMYYYY");

	static {
		System.out.println("Welcome Telcome "+ Calendar.getInstance().getTimeZone().getDisplayName());
		//dt.setTimeZone(TimeZone.getTimeZone("Indian/Reunion"));
		//dt1.setTimeZone(TimeZone.getTimeZone("Indian/Reunion"));
		//dt2.setTimeZone(TimeZone.getTimeZone("Indian/Reunion"));
	
	}
	private EquityService equityService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public LiveStatus(CompanySymbol symbol, EquityService equityService) {
		this.symbol = symbol;
		this.equityService = equityService;
	}

	private Timestamp getDateFormat(SimpleDateFormat simpleDateFormat, String str) {
		Timestamp result = null;
		logger.info("Welcome Telcome " + Calendar.getInstance().getTimeZone().getDisplayName());
		if (str.contains("-") && str.length() == 1) {
			return null;
		} else {
			try {
				java.util.Date date = simpleDateFormat.parse(str);
				result = new Timestamp(date.getTime());

			} catch (Exception ex) {
				logger.error("Error for " + str);
				ex.printStackTrace();
			}
		}

		return result;
	}

	private Double isDecimalValue(String str) {
		Double result = null;
		try {
			if (str.contains("-") ||str.contains("No Band")|| !StringUtils.hasText(str) ) {
				return null;
			} else {
				NumberFormat format = NumberFormat.getInstance(Locale.US);

				Number number = format.parse(str);
				result = number.doubleValue();

			}

		} catch (Exception ex) {
			logger.error("Error for Value =" + str);
			ex.printStackTrace();

		}

		return result;
	}

	public void formatLiveSharesData(LiveData liveData, LiveShares liveShares) {

		liveShares.setLastUpdateTime(getDateFormat(dt, liveData.getLastUpdateTime()));
		liveShares.setBcStartDate(getDateFormat(dt1, liveData.getData()[0].getBcStartDate()));
		liveShares.setNdStartDate(getDateFormat(dt1, liveData.getData()[0].getNdStartDate()));

		liveShares.setSecDate(getDateFormat(dt2, liveData.getData()[0].getSecDate()));
		liveShares.setCm_adj_low_dt(getDateFormat(dt1, liveData.getData()[0].getCm_adj_low_dt()));
		liveShares.setRecordDate(getDateFormat(dt1, liveData.getData()[0].getRecordDate()));
		liveShares.setCm_adj_high_dt(getDateFormat(dt1, liveData.getData()[0].getCm_adj_high_dt()));
		liveShares.setExDate(getDateFormat(dt1, liveData.getData()[0].getExDate()));
		liveShares.setBcEndDate(getDateFormat(dt1, liveData.getData()[0].getBcEndDate()));
		liveShares.setCss_status_desc( liveData.getData()[0].getCss_status_desc());
		liveShares.setNdEndDate(getDateFormat(dt1, liveData.getData()[0].getBcEndDate()));

		liveShares.setMarketType(liveData.getData()[0].getMarketType());
		liveShares.setCompanyName(liveData.getData()[0].getCompanyName());
		liveShares.setSymbol(liveData.getData()[0].getSymbol());
		liveShares.setIsinCode(liveData.getData()[0].getIsinCode());
		liveShares.setPurpose(liveData.getData()[0].getPurpose());
		liveShares.setSeries(liveData.getData()[0].getSeries());
		liveShares.setExtremeLossMargin(isDecimalValue(liveData.getData()[0].getExtremeLossMargin()));
		liveShares.setCm_ffm(isDecimalValue(liveData.getData()[0].getCm_ffm()));
		liveShares.setChange(isDecimalValue(liveData.getData()[0].getChange()));
		liveShares.setBuyQuantity3(isDecimalValue(liveData.getData()[0].getBuyQuantity3()));
		liveShares.setSellPrice1(isDecimalValue(liveData.getData()[0].getSellPrice1()));
		liveShares.setBuyQuantity4(isDecimalValue(liveData.getData()[0].getBuyQuantity4()));
		liveShares.setSellPrice2(isDecimalValue(liveData.getData()[0].getSellPrice2()));
		liveShares.setPriceBand(isDecimalValue(liveData.getData()[0].getPriceBand()));
		liveShares.setBuyQuantity1(isDecimalValue(liveData.getData()[0].getBuyQuantity1()));
		liveShares.setDeliveryQuantity(isDecimalValue(liveData.getData()[0].getDeliveryQuantity()));
		liveShares.setBuyQuantity2(isDecimalValue(liveData.getData()[0].getBuyQuantity2()));
		liveShares.setSellPrice5(isDecimalValue(liveData.getData()[0].getSellPrice5()));
		liveShares.setQuantityTraded(isDecimalValue(liveData.getData()[0].getQuantityTraded()));
		liveShares.setBuyQuantity5(isDecimalValue(liveData.getData()[0].getBuyQuantity5()));
		liveShares.setSellPrice3(isDecimalValue(liveData.getData()[0].getSellPrice3()));
		liveShares.setSellPrice4(isDecimalValue(liveData.getData()[0].getSellPrice4()));
		liveShares.setOpen(isDecimalValue(liveData.getData()[0].getOpen()));
		liveShares.setLow52(isDecimalValue(liveData.getData()[0].getLow52()));
		liveShares.setSecurityVar(isDecimalValue(liveData.getData()[0].getSecurityVar()));
		liveShares.setPricebandupper(isDecimalValue(liveData.getData()[0].getPricebandupper()));
		liveShares.setTotalTradedValue(isDecimalValue(liveData.getData()[0].getTotalTradedValue()));
		liveShares.setFaceValue(isDecimalValue(liveData.getData()[0].getFaceValue()));
		liveShares.setPreviousClose(isDecimalValue(liveData.getData()[0].getPreviousClose()));
		liveShares.setVarMargin(isDecimalValue(liveData.getData()[0].getVarMargin()));
		liveShares.setLastPrice(isDecimalValue(liveData.getData()[0].getLastPrice()));
		liveShares.setpChange(isDecimalValue(liveData.getData()[0].getpChange()));
		liveShares.setAdhocMargin(isDecimalValue(liveData.getData()[0].getAdhocMargin()));
		liveShares.setAveragePrice(isDecimalValue(liveData.getData()[0].getAveragePrice()));
		liveShares.setIndexVar(isDecimalValue(liveData.getData()[0].getIndexVar()));
		liveShares.setPricebandlower(isDecimalValue(liveData.getData()[0].getPricebandlower()));
		liveShares.setTotalBuyQuantity(isDecimalValue(liveData.getData()[0].getTotalBuyQuantity()));
		liveShares.setHigh52(isDecimalValue(liveData.getData()[0].getHigh52()));
		liveShares.setClosePrice(isDecimalValue(liveData.getData()[0].getClosePrice()));
		liveShares.setIsExDateFlag(Boolean.parseBoolean(liveData.getData()[0].getIsExDateFlag()));
		liveShares.setTotalSellQuantity(isDecimalValue(liveData.getData()[0].getTotalSellQuantity()));
		liveShares.setDayHigh(isDecimalValue(liveData.getData()[0].getDayHigh()));
		liveShares.setSellQuantity5(isDecimalValue(liveData.getData()[0].getSellQuantity5()));
		liveShares.setSellQuantity2(isDecimalValue(liveData.getData()[0].getSellQuantity2()));
		liveShares.setSellQuantity1(isDecimalValue(liveData.getData()[0].getSellQuantity1()));
		liveShares.setBuyPrice1(isDecimalValue(liveData.getData()[0].getBuyPrice1()));
		liveShares.setSellQuantity4(isDecimalValue(liveData.getData()[0].getSellQuantity4()));
		liveShares.setBuyPrice2(isDecimalValue(liveData.getData()[0].getBuyPrice2()));
		liveShares.setSellQuantity3(isDecimalValue(liveData.getData()[0].getSellQuantity3()));
		liveShares.setApplicableMargin(isDecimalValue(liveData.getData()[0].getApplicableMargin()));
		liveShares.setBuyPrice4(isDecimalValue(liveData.getData()[0].getBuyPrice4()));
		liveShares.setBuyPrice3(isDecimalValue(liveData.getData()[0].getBuyPrice3()));
		liveShares.setBuyPrice5(isDecimalValue(liveData.getData()[0].getBuyPrice5()));
		liveShares.setDayLow(isDecimalValue(liveData.getData()[0].getDayLow()));
		liveShares.setDeliveryToTradedQuantity(isDecimalValue(liveData.getData()[0].getDeliveryToTradedQuantity()));
		liveShares.setTotalTradedVolume(isDecimalValue(liveData.getData()[0].getTotalTradedVolume()));
	}

	public void run() {
		String threadName = "";
		if (symbol != null && symbol.getSymbol() != null) {
			threadName = symbol.getSymbol();
			if(threadName.contains("&"))
			{
				threadName = threadName.replaceAll("&", "%26");
			}
		}
		logger.info("Thread " + threadName + " started ");
		String httpsUrl = "https://www.nse-india.com/live_market/dynaContent/live_watch/get_quote/ajaxGetQuoteJSON.jsp?symbol="
				+ threadName + "&series="+symbol.getSeries();
		URL url = null;
		try {
			url = new URL(httpsUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			print_https_cert(con);

			// dump all the content
			String content = print_content(con);
			if (content != null) {
				Gson gson = new Gson();
				LiveData data = gson.fromJson(content, LiveData.class);
				LiveShares ls = new LiveShares();
				ls.setSymbolid(symbol);
				if (data != null && data.getData() != null && data.getData().length > 0) {
					formatLiveSharesData(data, ls);
					Boolean result = equityService.saveShareLive(ls);
					if (result) {
						logger.info("Saved new Information for " + threadName);
					}
				}else
				{
					// It means shares has been closed. Now need is to remove it from the database as well.
					//logic to remove the data from the table.
				}
				
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		logger.info("Thread " + threadName + " Ended ");

	}

	private void print_https_cert(HttpsURLConnection con) {

		if (con != null) {

			try {

				logger.info("Response Code : " + con.getResponseCode());
				logger.info("Cipher Suite : " + con.getCipherSuite());
				logger.info("\n");

				Certificate[] certs = con.getServerCertificates();
				for (Certificate cert : certs) {
					logger.info("Cert Type : " + cert.getType());
					logger.info("Cert Hash Code : " + cert.hashCode());
					logger.info("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
					logger.info("Cert Public Key Format : " + cert.getPublicKey().getFormat());
					logger.info("\n");
				}

			} catch (SSLPeerUnverifiedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private String print_content(HttpsURLConnection con) {
		String result = null;
		StringBuffer strBuffer = new StringBuffer();
		if (con != null) {

			try {

				System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String input;

				logger.info("Writing response");
				while ((input = br.readLine()) != null) {
					logger.info(input);
					strBuffer.append(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (strBuffer.length() > 0) {
					result = strBuffer.toString();
				}
			}

		}
		return result;
	}

	public CompanySymbol getSymbol() {
		return symbol;
	}

	public EquityService getEquityService() {
		return equityService;
	}

	public void setSymbol(CompanySymbol symbol) {
		this.symbol = symbol;
	}

	public void setEquityService(EquityService equityService) {
		this.equityService = equityService;
	}

}
