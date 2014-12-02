package com.cpp2.manager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理管理员退出系统
 * @author Rose
 */
public class LogoutServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/* 移除Session的admin标志 */
		request.getSession().removeAttribute("admin");
		
		/* 重定向 */
//		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); 						// 设置状态码为302
//		response.setHeader("Location", "http://localhost:8080/Cinema/manager/login.jsp");
		response.sendRedirect(request.getContextPath() + "/manager/login.jsp");
		
		/* 自动刷新 */
//		response.setHeader("Refresh", "1000;URL=http://localhost:8080/Cinema/manager/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
	}

}
