<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加电影</title>
    
    <script src="${pageContext.request.contextPath }/js/getDate.js"></script>
    
    <style type="text/css">
    	body{
    		margin: 0px;
    		padding: 0px;
    	}
    	#container{
    		margin-top: 20px;
    		margin-left: 150px;
    	}
    </style>
	<link href="${pageContext.request.contextPath }/css/tablestyle.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/tablejquery.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/bstablecrosshair.js"></script>

  </head>
  
  <body>
  	<div id="container">
  		<h3>请输入您要添加的影片的详细信息：</h3>
    	<form action="${pageContext.request.contextPath }/manager/MovieServlet?method=add" method="post" enctype="multipart/form-data" >
    		<table style="border:2px solid #444;border-collapse:collapse;" id="mytable">
    			<tr>
    				<td>影片名：</td><td ><input type="text" name="name" ></td>
    				<td>上映时间：</td><td><input type="text" name=showtime onfocus="HS_setDate(this)"></td>
    			</tr>
    			<tr>
    				<td>导演：</td><td><input type="text" name="director" ></td>
    				<td>时长：</td><td><input type="text" name="runtime"></td>
    			</tr>
    			<tr>
    				<td>主演：</td><td colspan="3" ><input type="text" name="castActor" style="width: 500px"></td>
    				
    			</tr>
    			<tr >
    				<td>语言：</td>
    				<td>
    					<select name="language">
    					<option>国语</option>
	    				<option>英语</option>
	    				<option>粤语</option>
	    				<option>日语</option>
	    				<option>韩语</option>
	    				<option>法语</option>
	    				<option>德语</option>
	    				<option>泰语</option>
	    				<option>印度语</option>
	    				<option>其他</option>
    					</select>
    				</td>
    				<td>制式：</td>
    				<td>
    					<select  name="style">
    					<option>2D</option>
    					<option>3D</option>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>地区：</td>
    				<td>
	    				<select name="area">
	    				<option>大陆</option>
	    				<option>美国</option>
	    				<option>香港</option>
	    				<option>韩国</option>
	    				<option>日本</option>
	    				<option>法国</option>
	    				<option>英国</option>
	    				<option>德国</option>
	    				<option>台湾</option>
	    				<option>泰国</option>
	    				<option>印度</option>
	    				<option>其他</option>
	    				</select>
    				</td>
    				<td>价格：</td>
    				<td><input type="text" name="price"></td>
    			</tr>
    			<tr >
    				<td>类型：</td>
    				<td colspan="3">
    				   <input type="checkbox" checked name="type" value="选择的内容是：" style="display: none;">  
    					<input type="checkbox" name="type" value="剧情" >剧情
    					<input type="checkbox" name="type" value="爱情" >爱情
    					<input type="checkbox" name="type" value="喜剧" >喜剧
    					<input type="checkbox" name="type" value="动作" >动作
    					<input type="checkbox" name="type" value="恐怖" >恐怖
    					<input type="checkbox" name="type" value="惊悚" >惊悚
    					<input type="checkbox" name="type" value="犯罪" >犯罪
    					<input type="checkbox" name="type" value="悬疑" >悬疑
    				</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td colspan="3">
    					<input type="checkbox" name="type" value="科幻" >科幻
    					<input type="checkbox" name="type" value="伦理" >伦理
    					<input type="checkbox" name="type" value="战争" >战争
    					<input type="checkbox" name="type" value="动画" >动画
    					<input type="checkbox" name="type" value="文艺" >文艺
    					<input type="checkbox" name="type" value="历史" >历史
    					<input type="checkbox" name="type" value="传记" >传记
    					<input type="checkbox" name="type" value="奇幻" >奇幻
    				</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td colspan="3" >
    					<input type="checkbox" name="type" value="古装" >古装
    					<input type="checkbox" name="type" value="歌舞" >歌舞
    					<input type="checkbox" name="type" value="纪录" >纪录
    					<input type="checkbox" name="type" value="家庭" >家庭
    					<input type="checkbox" name="type" value="冒险" >冒险
    					<input type="checkbox" name="type" value="灾难" >灾难
    					<input type="checkbox" name="type" value="武侠" >武侠
    					<input type="checkbox" name="type" value="青春" >青春
    					<input type="checkbox" name="type" value="其他" >其他
    				</td>
    			</tr>
    			<tr>
    				<td>剧情介绍：</td><td colspan="3"><textarea  cols="100" rows="5" name="introduction"></textarea></td>
    			</tr>
    			
    			<tr>
    				<td >图片：</td><td colspan="3"><input type="file"  name="image"></td>
    			</tr>
    		</table>
    		<div>
    			<input type="submit" name="submit"  value="提交" >
    			<input type="reset" name="reset" value="重置" >
    		</div>
    	</form>
  	</div>
  </body>
</html>
