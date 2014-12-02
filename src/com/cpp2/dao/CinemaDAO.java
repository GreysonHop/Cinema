package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Cinema;

public interface CinemaDAO {

	/**
	 * ��ȡ���е�ӰԺ��Ϣ
	 * @return
	 */
	public abstract List<Cinema> getAll();

	/**
	 * ����id����ӰԺ
	 * @param id
	 * @return
	 */
	public abstract Cinema findCinemaById(int id);
	/**
	 * ����ӰԺ������Ѱ��ӰԺ
	 * @param name
	 * @return
	 */
	public abstract Cinema findCinemaByName(String name);

}