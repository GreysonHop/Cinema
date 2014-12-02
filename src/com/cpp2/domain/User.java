package com.cpp2.domain;

import java.util.Date;

/**
 * 用户实体
 * @author Rose
 */
public class User
{
	private int id;
	private String username;
	private String phone;
	private String gender;
	private String vip;
	private String password;
	private String state;
	private Date birthday;
	private String email;
	private double consumption;					//  保存用户的总消费,用于判断vip级别
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getState()
	{
		return state;
	}
	@Override
	public String toString() {
		return "{"+
				"'id':"+getId()+
				",'username':'"+getUsername()+
				"','phone':'"+getPhone()+
				"','gender':'"+getGender()+
				"','vip':'"+getVip()+
				"','password':'"+getPassword()+
				"','state':'"+getState()+
				"','birthday':'"+getBirthday()+
				"','email':'"+getEmail()+
				"','consumption':"+getConsumption()+
				"}";

	}
	public void setState(String state)
	{
		this.state = state;
	}
	public double getConsumption()
	{
		return consumption;
	}
	public void setConsumption(double consumption)
	{
		this.consumption = consumption;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getBirthday()
	{
		return birthday;
	}
	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getVip()
	{
		return vip;
	}
	public void setVip(String vip)
	{
		this.vip = vip;
	}
}
