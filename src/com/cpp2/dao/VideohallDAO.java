package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Videohall;

public interface VideohallDAO {

	/**
	 * 查询所有的影厅
	 * @return
	 */
	public abstract List<Videohall> getAllVideohall();

	/**
	 * 根据影院的id查找该影院的所有影厅
	 * @param id
	 * @return
	 */
	public abstract List<Videohall> getVideohallByCinemaId(int id);
	/**
	 * 根据影厅的名字
	 * @param name
	 * @return
	 */
	public Videohall findVideohallByC_idAndV_name(int cinema_id,String name);
}