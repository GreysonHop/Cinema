package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Schedule;

public interface ScheduleDAO {

	/**
	 * �½�����
	 * @param schedule
	 */
	public abstract void createSchedule(Schedule schedule);

	/**
	 * ɾ������
	 * @param id
	 */
	public abstract void deleteSchedule(int id);

	/**
	 * �޸�������Ϣ
	 * @param schedule
	 */
	public abstract void updateSchedule(Schedule schedule,int id);

	/**
	 * ����������������ݿ��е�����
	 * @return
	 */
	public abstract int getScheduleTotalRecord();

	/**
	 * �鿴��������
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	public abstract List<Schedule> getAllSchedulePageDate(int beginIndex,
			int everyPage);

	/**
	 * ���ݵ�Ӱid������������
	 * @param id
	 * @return
	 */
	public abstract List<Schedule> getScheduleByMovieId(int id);

	/**
	 * ���ݵ�ӰID��ӰƬID��������
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public abstract List<Schedule> getScheduleByMovieIdAndCinemaId(
			int movie_id, int cinema_id);

	/**
	 * ����ʣ��Ʊ��
	 * @param id
	 * @param num
	 */
	public abstract void updateRemanent(int id, int num);
	/**
	 * ��������id��������
	 * @param id
	 * @return
	 */
	public abstract Schedule getScheduleById(int id);
}