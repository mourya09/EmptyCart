package com.cer.services;

import java.util.List;

import com.cer.persistent.LoadingUnloading;

public interface LoadingService {

	public Boolean saveLoading(LoadingUnloading loadInformation);
	public List<LoadingUnloading> getAllLoadedInformation();
	public List<LoadingUnloading> getAllTruckLoadedInformaton(Long truckId);
	public List<LoadingUnloading> getLoadingInformation(Long loadingId);
	
}
