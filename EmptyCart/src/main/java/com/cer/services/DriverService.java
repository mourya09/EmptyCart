/**
 * 
 */
package com.cer.services;

import com.cer.persistent.DriverInformation;

/**
 * @author Praveen Kumar
 *
 */
public interface DriverService {

	public boolean addDriverInformation(DriverInformation driver);
	
	public boolean updateDriverInformation(DriverInformation driver);
	
	public boolean deleteDriverInformaiton(DriverInformation driver);
	
}
