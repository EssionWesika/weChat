<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<% String res_path = request.getContextPath() + "/resources";%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微信公众号后台管理</title>
<link href="<%=res_path%>/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=res_path%>/component/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=res_path%>/component/sb-admin-2/css/timeline.css" rel="stylesheet">
<link href="<%=res_path%>/component/sb-admin-2/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=res_path%>/component/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="<%=res_path%>/css/common.css" rel="stylesheet" type="text/css">
<style type="text/css">

</style>
</head>
<body>
	<%-- <%@ include file="../layout/header-menu.jsp"%> --%>
	<jsp:include page="../layout/header-menu.jsp" flush="true" >
		<jsp:param name="menu" value="manger"></jsp:param>
	</jsp:include>

	<div id="page-wrapper" style="min-height: 451px;">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header" style="margin-top: 20px;">管理员中心</h3>
			</div>
		</div>
		<div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">基础信息<span class="font-700 hand rit" data-target="#setid" data-toggle="modal"><i class="fa fa-gear"></i>&nbsp;设置</span></div>
                        <div class="panel-body">
                            <div class="alert alert-info">
								<span class="font-700">AppID(应用ID):</span>
								<span class="font-700">s65e4f7s6******</span>
							</div>
                            <div class="alert alert-info">
								<span class="font-700">AppSecret(应用密钥):</span>
								<span class="font-700">未设置</span>
							</div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
<!-- toggle -->
<div class="modal fade" id="setid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div id="login_div" class="modal-dialog" style="max-width: 400px;">
		<div class="modal-content" >
			<div class="modal-header" >
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">开发者ID设置</h4>
			</div>
		
			<div class="modal-body">
				<div class="form-group">
			            <input id="appid" type="text" class="form-control" placeholder="AppID(应用ID)">
			    </div>
				<div class="form-group">
			            <input id="appsecret" type="text" class="form-control" placeholder="AppSecret(应用密钥)">
			    </div>
				<%--<div class="form-group">

			    </div>--%>
			</div>
		
			<div class="modal-footer">
				<button type="button" id="subid"  class="btn btn-outline btn-primary btn-block font-700"><i class="fa fa-wechat"></i> &nbsp;保存</button>
				<%--<button type="button" id="subid" class="btn btn-primary disabled" title="先检测ID">保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
			</div>
		</div>
	</div>
</div>
<!--  -->

<!-- jQuery -->
<script src="<%=res_path%>/js/jquery.min.2.1.3.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<%=res_path%>/component/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=res_path%>/component/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=res_path%>/component/sb-admin-2/js/sb-admin-2.js"></script>
<script src="<%=res_path%>/js/common.js"></script>
<script type="text/javascript">
$(function(){
	$("#subid").click(function(){
		saveId(this);
    });
    
    
    
    

});
function toManager(){
	window.location.href="manager";
}
function saveId(ele){
	var $can=$(ele).parent();
	var a=$.trim($("#appid").val());
	var b=$.trim($("#appsecret").val());
	if(isNull([a,b])){
		alert("请填将数据填写完整");
		return false;
	}else{
		$.ajax({
			type: "post",
			url: "manager/checkid",
			data: {
				appid:a,
				secret:b
			},
			dataType: "text",
			success: function(data){
				if(data=="success"){
					$("#subid").removeClass("disabled");
					$can.html("<button type='button' class='btn btn-outline btn-success btn-block' onclick='toManager()'><i class='fa fa-wechat'></i> &nbsp;保存成功，点击关闭</button>");
				}else{
					$can.html("<button type='button' id='subid' title='点击重新保存' class='btn btn-outline btn-danger btn-block' onclick='saveId(this)'><i class='fa fa-wechat'></i> &nbsp;"+dc(data)+",请输入正确ID重新保存</button>");
				}
			},
			error: function(){
			}
		});
	}
}


</script>
</body>
</html>
