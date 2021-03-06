<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>微信公众号管理员登陆</title>
<link href="${res_path}/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${res_path}/component/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="${res_path}/component/sb-admin-2/css/sb-admin-2.css" rel="stylesheet">
<link href="${res_path}/component/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${res_path}/component/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet">
<link href="${res_path}/component/bootstrap-switch/css/highlight.css" rel="stylesheet">
<link href="${res_path}/component/bootstrap-switch/css/main.css" rel="stylesheet">
<link href="${res_path}/css/common.css" rel="stylesheet" type="text/css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
	body{
    	background: url("${res_path}/images/bgImg/bg005.jpg");
		/* background-size:100% ;
    	background-color: black; */
    }
	.color-sty{
		/* color: #4B6A76 !important; */
	}
	.input-add{
		background-color: rgba(255, 255, 255, 1) !important;
		border: 1px solid #286090;
	}
	.min-h{
		min-height: 250px;
	}
	.mana-info{
		margin-left: 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row" style="padding-top: 2%">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">微信公众号总管理</div>
					<div class="panel-body">
						<div class="row">
						<c:forEach items="${managerList}" var="manager">
							<div class="col-md-4">
								<div class="panel panel-default min-h">
									<div class="panel-heading">${manager.belong}<i class="fa fa-trash-o rit"></i></div>
									<ul class="list-group">
										<li class="list-group-item"><strong>AppID：</strong><span class="mana-info">${manager.hasAppID}</span></li>
										<li class="list-group-item"><strong>管理者：</strong><span class="mana-info">${manager.nickname}</span><i class="fa fa-cog rit"></i></li>
										<li class="list-group-item">
											<strong>最后登录时间：</strong>
											<span class="mana-info">
												<fmt:formatDate value="${manager.loginTime}" type="date" dateStyle="long" timeStyle="long" pattern="yyyy-MM-dd"/>
											</span>
										</li>
										<li class="list-group-item"><strong>旗下子管理员:</strong><span class="mana-info">${(empty manager.under)?'0':manager.under}</span>&nbsp;人<i class="fa fa-eye rit"></i></li>
										<li class="list-group-item ct"><i>权限设置</i></li>
									</ul>
								</div>
							</div>
						</c:forEach>
							<div class="col-md-4">
								<div class="min-h" style="margin-bottom: 20px;">
									<div class="panel-body ct " style="padding-top: 90px;">
										<button type="button" class="color-sty btn btn-outline btn-primary btn-lg" data-toggle="modal" data-target="#addModal">
											<i class="glyphicon-plus"></i>&nbsp;新增微信公众号
										</button>
									</div>
								</div>
							</div>
							

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<div class="modal fade" id="addModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">新增管理</h4>
      		</div>
			<div class="modal-body">

				<div class="panel panel-default">
					<div class="panel-heading">公众号设置</div>
					<div class="panel-body">
						<div class="form-group">
							<input id="belong" type="text" class="form-control unsee-input" placeholder="公司或组织名称">
						</div>
						<div class="form-group">
							<input id="appid" type="text" class="form-control unsee-input" placeholder="AppID(应用ID)" aria-describedby="addon3">
						</div>
						<div class="form-group">
							<input id="appsecret" type="text" class="form-control unsee-input" placeholder="AppSecret(应用密钥)">
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">设置管理者</div>
					<div class="panel-body">
						<div class="form-group">
							<input id="acc" type="text" class="form-control unsee-input" placeholder="管理者账号" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
						</div>
						<div class="form-group">
							<input id="pass" type="password" class="form-control unsee-input" placeholder="管理者密码" onblur="check_pass()">
						</div>
						<div class="form-group">
							<input id="nickname" type="text" class="form-control unsee-input" placeholder="使用者昵称或姓名" >
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">权限设置</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="lft" style="margin-top: 1%">应用ID与密钥是否对该管理者可见：</div>
								<div class="switch switch-small rit">
									<input class="authority" name="a" type="checkbox" checked />
								</div>
							</div>
						</div>
					</div>
				</div>


      		</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary"  onclick="createManager()">创建</button>
			</div>
		</div>
	</div>
</div>

<script src="${res_path}/js/jquery.min.2.1.3.js"></script>
<script src="${res_path}/component/bootstrap/js/bootstrap.min.js"></script>
<script src="${res_path}/component/metisMenu/metisMenu.min.js"></script>
<script src="${res_path}/component/sb-admin-2/js/sb-admin-2.js"></script>
<script src="${res_path}/component/bootstrap-switch/js/bootstrap-switch.js"></script>
<script src="${res_path}/component/bootstrap-switch/js/highlight.js"></script>
<script src="${res_path}/component/bootstrap-switch/js/main.js"></script>
<script src="${res_path}/js/common.js"></script>
<script type="text/javascript">
$(function() {
	$(".input-add").popover();
});
function check_pass(){
	var $pass = $("#pass");
	$pass.next(".err-msg").remove();
	var val = $.trim($pass.val());
	if(val.length<6){
		$pass.parent().append("<p class='err-msg' style='color: red'>请输入6位或以上数字或字母</p>");
		return false;
	}else{
		$pass.next(".err-msg").remove();
		return true;
	}
}
function createManager(){
	$(".err-msg").remove();
	var $acc = $("#acc");
	var $pass = $("#pass");
	var $belong = $("#belong");
	var $appid = $("#appid");
	var $appsecret = $("#appsecret");
	var $nickname = $("#nickname");
	var html = function(data){
		return "<p class='err-msg' style='color: red'>请输入"+data+"</p>";
	};
	if(isNull([$.trim($acc.val())])){
		$acc.after(html("账号"));
		return false;
	}
	if(isNull([$.trim($belong.val())])){
		$belong.after(html("公司或组织名称"));
		return false;
	}
	if(isNull([$.trim($appid.val())])){
		$appid.after(html("AppID"));
		return false;
	}
	if(isNull([$.trim($appsecret.val())])){
		$appsecret.after(html("密匙"));
		return false;
	}
	if(isNull([$.trim($nickname.val())])){
		$nickname.after(html("姓名或昵称"));
		return false;
	}
	if(check_pass()){
		$.ajax({
			type: "post",
			url: "createManager",
			data: {
				acc: $.trim($acc.val()),
				pass: $.trim($pass.val()),
				belong: $.trim($belong.val()),
				appid: $.trim($appid.val()),
				appsecret: $.trim($appsecret.val()),
				nickname:$.trim($nickname.val()),
				authority:getRadio()
			},
			dataType: "text",
			success: function(data){
				if(data=="success"){
					alert("创建成功");
					window.location.reload();
				}else{
					alert(dc(data));
				}
			},
			error: function(){
				alert("服务器已关闭");
			}
		});
	}
}

function getRadio(){
	var $au = $(".authority");
	var data = '{';
	$.each($au, function(i, n){
		data+='"'+$(n).attr("name")+'":"'+((n.checked)?0:1)+'"';
		if(i!=($au.length-1)){
			data+=',';
		}
	});
	data=data+'}';
	return data.toString();//'{"name":"John"}'
}
</script>
</body>
</html>
