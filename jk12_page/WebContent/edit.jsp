<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3>
				修改书籍
			</h3>
			<form role="form" method="post" action="edit">
				<input type="hidden" name="id" value="${product.id}"/>
				<div class="form-group">
					 <label for="exampleInputEmail1">名称</label><input value="${product.name}" type="text" class="form-control" id="name" name="name" />
				</div>
				<div class="form-group">
					 <label for="exampleInputEmail1">类型</label><input value="${product.category}" type="text" class="form-control" id="category" name="category" />
				</div>
				<div class="form-group">
					 <label for="exampleInputEmail1">库存</label><input value="${product.num}" type="text" class="form-control" id="num" name="num" />
				</div>
				<div class="form-group">
					 <label for="exampleInputEmail1">价格</label><input value="${product.price}" type="text" class="form-control" id="price" name="price" />
				</div>
				<button type="submit" class="btn btn-default">保存</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>