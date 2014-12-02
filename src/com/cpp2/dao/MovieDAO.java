package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Movie;

public interface MovieDAO {

	/**
	 * ���һ��ӰƬ
	 * @param movie
	 */
	public abstract void create(Movie movie);

	/**
	 * ɾ��һ��ӰƬ
	 * @param id
	 */
	public abstract void delete(int id);

	/**
	 * �ָ���ɾ���ĵ�Ӱ
	 * @param id
	 */
	public abstract void restore(int id);

	/**
	 * ����ӰƬ��Ϣ
	 * @param movie
	 */
	public abstract void update(Movie movie);

	/**
	 * ����_id����ӰƬ
	 * @param id
	 * @return
	 */
	public abstract Movie retrieve(int id);

	/**
	 * ��ѯ���ݿ��е�Ӱ���ܼ�¼��
	 * @return
	 */
	public abstract int getAllMovieTotalRecord();

	/**
	 * ��ѯ������ӳ�ĵ�Ӱ�����ݿ��е��ܼ�¼��
	 * @return
	 */
	public abstract int getOnNowMovieTotalRecord();

	/**
	 * ��ѯ������ӳ�ĵ�Ӱ�����ݿ��е��ܼ�¼��
	 * @return
	 */
	public abstract int getComingSoonMovieTotalRecord();

	/**
	 * ��ѯ����Ӱ�ķ�ҳ����
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getMoviePageData(int beginIndex, int everyPage);

	/**
	 * ��ѯ��������ӳ��Ӱ�ķ�ҳ����
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getOnNowMoviePageData(int beginIndex,
			int everyPage);

	/**
	 * ��ѯ��������ӳ��Ӱ�ķ�ҳ����
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getComingSoonMoviePageData(int beginIndex,
			int everyPage);

	/**
	 * �޸�ӰƬͼƬ
	 * @param image
	 * @param id
	 */
	public abstract void changeImage(String image, int id);

	/**
	 * �鿴����������ӳ�ĵ�Ӱ
	 * @return
	 */
	public abstract List<Movie> getAllOnNowMovie();

	/**
	 * �鿴���м�����ӳ�ĵ�Ӱ
	 * @return
	 */
	public abstract List<Movie> getAllComingSoonMovie();
	/**
	 * ����ӰƬ����Ѱ��ӰƬ
	 * @param name
	 * @return
	 */
	public abstract Movie findMovieByName(String name);
	
	/**
	 * ���µ�Ӱ�ȶ�
	 * @param popularity
	 * @param id
	 */
	public abstract void updatePopularity(double popularity,int id);
}