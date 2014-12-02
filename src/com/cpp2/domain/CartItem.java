package com.cpp2.domain;

public class CartItem
{
	private Movie movie;
	private double price;
	private int quantity;
	public Movie getMovie()
	{
		return movie;
	}
	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}
	
	public double getPrice()
	{
		return this.getQuantity() * this.movie.getPrice();
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
