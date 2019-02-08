package com.login.dao;

public interface UserProfileDao {
	
	

	void saveUserData(UsersProfile usersProfile);


	Boolean validateUserDAta(String oauthUserId, String email);
	
}
