package com.cpp2.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cpp2.dao.CinemaDAO;
import com.cpp2.domain.Cinema;
import com.cpp2.utils.JDBCUtils;

public class CinemaDAOImpl implements CinemaDAO {
	/**
	 * 获取所有的影院信息
	 * @return
	 */
	public List<Cinema> getAll(){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_cinema";
			return (List<Cinema>)qr.query(sql, new BeanListHandler(Cinema.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id查找影院
	 * @param id
	 * @return
	 */
	public Cinema findCinemaById(int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_cinema where id=?";
			return (Cinema)qr.query(sql, id, new BeanHandler(Cinema.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据影院的名字寻找影院
	 * @param name
	 * @return
	 */
	public Cinema findCinemaByName(String name){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_cinema where name=?";
			return (Cinema)qr.query(sql, name, new BeanHandler(Cinema.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
