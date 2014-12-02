package com.cpp2.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cpp2.dao.AdminDAO;
import com.cpp2.domain.Admin;
import com.cpp2.exception.DBNotFoundException;
import com.cpp2.utils.JDBCUtils;

/**
 * 管理员DAO实现类
 * @author Rose
 */
public class AdminDAOImpl implements AdminDAO
{
	/**
	 * 根据帐号密码在数据库中查找是否存在管理员
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public Admin find(String username, String password)
	{
		try
		{
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from TB_Admin where AdminName=? and Password=?";
			Object params[] = {username, password};
			Admin admin = (Admin)qr.query(sql, params, new BeanHandler(Admin.class));
			
			return admin;

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
