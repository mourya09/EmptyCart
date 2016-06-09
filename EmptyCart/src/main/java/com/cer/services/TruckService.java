package com.cer.services;

import com.cer.persistent.TruckInformation;

public interface TruckService {
	public boolean addTruck(TruckInformation truck);
	public boolean updateTruck(TruckInformation truck);
	public boolean deleteTruck(TruckInformation truck);
}
