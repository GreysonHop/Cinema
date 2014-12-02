package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.User;

public interface UserDAO
{

	/**
	 * 向tb_user 增加一条记录
	 * @param user
	 */
	void create(User user);

	/**
	 * 根据id删除tb_user的一条记录
	 * @param id
	 */
	void delete(int id);

	/**
	 * modify tb_user
	 * @prama user
	 */
	void update(User user);

	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	User retrieve(int id);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User retrieve(String username, String password);

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User retrieve(String username);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getAll();

	void updatePassword(String newpassword, int id);

	List<User> getAllUserPageDate(int beginIndex, int everyPage);

	int getTotalRecord();

	// 用户名获取
	User retrieveFromUsername(String username, String password);

	// 邮箱名获取
	User retrieveFromEmail(String email, String password);

	// 手机号获取
	User retrieveFromPhone(String phone, String password);
}