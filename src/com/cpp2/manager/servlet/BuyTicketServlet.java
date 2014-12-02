package com.cpp2.manager.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.cpp2.domain.Cart;
import com.cpp2.domain.Movie;
import com.cpp2.domain.User;
import com.cpp2.service.impl.BusinessServiceImpl;

public class BuyTicketServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try
		{
			/* 获取移动端get过来的数据 */
			String movieid = request.getParameter("movieid");
			String ScheduleID = request.getParameter("ScheduleID");
			String seatID = request.getParameter("seatID");
			/* 业务逻辑处理: 查找到该电影 */
			BusinessServiceImpl service = new BusinessServiceImpl();
			Movie movie = service.retrieveMovie(movieid);
			service.updateRemanent(Integer.parseInt(ScheduleID), 1); 		// 更新剩余票数
			service.orderSeat(Integer.parseInt(seatID));    							// 更新座位信息
			/* 看看Session有没有购物车,没有就new 一个 */
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(null == cart)
			{
				cart = new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			service.buyTicket(cart,movie);
			
			/* 生成订单 */
			User user = (User)request.getSession().getAttribute("user");
			if(null != user)
			{
				service.createOrder(cart, user);
			}
			/* 返回json数据给用户  */
			
			/* 处理成功,返回json数据给移动端 */
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "buy success");
			topMap.put("result", "");
			JSONObject jsonArray = JSONObject.fromObject(topMap);

			/* 输出 */
			out.write(jsonArray.toString());
			
		} catch (Exception e)
		{
			e.printStackTrace();
			/* 处理成功,返回json数据给移动端 */
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "0");
			topMap.put("message", "failed to buy");
			topMap.put("result", "");
			JSONObject jsonArray = JSONObject.fromObject(topMap);

			/* 输出 */
			out.write(jsonArray.toString());
		}
		finally
		{
			out.close();
		}		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
