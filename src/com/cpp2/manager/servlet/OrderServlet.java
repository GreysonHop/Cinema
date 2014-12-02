package com.cpp2.manager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpp2.domain.Order;
import com.cpp2.domain.OrderItem;
import com.cpp2.service.impl.BusinessServiceImpl;
import com.cpp2.utils.Page;
import com.cpp2.utils.Result;

public class OrderServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/* 根据url的参数处理 */
		String method = request.getParameter("method");
		if("showUnsend".equals(method))				// 显示未发货的订单
		{
			showUnsend(request, response);
		}
		else if("showSend".equals(method))
		{
			showSend(request, response);
		}
		else if("detail".equals(method))					// 订单详情
		{
			listOrderDetail(request, response);
		}
		else if("confirm".equals(method))				// 确认收货
		{
			confirm(request, response);
		}
		else if("delete".equals(method))				// 删除一个订单
		{
			delete(request, response);
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			/* 先获取订单ID */
			String orderID = request.getParameter("orderid");
			/* 业务逻辑处理: 从数据库里面删除数据 */
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.deleteOrder(orderID);
			/* 从新回到订单页面 */
			String state = (String) request.getParameter("state");
			System.out.println(state);
			if("showUnsend".equals(state))
			{
				showUnsend(request, response);
			}
			else			// 已发货
			{
				showSend(request, response);
			}
		} catch (Exception e)
		{
			/* 异常 */
			throw new RuntimeException(e);
		}
		
		
	}

	private void confirm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String orderid = request.getParameter("orderid");
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.confirmOrder(Integer.parseInt(orderid));							// 更改订单状态为已发货
			Order order = service.retrieveOrder(Integer.parseInt(orderid));
			OrderItem orderItem= (OrderItem) order.getOrderitem();
			int movieId = orderItem.getMovie().getId();
			int quantity = orderItem.getQuantity();
			service.updatePopularity(quantity, movieId);	//更新影片的热度
			request.setAttribute("msg", "状态改为发货,请及时发货");
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("msg", "确认失败.");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

	private void listOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			/* 获取url的参数 */
			String orderID = request.getParameter("orderid");
			/* 业务逻辑处理: 根据id查询到订单 */
			BusinessServiceImpl service = new BusinessServiceImpl();
			Order order = (Order)service.retrieveOrder(Integer.parseInt(orderID));
			
			request.setAttribute("order", order);
			request.getRequestDispatcher("/manager/listOrderDetail.jsp").forward(request, response);
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("msg", "sorry , 服务器正忙,无法查询");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		
		
	}

	private void showSend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			BusinessServiceImpl service = new BusinessServiceImpl();
			int currentPage = 0;
			int pageSize = 5;					// 每个页面显示的数据量
			/* 判断参数是否为空, 空则设置当前页面为第一页*/
			if(null == request.getParameter("currentPage"))
			{
				currentPage = 1;
			}
			else
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			/* 业务逻辑: 获取订单的分页数据,并保存到Result */
			Result result = service.getSendOrderPage(pageSize, currentPage);
			request.setAttribute("state", "已发货");
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/listOrder.jsp").forward(request, response);		// 请求转发
			
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("msg", "抱歉, 服务器正忙 (= =)");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);		// 请求转发
		}
	}

	private void showUnsend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			BusinessServiceImpl service = new BusinessServiceImpl();
			int currentPage = 0;
			int pageSize = 5;					// 每个页面显示的数据量
			/* 判断参数是否为空, 空则设置当前页面为第一页*/
			if(null == request.getParameter("currentPage"))
			{
				currentPage = 1;
			}
			else
			{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			/* 业务逻辑: 获取订单的分页数据,并保存到Result */
			Result result = service.getAllOrderPageData(currentPage, pageSize, "unsend");
			request.setAttribute("state", "未发货");
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/listOrder.jsp").forward(request, response);		// 请求转发
			
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("msg", "抱歉, 服务器正忙 (= =)");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);		// 请求转发
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
	}

}
