package com.maven.model;

import java.io.Serializable;

public class UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2852382952006099389L;
	
    private int id;
    private String userName;
    private String passWord;
    private String nickName;
    private String userSex;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	
    
    
}
