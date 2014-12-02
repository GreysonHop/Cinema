package com.cpp2.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cpp2.dao.CollectionDAO;
import com.cpp2.domain.Collection;
import com.cpp2.domain.Movie;
import com.cpp2.utils.JDBCUtils;

public class CollectionDAOImpl implements CollectionDAO {

	/**
	 * 收藏电影
	 * @param movie_id
	 * @param user_id
	 */
	public void collectMovie(Collection collection) {
		try {
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "insert into tb_collection(movie_id,user_id,collectionTime,movieName,image) values(?,?,?,?,?)";
			Object params[] = { collection.getMovie_id(), collection.getUser_id() ,collection.getCollectionTime(),collection.getMoveiName(),collection.getImage()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回用户收藏的所有电影
	 * @param user_id
	 * @return
	 */
	public List<Collection> showCollection(int user_id,int beginIndex,int everyPage) {
		try {
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from  tb_collection where user_id=? order by collectiontime limit ?,?";
			Object params[] = {user_id,beginIndex,everyPage};
			return (List<Collection>)qr.query(sql, params, new BeanListHandler(Movie.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取用户收藏电影的总记录数
	 * @param user_id
	 * @return
	 */
	public int getTotalRecord(int user_id){
		try {
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select count(*) from tb_collection where user_id=?";
			long l =  qr.query(sql, user_id, new ScalarHandler());
			return (int)l;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	/**
	 * 检查是该电影是否已经收藏
	 * @param movie_id
	 * @return
	 */
	public boolean checkCollection(int movie_id , int user_id){
		try{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "SELECT count(*) from tb_collection WHERE movie_id=? and user_id=?";
			Object params[] = {movie_id,user_id};
			long l =  qr.query(sql, params, new ScalarHandler());
			if(l>0){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
