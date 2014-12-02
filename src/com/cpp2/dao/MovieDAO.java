package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.Movie;

public interface MovieDAO {

	/**
	 * 添加一部影片
	 * @param movie
	 */
	public abstract void create(Movie movie);

	/**
	 * 删除一部影片
	 * @param id
	 */
	public abstract void delete(int id);

	/**
	 * 恢复已删除的电影
	 * @param id
	 */
	public abstract void restore(int id);

	/**
	 * 更新影片信息
	 * @param movie
	 */
	public abstract void update(Movie movie);

	/**
	 * 根据_id检索影片
	 * @param id
	 * @return
	 */
	public abstract Movie retrieve(int id);

	/**
	 * 查询数据库中电影的总记录数
	 * @return
	 */
	public abstract int getAllMovieTotalRecord();

	/**
	 * 查询正在热映的电影在数据库中的总记录数
	 * @return
	 */
	public abstract int getOnNowMovieTotalRecord();

	/**
	 * 查询即将上映的电影在数据库中的总记录数
	 * @return
	 */
	public abstract int getComingSoonMovieTotalRecord();

	/**
	 * 查询出电影的分页数据
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getMoviePageData(int beginIndex, int everyPage);

	/**
	 * 查询出正在热映电影的分页数据
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getOnNowMoviePageData(int beginIndex,
			int everyPage);

	/**
	 * 查询出即将上映电影的分页数据
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public abstract List<Movie> getComingSoonMoviePageData(int beginIndex,
			int everyPage);

	/**
	 * 修改影片图片
	 * @param image
	 * @param id
	 */
	public abstract void changeImage(String image, int id);

	/**
	 * 查看所有正在热映的电影
	 * @return
	 */
	public abstract List<Movie> getAllOnNowMovie();

	/**
	 * 查看所有即将上映的电影
	 * @return
	 */
	public abstract List<Movie> getAllComingSoonMovie();
	/**
	 * 根据影片名字寻找影片
	 * @param name
	 * @return
	 */
	public abstract Movie findMovieByName(String name);
	
	/**
	 * 更新电影热度
	 * @param popularity
	 * @param id
	 */
	public abstract void updatePopularity(double popularity,int id);
}