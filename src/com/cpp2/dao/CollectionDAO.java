package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Collection;
import com.cpp2.domain.Movie;

public interface CollectionDAO {

	/**
	 * �ղص�Ӱ
	 * 
	 * @param movie_id
	 * @param user_id
	 */
	public abstract void collectMovie(Collection collection);

	/**
	 * �����û��ղص����е�Ӱ
	 * @param user_id
	 * @return
	 */
	public abstract List<Collection> showCollection(int user_id,int beginIndex,int everyPage);

	/**
	 * ��ȡ�û��ղص�Ӱ���ܼ�¼��
	 * @param user_id
	 * @return
	 */
	public abstract int getTotalRecord(int user_id);
	
	
	/**
	 * ����Ǹõ�Ӱ�Ƿ��Ѿ��ղ�
	 * @param movie_id
	 * @return
	 */
	public abstract boolean checkCollection(int movie_id,int user_id);
}