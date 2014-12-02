<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/login_config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/login_do.js"></script>
  </head>
  <body>
  <h3>${message }</h3>
   <div class="login">
   		<form action="${pageContext.request.contextPath }/manager/LoginServlet" method="post">
   			<div class="logo"></div>
   			<div class="login_form">
   				<div class="user">
   					<input type="text" name="username" class="txt_value" id="usernmae"/>
   					<input type="password" name="password" class="txt_value" id="password"/>
   				</div>
   				<button type="submit" class="btn" id="submit">登陆</button>
   			</div>
   		</form>
   </div>
  </body>
</html>
