<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>addSchedule</title>
    
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
             alert( ss["label"].value);
         }
	</script>
  </head>
  
  <body>
  	<div id="container">
 	 <h3>请输入排期具体信息：</h3>
  		<form action="${pageContext.request.contextPath }/manager/ScheduleServlet?method=add" method="post">
  			<table>
  				<tr>
  					<td>请选电影：</td>
  					<td>
  						<select name="movieName">
  						<option selected="selected" value="0">--请选择--</option>   
  							<c:forEach var="movie" items="${movies }">
  								<option >${movie.name }</option>
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
	  										<option>${videohall.name }</option>
	  									</c:if>
	  								</c:forEach>
  								</optgroup>
  							</c:forEach>
  						</select>
  					</td>
  				</tr>
  				<tr>
					<td>你选择的影院是：</td>  	
					<td>  <input type="text" id="cn" name="cinemaName" value="" readonly/></td>	
  				</tr>
  				<tr>
  					<td> 播放时间：</td>
  					<td><input type="text" name="airTime_date" onfocus="HS_setDate(this)">
  						<select name="airTime_hour">
	  						<option>00</option>
	  						<option>01</option>
	  						<option>02</option>
	  						<option>03</option>
	  						<option>04</option>
	  						<option>05</option>
	  						<option>06</option>
	  						<option>07</option>
	  						<option>08</option>
	  						<option>09</option>
	  						<option>10</option>
	  						<option>11</option>
	  						<option>12</option>
	  						<option>13</option>
	  						<option>14</option>
	  						<option>15</option>
	  						<option>16</option>
	  						<option>17</option>
	  						<option>18</option>
	  						<option>19</option>
	  						<option>20</option>
	  						<option>21</option>
	  						<option>22</option>
	  						<option>23</option>
	  						<option>24</option>
  						</select>:
  						<select name="airTime_min">
	  						<option>00</option>
	  						<option>01</option>
	  						<option>02</option>
	  						<option>03</option>
	  						<option>04</option>
	  						<option>05</option>
	  						<option>06</option>
	  						<option>07</option>
	  						<option>08</option>
	  						<option>09</option>
	  						<option>10</option>
	  						<option>11</option>
	  						<option>12</option>
	  						<option>13</option>
	  						<option>14</option>
	  						<option>15</option>
	  						<option>16</option>
	  						<option>17</option>
	  						<option>18</option>
	  						<option>19</option>
	  						<option>20</option>
	  						<option>21</option>
	  						<option>22</option>
	  						<option>23</option>
	  						<option>24</option>
	  						<option>25</option>
	  						<option>26</option>
	  						<option>27</option>
	  						<option>28</option>
	  						<option>29</option>
	  						<option>30</option>
	  						<option>31</option>
	  						<option>32</option>
	  						<option>33</option>
	  						<option>34</option>
	  						<option>35</option>
	  						<option>36</option>
	  						<option>37</option>
	  						<option>38</option>
	  						<option>39</option>
	  						<option>40</option>
	  						<option>41</option>
	  						<option>42</option>
	  						<option>43</option>
	  						<option>44</option>
	  						<option>45</option>
	  						<option>46</option>
	  						<option>47</option>
	  						<option>48</option>
	  						<option>49</option>
	  						<option>50</option>
	  						<option>51</option>
	  						<option>52</option>
	  						<option>53</option>
	  						<option>54</option>
	  						<option>55</option>
	  						<option>56</option>
	  						<option>57</option>
	  						<option>58</option>
	  						<option>59</option>
	  						<option>60</option>
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
