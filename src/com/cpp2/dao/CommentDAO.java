package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Comment;

public interface CommentDAO {

	/**
	 * ��������
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
	 * ��øõ�Ӱ��
	 * @return
	 */
	public abstract int getTotalRecord(int movie_id);
}