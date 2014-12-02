package com.cpp2.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单实体
 * @author Rose
 */
public class Order
{
	private int id;
	private Date ordertime;
	private double price;
	private String state;													// 标识发货状态
	private User user;
	private Set<OrderItem> orderitem = new HashSet(); 	// 订单项
	
	public Date getOrdertime()
	{
		return ordertime;
	}
	public void setOrdertime(Date orderTime)
	{
		this.ordertime = orderTime;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public Set<OrderItem> getOrderitem()
	{
		return orderitem;
	}
	public void setOrderitem(Set<OrderItem> orderItem)
	{
		this.orderitem = orderItem;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
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
