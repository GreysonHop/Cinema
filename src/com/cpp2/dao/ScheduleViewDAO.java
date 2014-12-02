package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.ScheduleView;

public interface ScheduleViewDAO {

	/**
	 * 利用视图查询所有的排期以及一些信息
	 * @return
	 */
	public abstract List<ScheduleView> getAll();

	/**
	 * 利用视图，根据影片id查找排期
	 * @param id
	 * @return
	 */
	public abstract List<ScheduleView> getScheduleViewByMovieId(int id);

	/**
	 * 利用视图，查询指定的影院指定的影片的所有排期，给移动端使用
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public abstract List<ScheduleView> getScheduleViewByMovieIdAndCinemaId(
			int movie_id, int cinema_id);
	/**
	 * 利用视图查询所有的排期以及一些信息的分页数据
	 * @return
	 */
	public abstract List<ScheduleView> getAllScheduleViewPageData(int beginIndex,int everyPage);
	/**
	 * 获取排期视图的总记录数
	 * @return
	 */
	public abstract int getScheudleViewTotalRecord();
}