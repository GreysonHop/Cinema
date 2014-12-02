package com.cpp2.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cpp2.dao.AdminDAO;
import com.cpp2.dao.CinemaDAO;
import com.cpp2.dao.CollectionDAO;
import com.cpp2.dao.CommentDAO;
import com.cpp2.dao.MovieDAO;
import com.cpp2.dao.OrderDAO;
import com.cpp2.dao.ScheduleDAO;
import com.cpp2.dao.ScheduleViewDAO;
import com.cpp2.dao.SeatDAO;
import com.cpp2.dao.UserDAO;
import com.cpp2.dao.VideohallDAO;
import com.cpp2.domain.Admin;
import com.cpp2.domain.Cart;
import com.cpp2.domain.CartItem;
import com.cpp2.domain.Cinema;
import com.cpp2.domain.Collection;
import com.cpp2.domain.Comment;
import com.cpp2.domain.Movie;
import com.cpp2.domain.Order;
import com.cpp2.domain.OrderItem;
import com.cpp2.domain.Schedule;
import com.cpp2.domain.ScheduleView;
import com.cpp2.domain.Seat;
import com.cpp2.domain.User;
import com.cpp2.domain.Videohall;
import com.cpp2.factory.DAOFactory;
import com.cpp2.utils.Page;
import com.cpp2.utils.PageUtil;
import com.cpp2.utils.Result;

/**
 * 业务逻辑处理类
 */
public class BusinessServiceImpl
{
	/* 使用工厂类生成DAO对象 */
	private AdminDAO aDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.AdminDAOImpl", AdminDAO.class);
	private UserDAO uDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.UserDAOImpl", UserDAO.class);
	private OrderDAO oDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.OrderDAOImpl", OrderDAO.class);
	private MovieDAO movieDAO =  DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.MovieDAOImpl", MovieDAO.class);
	private ScheduleDAO sDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.ScheduleDAOImpl", ScheduleDAO.class);
	private VideohallDAO vDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.VideohallDAOImpl", VideohallDAO.class);
	private CinemaDAO cDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.CinemaDAOImpl", CinemaDAO.class);
	private ScheduleViewDAO svDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.ScheduleViewDAOImpl", ScheduleViewDAO.class);
	private SeatDAO seatDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.SeatDAOImpl", SeatDAO.class);
	private CommentDAO commentDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.CommentDAOImpl", CommentDAO.class);
	private CollectionDAO collectionDAO = DAOFactory.getInstance().createDAO("com.cpp2.dao.impl.CollectionDAOImpl", CollectionDAO.class);
	
	/**
	 * 后台登陆处理,检查数据库是否存在该管理员
	 * @param admin
	 * @throws Exception
	 * @author Rose
	 */
	public Admin adminLogin(String username, String password) throws Exception
	{
		return aDAO.find(username, password);
	}

	/**
	 * 用户登录处理, 检查数据库是否存在该用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User userLogin(String username, String password)
	{
		return uDAO.retrieve(username, password);
	}
	
	/**
	 * 更新用户密码
	 * @author SevenLin
	 * @param newpassword
	 * @param id
	 */
	public void updatePassword(String newpassword,int id){
		uDAO.updatePassword(newpassword, id);
	}
	
	/**
	 * 获取所有用户
	 * @author SevenLin
	 * @return
	 */
	public List<User> getAllUser(){
		return uDAO.getAll();
	}
	
