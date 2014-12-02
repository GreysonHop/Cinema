package com.cpp2.domain;

import java.util.Date;

public class Collection {

	private int id;
	private int movie_id;
	private int user_id;
	private Date collectionTime;
	private String image;
	private String moveiName;
	
	
	public Date getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMoveiName() {
		return moveiName;
	}
	public void setMoveiName(String moveiName) {
		this.moveiName = moveiName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
