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
 * ҵ���߼�������
 */
public class BusinessServiceImpl
{
	/* ʹ�ù���������DAO���� */
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
	 * ��̨��½����,������ݿ��Ƿ���ڸù���Ա
	 * @param admin
	 * @throws Exception
	 * @author Rose
	 */
	public Admin adminLogin(String username, String password) throws Exception
	{
		return aDAO.find(username, password);
	}

	/**
	 * �û���¼����, ������ݿ��Ƿ���ڸ��û�
	 * @param username
	 * @param password
	 * @return
	 */
	public User userLogin(String username, String password)
	{
		return uDAO.retrieve(username, password);
	}
	
	/**
	 * �����û�����
	 * @author SevenLin
	 * @param newpassword
	 * @param id
	 */
	public void updatePassword(String newpassword,int id){
		uDAO.updatePassword(newpassword, id);
	}
	
	/**
	 * ��ȡ�����û�
	 * @author SevenLin
	 * @return
	 */
	public List<User> getAllUser(){
		return uDAO.getAll();
	}
	
	/**
	 * User��ҳ����
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
	 * ���һ����Ӱ
	 * @param movie
	 */
	public void addMovie(Movie movie){
		movieDAO.create(movie);
	}
	/**
	 * ɾ��һ����Ӱ
	 * @param id
	 */
	public void deleteMovie(int id){
		movieDAO.delete(id);
	}
	public void restoreMovie(int id){
		movieDAO.restore(id);
	}
	/**
	 * ����id����һ����Ӱ
	 * @param id
	 * @return
	 */
	public Movie findMovie(int id){
		return movieDAO.retrieve(id);
	}
	/**
	 * �޸ĵ�Ӱ����
	 * @param movie
	 */
	public void changeMovieDetail(Movie movie){
		movieDAO.update(movie);
	}
	/**
	 * ��ȡ���е�Ӱ�ķ�ҳ����
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
	 * ��ȡ������ӳ�ĵ�Ӱ�ķ�ҳ����
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
	 * ��ȡ������ӳ��Ӱ�ķ�ҳ����
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
	 * �޸�ӰƬ��ͼƬ
	 * @param image
	 * @param id
	 */
	public void changeMovieImage(String image,int id){
		movieDAO.changeImage(image, id);
	}
	/**
	 * ��ȡ����������ӳ�ĵ�Ӱ
	 * @return
	 */
	public List<Movie> getAllOnNowMovie(){
		return movieDAO.getAllOnNowMovie();
	}
	/**
	 * ��ȡ���м�����ӳ�ĵ�Ӱ
	 * @return
	 */
	public List<Movie> getAllComingSoonMovie(){
		return movieDAO.getAllComingSoonMovie();
	}
	/**
	 * ��������
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
		order.setState("unsend");										// δ����״̬
		/* ����������ӵ������� */
		for(Map.Entry<Integer, CartItem> me : cart.getMap().entrySet())
		{
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setMovie(citem.getMovie());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			order.getOrderitem().add(oitem);					// ����������ӵ�����
		}
		/* ��������ӵ����ݿ� */
		oDAO.create(order);
	}

	/**
	 * ȷ�϶�������״̬
	 * @param orderId
	 */
	public void confirmOrder(int orderId)
	{
		Order order = oDAO.retrieve(orderId);
		order.setState("send"); 										// ������״̬��Ϊ�ѷ���
		oDAO.update(order);
	}
	
	/**
	 * �г�δ����/�ѷ����Ķ���
	 * @param state
	 * @return
	 */
	public List<Order> listOrder(boolean state)
	{
		return oDAO.getAll(state);
	}
	
	/**
	 * ���ݶ���id��ȡ��������
	 * @param orderId
	 * @return
	 */
	public Order retrieveOrder(int orderId)
	{
		return oDAO.retrieve(orderId);
	}
	
	/**
	 * �����ﳵ���һ����Ӱ
	 * @param cart
	 * @param movie
	 */
	public void buyTicket(Cart cart, Movie movie)
	{
		cart.create(movie);
	}
	
	/**
	 *  ���ȫ�������ķ�ҳ����
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
	 * ����ѷ����ķ�ҳ����
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
	 * �½�����
	 * @param schedule
	 */
	public void createSchedule(Schedule schedule){
		sDAO.createSchedule(schedule);
	}
	/**
	 * ɾ������
	 * @param id
	 */
	public void deleteSchedule(int id){
		sDAO.deleteSchedule(id);
	}
	/**
	 * ��������
	 * @param schedule
	 */
	public void updateSchedule(Schedule schedule,int id){
		sDAO.updateSchedule(schedule,id);
	}
	/**
	 * ��ȡ���ڵķ�ҳ����
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
	 * ���ݵ�Ӱid���Ҹ�ӰƬ����������
	 * @param id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieId(int id){
		return sDAO.getScheduleByMovieId(id);
	}
	/**
	 * ����ӰƬ��id��ӰԺ��id����������
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<Schedule> getScheduleByMovieIdAndCinemaId(int movie_id,int cinema_id){
		return sDAO.getScheduleByMovieIdAndCinemaId(movie_id, cinema_id);
	}
	/**
	 * ����ʣ��Ʊ��
	 * @param id
	 * @param num
	 */
	public void updateRemanent(int id ,int num){
		sDAO.updateRemanent(id, num);
	}

	/**
	 * ע���û�
	 * @param user
	 */
	public void register(User user)
	{
		/* ��ɨ�����ݿ�, ����Ƿ��Ѿ����ڸ��û� */
		uDAO.create(user);
	}

	/**
	 * �����û����ֻ�����,�޸�����
	 * @param username
	 * @param phone
	 * @param password
	 */
	public boolean forgotten(String username, String phone, String password)
	{
		/* ��ͨ��username�ҵ��û� */
		User user = uDAO.retrieve(username);
		/* �˶���Ϣ */
		if(!user.getPhone().equals(phone))
		{
			return false;
		}
		/* �޸�����(update) */
		uDAO.updatePassword(password, user.getId());
		return true;
	}

	/**
	 * ���ݵ�ӰID�ҵ���Ӱ
	 * @param movieid
	 * @return
	 */
	public Movie retrieveMovie(String movieid)
	{
		return movieDAO.retrieve(Integer.parseInt(movieid));
	}
	/**
	 * �������е�ӰԺ
	 * @return
	 */
	public List<Cinema> getAllCinema(){
		return cDAO.getAll();
	}
	/**
	 * ����ӰԺid����ӰԺ
	 * @param id
	 * @return
	 */
	public Cinema findCinemaById(int id){
		return cDAO.findCinemaById(id);
	}
	/**
	 * ������ӴӰ��
	 * @return
	 */
	public List<Videohall> getAllVideohall(){
		return vDAO.getAllVideohall();
	}
	/**
	 * ����ӰԺ���Ҹ�ӰԺ������Ӱ��
	 * @param id
	 * @return
	 */
	public List<Videohall> getVideohallByCinemaId(int id){
		return vDAO.getVideohallByCinemaId(id);
	}
	/**
	 * ����ӰƬ������Ѱ�ҵ�Ӱ
	 * @param name
	 * @return
	 */
	public Movie findMovieByName(String name){
		return movieDAO.findMovieByName(name);
	}
	/**
	 * ����ӰԺ������Ѱ��ӰԺ
	 * @param name
	 * @return
	 */
	public Cinema findCinemaByName(String name){
		return cDAO.findCinemaByName(name);
	}
	/**
	 * ����Ӱ��������
	 * @param name
	 * @return
	 */
	public Videohall findVideohallByC_idAndV_name(int cinema_id,String name){
		return vDAO.findVideohallByC_idAndV_name(cinema_id, name);
	}
	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ
	 * @return
	 */
	public List<ScheduleView> getAllScheduleView(){
		return svDAO.getAll();
	}
	/**
	 * ������ͼ������ӰƬid��������
	 * @param id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieId(int id){
		return svDAO.getScheduleViewByMovieId(id);
	}
	/**
	 * ������ͼ����ѯָ����ӰԺָ����ӰƬ���������ڣ����ƶ���ʹ��
	 * @param movie_id
	 * @param cinema_id
	 * @return
	 */
	public List<ScheduleView> getScheduleViewByMovieIdAndCinemaId(int movie_id,int cinema_id){
		return svDAO.getScheduleViewByMovieIdAndCinemaId(movie_id, cinema_id);
	}
	/**
	 * ������ͼ��ѯ���е������Լ�һЩ��Ϣ,��ҳ
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
	 * ��������id��������
	 * @param id
	 * @return
	 */
	public Schedule getScheduleById(int id){
		return sDAO.getScheduleById(id);
	}

	/**
	 * ���ݶ���id,ɾ������
	 * @param orderID
	 */
	public void deleteOrder(String orderID)
	{
		/* ���ҵ�������ɾ�� */
		Order order = oDAO.retrieve(Integer.parseInt(orderID));
		oDAO.delete(order);
	}
	/**
	 * ��Ʊʱѡ����λ,������λid������λ��Ϣ
	 * @param id
	 */
	public void orderSeat(int id){
		seatDAO.orderSeat(id);
	}
	/**
	 * ��������id���Ҹ����ڵ�������λ����,���ƶ���ʹ��
	 * @param schedule_id
	 * @return
	 */
	public List<Seat> getAllSeatByScheduleId(int schedule_id){
		return seatDAO.getAllSeatByScheduleId(schedule_id);
	}
	/**
	 * ��������
	 * @param comment
	 */
	public void addComment(Comment comment){
		commentDAO.addComment(comment);
	}
	/**
	 * ���ӰƬ�ķ�ҳ����
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
	 * �ղص�Ӱ
	 * @param movie_id
	 * @param user_id
	 */
	public void collectMovie(Collection collection) {
		collectionDAO.collectMovie(collection);
	}
	/**
	 * ��ȡ�û��ղص����е�Ӱ
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
	 * ����Ӱ�Ƿ����ղ�
	 * @param movie_id
	 * @param user_id
	 * @return
	 */
	public boolean checkCollection(int movie_id,int user_id){
			return collectionDAO.checkCollection(movie_id,user_id);
	}
	/**
	 * ���µ�Ӱ�ȶ�
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
