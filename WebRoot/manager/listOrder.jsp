<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列出订单</title>
    <link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>
	<style type="text/css">
			#page{
    		margin-left:43%;
    		margin-top:20px;
    	}
	</style>
  </head>
  <body>
  	<center>
  		<div>
  			<h2 id="x">${state }订单显示</h2>
  		</div>
  		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
  			<tr>
  				<td>订单号</td>
  				<td>下单时间</td>
  				<td>客户</td>
  				<td>金额(元)</td>
  				<td>状态</td>
  				<td>操作</td>
  			</tr>
  			<c:forEach items="${result.list }" var="order">
  				<tr>
  				<td>${order.id }</td>
  				<td>${order.ordertime }</td>
  				<td>${order.user.username }</td>
  				<td>${order.price }</td>
  				<td>${order.state=='unsend'?'未发货':'已发货' }</td>
  				<td>
  					<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=detail&orderid=${order.id}">查看详情</a>
  					<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=delete&orderid=${order.id}&state=${state=='未发货'?'showUnsend':'showSend' }">删除</a>
				</td>
  			</tr>
  			</c:forEach>
  		</table>
  			<c:if test="${result.page.currentPage-1>0}">
  				<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=${state=='未发货'?'showUnsend':'showSend' }&currentPage=${result.page.currentPage-1}">[上一页]</a>
  			</c:if>
  			
			<c:forEach begin="${result.page.startPage }" end="${result.page.endPage }" var="currentPage">
				[<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=${state=='未发货'?'showUnsend':'showSend' }&currentPage=${currentPage}">${currentPage }</a>]
			</c:forEach>
			
  			<c:if test="${result.page.currentPage+1 <= result.page.totalPage}">
				<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=${state=='未发货'?'showUnsend':'showSend' }&currentPage=${result.page.currentPage+1}">[下一页]</a>
  			</c:if>
  		<script type="text/javascript">
			$.bstablecrosshair('mytable',{color:'#444',background:'#aaa','foreground':'#fff'});
		</script>
  	</center>
  </body>
</html>
