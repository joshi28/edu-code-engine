package com.login.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.login.connection.DatabaseConnection;

public class UserProfileDaoImpl implements UserProfileDao {
	final static Logger logger = Logger.getLogger(UserProfileDaoImpl.class);
	PreparedStatement preparedStmt = null;
	ResultSet resultSet = null;

	@Override
	public void saveUserData(UsersProfile usersProfile) {
		try {
			String insertUsersProfileSql = "INSERT INTO user_profile"
					+ "(oauth_provider, oauth_userid, first_name, last_name,email,gender,picture,link,created,modified) VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?)";

			preparedStmt = DatabaseConnection.getConnection().prepareStatement(insertUsersProfileSql);
			int preparedStmtIndex = 1;
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getOauthProvider());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getOauthUserId());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getFirstName());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getLastName());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getEmail());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getGender());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getPicture());
			preparedStmt.setString(preparedStmtIndex++, usersProfile.getLink());
			preparedStmt.setDate(preparedStmtIndex++, usersProfile.getCreated());
			preparedStmt.setDate(preparedStmtIndex, usersProfile.getModified());
			preparedStmt.execute();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			DatabaseConnection.closePreparedStatement(preparedStmt);
			DatabaseConnection.closeConnection();
		}

	}

	@Override
	public Boolean validateUserDAta(String oauthUserId, String email) {
		boolean dataAvailable = false;
		try {
			String selectUserProfileSql = "Select count(oauth_provider) from  user_profile where oauth_provider=? and email=?";
			preparedStmt = DatabaseConnection.getConnection().prepareStatement(selectUserProfileSql);

			int parameterIndex = 1;
			preparedStmt.setString(parameterIndex++, oauthUserId);
			preparedStmt.setString(parameterIndex, email);
			resultSet = preparedStmt.executeQuery();
			if (resultSet.next()) {
				dataAvailable = true;
			}

		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getLocalizedMessage());
			}
		}

		return dataAvailable;
	}

}
