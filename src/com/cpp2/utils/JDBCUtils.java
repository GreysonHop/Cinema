package com.cpp2.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0数据库链接池工具类(单例设计模式)
 * @author Rose
 */
public class JDBCUtils
{
	private static ComboPooledDataSource cpds = null;
	
	static
	{
		cpds = new ComboPooledDataSource("rose");
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		return cpds.getConnection();
	}
	
	/**
	 * 获取链接池资源
	 * @return
	 */
	public static DataSource getDataSource()
	{
		return cpds;
	}
}
