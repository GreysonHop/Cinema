package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Comment;

public interface CommentDAO {

	/**
	 * 增加评论
	 * @param comment
	 */
	public abstract void addComment(Comment comment);

	/**
	 * 
	 * @param movie_id
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	public abstract List<Comment> showComment(int movie_id, int beginIndex,
			int everyPage);

	/**
	 * 获得该电影的
	 * @return
	 */
	public abstract int getTotalRecord(int movie_id);
}