<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改排期</title>
    
    <script src="${pageContext.request.contextPath }/js/getDate.js"></script>
     
     
    <style type="text/css">
    	body{
    		margin: 0px;
    		padding: 0px;
			background-color: #F7F8F9;
    	}
    	#container{
    		margin-top: 100px;
    		margin-left:240px;
    	}
    	#button{
    		margin-left: 60px;
    	}
    </style>

	
	<script type="text/javascript">
	  function getopt()
         {
             var ss = sel.options[sel.selectedIndex].parentNode.attributes;
             var cn = document.getElementById("cn");
             cn.value = ss["label"].value;
         }
	</script>
  </head>
  
  <body onload="getopt()">
  	<div id="container">
 	 <h3>请输入排期具体信息：</h3>
  		<form action="${pageContext.request.contextPath }/manager/ScheduleServlet?method=update&id=${schedule.id}" method="post">
  			<table>
  				<tr>
  					<td>请选电影：</td>
  					<td>
  						<select name="movieName">
  						<option selected="selected" value="0">--请选择--</option>   
  							<c:forEach var="movie" items="${movies }">
  								<option <c:if test="${movie.id==schedule.movie_id }">selected="selected"</c:if> >${movie.name }</option>
  							</c:forEach>
  						</select>
  					</td>
  				</tr>
  				<tr>
  					<td>影厅：</td>
  					<td>
  						<select name="videohallName" id="sel" onchange="getopt()">
  							<option selected="selected" value="0">--请选择--</option>   
  							<c:forEach items="${cinemas }" var="cinema">
  								<optgroup label="${cinema.name }">
	  								<c:forEach items="${videohalls }" var="videohall">
	  									<c:if test="${cinema.id==videohall.cinema_id }">
	  										<option <c:if test="${videohall.id==schedule.videoHall_id }">selected="selected"</c:if>>${videohall.name }</option>
	  									</c:if>
	  								</c:forEach>
  								</optgroup>
  							</c:forEach>
  						</select>
  					</td>
  				</tr>
  				<tr>
					<td>你选择的影院是：</td>  	
					<td><input type="text" id="cn" name="cinemaName" /></td>	
  				</tr>
  				<tr>
  					<td> 播放时间：</td>
  					<td><input type="text" name="airTime_date" onfocus="HS_setDate(this)" value=${airTime_date }>
  						<select name="airTime_hour">
	  						<option <c:if test="${airTime_hour eq '00'}">selected="selected"</c:if>>00</option>
	  						<option <c:if test="${airTime_hour eq '01'}">selected="selected"</c:if>>01</option>
	  						<option <c:if test="${airTime_hour eq '02'}">selected="selected"</c:if>>02</option>
	  						<option <c:if test="${airTime_hour eq '03'}">selected="selected"</c:if>>03</option>
	  						<option <c:if test="${airTime_hour eq '04'}">selected="selected"</c:if>>04</option>
	  						<option <c:if test="${airTime_hour eq '05'}">selected="selected"</c:if>>05</option>
	  						<option <c:if test="${airTime_hour eq '06'}">selected="selected"</c:if>>06</option>
	  						<option <c:if test="${airTime_hour eq '07'}">selected="selected"</c:if>>07</option>
	  						<option <c:if test="${airTime_hour eq '08'}">selected="selected"</c:if>>08</option>
	  						<option <c:if test="${airTime_hour eq '09'}">selected="selected"</c:if>>09</option>
	  						<option <c:if test="${airTime_hour eq '10'}">selected="selected"</c:if>>10</option>
	  						<option <c:if test="${airTime_hour eq '11'}">selected="selected"</c:if>>11</option>
	  						<option <c:if test="${airTime_hour eq '12'}">selected="selected"</c:if>>12</option>
	  						<option <c:if test="${airTime_hour eq '13'}">selected="selected"</c:if>>13</option>
	  						<option <c:if test="${airTime_hour eq '14'}">selected="selected"</c:if>>14</option>
	  						<option <c:if test="${airTime_hour eq '15'}">selected="selected"</c:if>>15</option>
	  						<option <c:if test="${airTime_hour eq '16'}">selected="selected"</c:if>>16</option>
	  						<option <c:if test="${airTime_hour eq '17'}">selected="selected"</c:if>>17</option>
	  						<option <c:if test="${airTime_hour eq '18'}">selected="selected"</c:if>>18</option>
	  						<option <c:if test="${airTime_hour eq '19'}">selected="selected"</c:if>>19</option>
	  						<option <c:if test="${airTime_hour eq '20'}">selected="selected"</c:if>>20</option>
	  						<option <c:if test="${airTime_hour eq '21'}">selected="selected"</c:if>>21</option>
	  						<option <c:if test="${airTime_hour eq '22'}">selected="selected"</c:if>>22</option>
	  						<option <c:if test="${airTime_hour eq '23'}">selected="selected"</c:if>>23</option>
	  						<option <c:if test="${airTime_hour eq '24'}">selected="selected"</c:if>>24</option>
  						</select>:
  						<select name="airTime_min">
	  						<option <c:if test="${airTime_min eq '00'}">selected="selected"</c:if>>00</option>
	  						<option <c:if test="${airTime_min eq '01'}">selected="selected"</c:if>>01</option>
	  						<option <c:if test="${airTime_min eq '02'}">selected="selected"</c:if>>02</option>
	  						<option <c:if test="${airTime_min eq '03'}">selected="selected"</c:if>>03</option>
	  						<option <c:if test="${airTime_min eq '04'}">selected="selected"</c:if>>04</option>
	  						<option <c:if test="${airTime_min eq '05'}">selected="selected"</c:if>>05</option>
	  						<option <c:if test="${airTime_min eq '06'}">selected="selected"</c:if>>06</option>
	  						<option <c:if test="${airTime_min eq '07'}">selected="selected"</c:if>>07</option>
	  						<option <c:if test="${airTime_min eq '08'}">selected="selected"</c:if>>08</option>
	  						<option <c:if test="${airTime_min eq '09'}">selected="selected"</c:if>>09</option>
	  						<option <c:if test="${airTime_min eq '10'}">selected="selected"</c:if>>10</option>
	  						<option <c:if test="${airTime_min eq '11'}">selected="selected"</c:if>>11</option>
	  						<option <c:if test="${airTime_min eq '12'}">selected="selected"</c:if>>12</option>
	  						<option <c:if test="${airTime_min eq '13'}">selected="selected"</c:if>>13</option>
	  						<option <c:if test="${airTime_min eq '14'}">selected="selected"</c:if>>14</option>
	  						<option <c:if test="${airTime_min eq '15'}">selected="selected"</c:if>>15</option>
	  						<option <c:if test="${airTime_min eq '16'}">selected="selected"</c:if>>16</option>
	  						<option <c:if test="${airTime_min eq '17'}">selected="selected"</c:if>>17</option>
	  						<option <c:if test="${airTime_min eq '18'}">selected="selected"</c:if>>18</option>
	  						<option <c:if test="${airTime_min eq '19'}">selected="selected"</c:if>>19</option>
	  						<option <c:if test="${airTime_min eq '20'}">selected="selected"</c:if>>20</option>
	  						<option <c:if test="${airTime_min eq '21'}">selected="selected"</c:if>>21</option>
	  						<option <c:if test="${airTime_min eq '22'}">selected="selected"</c:if>>22</option>
	  						<option <c:if test="${airTime_min eq '23'}">selected="selected"</c:if>>23</option>
	  						<option <c:if test="${airTime_min eq '24'}">selected="selected"</c:if>>24</option>
	  						<option <c:if test="${airTime_min eq '25'}">selected="selected"</c:if>>25</option>
	  						<option <c:if test="${airTime_min eq '26'}">selected="selected"</c:if>>26</option>
	  						<option <c:if test="${airTime_min eq '27'}">selected="selected"</c:if>>27</option>
	  						<option <c:if test="${airTime_min eq '28'}">selected="selected"</c:if>>28</option>
	  						<option <c:if test="${airTime_min eq '29'}">selected="selected"</c:if>>29</option>
	  						<option <c:if test="${airTime_min eq '30'}">selected="selected"</c:if>>30</option>
	  						<option <c:if test="${airTime_min eq '31'}">selected="selected"</c:if>>31</option>
	  						<option <c:if test="${airTime_min eq '32'}">selected="selected"</c:if>>32</option>
	  						<option <c:if test="${airTime_min eq '33'}">selected="selected"</c:if>>33</option>
	  						<option <c:if test="${airTime_min eq '34'}">selected="selected"</c:if>>34</option>
	  						<option <c:if test="${airTime_min eq '35'}">selected="selected"</c:if>>35</option>
	  						<option <c:if test="${airTime_min eq '36'}">selected="selected"</c:if>>36</option>
	  						<option <c:if test="${airTime_min eq '37'}">selected="selected"</c:if>>37</option>
	  						<option <c:if test="${airTime_min eq '38'}">selected="selected"</c:if>>38</option>
	  						<option <c:if test="${airTime_min eq '39'}">selected="selected"</c:if>>39</option>
	  						<option <c:if test="${airTime_min eq '40'}">selected="selected"</c:if>>40</option>
	  						<option <c:if test="${airTime_min eq '41'}">selected="selected"</c:if>>41</option>
	  						<option <c:if test="${airTime_min eq '42'}">selected="selected"</c:if>>42</option>
	  						<option <c:if test="${airTime_min eq '43'}">selected="selected"</c:if>>43</option>
	  						<option <c:if test="${airTime_min eq '44'}">selected="selected"</c:if>>44</option>
	  						<option <c:if test="${airTime_min eq '45'}">selected="selected"</c:if>>45</option>
	  						<option <c:if test="${airTime_min eq '46'}">selected="selected"</c:if>>46</option>
	  						<option <c:if test="${airTime_min eq '47'}">selected="selected"</c:if>>47</option>
	  						<option <c:if test="${airTime_min eq '48'}">selected="selected"</c:if>>48</option>
	  						<option <c:if test="${airTime_min eq '49'}">selected="selected"</c:if>>49</option>
	  						<option <c:if test="${airTime_min eq '50'}">selected="selected"</c:if>>50</option>
	  						<option <c:if test="${airTime_min eq '51'}">selected="selected"</c:if>>51</option>
	  						<option <c:if test="${airTime_min eq '52'}">selected="selected"</c:if>>52</option>
	  						<option <c:if test="${airTime_min eq '53'}">selected="selected"</c:if>>53</option>
	  						<option <c:if test="${airTime_min eq '54'}">selected="selected"</c:if>>54</option>
	  						<option <c:if test="${airTime_min eq '55'}">selected="selected"</c:if>>55</option>
	  						<option <c:if test="${airTime_min eq '56'}">selected="selected"</c:if>>56</option>
	  						<option <c:if test="${airTime_min eq '57'}">selected="selected"</c:if>>57</option>
	  						<option <c:if test="${airTime_min eq '58'}">selected="selected"</c:if>>58</option>
	  						<option <c:if test="${airTime_min eq '59'}">selected="selected"</c:if>>59</option>
	  						<option <c:if test="${airTime_min eq '60'}">selected="selected"</c:if>>60</option>
  						</select>
  				</tr>
  			</table>
  			<br>
  			<div id="button">
	  			<input type="submit" name="submit" value="提交" >&nbsp;&nbsp;
	  			<input type="reset" name="reset" value="重置">
  			</div>
		</form>
	</div>
  </body>
</html>
