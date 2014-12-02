package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Order;
import com.cpp2.domain.User;

public interface OrderDAO
{

	/**
	 * ��TB_Order�����һ������
	 * @param order
	 */
	public abstract void create(Order order);

	/**
	 * genju ������id,�����ݿ��в���Order,������
	 * @param id
	 * @return
	 */
	public abstract Order retrieve(int id);

	/**
	 * ��ȡ���ж���
	 * @return
	 */
	public abstract List<Order> getAll(boolean state);

	/**
	 * ����
	 * @param order
	 */
	public abstract void update(Order order);

	public abstract void delete(Order order);

	public abstract int getTotalRecord();

	public abstract List<Order> getAllOrderPageDate(int beginIndex, int everyPage,String state);

	int getUnsendRecord();

}