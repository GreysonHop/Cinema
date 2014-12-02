package com.cpp2.domain;

/**
 * 订单项实体
 * @author Rose
 */
public class OrderItem
{
	private int id;
	private Movie movie;
	private int  quantity;				// 保存用户购买票数
	private double price;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public Movie getMovie()
	{
		return movie;
	}
	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}
	
}
