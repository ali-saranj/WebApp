package com.example.webapp.data.model.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {

	@SerializedName("Image")
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

    public Post(com.example.webapp.ui.viewModel.Post item) {
		id = item.getId();
		description = item.getDescription();
		username = item.getUsername();
		titel = item.getTitle();
		data = item.getData();
    }

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