package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Collection;
import com.cpp2.domain.Movie;

public interface CollectionDAO {

	/**
	 * 收藏电影
	 * 
	 * @param movie_id
	 * @param user_id
	 */
	public abstract void collectMovie(Collection collection);

	/**
	 * 返回用户收藏的所有电影
	 * @param user_id
	 * @return
	 */
	public abstract List<Collection> showCollection(int user_id,int beginIndex,int everyPage);

	/**
	 * 获取用户收藏电影的总记录数
	 * @param user_id
	 * @return
	 */
	public abstract int getTotalRecord(int user_id);
	
	
	/**
	 * 检查是该电影是否已经收藏
	 * @param movie_id
	 * @return
	 */
	public abstract boolean checkCollection(int movie_id,int user_id);
}