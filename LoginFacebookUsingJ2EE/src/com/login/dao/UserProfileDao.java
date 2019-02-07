package com.login.dao;

public interface UserProfileDao {
	
	

	void SaveUserData(UsersProfile usersProfile);


	Boolean validateUserDAta(String oauthUserId, String email);
	
}
