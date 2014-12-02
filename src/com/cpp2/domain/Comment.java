package com.cpp2.domain;

import java.util.Date;

public class Comment {

	private int id;
	private String content;
	private Date CommitTime;
	private int user_id;
	private int movie_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommitTime() {
		return CommitTime;
	}
	public void setCommitTime(Date commitTime) {
		CommitTime = commitTime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	
	
}
