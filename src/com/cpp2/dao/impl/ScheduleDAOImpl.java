package com.cpp2.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;




import com.cpp2.dao.ScheduleDAO;
import com.cpp2.dao.SeatDAO;
import com.cpp2.domain.Schedule;
import com.cpp2.utils.JDBCUtils;

public class ScheduleDAOImpl implements ScheduleDAO {
	/**
	 * �½�����
	 * @param schedule
	 */
	public void createSchedule(Schedule schedule){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "insert into tb_schedule(Airtime,VideoHall_id,Movie_id) values(?,?,?)";
			Object params[] = {schedule.getAirtime(),schedule.getVideoHall_id(),schedule.getMovie_id()};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ɾ������
	 * @param id
	 */
	public void deleteSchedule(int id){
		try{
			/*����ɾ�������ڵ���λ��*/
			SeatDAO seatDAO = new SeatDAOImpl();
			seatDAO.deleteSeatBySchedule(id);
			/*Ȼ��ſ���ɾ��������*/
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "delete from tb_schedule where id=?";
			qr.update(sql, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * �޸�������Ϣ
	 * @param schedule
	 */
	public void updateSchedule(Schedule schedule,int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "update tb_schedule set Airtime=?,VideoHall_id=?,Movie_id=? where id=?";
			Object params[] = {schedule.getAirtime(),schedule.getVideoHall_id(),schedule.getMovie_id(),id};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����������������ݿ��е�����
	 * @return
	 */
	public int getScheduleTotalRecord(){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select count(*) from tb_schedule";
			long l = qr.query(sql, new ScalarHandler());
			return (int) l;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * �鿴��������
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	public List<Schedule> getAllSchedulePageDate(int beginIndex,int everyPage){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_schedule order by Airtime limit ?,?";
			Object params[] = {beginIndex,everyPage};
			return (List<Schedule>)qr.query(sql, params, new BeanListHandler(Schedule.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���ݵ�Ӱid������������
	 * @param id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieId(int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_schedule  where Movie_id=? order by Airtime";
			return (List<Schedule>)qr.query(sql, id, new BeanListHandler(Schedule.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���ݵ�ӰID��ӰƬID��������
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieIdAndCinemaId(int movie_id,int cinema_id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select tb_schedule.* from tb_schedule,tb_videohall,tb_cinema where  VideoHall_id=tb_videohall.id and tb_videohall.Cinema_id=tb_cinema.id and Movie_id=? and tb_cinema.id=?";
			Object params[] = {movie_id,cinema_id};
			return (List<Schedule>)qr.query(sql, params, new BeanListHandler(Schedule.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����ʣ��Ʊ��
	 * @param id
	 * @param num
	 */
	public void updateRemanent(int id ,int num){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select remanent from tb_schedule where id=?";
			long l = qr.query(sql, new ScalarHandler());
			int remanent = (int)l-num;
			sql = "update  tb_schedule set Remanent=? where id=?";
			Object params[] = {remanent,id};
			qr.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��������id��������
	 * @param id
	 * @return
	 */
	public Schedule getScheduleById(int id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from tb_schedule  where id=?";
			return (Schedule)qr.query(sql, id, new BeanHandler(Schedule.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
