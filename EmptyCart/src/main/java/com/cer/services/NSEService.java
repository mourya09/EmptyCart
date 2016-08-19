package com.cer.services;

import com.cer.model.JuniorNiftyStockWatch;
import com.cer.model.NSELiveStockWatch;

public interface NSEService {

	public JuniorNiftyStockWatch getNSEPreparedFifty();
	public JuniorNiftyStockWatch getNSEPreparedNextFifty();
	public JuniorNiftyStockWatch getNSEPreparedMidcapFifty();
	public JuniorNiftyStockWatch getNSEPreparedNiftyAuto();
	public JuniorNiftyStockWatch getNSEPreparedNiftyBank();
	public JuniorNiftyStockWatch getNSEPreparedNiftyEnergy();
	public JuniorNiftyStockWatch getNSEPreparedNiftyFinancialServices (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyFMCG (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyIT (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyMedia (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyMetal (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyPharma (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyPSUBank (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyRealty (); 
	public JuniorNiftyStockWatch getNSEPreparedNiftyPrivateBank (); 
	
	
	public NSELiveStockWatch getNSELiveDataForASymbol(String series, String symbol);
	
	
}
