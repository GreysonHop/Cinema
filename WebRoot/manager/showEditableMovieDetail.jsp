<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加电影</title>
    
    <style type="text/css">
    	body{
    		margin: 0px;
    		padding: 0px;
    	}
    	#container{
    		margin-top: 20px;
    		margin-left: 150px;
    	}
    	.input{
    		width: 500px;
    	}
    </style>

	<link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>
    <script src="${pageContext.request.contextPath }/js/getDate.js"></script>
    
  </head>
  
  <body>
  	<c:if test="${!empty changeImageResult }">
  		<script type="text/javascript">
  			alert("修改图片成功！");
  		</script>
  	</c:if>
  	<div id="container">
  		<h3>在这里你可以修改影片数据：</h3>
    	<form action="${pageContext.request.contextPath }/manager/MovieServlet?method=changeMovieDetail&id=${movie.id}" method="post">
    		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
    			<tr>
    				<td>影片名：</td><td ><input type="text" name="name"  value=${movie.name }></td>
    				<td>上映时间：</td><td><input type="text" name=showtime onfocus="HS_setDate(this)" value=${movie.showtime}></td>
    			</tr>
    			<tr>
    				<td>导演：</td><td><input type="text" name="director"  value=${movie.director }></td>
    				<td>片场：</td><td><input type="text" name="runtime" value=${movie.runtime }></td>
    			</tr>
    			<tr>
    				<td>主演：</td><td colspan="3" ><input type="text" name="castActor" class="input"  value=${movie.castActor }></td>
    				
    			</tr>
    			<tr >
    				<td>语言：</td>
    				<td>
    					<select name="language">
    					<option <c:if test="${movie.language eq '国语'}">selected="selected"</c:if>>国语</option>
	    				<option <c:if test="${movie.language eq '英语'}">selected="selected"</c:if>>英语</option>
	    				<option <c:if test="${movie.language eq '日语'}">selected="selected"</c:if>>日语</option>
	    				<option <c:if test="${movie.language eq '韩语'}">selected="selected"</c:if>>韩语</option>
	    				<option <c:if test="${movie.language eq '法语'}">selected="selected"</c:if>>法语</option>
	    				<option <c:if test="${movie.language eq '德语'}">selected="selected"</c:if>>德语</option>
	    				<option <c:if test="${movie.language eq '泰语'}">selected="selected"</c:if>>泰语</option>
	    				<option <c:if test="${movie.language eq '印度语'}">selected="selected"</c:if>>印度语</option>
	    				<option <c:if test="${movie.language eq '其他'}">selected="selected"</c:if>>其他</option>
    					</select>
    				</td>
    				<td>制式：</td>
    				<td>
    					<select  name="style">
    					<option <c:if test="${movie.style eq '2D'}">selected="selected"</c:if>>2D</option>
    					<option <c:if test="${movie.style eq '3D'}">selected="selected"</c:if>>3D</option>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>地区：</td>
    				<td>
	    				<select name="area">
	    				<option <c:if test="${movie.area eq '大陆'}">selected="selected"</c:if>>大陆</option>
	    				<option <c:if test="${movie.area eq '美国'}">selected="selected"</c:if>>美国</option>
	    				<option <c:if test="${movie.area eq '香港'}">selected="selected"</c:if>>香港</option>
	    				<option <c:if test="${movie.area eq '韩国'}">selected="selected"</c:if>>韩国</option>
	    				<option <c:if test="${movie.area eq '日本'}">selected="selected"</c:if>>日本</option>
	    				<option <c:if test="${movie.area eq '法国'}">selected="selected"</c:if>>法国</option>
	    				<option <c:if test="${movie.area eq '英国'}">selected="selected"</c:if>>英国</option>
	    				<option <c:if test="${movie.area eq '德国'}">selected="selected"</c:if>>德国</option>
	    				<option <c:if test="${movie.area eq '台湾'}">selected="selected"</c:if>>台湾</option>
	    				<option <c:if test="${movie.area eq '泰国'}">selected="selected"</c:if>>泰国</option>
	    				<option <c:if test="${movie.area eq '印度'}">selected="selected"</c:if>>印度</option>
	    				<option <c:if test="${movie.area eq '其他'}">selected="selected"</c:if>>其他</option>
	    				</select>
    				</td>
    				<td>价格：</td>
    				<td><input type="text" name="price" value=${movie.price }></td>
    			</tr>
    			<tr >
    				<td>类型：</td>
    				<td colspan="3">
    					<input type="checkbox" checked name="type" value="选择的内容是：" style="display: none;">    
    					<input type="checkbox" name="type" value="剧情" <c:if test="${fn:contains( movie.type , '剧情')}">checked="ckecked"</c:if>>剧情
    					<input type="checkbox" name="type" value="爱情" <c:if test="${fn:contains( movie.type , '爱情')}">checked="ckecked"</c:if>>爱情
    					<input type="checkbox" name="type" value="喜剧" <c:if test="${fn:contains( movie.type , '喜剧')}">checked="ckecked"</c:if>>喜剧
    					<input type="checkbox" name="type" value="动作" <c:if test="${fn:contains( movie.type , '动作')}">checked="ckecked"</c:if>>动作
    					<input type="checkbox" name="type" value="恐怖" <c:if test="${fn:contains( movie.type , '恐怖')}">checked="ckecked"</c:if>>恐怖
    					<input type="checkbox" name="type" value="惊悚" <c:if test="${fn:contains( movie.type , '惊悚')}">checked="ckecked"</c:if>>惊悚
    					<input type="checkbox" name="type" value="犯罪" <c:if test="${fn:contains( movie.type , '犯罪')}">checked="ckecked"</c:if>>犯罪
    					<input type="checkbox" name="type" value="悬疑" <c:if test="${fn:contains( movie.type , '悬疑')}">checked="ckecked"</c:if>>悬疑
    				</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td colspan="3">
    					<input type="checkbox" name="type" value="科幻" <c:if test="${fn:contains( movie.type , '科幻')}">checked="ckecked"</c:if>>科幻
    					<input type="checkbox" name="type" value="伦理" <c:if test="${fn:contains( movie.type , '伦理')}">checked="ckecked"</c:if>>伦理
    					<input type="checkbox" name="type" value="战争" <c:if test="${fn:contains( movie.type , '战争')}">checked="ckecked"</c:if>>战争
    					<input type="checkbox" name="type" value="动画" <c:if test="${fn:contains( movie.type , '动画')}">checked="ckecked"</c:if>>动画
    					<input type="checkbox" name="type" value="文艺" <c:if test="${fn:contains( movie.type , '文艺')}">checked="ckecked"</c:if>>文艺
    					<input type="checkbox" name="type" value="历史" <c:if test="${fn:contains( movie.type , '历史')}">checked="ckecked"</c:if>>历史
    					<input type="checkbox" name="type" value="传记" <c:if test="${fn:contains( movie.type , '传记')}">checked="ckecked"</c:if>>传记
    					<input type="checkbox" name="type" value="奇幻" <c:if test="${fn:contains( movie.type , '奇幻')}">checked="ckecked"</c:if>>奇幻
    				</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td colspan="3">
    					<input type="checkbox" name="type" value="古装" <c:if test="${fn:contains( movie.type , '武装')}">checked="ckecked"</c:if>>古装
    					<input type="checkbox" name="type" value="歌舞" <c:if test="${fn:contains( movie.type , '歌舞')}">checked="ckecked"</c:if>>歌舞
    					<input type="checkbox" name="type" value="纪录" <c:if test="${fn:contains( movie.type , '记录')}">checked="ckecked"</c:if>>纪录
    					<input type="checkbox" name="type" value="家庭" <c:if test="${fn:contains( movie.type , '家庭')}">checked="ckecked"</c:if>>家庭
    					<input type="checkbox" name="type" value="冒险" <c:if test="${fn:contains( movie.type , '冒险')}">checked="ckecked"</c:if>>冒险
    					<input type="checkbox" name="type" value="灾难" <c:if test="${fn:contains( movie.type , '灾难')}">checked="ckecked"</c:if>>灾难
    					<input type="checkbox" name="type" value="武侠" <c:if test="${fn:contains( movie.type , '武侠')}">checked="ckecked"</c:if>>武侠
    					<input type="checkbox" name="type" value="青春" <c:if test="${fn:contains( movie.type , '青春')}">checked="ckecked"</c:if>>青春
    					<input type="checkbox" name="type" value="其他" <c:if test="${fn:contains( movie.type , '其他')}">checked="ckecked"</c:if>>其他
    				</td>
    			</tr>
    			<tr>
    				<td>剧情介绍：</td><td colspan="3"><textarea cols="100" rows="5"   name="introduction" >${movie.introduction }</textarea></td>
    			</tr>
    			<tr>
    				<td>图片：</td><td colspan="3"><a href="${pageContext.request.contextPath }/manager/changeMovieImage.jsp?id=${movie.id}&imageName=${movie.image}" title="点击图片修改"><img src="${pageContext.request.contextPath }/upload/${movie.image}"></a></td>
    			</tr>
    		</table>
    		<div>
    			<input type="submit" name="submit" value="修改" >
    			<input type="reset" name="reset" value="重置" >
    		</div>
    	</form>
  	</div>
  </body>
</html>
