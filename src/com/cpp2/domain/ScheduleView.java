package com.cpp2.domain;

import java.util.Date;

public class ScheduleView {
	private int schedule_id;
	private Date airtime;				// ²¥·ÅÊ±¿Ì
	private int remanent;				// Ê£ÓàÆ±Êý
	private int movie_id;
	private String movie_name;
	private String movie_style;
	private String language;
	private double movie_price;
	private int cinema_id;
	private String cinema_name;
	private String cinema_address;
	private String cinema_phone;
	private String videohall_name;
	
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public Date getAirtime() {
		return airtime;
	}
	public void setAirtime(Date airtime) {
		this.airtime = airtime;
	}
	public int getRemanent() {
		return remanent;
	}
	public void setRemanent(int remanent) {
		this.remanent = remanent;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public String getVideohall_name() {
		return videohall_name;
	}
	public void setVideohall_name(String videohall_name) {
		this.videohall_name = videohall_name;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_style() {
		return movie_style;
	}
	public void setMovie_style(String movie_style) {
		this.movie_style = movie_style;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public double getMovie_price() {
		return movie_price;
	}
	public void setMovie_price(double movie_price) {
		this.movie_price = movie_price;
	}
	public int getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getCinema_address() {
		return cinema_address;
	}
	public void setCinema_address(String cinema_address) {
		this.cinema_address = cinema_address;
	}
	public String getCinema_phone() {
		return cinema_phone;
	}
	public void setCinema_phone(String cinema_phone) {
		this.cinema_phone = cinema_phone;
	}
	
}
