package com.example.cg_cafe.model;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}