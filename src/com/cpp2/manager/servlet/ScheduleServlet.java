package com.cpp2.manager.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
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

import com.cpp2.domain.Cinema;
import com.cpp2.domain.Movie;
import com.cpp2.domain.Schedule;
import com.cpp2.domain.ScheduleView;
import com.cpp2.domain.Videohall;
import com.cpp2.service.impl.BusinessServiceImpl;
import com.cpp2.utils.Result;

public class ScheduleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  method = request.getParameter("method");
		if ("getInfo".equals(method)) {
			getInfo(request, response);
		}
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		if ("delete".equals(method)) {
			delete(request, response);
		}
		if ("showBack".equals(method)) {
			showBack(request, response);
		}
		if ("update".equals(method)){
			update(request, response);
		}
		if("getScheduleViewByMovieIdAndCinemaId".equals(method)){
			getScheduleViewByMovieIdAndCinemaId(request,response);
		}
	}




	/**
	 * �������֮ǰҪ���Ӱ����ӰԺ��ӰƬ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<Cinema> cinemas = businessService.getAllCinema();
			List<Videohall> videohalls = businessService.getAllVideohall();
			List<Movie> movies = businessService.getAllOnNowMovie();
			request.setAttribute("cinemas", cinemas);
			request.setAttribute("videohalls", videohalls);
			request.setAttribute("movies", movies);
			request.getRequestDispatcher("/manager/addSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��Ǹ��ϵͳ����");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * �������
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			//��ӰƬ��Ϣ�ҳ���
			String movieName = request.getParameter("movieName");
			Movie movie = businessService.findMovieByName(movieName);
			//��ӰԺ��Ϣ�ҳ���
			String cinemaName = request.getParameter("cinemaName");
			Cinema cinema = businessService.findCinemaByName(cinemaName); 
			//��Ӱ����Ϣ�ҳ���
			String videohallName = request.getParameter("videohallName");
			Videohall videohall = businessService.findVideohallByC_idAndV_name(cinema.getId(), videohallName);
			//��ӳʱ����Ҫ String TO Date
			String airTime_date= request.getParameter("airTime_date");
			String airTime_hour= request.getParameter("airTime_hour");
			String airTime_min= request.getParameter("airTime_min");
			String datetime = airTime_date+" "+airTime_hour+":"+airTime_min+":"+"00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date airTime =(Date)sdf.parse(datetime);
			//�����ݷ�װ��schedule
			Schedule schedule = new Schedule();
			schedule.setAirtime(airTime);
			schedule.setMovie_id(movie.getId());
			schedule.setVideoHall_id(videohall.getId());
			//���浽���ݿ�
			businessService.createSchedule(schedule);
			request.setAttribute("msg", "������ڳɹ���");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "���ʧ�ܣ������ԣ�");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	/**
	 * ��ȡ�������ڣ���ҳ��ʾ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();			
			//�����ҳ����
			int currentPage = 0;
			if(request.getParameter("currentPage")==null){
				currentPage = 1;
			}else{
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int everyPage = 5;
			Result result = businessService.getAllScheduleViewPageData(everyPage, currentPage);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/manager/showAllSchedule.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ���������ԣ�");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			int id = Integer.parseInt(request.getParameter("schedule_id"));
			businessService.deleteSchedule(id);
			request.setAttribute("msg", "ɾ���ɹ���");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "ɾ��ʧ�ܣ�ϵͳ����");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	/**
	 * ��������ǰ�����ݻ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			//���Ҫ���Ե����ڵ���Ϣ
			int id = Integer.parseInt(request.getParameter("schedule_id"));
			Schedule schedule = businessService.getScheduleById(id);
			Date date = schedule.getAirtime();
			String airTime_hour = String.valueOf(date.getHours());
			String airTime_min = String.valueOf(date.getMinutes());
			String airTime_year = String.valueOf(date.getYear()+1900);
			String airTime_month = String.valueOf(date.getMonth()+1);
			String airTime_day = String.valueOf(date.getDate());
			String airTime_date = airTime_year+"-"+airTime_month+"-"+airTime_day;
			request.setAttribute("schedule", schedule);
			request.setAttribute("airTime_hour", airTime_hour);
			request.setAttribute("airTime_min", airTime_min);
			request.setAttribute("airTime_date", airTime_date);
			//������ڱ�ѡ�������
			List<Cinema> cinemas = businessService.getAllCinema();
			List<Videohall> videohalls = businessService.getAllVideohall();
			List<Movie> movies = businessService.getAllOnNowMovie();
			request.setAttribute("cinemas", cinemas);
			request.setAttribute("videohalls", videohalls);
			request.setAttribute("movies", movies);
			request.getRequestDispatcher("/manager/showBackSchedule.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "ϵͳ����");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
	}
	/**
	 * ����������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			//��ӰƬ��Ϣ�ҳ���
			String movieName = request.getParameter("movieName");
			Movie movie = businessService.findMovieByName(movieName);
			//��ӰԺ��Ϣ�ҳ���
			String cinemaName = request.getParameter("cinemaName");
			Cinema cinema = businessService.findCinemaByName(cinemaName); 
			//��Ӱ����Ϣ�ҳ���
			String videohallName = request.getParameter("videohallName");
			Videohall videohall = businessService.findVideohallByC_idAndV_name(cinema.getId(), videohallName);
			//��ӳʱ����Ҫ String TO Date
			String airTime_date= request.getParameter("airTime_date");
			String airTime_hour= request.getParameter("airTime_hour");
			String airTime_min= request.getParameter("airTime_min");
			String datetime = airTime_date+" "+airTime_hour+":"+airTime_min+":"+"00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date airTime =(Date)sdf.parse(datetime);
			//�����ݷ�װ��schedule
			Schedule schedule = new Schedule();
			schedule.setAirtime(airTime);
			schedule.setMovie_id(movie.getId());
			schedule.setVideoHall_id(videohall.getId());
			//����
			businessService.updateSchedule(schedule,Integer.parseInt(request.getParameter("id")));
			request.setAttribute("msg", "�޸ĳɹ���");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "�޸ĳɹ���");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	/**
	 * ����ӰԺid��ӰƬid������ڣ����ƶ���ʹ��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getScheduleViewByMovieIdAndCinemaId(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		//DataOutputStream out = new DataOutputStream(response.getOutputStream());
		PrintWriter out = response.getWriter();
		try{
			//��ȡ�������Ĳ���
			int movie_id = Integer.parseInt(request.getParameter("movie_id"));
			int cinema_id = Integer.parseInt(request.getParameter("cinema_id"));
			//��ȡ��������
			BusinessServiceImpl businessService = new BusinessServiceImpl();
			List<ScheduleView> list = businessService.getScheduleViewByMovieIdAndCinemaId(movie_id, cinema_id);
			//��װ��json
			JSONArray scheduleViewArray = new JSONArray();
			for(ScheduleView scheduleView:list){
				//�޸�date����������
				java.util.Date date = new java.util.Date(scheduleView.getAirtime().getTime());
				scheduleView.setAirtime(date);
				scheduleViewArray.add(scheduleView);
			}
			//��װ�������
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("scheduleView.list", scheduleViewArray);
			JSONObject resultObject = JSONObject.fromObject(resultMap);
			//��װ������json
			Map<String, Object> topMap = new HashMap<String, Object>();
			topMap.put("code", "1");
			topMap.put("message", "get all schedule success");
			topMap.put("result", resultObject);
			JSONObject jsonObject = JSONObject.fromObject(topMap);
			//д����
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
