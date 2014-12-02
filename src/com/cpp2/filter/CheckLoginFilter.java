package com.cpp2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpp2.domain.Admin;

/**
 * 登陆检查过滤器
 * @author Rose
 */
public class CheckLoginFilter implements Filter
{

	@Override
	public void destroy()
	{
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		/* 检查是否登陆,若没登陆,跳转到登陆页面 */
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(null == admin)
		{
			request.setAttribute("messag", "请先登陆再操作");
			request.getRequestDispatcher("/manager/login.jsp").forward(request, response);
			return ;
		}
		
		/* 若已经登陆 */
		request.removeAttribute("message");
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
	}

}
