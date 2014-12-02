package com.cpp2.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart
{
	private Map<Integer,CartItem> map = new HashMap();
	private double price;
	
	/**
	 * �����ﳵ��ӵ�Ӱ
	 * @param movie
	 */
	public void create(Movie movie)
	{
		/* ���ж��Ƿ��Ѵ��ڹ��ﳵ���� ,�������ھ����һ����Ӱ,������������һ*/
		CartItem item = map.get(movie.getId());			// ��ȡ���ĳ��Ӱ�Ĺ�����
		if(null == item)
		{
			item = new CartItem();
			item.setMovie(movie);
			item.setPrice(movie.getPrice());
			item.setQuantity(1);
			map.put(movie.getId(), item);						// ����Ӱ��id����Ϊkey, ����õ�Ӱ��map����
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
	 * ���ع��ﳵ��С��
	 * @return
	 */
	public double getPrice()
	{
		/* �����й������С�ƶ������� */
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
