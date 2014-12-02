package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Order;
import com.cpp2.domain.User;

public interface OrderDAO
{

	/**
	 * 往TB_Order表添加一条数据
	 * @param order
	 */
	public abstract void create(Order order);

	/**
	 * genju 所给的id,在数据库中查找Order,并返回
	 * @param id
	 * @return
	 */
	public abstract Order retrieve(int id);

	/**
	 * 获取所有订单
	 * @return
	 */
	public abstract List<Order> getAll(boolean state);

	/**
	 * 更新
	 * @param order
	 */
	public abstract void update(Order order);

	public abstract void delete(Order order);

	public abstract int getTotalRecord();

	public abstract List<Order> getAllOrderPageDate(int beginIndex, int everyPage,String state);

	int getUnsendRecord();

}