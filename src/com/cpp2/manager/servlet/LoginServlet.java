package com.cpp2.manager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpp2.domain.Admin;
import com.cpp2.exception.LoginException;
import com.cpp2.service.impl.BusinessServiceImpl;

/**
 * 处理登陆的servlet
 * @author Rose
 */
public class LoginServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			/* 1.获取页面post过来的数据 */
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			/* 2.业务逻辑处理,检查数据库是否存在该管理员*/
			BusinessServiceImpl service = new BusinessServiceImpl();
			Admin admin = service.adminLogin(username, password);
			
			/* 3. 不存在则回到登陆页面 */
			if(null == admin)
			{
				request.setAttribute("message", "帐号或密码错误");
				System.out.println("登陆失败");
				request.getRequestDispatcher("/manager/login.jsp").forward(request, response);
				return ;
			}
			/* 4.若存在则跳转到后台首页 */
			System.out.println("登陆成功");
			request.getSession().setAttribute("admin", admin); 							// 登陆标志
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new LoginException(e);
		}
		
		
	}

}
