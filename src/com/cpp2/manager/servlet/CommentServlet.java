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

import com.cpp2.domain.Comment;
import com.cpp2.service.impl.BusinessServiceImpl;

public class CommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}
		if("getComment".equals(method)){
			getComment(request,response);
		}
	}
	/**
	 * 获取电影的评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try{
			BusinessServiceImpl bs = new BusinessServiceImpl();
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int movie_id = Integer.parseInt(request.getParameter("movie_id"));
			int everyPage = 10;
			List<Comment> comments = bs.getCommentPageData(everyPage, currentPage, movie_id).getList();
			//得到数据之后进行json封装
			JSONArray jsonArray = new JSONArray();
			for(Comment comment : comments){
				//修改date的数据类型
				java.util.Date date = new java.util.Date(comment.getCommitTime().getTime());
				comment.setCommitTime(date);
				jsonArray.add(comment);
			}
			
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("comment.list", jsonArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get movie comments success");
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
	 * 增加评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			BusinessServiceImpl bs = new BusinessServiceImpl();
			int movie_id = Integer.parseInt(request.getParameter("movie_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String content = request.getParameter("content");
			Date commitTime = new Date();
			Comment comment = new Comment();
			comment.setCommitTime(commitTime);
			comment.setContent(content);
			comment.setMovie_id(movie_id);
			comment.setUser_id(user_id);
			bs.addComment(comment);
			
			
			//操作成功放回结果
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "comment success!");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			//操作失败放回结果
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "0");
			topMap.put("message", "comment fall!");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
		}
		
		
	}

}
