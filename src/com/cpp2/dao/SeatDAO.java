package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Seat;

public interface SeatDAO {

	/**
	 * ��������idɾ�������ڵ�������λ
	 * @param schedule_id
	 */
	public abstract void deleteSeatBySchedule(int schedule_id);

	/**
	 * ��Ʊʱѡ����λ
	 * @param id
	 */
	public abstract void orderSeat(int id);
	/**
	 * ��������id���Ҹ����ڵ�������λ����
	 * @param schedule_id
	 * @return
	 */
	public abstract List<Seat> getAllSeatByScheduleId(int schedule_id);
}