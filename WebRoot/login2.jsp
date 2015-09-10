<%@ page language="java" import="java.util.*" import="com.wxsys.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
String lang = (String)request.getSession().getAttribute("locale");
/* request.setAttribute("useyzm",); */
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><%=SpringUtils.getMessage("login.title",lang)%></title>
    <link rel="shortcut icon" href="${res_path}/images/favicon.ico">
    <link href="${res_path}/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${res_path}/component/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="${res_path}/component/sb-admin-2/css/sb-admin-2.css" rel="stylesheet">
    <link href="${res_path}/component/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${res_path}/css/common.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
    .ver-code{
        width: 100px;
        height: 32px;
    }
    .input-group-yzm{
        background-color: #EEE;
        border: 1px solid #CCC;
        border-radius: 4px;
        vertical-align: middle;
        width: 1%;
        display: table-cell;
        padding: 0;
        margin-bottom: 5px;
    }
    body{
    	background: url("${res_path}/images/bgImg/bg004_edit.jpg") no-repeat;
		background-size:100% ;
    	background-color: black;
    }
    .unsee-div{
    	background-color: rgba(255,255,255,0.0);
    }
    .unsee-input{
    	background-color:transparent;
    	border: 1px solid #286090;
    }
    .login-div{
    	margin-top: 10%;
		float: right;
		/* margin-right: 10%;
		margin-left: 10%; */
    }
    /* @media screen and (max-width:1170px){
		.login-div{ margin-right:0;margin-left:0; }
	} */
	/* @media screen and (max-width:768px){
		.container{ width: 100%; }
	} */ 
</style>
</head>
<body>
<%-- <body BACKGROUND= "${res_path}/images/bgImg/bg004_edit.jpg" style="background-repeat: no-repeat;background-attachment: scroll;margin: auto;"> --%> 
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-xs-12 login-div">
                <div class="login-panel panel-default unsee-div">
                    <%-- <div class="panel-heading">
                        <h3 class="panel-title"><%=SpringUtils.getMessage("login.title",lang)%></h3>
                    </div> --%>
                    <div class="panel-body">
                        <div class="form-group ">
                            <input type="text" class="form-control unsee-input" placeholder="<%=SpringUtils.getMessage("login.acc",lang)%>" id="acc" autofocus>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control unsee-input" placeholder="<%=SpringUtils.getMessage("login.pass",lang)%>" id="pass" >
                        </div>
                        <c:if test="${(!empty logingTimes) && logingTimes>=useyzm}">
                        <div class="input-group form-group">
                            <input type="text" class="form-control unsee-input" placeholder="<%=SpringUtils.getMessage("login.ver",lang)%>" id="verify">
                            <span class="input-group-yzm" id="yzm">
                                <img src="verifyCode" class="ver-code" />
                            </span>
                        </div>
                        </c:if>
                        <button class="btn btn-primary btn-outline btn-block" onclick="login()">
                        	<%=SpringUtils.getMessage("login.login",lang)%>
						</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="${res_path}/js/jquery.min.2.1.3.js"></script>
<script src="${res_path}/component/bootstrap/js/bootstrap.min.js"></script>
<script src="${res_path}/js/bootbox.min.js"></script>
<script src="${res_path}/component/metisMenu/metisMenu.min.js"></script>
<script src="${res_path}/component/sb-admin-2/js/sb-admin-2.js"></script>
<script src="${res_path}/js/common.js"></script>
<script type="text/javascript">
$(function(){
    $("input").blur(function(){
        if(!isNull([$(this).val()])){
            $(".error-color").remove();
        }
    });
    $("#yzm").click(function(){
        var num = parseInt(100*Math.random());
        $("#yzm").empty();
        $(this).html("<img src='verifyCode?num="+num+"' class='ver-code'/>");
    })
});

function login(){
    $(".error-color").remove();
    var $acc=$("#acc");
    var $pass=$("#pass");
    var ver= $("#verify").val()==""?0:$("#verify").val();
    var ptHtml=function(data){
        return "<div class='form-group error-color'>"+data+"</div>";
    };
    /* if(isNull([ver])){
        alert("请输入验证码");
        return false;
    } */
    if(isNull([$.trim($acc.val())])){
        $acc.parent().after(ptHtml("请出入账号"));
    }else if(isNull([$.trim($pass.val())])){
        $pass.parent().after(ptHtml("请出入密码"));
    }else{
        $.ajax({
            type: "post",
            url: "login",
            data: {
                acc: $.trim($acc.val()),
                pass: $.trim($pass.val()),
                ver:ver
            },
            dataType: "text",
            success: function(data){
            	var arr = data.split("_");
                if(arr[0]=="success"){
                    post('manager',{type:arr[1]});
                }else{
                    alert(dc(data));
                    window.location.reload();
                }
            },
            error: function(){
                alert("服务器已关闭");
            }
        });
    }
}
</script>
</body>
</html>
