package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Videohall;

public interface VideohallDAO {

	/**
	 * ��ѯ���е�Ӱ��
	 * @return
	 */
	public abstract List<Videohall> getAllVideohall();

	/**
	 * ����ӰԺ��id���Ҹ�ӰԺ������Ӱ��
	 * @param id
	 * @return
	 */
	public abstract List<Videohall> getVideohallByCinemaId(int id);
	/**
	 * ����Ӱ��������
	 * @param name
	 * @return
	 */
	public Videohall findVideohallByC_idAndV_name(int cinema_id,String name);
}