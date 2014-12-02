package com.cpp2.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart
{
	private Map<Integer,CartItem> map = new HashMap();
	private double price;
	
	/**
	 * 往购物车添加电影
	 * @param movie
	 */
	public void create(Movie movie)
	{
		/* 先判断是否已存在购物车里面 ,若不存在就添加一部电影,存在则数量加一*/
		CartItem item = map.get(movie.getId());			// 获取存放某电影的购物项
		if(null == item)
		{
			item = new CartItem();
			item.setMovie(movie);
			item.setPrice(movie.getPrice());
			item.setQuantity(1);
			map.put(movie.getId(), item);						// 将电影的id设置为key, 保存该电影到map集合
		}
		else
		{
			item.setQuantity(item.getQuantity()+1);
		}
	}
	public Map<Integer, CartItem> getMap()
	{
		return map;
	}
	public void setMap(Map<Integer, CartItem> map)
	{
		this.map = map;
	}
	
	/**
	 * 返回购物车的小计
	 * @return
	 */
	public double getPrice()
	{
		/* 将所有购物项的小计都加起来 */
		double totalPrice = 0;
		for(Map.Entry<Integer, CartItem> me : map.entrySet())
		{
			CartItem item = me.getValue();
			totalPrice += item.getPrice();
		}
		this.price = totalPrice;
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	
}
