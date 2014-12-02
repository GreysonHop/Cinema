package com.cpp2.domain;

/**
 * 后台管理员实体
 * @author Rose
 */
public class Admin
{
	private int admin_id;
	private String adminName;
	private String password;
	
	public int getAdmin_id()
	{
		return admin_id;
	}
	public void setAdmin_id(int admin_id)
	{
		admin_id = admin_id;
	}
	public String getAdminName()
	{
		return adminName;
	}
	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
