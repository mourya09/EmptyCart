package com.cer.services.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.UserManagement;
import com.cer.services.UserMgmtService;
import com.cer.util.PropertyConfigurer;


@Service("userMgmtService")
public class UserMgmtServiceImpl implements UserMgmtService {
	protected final Logger logger = LoggerFactory.getLogger(UserMgmtServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;
	
	public Boolean saveUserInformation(UserManagement user) {
		logger.info("saveUserInformation start ");
		Boolean result = false;
		if (user != null && user.getUsername() != null && user.getPassword() != null) {
			List<UserManagement> list = gmDao.find(propertyConfigurer.getProperty("USER_DETAILS_QUERY1"),
					new Object[] { user.getUsername(), user.getPassword() });

			if (list != null && list.size() > 0) {
				// It means user is already saved
				UserManagement temp = list.get(0);
				temp.setCreatedDate(new Date(Calendar.getInstance().getTime().getTime()));
				temp.setDisplayName(user.getDisplayName());
				gmDao.update(temp);
				result = true;
			} else {
				gmDao.save(user);
				if (user.getId() != null && user.getId() > 0) {
					result = true;
				}
			}
		}

		logger.info("saveUserInformation end ");
		return result;
	}

	public Boolean deleteUserInformation(Long userId) {
		logger.info("deleteUserInformation start ");
		Boolean result = false;
		if (userId != null ) {
			List<UserManagement> list = gmDao.find(propertyConfigurer.getProperty("USER_DETAILS_QUERY2"),
					userId );

			if (list != null && list.size() > 0) {
				// It means user is already saved
				UserManagement temp = list.get(0);
				
				gmDao.delete(temp);
				result = true;
			}
		}

		logger.info("deleteUserInformation end ");
		return result;
	}

	public Boolean updateUserInformation(UserManagement user) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserManagement isAuthenticated(String username, String pwd) {
		logger.info("isAuthenticated start ");
		UserManagement result = null;
		if (username != null && pwd != null ) {
			List<UserManagement> list = gmDao.find(propertyConfigurer.getProperty("USER_DETAILS_QUERY1"),
					new Object[] { username, pwd });

			if (list != null && list.size() > 0) {
				// It means user is already saved
				result = list.get(0);
				
			} 
		}

		logger.info("isAuthenticated end ");
		return result;
	}

}
