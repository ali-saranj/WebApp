package com.example.webapp.data.model.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {

	@SerializedName("image")
	private String image;

	@SerializedName("des")
	private String description;

	@SerializedName("Username")
	private String username;

	@SerializedName("Titel")
	private String titel;

	@SerializedName("Data")
	private String data;

	@SerializedName("id")
	private String id;

	public String getImage(){
		return image;
	}

	public String getDescription(){
		return description;
	}

	public String getUsername(){
		return username;
	}

	public String getTitel(){
		return titel;
	}

	public String getData(){
		return data;
	}

	public String getId(){
		return id;
	}
}