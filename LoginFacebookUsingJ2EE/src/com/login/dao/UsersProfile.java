package com.login.dao;

import java.sql.Date;

public class UsersProfile {
	
	private String oauthProvider; 
	private String oauthUserId; 
	private String firstName; 
	private String lastName; 
	private String email; 
	private String gender; 
	private String picture; 
	private String link; 
	private Date created; 
	private Date modified;
	
	
	public UsersProfile() {
		super();
	}

	public UsersProfile(String oauthProvider, String oauthUserId, String firstName, String lastName, String email,
			String gender, String picture, String link, Date created, Date modified) {
		super();
		this.oauthProvider = oauthProvider;
		this.oauthUserId = oauthUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.picture = picture;
		this.link = link;
		this.created = created;
		this.modified = modified;
	}

	public String getOauthProvider() {
		return oauthProvider;
	}

	public String getOauthUserId() {
		return oauthUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getPicture() {
		return picture;
	}

	public String getLink() {
		return link;
	}

	public Date getCreated() {
		return created;
	}

	public Date getModified() {
		return modified;
	}
	
	
	
	
	
		
}
