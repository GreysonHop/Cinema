package com.cpp2.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cpp2.dao.OrderDAO;
import com.cpp2.domain.Movie;
import com.cpp2.domain.Order;
import com.cpp2.domain.OrderItem;
import com.cpp2.domain.User;
import com.cpp2.utils.JDBCUtils;

public class OrderDAOImpl implements OrderDAO
{
	/**
	 * 往TB_Order表添加一条数据
	 * @param order
	 */
	@Override
	public void create(Order order)
	{
		try
		{
			/* 基本信息保存到Order表*/
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "INSERT INTO TB_Order(_id,OrderTime,Price,State,User_id) values(?,?,?,?,?)";
			Object params[] = {order.getId(), order.getOrdertime(), order.getPrice(), order.getState(), order.getUser().getId()};
			qr.update(sql,params);
			
			/* 把Order表中的订单保存到OrderItem表 */
			Set<OrderItem> items = order.getOrderitem();
			for(OrderItem item : items)
			{
				sql = "INSERT INTO TB_OrderItem(_id,Quantity,Price,Movie_id,Oder_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(),item.getMovie().getId(), order.getId()};
				qr.update(sql,params);
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * genju 所给的id,在数据库中查找Order,并返回
	 * @param id
	 * @return
	 */
	@Override
	public Order retrieve(int id)
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			/* 先获取Order基本信息 */
			String sql = "SELECT * FROM tb_order WHERE id=?";
			Order order = qr.query(sql,id,new BeanHandler(Order.class));
			/* 再获取所有OrderItem */
			sql = "SELECT * FROM tb_orderItem WHERE Order_id=?";
			List<OrderItem> list = (List<OrderItem>)qr.query(sql, id, new BeanListHandler(OrderItem.class));
			for(OrderItem item : list)
			{
				sql = "SELECT m.* FROM tb_orderItem i, tb_movie m WHERE i.id=? and i.Movieid=m.id";
				Movie movie = (Movie)qr.query(sql, item.getId(), new BeanHandler(Movie.class));
				item.setMovie(movie);
			}
			/* 订单属于哪个用户 */
			sql = "SELECT u.* FROM tb_order o,tb_user u WHERE o.id=? and o.User_id=u.id";
			User user = (User)qr.query(sql, id, new BeanHandler(User.class));
			order.setUser(user);
			
			return order;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取所有订单
	 * @return
	 */
	@Override
	public List<Order> getAll(boolean state)
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "SELECT * FROM TB_Order WHERE State=?";
			List<Order> list = qr.query(sql, state, new BeanListHandler(Order.class));
			for(Order order : list)
			{
				sql = "SELECT User.* FROM TB_Order o,TB_User u WHERE o.id=? and o.User_id=u.id";
				User user = (User)qr.query(sql, order.getId(), new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 更新
	 * @param order
	 */
	@Override
	public void update(Order order)
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "UPDATE TB_Order SET State=? WHERE id=?";
			Object params[] = {order.getState(), order.getId()};
			qr.update(sql, params);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 删除订单
	 */
	@Override
	public void delete(Order order)
	{
		try
		{
			/* 先将外键断开 */
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "UPDATE tb_order SET User_id=null,Schedule_id=null where id=?";
			qr.update(sql, order.getId());
			/* 再进行delete*/
			sql = "DELETE FROM tb_order WHERE id=?";
			qr.update(sql, order.getId());
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取订单的总记录数
	 */
	@Override
	public int getTotalRecord()
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "SELECT COUNT(*) FROM tb_order";
			long l = (Long)qr.query(sql, new ScalarHandler());
			return (int) l;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取未发货的总记录数
	 * @return
	 */
	@Override
	public int getUnsendRecord()
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "SELECT COUNT(*) FROM tb_order WHERE State='unsend'";
			long l = (Long)qr.query(sql, new ScalarHandler());
			return (int) l;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 获取订单分页数据
	 */
	@Override
	public List<Order> getAllOrderPageDate(int beginIndex, int everyPage,String state)
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "SELECT * FROM tb_order WHERE State=? limit ?,?";
			Object params[] = {state,beginIndex, everyPage};
			return (List<Order>)qr.query(sql, params,new BeanListHandler(Order.class));
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	
}
