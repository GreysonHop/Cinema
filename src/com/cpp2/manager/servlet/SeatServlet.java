package com.cpp2.manager.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cpp2.domain.Movie;
import com.cpp2.domain.Seat;
import com.cpp2.service.impl.BusinessServiceImpl;

public class SeatServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("getAllSeatByScheduleId".equals(method)){
			getAllSeatByScheduleId(request,response);
			System.out.println(123);
		}
	}
	/**
	 * 根据排期的id查找该排期的所有座位信息，给移动端
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllSeatByScheduleId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			//获得参数
			int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
			/*获取数据*/
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Seat> list = businessService.getAllSeatByScheduleId(schedule_id);
			
			/*封装到json中*/
			JSONArray seatArray = new JSONArray();
			for(Seat seat : list){
				seatArray.add(seat);
			}
			
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("seat.list", seatArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get seats success");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
			
		}catch(Exception e){
			e.printStackTrace();
			out.write(e.getMessage());
			out.flush();
		}
		finally{
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
