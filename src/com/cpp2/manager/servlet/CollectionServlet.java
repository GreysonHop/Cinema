package com.cpp2.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cpp2.domain.Collection;
import com.cpp2.domain.Comment;
import com.cpp2.domain.Movie;
import com.cpp2.service.impl.BusinessServiceImpl;

public class CollectionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("collectMovie".equals(method)){
			collectMovie(request,response);
		}
		if("showCollection".equals(method)){
			showCollcetion(request,response);
		}
	}

	/**
	 * 查看收藏的影片
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showCollcetion(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try{
			BusinessServiceImpl bs = new BusinessServiceImpl();
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int everyPage = 10;
			List<Collection> collections = bs.getAllCollection(user_id, everyPage, currentPage).getList();
			
			//得到数据之后进行json封装
			JSONArray jsonArray = new JSONArray();
			for(Collection collection: collections){
				//修改date的数据类型
				java.util.Date date = new java.util.Date(collection.getCollectionTime().getTime());
				collection.setCollectionTime(date);
				jsonArray.add(collection);
			}
			
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("collection.list", jsonArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get  collection success");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 收藏电影
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void collectMovie(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try{
			BusinessServiceImpl bs = new BusinessServiceImpl();
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int movie_id = Integer.parseInt(request.getParameter("movie_id"));
			Movie movie = bs.findMovie(movie_id);
			Collection collection = new Collection();
			collection.setCollectionTime(new Date());
			collection.setImage(movie.getImage());
			collection.setMoveiName(movie.getName());
			collection.setMovie_id(movie_id);
			collection.setUser_id(user_id);
			if(bs.checkCollection(movie_id,user_id)){
				
				bs.collectMovie(collection);
				//操作成功放回结果
				/*封装到结果集中*/
				Map<String, Object> resultMap = new HashMap<String, Object>();
				JSONObject resultObject =JSONObject.fromObject(resultMap);
				
				/*封装到顶层json*/
				Map<String, Object> topMap = new HashMap<String, Object>();
				topMap.put("code", "1");
				topMap.put("message", "collect success!");
				topMap.put("result", resultObject);
				JSONObject jsonObject = JSONObject.fromObject(topMap);
				
				/*写到客户端*/
				out.write(jsonObject.toString());
				out.flush();
			}else{
				//操作失败放回结果
				/*封装到结果集中*/
				Map<String, Object> resultMap = new HashMap<String, Object>();
				JSONObject resultObject =JSONObject.fromObject(resultMap);
				
				/*封装到顶层json*/
				Map<String, Object> topMap = new HashMap<String, Object>();
				topMap.put("code", "0");
				topMap.put("message", "you have collected this movie!");
				topMap.put("result", resultObject);
				JSONObject jsonObject = JSONObject.fromObject(topMap);
				
				/*写到客户端*/
				out.write(jsonObject.toString());
				out.flush();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			//操作失败放回结果
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "collect fall!");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
		}
	}

}