	/**
	 * User分页数据
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	public Result getAllUserPageData(int currentPage,int everyPage){
		Result result = new Result();
		int totalCount = uDAO.getTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		List<User> list = uDAO.getAllUserPageDate(page.getBeginIndex(), everyPage);
		result.setPage(page);
		result.setList(list);
		return result;
	}
	
	
	
	/**
	 * 添加一部电影
	 * @param movie
	 */
	public void addMovie(Movie movie){
		movieDAO.create(movie);
	}
	/**
	 * 删除一部电影
	 * @param id
	 */
	public void deleteMovie(int id){
		movieDAO.delete(id);
	}
	public void restoreMovie(int id){
		movieDAO.restore(id);
	}
	/**
	 * 根据id查找一部电影
	 * @param id
	 * @return
	 */
	public Movie findMovie(int id){
		return movieDAO.retrieve(id);
	}
	/**
	 * 修改电影详情
	 * @param movie
	 */
	public void changeMovieDetail(Movie movie){
		movieDAO.update(movie);
	}
	/**
	 * 获取所有电影的分页数据
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	public Result getAllMoviePageData(int currentPage,int everyPage){
		Result result = new Result();
		int totalCount = movieDAO.getAllMovieTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		List<Movie> list = movieDAO.getMoviePageData(page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	
	/**
	 * 获取正在热映的电影的分页数据
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	public Result getOnNowMoviePageData(int currentPage,int everyPage){
		Result result = new Result();
		int totalCount = movieDAO.getOnNowMovieTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		List<Movie> list = movieDAO.getOnNowMoviePageData(page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	/**
	 * 获取即将上映电影的分页数据
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	public Result getComingSoonMoviePageData(int currentPage,int everyPage){
		Result result = new Result();
		int totalCount = movieDAO.getComingSoonMovieTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		List<Movie> list = movieDAO.getComingSoonMoviePageData(page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	/**
	 * 修改影片的图片
	 * @param image
	 * @param id
	 */
	public void changeMovieImage(String image,int id){
		movieDAO.changeImage(image, id);
	}
	/**
	 * 获取所有正在热映的电影
	 * @return
	 */
	public List<Movie> getAllOnNowMovie(){
		return movieDAO.getAllOnNowMovie();
	}
	/**
	 * 获取所有即将上映的电影
	 * @return
	 */
	public List<Movie> getAllComingSoonMovie(){
		return movieDAO.getAllComingSoonMovie();
	}
	/**
	 * 新增订单
	 * @param cart
	 * @param user
	 */
	public void createOrder(Cart cart, User user)
	{
		if(cart == null)
		{
			throw  new  RuntimeException("sorry, you didn't buy  anything..");
		}
		Order order = new Order();
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setUser(user);
		order.setState("unsend");										// 未发货状态
		/* 将购物项添加到订单项 */
		for(Map.Entry<Integer, CartItem> me : cart.getMap().entrySet())
		{
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setMovie(citem.getMovie());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			order.getOrderitem().add(oitem);					// 将订单项添加到订单
		}
		/* 将订单添加到数据库 */
		oDAO.create(order);
	}

	/**
	 * 确认订单发货状态
	 * @param orderId
	 */
	public void confirmOrder(int orderId)
	{
		Order order = oDAO.retrieve(orderId);
		order.setState("send"); 										// 将订单状态置为已发货
		oDAO.update(order);
	}
	
	/**
	 * 列出未发货/已发货的订单
	 * @param state
	 * @return
	 */
	public List<Order> listOrder(boolean state)
	{
		return oDAO.getAll(state);
	}
	
	/**
	 * 根据订单id获取订单对象
	 * @param orderId
	 * @return
	 */
	public Order retrieveOrder(int orderId)
	{
		return oDAO.retrieve(orderId);
	}
	
	/**
	 * 往购物车添加一部电影
	 * @param cart
	 * @param movie
	 */
	public void buyTicket(Cart cart, Movie movie)
	{
		cart.create(movie);
	}
	
