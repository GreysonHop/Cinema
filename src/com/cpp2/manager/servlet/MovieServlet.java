package com.cpp2.manager.servlet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cpp2.domain.Movie;
import com.cpp2.service.impl.BusinessServiceImpl;
import com.cpp2.utils.Result;
import com.cpp2.utils.WebUtils;

public class MovieServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}
		if("showAll".equals(method)){
			showAll(request,response);
		}
		if("showMovieDetail".equals(method)){
			showMovieDetail(request,response);
		}
		if("showEditableMovieDetail".equals(method)){
			showEditableMovieDetail(request,response);
		}
		if("changeMovieDetail".equals(method)){
			changeMovieDetail(request,response);
		}
		if("changeImage".equals(method)){
			changeImage(request,response);
		}
		if("showOnNowMovie".equals(method)){
			showOnNowMovie(request,response);
		}
		if("showComingSoonMovie".equals(method)){
			showComingSoonMovie(request,response);
		}
		if("delete".equals(method)){
			delete(request,response);
		}
		if("restore".equals(method)){
			restore(request,response);
		}
		if("getOnNowMovieForMobile".equals(method)){
			getOnNowMovieForMobile(request,response);
		}
		if("getComingSoonMovieForMobile".equals(method)){
			getComingSoonMovieForMobile(request,response);
		}
		if("getMovieDetailForMobile".equals(method)){
			getMovieDetailForMobile(request,response);
		}
	}
	



	/**
	 * 添加影片
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Movie movie = doUpload(request);
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			businessService.addMovie(movie);
			request.setAttribute("msg", "影片添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "影片添加失败！");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	/**
	 * 对上传文件进行处理
	 * @param request
	 * @return
	 */
	private Movie doUpload(HttpServletRequest request) {
		ConvertUtils.register(new DateLocaleConverter(), Date.class);//注册日期装换器
		Movie movie = new Movie();
		String str = "";
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				//如果是普通输入项
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					if("type".equals(name)){
						str = str+value+",";
						value=str.substring(0,str.lastIndexOf(","));
					}
					BeanUtils.setProperty(movie, name, value);
				}else{//如果是图片
					String fileName = item.getName();
					String saveName = makeFileName(fileName);
					String savePath = this.getServletContext().getRealPath("/upload");
					//这里的file不是用来写文件，而是用来创建文件夹
					File saveFile = new File(new File(savePath),saveName);
					if(!saveFile.getParentFile().exists()){
						saveFile.getParentFile().mkdirs();
					}
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath+"\\"+saveName);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					movie.setImage(saveName);
				}
			}
			return movie;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 生成文件名称
	 * @param fileName
	 * @return
	 */
	private String makeFileName(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return WebUtils.makeName()+ext;
	}
	/**
	 * 查看所有影片,分页显示
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int currentPage = 0;
			if(request.getParameter("currentPage")==null){
				currentPage = 1;
			}else{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int everyPage = 5;
			Result result = businessService.getAllMoviePageData(currentPage, everyPage);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/showAllMovie.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "查询失败，请重试！");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * 查看影片详情
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showMovieDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			Movie movie = businessService.findMovie(id);
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("/manager/showMovieDetail.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "查看失败，请重试！");
		}
	}
	/**
	 * 修改影片信息的时候，回显电影信息
	 * @param request
	 * @param response
	 */
	private void showEditableMovieDetail(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			Movie movie = businessService.findMovie(id);
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("/manager/showEditableMovieDetail.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 修改影片信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void changeMovieDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			ConvertUtils.register(new DateLocaleConverter(), Date.class);//注册日期装换器
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			Movie movie = new Movie();
			Map map = request.getParameterMap();
			BeanUtils.populate(movie, map);
			String array[] = request.getParameterValues("type");
			String type="";
			if(array.length==1){
				type="";
			}else{
				for(int i = 0;i<array.length;i++){
					type = type+array[i]+",";
					type=type.substring(0,type.lastIndexOf(","));
				}
			}
			movie.setType(type);
			businessService.changeMovieDetail(movie);
			request.setAttribute("msg", "修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "修改失败，请重试！");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

	/**
	 * 修改影片图片
	 * @author SevenLin
	 * @param request
	 * @param response
	 */
	private void changeImage(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			//首先上传图片,返回图片名称
			String image = doUpload(request,response);
			//然后移除原来图片
			doRemove(request,response);
			//保存修改信息
			BusinessServiceImpl businessService = new BusinessServiceImpl(); 
			int id = Integer.parseInt(request.getParameter("id"));
			businessService.changeMovieImage(image, id);
			//回到原来页面
			request.setAttribute("movie", businessService.findMovie(id));
			request.setAttribute("changeImageResult", "success");
			request.getRequestDispatcher("/manager/showEditableMovieDetail.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "修改失败请重试！");
			request.getRequestDispatcher("/msg.jsp");
		}
	}
	
	/**
	 * 处理修改图片时的图片上传
	 * @param request
	 * @param response
	 * @return
	 */
	private String doUpload(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);//对表单进行解析
			String saveName="";
			for(FileItem item : list ){
				if(!item.isFormField()){//如果不是普通输入项，就是图片
					String fileName = item.getName();
					saveName= makeFileName(fileName);
					String savePath = this.getServletContext().getRealPath("\\upload");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath+"\\"+saveName);
					int len = 0;
					byte [] buffer = new byte[1024];
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
					item.delete();
				}
			}
			return saveName;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 移除原来的图片
	 * @param request
	 * @param response
	 */
	private void doRemove(HttpServletRequest request,
			HttpServletResponse response) {
		String oldImageName = request.getParameter("imageName");
		String savePath = this.getServletContext().getRealPath("/upload");
		String realPath = savePath + "\\" + oldImageName;
		File file = new File(realPath);
		file.delete();
	}

	/**
	 * 查看正在热映的电影，并分页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showOnNowMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int currentPage = 0;
			if(request.getParameter("currentPage")==null){
				currentPage = 1;
			}else{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int everyPage = 5;
			Result result = businessService.getOnNowMoviePageData(currentPage, everyPage);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/showAllOnNowMovie.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "查询失败，请重试！");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * 查看即将上映的电影
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showComingSoonMovie(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int currentPage = 0;
			if(request.getParameter("currentPage")==null){
				currentPage = 1;
			}else{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int everyPage = 5;
			Result result = businessService.getComingSoonMoviePageData(currentPage, everyPage);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/showAllComingSoonMovie.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "查询失败，请重试！");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * 根据id删除影片
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			businessService.deleteMovie(id);
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>self.location=document.referrer; </script>");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 恢复已删除的电影
	 * @param request
	 * @param response
	 */
	private void restore(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			businessService.restoreMovie(id);
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>self.location=document.referrer; </script>");
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * 提供正在热映的影片数据给移动端
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getOnNowMovieForMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			/*获取数据*/
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Movie> list = businessService.getAllOnNowMovie();
			
			/*封装到json中*/
			JSONArray movieArray = new JSONArray();
			for(Movie movie : list){
				//修改date的数据类型
				java.util.Date date = new java.util.Date(movie.getShowtime().getTime());
				
				//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				//java.util.Date date=df.parse(df.format(movie.getShowtime().getTime()));
				movie.setShowtime(date);
				//System.out.print(movie.getShowtime().toLocaleString());
				movieArray.add(movie);
			};
			
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("movie.list", movieArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get all OnNowMovies success");
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
	
	/**
	 * 获取所有即将上映的电影
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getComingSoonMovieForMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			
			/*获取数据*/
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Movie> list = businessService.getAllComingSoonMovie();
			
			/*封装到json中*/
			JSONArray movieArray = new JSONArray();
			//movieArray.fromObject(list.toArray());
			for(Movie movie : list){
				//修改date的数据类型
				java.util.Date date = new java.util.Date(movie.getShowtime().getTime());
				movie.setShowtime(date);
				movieArray.add(movie);
			}
			
			/*封装到结果集中*/
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("movie.list", movieArray);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json*/
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get all ComingSoonMovies success");
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
	/**
	 * 获取电影详情给移动端
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getMovieDetailForMobile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			/*获取移动端传过来的数据*/
			int id = Integer.parseInt(request.getParameter("id"));
			
			/*查询出要发送的数据*/
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			Movie movie = businessService.findMovie(id);
			//修改date的类型
			java.util.Date date = new java.util.Date(movie.getShowtime().getTime());
			movie.setShowtime(date);
			/*封装数据到json*/
			JSONObject movieJson = JSONObject.fromObject(movie);
			
			/*封装到结果集中*/
			Map<String , Object> resultMap = new HashMap<String, Object>();
			resultMap.put("movie", movieJson);
			JSONObject resultObject =JSONObject.fromObject(resultMap);
			
			/*封装到顶层json中*/
			Map<String , Object> topMap = new HashMap<String, Object>();
			topMap.put("code", 1);
			topMap.put("message", "get Movei Detail success");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			
			/*写到客户端*/
			out.write(jsonObject.toString());
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
			out.write(e.getMessage());
			out.flush();
		}finally{
			out.close();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
