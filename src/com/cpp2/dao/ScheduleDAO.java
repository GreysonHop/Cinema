package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Schedule;

public interface ScheduleDAO {

	/**
	 * 新建排期
	 * @param schedule
	 */
	public abstract void createSchedule(Schedule schedule);

	/**
	 * 删除排期
	 * @param id
	 */
	public abstract void deleteSchedule(int id);

	/**
	 * 修改排期信息
	 * @param schedule
	 */
	public abstract void updateSchedule(Schedule schedule,int id);

	/**
	 * 获得所有排期在数据库中的数据
	 * @return
	 */
	public abstract int getScheduleTotalRecord();

	/**
	 * 查看所有排期
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	public abstract List<Schedule> getAllSchedulePageDate(int beginIndex,
			int everyPage);

	/**
	 * 根据电影id查找所有排期
	 * @param id
	 * @return
	 */
	public abstract List<Schedule> getScheduleByMovieId(int id);

	/**
	 * 根据电影ID和影片ID查找排期
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public abstract List<Schedule> getScheduleByMovieIdAndCinemaId(
			int movie_id, int cinema_id);

	/**
	 * 跟新剩余票数
	 * @param id
	 * @param num
	 */
	public abstract void updateRemanent(int id, int num);
	/**
	 * 根据排期id查找排期
	 * @param id
	 * @return
	 */
	public abstract Schedule getScheduleById(int id);
}