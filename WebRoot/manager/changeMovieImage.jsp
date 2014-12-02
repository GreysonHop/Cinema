<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'changeMovieImage.jsp' starting page</title>
    <style type="text/css">
    	body{
    		margin: 0px;
    		padding: 0px;
    	}
    	#container{
    		
    	}
    
    </style>

  </head>
  
  <body>
  	<div id="container">
  		<form action="${pageContext.request.contextPath }/manager/MovieServlet?id=${param.id }&imageName=${param.imageName }&method=changeImage" method="post" enctype="multipart/form-data" >
  			请选择新图片：
  			<table>
  				<tr>
  					<td>新图片</td><td><input type="file" name="image"></td>
  			</table>
  			<input type="submit" value="提交" >
  		</form>
  	</div>
  </body>
</html>
