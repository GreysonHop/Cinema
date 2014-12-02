package com.cpp2.dao;

import com.cpp2.domain.Admin;

public interface AdminDAO
{

	/**
	 * 根据帐号密码在数据库中查找是否存在管理员
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	Admin find(String username, String password);

}