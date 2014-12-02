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

import com.cpp2.domain.Cinema;
import com.cpp2.service.impl.BusinessServiceImpl;

public class CinemaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("getAllCinemaForMobile".equals(method)){
			getAllCinemaForMobile(request,response);
		}
	}

	/**
	 * ��ȡ���е�ӰԺ���ƶ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllCinemaForMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			//��ȡ����
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Cinema> list = businessService.getAllCinema();
			//��װ��json
			JSONArray cinemaArray = new JSONArray();
			for(Cinema cinema : list){
				cinemaArray.add(cinema);
			}
			//��װ�������
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("cinema.list", cinemaArray);
			JSONObject resultObject = JSONObject.fromObject(resultMap);
			//��װ��������
			Map<String , Object> topMap  = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get all cinema success!");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			//д���ݵ�
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
