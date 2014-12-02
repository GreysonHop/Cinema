package com.cpp2.domain;

import java.util.Date;


public class Movie {
	
	private int id;
	private String name;
	private String director;
	private Date showtime;					// ��ӳʱ��
	private String runtime;					// Ƭ��
	private String castActor;					// ����
	private String language;
	private String style;							// ��Ӱ����ʽ 2d/3d
	private String area;
	private String type;							// ��Ӱ����
	private String introduction;
	private double price;
	private String state;							// ��ʶӰƬ�Ƿ��¼�
	private String image;
	private double popularity;				// ӰƬ�ȶ�
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String diretor) {
		this.director = diretor;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getCastActor() {
		return castActor;
	}
	public void setCastActor(String castActor) {
		this.castActor = castActor;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Date getShowtime()
	{
		return showtime;
	}
	public void setShowtime(Date showtime)
	{
		this.showtime = showtime;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	
	
	
	
	
}
