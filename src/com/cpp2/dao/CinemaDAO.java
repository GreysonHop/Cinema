package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Cinema;

public interface CinemaDAO {

	/**
	 * 获取所有的影院信息
	 * @return
	 */
	public abstract List<Cinema> getAll();

	/**
	 * 根据id查找影院
	 * @param id
	 * @return
	 */
	public abstract Cinema findCinemaById(int id);
	/**
	 * 根据影院的名字寻找影院
	 * @param name
	 * @return
	 */
	public abstract Cinema findCinemaByName(String name);

}