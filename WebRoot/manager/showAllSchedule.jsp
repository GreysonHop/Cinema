<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查看排期</title>
    
	<style type="text/css">
		body{
			margin:0px;
			padding:0px;
			background-color:  #F7F8F9;
		}
		#container{
    		margin-top: 100px;
    		margin-left: 50px;
    	}
		a{
			color: blue;
			text-decoration: none;
			font-size: 14px;
		}
		#page{
    		margin-left:43%;
    		margin-top:20px;
    	}
	</style>
	 <link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<div id="container">
  		<div id="table">
  			<h1>当前所有影片排期如下：</h1>
	  		<table style="border:2px solid #444;border-collapse:collapse; margin-left:15%;" id="mytable" >
	  			<tr>
	  				<th>影片名</th>
	  				<th>播映时间</th>
	  				<th>影厅</th>
	  				<th>影院</th>
	  				<th>操作</th>
	  			</tr>
	  			<c:forEach var="scheduleView" items="${result.list}">
		  			<tr>
		  				<td>${scheduleView.movie_name }</td>
		  				<td>${scheduleView.airtime}</td>
		  				<td>${scheduleView.cinema_name }</td>
		  				<td>${scheduleView.videohall_name}</td>
		  				<td>
		  					<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=showBack&schedule_id=${scheduleView.schedule_id}">修改</a> &nbsp;
		  					<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=delete&schedule_id=${scheduleView.schedule_id}">删除</a>
		  				</td>
		  			</tr>
	  			</c:forEach>
	  		</table>
	  		<br>
	  	</div>
		<div id="page">
  			<c:if test="${result.page.currentPage-1>0}">
  				<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=getAll&currentPage=${result.page.currentPage-1}">[上一页]</a>
  			</c:if>
  			<c:if test="${result.page.currentPage-1<=0}">
  				<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=getAll&currentPage=${result.page.startPage}">[上一页]</a>
  			</c:if>
			<c:forEach begin="${result.page.startPage }" end="${result.page.endPage }" var="currentPage">
				[<a href="${pageContext.request.contextPath }//manager/ScheduleServlet?method=getAll&currentPage=${currentPage}">${currentPage }</a>]
			</c:forEach>
			<c:if test="${result.page.currentPage+1>result.page.totalPage}">
				<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=getAll&currentPage=${result.page.totalPage}">[下一页]</a>
  			</c:if>
  			<c:if test="${result.page.currentPage+1<=result.page.totalPage}">
				<a href="${pageContext.request.contextPath }/manager/ScheduleServlet?method=getAll&currentPage=${result.page.currentPage+1}">[下一页]</a>
  			</c:if>
  		</div>
   </div>
  </body>
</html>