	/**
	 *  获得全部订单的分页数据
	 *  @author Rose
	 * @param currentPage
	 * @param everyPage
	 * @param state
	 * @return
	 */
	public Result getAllOrderPageData(int currentPage,int everyPage,String state){
		Result result = new Result();
		int totalCount = oDAO.getTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalCount, currentPage);
		List<Order> list = oDAO.getAllOrderPageDate(page.getBeginIndex(), everyPage,state);
		result.setPage(page);
		result.setList(list);
		return result;
	}
	
	/**
	 * 获得已发货的分页数据
	 * @author Rose
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public Result getSendOrderPage(int pageSize, int currentPage)
	{
		int sendCount = oDAO.getTotalRecord() - oDAO.getUnsendRecord();
		Result result = new Result();
		Page page = PageUtil.createPage(pageSize, sendCount, currentPage);
		List<Order> list = oDAO.getAllOrderPageDate(page.getBeginIndex(), pageSize, "send");
		result.setPage(page);
		result.setList(list);
		return result;
	}
	/**
	 * 新建排期
	 * @param schedule
	 */
	public void createSchedule(Schedule schedule){
		sDAO.createSchedule(schedule);
	}
	/**
	 * 删除排期
	 * @param id
	 */
	public void deleteSchedule(int id){
		sDAO.deleteSchedule(id);
	}
	/**
	 * 更新排期
	 * @param schedule
	 */
	public void updateSchedule(Schedule schedule,int id){
		sDAO.updateSchedule(schedule,id);
	}
	/**
	 * 获取排期的分页数据
	 * @param everyPage
	 * @param currentPage
	 * @return
	 */
	public Result getAllSchedulePageData(int everyPage,int currentPage){
		Result result = new Result();
		int totalRecord = sDAO.getScheduleTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalRecord, currentPage);
		List<Schedule> list = sDAO.getAllSchedulePageDate(page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	/**
	 * 根据电影id查找该影片的所有排期
	 * @param id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieId(int id){
		return sDAO.getScheduleByMovieId(id);
	}
	/**
	 * 根据影片的id和影院的id来查找排期
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieIdAndCinemaId(int movie_id,int cinema_id){
		return sDAO.getScheduleByMovieIdAndCinemaId(movie_id, cinema_id);
	}
	/**
	 * 更新剩余票数
	 * @param id
	 * @param num
	 */
	public void updateRemanent(int id ,int num){
		sDAO.updateRemanent(id, num);
	}

	/**
	 * 注册用户
	 * @param user
	 */
	public void register(User user)
	{
		/* 先扫描数据库, 检查是否已经存在该用户 */
		uDAO.create(user);
	}

	/**
	 * 根据用户的手机号码,修改密码
	 * @param username
	 * @param phone
	 * @param password
	 */
	public boolean forgotten(String username, String phone, String password)
	{
		/* 先通过username找到用户 */
		User user = uDAO.retrieve(username);
		/* 核对信息 */
		if(!user.getPhone().equals(phone))
		{
			return false;
		}
		/* 修改密码(update) */
		uDAO.updatePassword(password, user.getId());
		return true;
	}

	/**
	 * 根据电影ID找到电影
	 * @param movieid
	 * @return
	 */
	public Movie retrieveMovie(String movieid)
	{
		return movieDAO.retrieve(Integer.parseInt(movieid));
	}
	/**
	 * 查找所有的影院
	 * @return
	 */
	public List<Cinema> getAllCinema(){
		return cDAO.getAll();
	}
	/**
	 * 根据影院id查找影院
	 * @param id
	 * @return
	 */
	public Cinema findCinemaById(int id){
		return cDAO.findCinemaById(id);
	}
	/**
	 * 查找所哟影厅
	 * @return
	 */
	public List<Videohall> getAllVideohall(){
		return vDAO.getAllVideohall();
	}
	/**
	 * 根据影院查找该影院的所有影厅
	 * @param id
	 * @return
	 */
	public List<Videohall> getVideohallByCinemaId(int id){
		return vDAO.getVideohallByCinemaId(id);
	}
	/**
	 * 根据影片的名称寻找电影
	 * @param name
	 * @return
	 */
	public Movie findMovieByName(String name){
		return movieDAO.findMovieByName(name);
	}
	/**
	 * 根据影院的名字寻找影院
	 * @param name
	 * @return
	 */
	public Cinema findCinemaByName(String name){
		return cDAO.findCinemaByName(name);
	}
	/**
	 * 根据影厅的名字
	 * @param name
	 * @return
	 */
	public Videohall findVideohallByC_idAndV_name(int cinema_id,String name){
		return vDAO.findVideohallByC_idAndV_name(cinema_id, name);
	}
	/**
	 * 利用视图查询所有的排期以及一些信息
	 * @return
	 */
	public List<ScheduleView> getAllScheduleView(){
		return svDAO.getAll();
	}
	/**
	 * 利用视图，根据影片id查找排期
	 * @param id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieId(int id){
		return svDAO.getScheduleViewByMovieId(id);
	}
	/**
	 * 利用视图，查询指定的影院指定的影片的所有排期，给移动端使用
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieIdAndCinemaId(int movie_id,int cinema_id){
		return svDAO.getScheduleViewByMovieIdAndCinemaId(movie_id, cinema_id);
	}
	/**
	 * 利用视图查询所有的排期以及一些信息,分页
	 * @return
	 */
	public Result getAllScheduleViewPageData(int everyPage,int currentPage){
		Result result = new Result();
		int totalRecord = svDAO.getScheudleViewTotalRecord();
		Page page = PageUtil.createPage(everyPage, totalRecord, currentPage);
		List<ScheduleView> list = svDAO.getAllScheduleViewPageData(page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	/**
	 * 根据排期id查找排期
	 * @param id
	 * @return
	 */
	public Schedule getScheduleById(int id){
		return sDAO.getScheduleById(id);
	}

	/**
	 * 根据订单id,删除订单
	 * @param orderID
	 */
	public void deleteOrder(String orderID)
	{
		/* 先找到订单再删除 */
		Order order = oDAO.retrieve(Integer.parseInt(orderID));
		oDAO.delete(order);
	}
	/**
	 * 购票时选择座位,根据座位id跟新座位信息
	 * @param id
	 */
	public void orderSeat(int id){
		seatDAO.orderSeat(id);
	}
	/**
	 * 根据排期id查找该排期的所有座位详情,给移动端使用
	 * @param schedule_id
	 * @return
	 */
	public List<Seat> getAllSeatByScheduleId(int schedule_id){
		return seatDAO.getAllSeatByScheduleId(schedule_id);
	}
	/**
	 * 新增评论
	 * @param comment
	 */
	public void addComment(Comment comment){
		commentDAO.addComment(comment);
	}
	/**
	 * 获得影片的分页数据
	 * @param everyPage
	 * @param currentPage
	 * @param movie_id
	 * @return
	 */
	public Result getCommentPageData(int everyPage,int currentPage,int movie_id){
		Result result = new Result();
		int totalRecord = commentDAO.getTotalRecord(movie_id);
		Page page = PageUtil.createPage(everyPage, totalRecord, currentPage);
		List<Comment> list = commentDAO.showComment(movie_id, page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	
	/**
	 * 收藏电影
	 * @param movie_id
	 * @param user_id
	 */
	public void collectMovie(Collection collection) {
		collectionDAO.collectMovie(collection);
	}
	/**
	 * 获取用户收藏的所有电影
	 * @param user_id
	 * @return
	 */
	public Result getAllCollection(int user_id,int everyPage,int currentPage){
		Result result = new Result();
		int totalRecord = collectionDAO.getTotalRecord(user_id);
		Page page = PageUtil.createPage(everyPage, totalRecord, currentPage);
		List<Collection> list = collectionDAO.showCollection(user_id, page.getBeginIndex(), everyPage);
		result.setList(list);
		result.setPage(page);
		return result;
	}
	/**
	 * 检查电影是否已收藏
	 * @param movie_id
	 * @param user_id
	 * @return
	 */
	public boolean checkCollection(int movie_id,int user_id){
			return collectionDAO.checkCollection(movie_id,user_id);
	}
	/**
	 * 更新电影热度
	 * @param popularity
	 * @param id
	 */
	public void updatePopularity(int num,int id){
		double popularity = 0;
		int totalRecord = oDAO.getTotalRecord();
		popularity = 7.5 + ((int)(totalRecord+num)/20)*0.1;
		if(popularity>10){
			popularity=10.0;
		}
		movieDAO.updatePopularity(popularity, id);
	}
	}
