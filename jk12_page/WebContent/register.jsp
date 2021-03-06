<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var n = 0;	function onimgclick() {
		//把img上的验证重新生成
		//使得img的src重新请求我们图片验证码的那个servlet
		//设置src的时候，浏览器会自己检查src是否是和之前的src一样，如果一样，就不请求网络了。
		document.getElementById("img").src = "imagecode?n=" + n;
		n++;
	}
</script>
<script>
	function getXMLHttpRequest() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			//主流浏览器
			xmlhttp = new XMLHttpRequest();
		} else {
			//ie6,7……
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	var tempusername = false;
	var temppassword = false;
	var tempcheckcode = false;
	function onusernamekeyup() {
		var username = document.getElementById("username").value;
		var usernametext = document.getElementById("usernametext");
		if (username.length < 6) {
			//用户名不可少于6位。
			usernametext.style.color = "red";
			usernametext.innerHTML = "用户名不可少于6位";
			tempusername = false;
			return;
		}
		var xmlhttp = getXMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//请求状态是成功（200），读取响应完成（4）
				var str = xmlhttp.responseText;
				if (str == "true") {
					//可用
					usernametext.style.color = "green";
					usernametext.innerHTML = "用户名可用";
					tempusername = true;
				} else {
					//不可用
					usernametext.style.color = "red";
					usernametext.innerHTML = "用户名已经存在";
					tempusername = false;
				}
			}

		};
		var url = "checkusername";
		//打开连接,以post方式打开url这个连接
		xmlhttp.open("post", url);
		//设置请求头信息
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send("username=" + username);

		//永远让登录点击失效
		return false;
	}

	function oncheckcodekeyup() {

		var userCheckCode = document.getElementById("checkcode").value;
		var checkcodetext = document.getElementById("checkcodetext");
		if (userCheckCode.length != 4) {
			//断定，验证码输入错误
			checkcodetext.style.color = "red";
			checkcodetext.innerHTML = "验证码位数不对";
			tempcheckcode = false;
			return;
		}
		var xmlhttp = getXMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//请求状态是成功（200），读取响应完成（4）
				var str = xmlhttp.responseText;
				//str可能是“一心一意”“三心二意”……
				//把str和用户输入的checkcode对比
				if (str == userCheckCode) {
					//验证码输入正确
					checkcodetext.style.color = "green";
					checkcodetext.innerHTML = "验证码正确";
					tempcheckcode = true;
				} else {
					//验证码输入错误
					checkcodetext.style.color = "red";
					checkcodetext.innerHTML = "验证码有误";
					tempcheckcode = false;
				}
			}

		};
		var url = "textcode";
		//打开连接,以post方式打开url这个连接
		xmlhttp.open("post", url);
		//设置请求头信息
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send();

		//永远让登录点击失效
		return false;
	}

	function onpasswordchanged() {
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		var passwordtext = document.getElementById("passwordtext");
		if (password == repassword) {
			passwordtext.style.color = "green";
			passwordtext.innerHTML = "两次密码输入一致";
			temppassword = true;
		} else {
			passwordtext.style.color = "red";
			passwordtext.innerHTML = "两次密码输入不一致";
			temppassword = false;
		}

	}
	function onregisterclick() {
		return tempusername && temppassword && tempcheckcode;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-4 column"></div>
			<div class="col-md-4 column">
				<center>
					<h3>用户注册</h3>
				</center>
				<form role="form" action="register" method="post">
					<div class="form-group">
						<label for="exampleInputEmail1" id="usernametext">用户名</label><input
							type="text" onkeyup="onusernamekeyup()" class="form-control"
							id="username" name="username" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密码</label><input
							onkeyup="onpasswordchanged();" type="password"
							class="form-control" id="password" name="password" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1" id="passwordtext">密码重复</label><input
							onkeyup="onpasswordchanged();" type="password"
							class="form-control" id="repassword" />
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1" id="checkcodetext">验证码</label><input
							type="text" class="form-control" id="checkcode" name="checkcode"
							onkeyup="oncheckcodekeyup();" /> <img id="img" src="imagecode"
							onclick="onimgclick();" />
					</div>

					<button type="submit" class="btn btn-default"
						onclick="return onregisterclick();">注册</button>
				</form>
			</div>
			<div class="col-md-4 column"></div>
		</div>
	</div>

</body>
</html>