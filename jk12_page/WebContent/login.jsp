<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
function getXMLHttpRequest(){
	var xmlhttp;
	if(window.XMLHttpRequest){
		//主流浏览器
		xmlhttp=new XMLHttpRequest();
	}else{
		//ie6,7……
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

function onloginClick(){
	var xmlhttp=getXMLHttpRequest();
	xmlhttp.onreadystatechange=function (){
		if(xmlhttp.readyState==4&&xmlhttp.status==200){
			//请求状态是成功（200），读取响应完成（4）
			var  str=xmlhttp.responseText;
			
			//str可能是"true"或者"false"
			if(str=="true"){
				//登录成功
				document.getElementById("temp").innerHTML="登录成功";
				document.getElementById("temp").style.color="green";
				//跳转到主页面
				window.location.href="index1.html";
				//return false;
			}else{
				//登陆失败
				document.getElementById("temp").innerHTML="用户名/密码错误";
				document.getElementById("temp").style.color="red";
			}
		}
		
		
	};
	var url="login";
	//打开连接,以post方式打开url这个连接
	xmlhttp.open("post",url);
	//设置请求头信息
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	xmlhttp.send("username="+username+"&password="+password);
	
	
	
	
	//永远让登录点击失效
	return false;
}


function onregisterClick(){

				window.location.href="register.jsp";

}


</script>

</head>
<body>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-4 column">
		</div>
		<div class="col-md-4 column">
		<center>
			<h3>
				用户登录
			</h3>
			</center>
			<center>
			<h6 id="temp">
				欢迎使用
			</h6>
				</center>
			<form class="form-horizontal" role="form">
				<div class="form-group">
					
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" name="username" placeholder="用户名" />
					</div>
				</div>
				<div class="form-group">
					
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password" name="password" placeholder="密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							 <label><input type="checkbox" />记住我</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
							<!--当onclick指定为return  xxx()的时候，xxx()返回true就是本次点击生效，返回false就是本次点击无效  -->
						 <button type="submit" class="btn btn-default" onclick="return onloginClick()">登录</button>
						 <button type="submit" class="btn btn-primary" onclick="return onregisterClick()">注册</button>
					</div>
				
				</div>
			</form>
		</div>
		<div class="col-md-4 column">
		</div>
	</div>
</div>
</body>
</html>