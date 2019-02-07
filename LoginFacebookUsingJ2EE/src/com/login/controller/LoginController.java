package com.login.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.login.dao.UserProfileDao;
import com.login.dao.UserProfileDaoImpl;
import com.login.dao.UsersProfile;

import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONObject jobj=null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
			JSONParser parser = new JSONParser();
			Date todaysDate = new Date();
			String userData = request.getParameter("userData");
			 jobj= (JSONObject) parser.parse(userData);
			System.out.println(jobj.get("gender"));
			String auth_provider= request.getParameter("oauth_provider");
			System.out.println(auth_provider);
			 java.util.Date date=new java.util.Date();
				
				java.sql.Date sqlDate=new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			UsersProfile usersProfile = new UsersProfile(auth_provider,
					jobj.get("id").toString(), jobj.get("first_name").toString(), jobj.get("last_name").toString(),
					jobj.get("email").toString(), jobj.get("gender").toString(), jobj.get("picture").toString(),
					jobj.get("link").toString(), sqlDate, sqlDate);
			UserProfileDao userdao= new UserProfileDaoImpl();
			if(userdao.validateUserDAta(auth_provider, jobj.get("email").toString())) {
				System.out.println("User already exist");
			}else {
				userdao.SaveUserData(usersProfile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
