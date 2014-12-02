<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查看所有影片</title>
    <style type="text/css">
    	body{
    		margin: 0ps;
    		padding: 0px;
    	}
    	#container{
    		margin-top: 100px;
    		margin-left: 50px;
    	}
    	#page{
    		margin-left:43%;
    		margin-top:20px;
    	}
    </style>
    
    <link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>
  </head>
  
  <body>
  	<div id="container">
  		<table style="border:2px solid #444;border-collapse:collapse; margin-left:15%;" id="mytable">
  			<tr>
  				<th>片名</th>
  				<th>上映时间</th>
  				<th>时长</th>
  				<th>状态</th>
  				<th>查看详情</th>
  				<th>修改</th>
  				<th>删除/恢复</th>
  			</tr>
  			<c:forEach items="${result.list}" var="movie" >
  				<tr>
  					<td>${movie.name }</td>
  					<td>${movie.showtime }</td>
  					<td>${movie.runtime }</td>
  					<td>${movie.state }</td>
  					<td><a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showMovieDetail&id=${movie.id}">查看详情</a></td>
  					<td><a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showEditableMovieDetail&id=${movie.id}">修改</a></td>
  					<td>
						<c:if test="${movie.state eq '已删除'}"><a href="${pageContext.request.contextPath }/manager/MovieServlet?method=restore&id=${movie.id}">恢复</a></c:if>
						<c:if test="${movie.state eq '未删除'}"><a href="${pageContext.request.contextPath }/manager/MovieServlet?method=delete&id=${movie.id}">删除</a></c:if>
					</td>
  				</tr>
  			</c:forEach>
  		</table>
  		<div id="page">
  			<c:if test="${result.page.currentPage-1>0}">
  				<a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showAll&currentPage=${result.page.currentPage-1}">[上一页]</a>
  			</c:if>
  			<c:if test="${result.page.currentPage-1<=0}">
  				<a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showAll&currentPage=${result.page.startPage}">[上一页]</a>
  			</c:if>
			<c:forEach begin="${result.page.startPage }" end="${result.page.endPage }" var="currentPage">
				[<a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showAll&currentPage=${currentPage}">${currentPage }</a>]
			</c:forEach>
			<c:if test="${result.page.currentPage+1>result.page.totalPage}">
				<a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showAll&currentPage=${result.page.totalPage}">[下一页]</a>
  			</c:if>
  			<c:if test="${result.page.currentPage+1<=result.page.totalPage}">
				<a href="${pageContext.request.contextPath }/manager/MovieServlet?method=showAll&currentPage=${result.page.currentPage+1}">[下一页]</a>
  			</c:if>
  		</div>
  	</div>
  </body>
</html>
