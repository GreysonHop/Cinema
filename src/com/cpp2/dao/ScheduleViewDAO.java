package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.ScheduleView;

public interface ScheduleViewDAO {

	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ
	 * @return
	 */
	public abstract List<ScheduleView> getAll();

	/**
	 * ������ͼ������ӰƬid��������
	 * @param id
	 * @return
	 */
	public abstract List<ScheduleView> getScheduleViewByMovieId(int id);

	/**
	 * ������ͼ����ѯָ����ӰԺָ����ӰƬ���������ڣ����ƶ���ʹ��
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public abstract List<ScheduleView> getScheduleViewByMovieIdAndCinemaId(
			int movie_id, int cinema_id);
	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ�ķ�ҳ����
	 * @return
	 */
	public abstract List<ScheduleView> getAllScheduleViewPageData(int beginIndex,int everyPage);
	/**
	 * ��ȡ������ͼ���ܼ�¼��
	 * @return
	 */
	public abstract int getScheudleViewTotalRecord();
}