package com.cer.services;

import com.cer.persistent.UserManagement;

public interface UserMgmtService {

	public Boolean saveUserInformation(UserManagement user);
	
	public Boolean deleteUserInformation(Long userId);
	
	public Boolean updateUserInformation(UserManagement user);
	
	public UserManagement isAuthenticated(String username, String pwd);
	
}
