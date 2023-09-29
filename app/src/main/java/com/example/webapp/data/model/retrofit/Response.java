package com.example.webapp.data.model.retrofit;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}