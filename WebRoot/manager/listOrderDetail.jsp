<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列出订单</title>
    <link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>
  </head>
  <body>
  	<center>
  		<div>
  			<h2 id="x">订单详情</h2>
  		</div>
  		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
  			<tr>
  				<td>影片名称</td>
  				<td>单价</td>
  				<td>票数</td>
  				<td>应收货款</td>
  			</tr>
  			<c:forEach items="${order.orderitem }" var="orderitem">
  				<tr>
  				<td>${orderitem.movie.name }</td>
  				<td>${orderitem.movie.price }</td>
  				<td>${orderitem.quantity }</td>
  				<td>${orderitem.price }</td>
  			</tr>
  			</c:forEach>
  			<tr>
  				<td>订单总价</td>
  				<td colspan="3">${order.price }</td>
  			</tr>
  		</table>
  		<br>
  		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
  			<tr>
  				<td>用户</td>
  				<td>手机</td>
  				<td>邮箱</td>
  			</tr>
  			<tr>
  				<td>${order.user.username }</td>
  				<td>${order.user.phone }</td>
  				<td>${order.user.email }</td>
  			</tr>
  		</table>
  		<br>
  		<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=confirm&orderid=${order.id}">确认收货</a>
  		<script type="text/javascript">
			$.bstablecrosshair('mytable',{color:'#444',background:'#aaa','foreground':'#fff'});
		</script>
  	</center>
  </body>
</html>