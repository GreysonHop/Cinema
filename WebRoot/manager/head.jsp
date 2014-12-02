<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台头部</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/head.css"/>
  </head>
  <body>
   	<div class="head">
   		<div class="logo"></div>
   		<div class="logout">
   			<a href="${pageContext.request.contextPath }/manager/LogoutServlet" target="_parent">* 退出系统</a>
   		</div>
   	</div>
  </body>
</html>
