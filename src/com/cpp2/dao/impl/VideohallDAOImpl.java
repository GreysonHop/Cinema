package com.cpp2.dao.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cpp2.dao.VideohallDAO;
import com.cpp2.domain.Videohall;
import com.cpp2.utils.JDBCUtils;

public class VideohallDAOImpl implements VideohallDAO {
	/**
	 * 查询所有的影厅
	 * @return
	 */
	public List<Videohall> getAllVideohall(){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_videohall";
			return (List<Videohall>)qr.query(sql, new BeanListHandler<Videohall>(Videohall.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据影院的id查找该影院的所有影厅
	 * @param id
	 * @return
	 */
	public List<Videohall> getVideohallByCinemaId(int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_videohall where Cinema_id=?";
			return (List<Videohall> )qr.query(sql, id, new BeanListHandler(Videohall.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据影厅的名字
	 * @param name
	 * @return
	 */
	public Videohall findVideohallByC_idAndV_name(int cinema_id,String name){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_videohall where Cinema_id=? and name=?";
			Object params[] = {cinema_id,name};
			return (Videohall)qr.query(sql, params, new BeanHandler(Videohall.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
