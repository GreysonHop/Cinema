package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Seat;

public interface SeatDAO {

	/**
	 * 根据排期id删除该排期的所有座位
	 * @param schedule_id
	 */
	public abstract void deleteSeatBySchedule(int schedule_id);

	/**
	 * 购票时选择座位
	 * @param id
	 */
	public abstract void orderSeat(int id);
	/**
	 * 根据排期id查找该排期的所有座位详情
	 * @param schedule_id
	 * @return
	 */
	public abstract List<Seat> getAllSeatByScheduleId(int schedule_id);
}