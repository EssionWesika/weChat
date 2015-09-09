<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setAttribute("menu",request.getParameter("menu"));%>
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand">微信公众号后台管理</a>
	</div>
	<div class="nav navbar-top-links navbar-right">
		<span class="navbar-brand" style="float: right;">退出</span>
		<span class="navbar-brand" style="float: right;">管理员：XXX</span>
	</div>

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav in" id="side-menu">
				<li><a href="index"><i class="fa fa-home fa-fw"></i>&nbsp;首页</a></li><!-- <i class="fa fa-dashboard fa-fw"></i> -->
				<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>Charts<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level collapse">
						<li><a href="flot.html">Flot Charts</a></li>
						<li><a href="morris.html">Morris.js Charts</a></li>
					</ul> <!-- /.nav-second-level -->
				</li>
				<li><a href="manager"><i class="fa fa-user fa-fw"></i>&nbsp;管理员中心</a></li>
				<li><a href="forms.html"><i class="fa fa-edit fa-fw"></i>Forms</a></li>
				<li><a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level collapse">
						<li><a href="panels-wells.html">Panels and Wells</a></li>
						<li><a href="buttons.html">Buttons</a></li>
						<li><a href="notifications.html">Notifications</a></li>
						<li><a href="typography.html">Typography</a></li>
						<li><a href="icons.html"> Icons</a></li>
						<li><a href="grid.html">Grid</a></li>
					</ul> <!-- /.nav-second-level -->
				</li>
				<li><a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level collapse">
						<li><a href="#">Second Level Item</a></li>
						<li><a href="#">Second Level Item</a></li>
						<li><a href="#">Third Level <span class="fa arrow"></span></a>
							<ul class="nav nav-third-level collapse">
								<li><a href="#">Third Level Item</a></li>
								<li><a href="#">Third Level Item</a></li>
								<li><a href="#">Third Level Item</a></li>
								<li><a href="#">Third Level Item</a></li>
							</ul> <!-- /.nav-third-level -->
						</li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level collapse">
						<li><a href="blank.html">Blank Page</a></li>
						<li><a href="login.html">Login Page</a></li>
					</ul> <!-- /.nav-second-level -->
				</li>
			</ul>
		</div>
	</div>
</nav>
