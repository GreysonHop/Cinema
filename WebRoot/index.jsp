<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台首页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_css.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zTreeStyle.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.core-3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
  </head>
<%--   <frameset rows="25%,*"  border="1px">
  	<frame src="${pageContext.request.contextPath}/manager/head.jsp" name="head">
  	<frameset cols="20%,*">
  		<frame src="${pageContext.request.contextPath}/manager/nav.jsp" name="nav">
  		<frame src="${pageContext.request.contextPath}/manager/content.jsp" name="content">
  	</frameset>
  </frameset> --%>
  <script type="text/javascript">
  	/* 退出系统 */
  	function logout()
  	{
  		if(confirm("您确定要退出系统吗?"))
  		{
  			window.location.href="${pageContext.request.contextPath }/manager/LogoutServlet";
  		}
  	}
  </script>
  <body>
  	<div id="top">
  		<div id="top_logo">
  			<img alt="logo" src="${pageContext.request.contextPath }/imgs/logo.jpg"  width="280"  height="65"  style="vertical-align:middle">
  		</div>
  		<div id="top_links">
  			<div id="top_op">
  				<ul>
  					<li>
  						<img alt="当前用户" src="${pageContext.request.contextPath}/imgs/user.jpg"> : <span>${admin.adminName }</span>
  					</li>
  				</ul>
  			</div>
  			<div id="top_close">
  				<a href="javascript:void(0);" onclick="logout();" target="_parent">
  					<img alt="logout" src="${pageContext.request.contextPath}/imgs/close.jpg" style="position:relative; top:18px; left:25px;">
  				</a>
  			</div>
  		</div>
  	</div>
  	<!-- side menu -->
  	<div id="side" style="width:280px;">
  		<div id="left_menu">
  			<ul id="TabPage2" style="height:200px; margin-top:50px;">
  				<li id="left_tab1" class="selected" onClick="javascript:switchTab('TabPage2','left_tab1')" style="background:rgb(4.69.153);">
  					<img alt="影片模块" src="${pageContext.request.contextPath}/imgs/1_hover.jpg" width="33" height="33">
  				</li>
  				<li id="left_tab2" onClick="javascript:switchTab('TabPage2','left_tab2');" style="background:rgb(4.69.153);">
  					<img alt="订单模块" src="${pageContext.request.contextPath}/imgs/2.jpg" width="33" height="33">
  				</li>
  			</ul>
  		</div>
  		<div id="left_menu_cnt">
  			<div id="nav_resource">
  				<ul id="dleft_tab1" class="ztree">
  				</ul>
  			</div>
  		</div>
  	</div>
  	<script type="text/javascript">
		$(function(){
			$('#TabPage2 li').click(function(){
				var index = $(this).index();
				$(this).find('img').attr('src', 'imgs/'+ (index+1) +'_hover.jpg');
				$(this).css({background:'#fff'});
				/* $('#nav_module').find('img').attr('src', 'images/common/module_'+ (index+1) +'.png'); */
				$('#TabPage2 li').each(function(i, ele){
					if( i!=index ){
						$(ele).find('img').attr('src', 'imgs/'+ (i+1) +'.jpg');
						$(ele).css({background:'#044599'});
					}
				});
				// 显示侧边栏
				switchSysBar(true);
			});
			
			// 显示隐藏侧边栏
			$("#show_hide_btn").click(function() {
		        switchSysBar();
		    });
		});
		
		/**隐藏或者显示侧边栏**/
		function switchSysBar(flag){
			var side = $('#side');
	        var left_menu_cnt = $('#left_menu_cnt');
			if( flag==true ){	// flag==true
				left_menu_cnt.show(500, 'linear');
				side.css({width:'280px'});
				$('#top_nav').css({width:'77%', left:'304px'});
	        	$('#main').css({left:'280px'});
			}else{
		        if ( left_menu_cnt.is(":visible") ) {
					left_menu_cnt.hide(10, 'linear');
					side.css({width:'60px'});
		        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
		        	$('#main').css({left:'60px'});
		        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
		        } else {
					left_menu_cnt.show(500, 'linear');
					side.css({width:'280px'});
					$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
		        	$('#main').css({left:'280px'});
		        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
		        }
			}
		}
	</script>
  	<!-- main content -->
    <div id="main">
      	<iframe name="right" id="rightMain" src="msg.jsp" frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true"/>
    </div>
  </body>
</html>
