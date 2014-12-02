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
	 * �������ڵ�id���Ҹ����ڵ�������λ��Ϣ�����ƶ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllSeatByScheduleId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			//��ò���
			int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
			/*��ȡ����*/
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Seat> list = businessService.getAllSeatByScheduleId(schedule_id);
			
			/*��װ��json��*/
			JSONArray seatArray = new JSONArray();
			for(Seat seat : list){
				seatArray.add(seat);
			}
			
			/*��װ���������*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("seat.list", seatArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*��װ������json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get seats success");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*д���ͻ���*/
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
