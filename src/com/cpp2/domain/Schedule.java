package com.cpp2.domain;

import java.util.Date;

public class Schedule {

	private int id;
	private Date airtime;
	private int totalTicket;
	private int videoHall_id;
	private int movie_id;
	private int remanent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getAirtime() {
		return airtime;
	}
	public void setAirtime(Date airtime) {
		this.airtime = airtime;
	}
	public int getTotalTicket() {
		return totalTicket;
	}
	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}
	public int getVideoHall_id() {
		return videoHall_id;
	}
	public void setVideoHall_id(int videoHall_id) {
		this.videoHall_id = videoHall_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getRemanent() {
		return remanent;
	}
	public void setRemanent(int remanent) {
		this.remanent = remanent;
	}
	
	
}
