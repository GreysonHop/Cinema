<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查看电影详情</title>
    
    
    <style type="text/css">
    	body{
    		margin: 0px;
    		padding: 0px;
    	}
    	#container{
    		margin-top: 20px;
    		margin-left: 50px;
    	}
    	#image{
    		position: absolute;
    		top: 50px;
    		right: 100px;
    	}
    </style>

	<link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>

  </head>
  
  <body>
  	<div id="container">
  		<h3>影片的详细信息：</h3>
    		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
    			<tr>
    				<td>影片名：</td><td>${movie.name }</td>
    				<td>上映时间：</td><td>${movie.showtime }</td>
    			</tr>
    			<tr>
    				<td>导演：</td><td>${movie.director }</td>
    				<td>片场：</td><td>${movie.runtime }</td>
    			</tr>
    			<tr>
    				<td>主演：</td><td colspan="3">${movie.castActor }</td>
    				
    			</tr>
    			<tr >
    				<td>语言：</td>
    				<td>${movie.language }</td>
    				<td>制式：</td>
    				<td>${movie.style }</td>
    			</tr>
    			<tr>
    				<td>地区：</td>
    				<td>${movie.area }
    				</td>
    				<td>价格：</td>
    				<td>${movie.price }</td>
    			</tr>
    			<tr >
    				<td>类型：</td>
    				<td colspan="3">${movie.type }</td>
    			</tr>
    			<tr>
    				<td>剧情介绍：</td><td colspan="3">${movie.introduction }</td>
    			</tr>
    			<tr>
    				<td>热度：</td>
    				<td colspan="3">${movie.popularity }</td>
    			</tr>
    		</table>
    		<div id="image"> 
    			<img alt="图片" src="${pageContext.request.contextPath }/upload/${movie.image}">
    		</div>
  	</div>
  </body>
</html>
