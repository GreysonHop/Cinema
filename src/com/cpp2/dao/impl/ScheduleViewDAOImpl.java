package com.cpp2.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cpp2.dao.ScheduleViewDAO;
import com.cpp2.domain.Schedule;
import com.cpp2.domain.ScheduleView;
import com.cpp2.utils.JDBCUtils;

public class ScheduleViewDAOImpl implements ScheduleViewDAO {
	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ
	 * @return
	 */
	public List<ScheduleView> getAll(){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from v_scheduleview";
			return (List<ScheduleView>)qr.query(sql, new BeanListHandler(ScheduleView.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ������ͼ������ӰƬid��������
	 * @param id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieId(int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from v_scheduleview where movie_id=?";
			return (List<ScheduleView>)qr.query(sql, id, new BeanListHandler(ScheduleView.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ������ͼ����ѯָ����ӰԺָ����ӰƬ���������ڣ����ƶ���ʹ��
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieIdAndCinemaId(int movie_id,int cinema_id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from v_scheduleview where movie_id=? and cinema_id=?";
			Object params[] = {movie_id,cinema_id};
			return (List<ScheduleView>)qr.query(sql, params, new BeanListHandler(ScheduleView.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ�ķ�ҳ����
	 * @return
	 */
	public List<ScheduleView> getAllScheduleViewPageData(int beginIndex,int everyPage){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from v_scheduleview limit ?,?";
			Object params[] = {beginIndex,everyPage};
			return (List<ScheduleView>)qr.query(sql, params, new BeanListHandler(ScheduleView.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��ȡ������ͼ���ܼ�¼��
	 * @return
	 */
	public int getScheudleViewTotalRecord(){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql ="select count(*) from v_scheduleview";
			long l = (Long)qr.query(sql, new ScalarHandler());
			return (int)l;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
