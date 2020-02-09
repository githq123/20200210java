<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>餐品列表</title>
</head>

<body>
	<table id="meal_dg" class="easyui-datagrid"></table>

 
    <!-- 工具栏 -->
	<div id="meal_tb" style="padding: 2px 5px;">
		<a href="javascript:void(0)" iconCls="icon-add"
			class="easyui-linkbutton" onclick="openAddMealDlg()">添加</a> <a
			href="javascript:void(0)" iconCls="icon-edit"
			class="easyui-linkbutton" onclick="openUpdateMealDlg()">修改</a> <a
			href="javascript:void(0)" iconCls="icon-remove"
			class="easyui-linkbutton" onclick="soldOut()">下架</a>
	</div>
	
	<!-- 搜索栏 -->
	<div id="meal_searchtb">
		<form id="meal_searchForm">
			<div style="padding: 10px  5px  1px   5px;">
				名称&nbsp;&nbsp;<input class="easyui-textbox" name="mealName"
					id="mealName" style="width: 200px" />&nbsp;&nbsp;&nbsp;&nbsp;
				类型&nbsp;&nbsp;<input class="easyui-combobox"
					name="mealseries.seriesId" id="mealseries.seriesId"
					style="width: 110px"
					data-options="valueField:'seriesId',textField:'seriesName',url:'mealSeries/list'" />
				&nbsp;&nbsp;<a href="javascript:void(0)" iconCls="icon-search"
					plain="true" class="easyui-linkbutton" onclick="searchMeal();">查找</a>
			</div>
		</form>
	</div>

	<!-- 添加餐品信息对话框 -->
	<div id="meal_dlg" class="easyui-dialog" title="New Topic"
		closed="true" style="width:500px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="meal_ff" method="POST" action=""
				enctype="multipart/form-data">
				<table cellpadding="5">
					<tr>
						<td>状态:</td>
						<td><select id="mealStatus" class="easyui-combobox"
							name="mealStatus" style="width:150px;">
								<option value="1">在售</option>
								<option value="0">下架</option>
						</select></td>
					</tr>
					<tr>
						<td>菜系:</td>
						<td><input style="width:150px;" id="mealseries.seriesId"
							class="easyui-combobox" name="mealseries.seriesId"
							data-options="valueField:'seriesId',textField:'seriesName',url:'mealSeries/list'"></input>
						</td>
					</tr>
					<tr>
						<td>名称:</td>
						<td><input class="easyui-textbox" type="text" id="mealName"
							name="mealName" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>简介:</td>
						<td><input class="easyui-textbox" type="text"
							id="mealSummarize" name="mealSummarize"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><input class="easyui-textbox" type="text"
							id="mealDescription" name="mealDescription"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>价格:</td>
						<td><input class="easyui-textbox" type="text" id="mealPrice"
							name="mealPrice" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>图片:</td>
						<td><input class="easyui-filebox" id="pic" name="pic"
							style="width:200px" value="选择图片"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveMeal();">保&nbsp;&nbsp;存</a>&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm();">清&nbsp;&nbsp;空</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#meal_dg').datagrid({
				singleSelect : false,
				url : 'meal/list', //为datagrid控件设置数据源
				queryParams : {}, //查询条件
				pagination : true, // 启用分页
				pageSize : 10, // 初始每页记录数
				pageList : [ 10, 15, 20 ], // 设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#meal_tb', //给datagrid添加工具栏
				header : '#meal_searchtb', //给datagrid添加搜索工具栏
				columns : [ [ {
					title : '序号',
					field : 'mealId',
					align : 'center',
					checkbox : true
				}, {
					title : '名称',
					field : 'mealName',
					width : 150
				}, {
					title : '菜系',
					field : 'mealseries',
					width : 60,
					formatter : function(value, row, index) {
						if (row.mealseries) {
							return row.mealseries.seriesName;
						} else {
							return value;
						}
					}
				}, {
					title : '简介',
					field : 'mealSummarize',
					width : 300
				}, {
					title : '描述',
					field : 'mealDescription',
					width : 450
				}, {
					title : '价格',
					field : 'mealPrice',
					width : 50
				}, {
					title : '状态',
					field : 'mealStatus',
					width : 60,
					formatter : function(value, row, index) {
						if (row.mealStatus == 1) {
							return "在售";
						} else {
							return "下架";
						}
					}
				} ] ]
			});
		});

        // 查询餐品
		function searchMeal() {
			$('#meal_dg').datagrid('load',
					convertArray($('#meal_searchForm').serializeArray()));
		}

		function convertArray(o) { //主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。 
			var v = {};
			for ( var i in o) {
				if (typeof (v[o[i].name]) == 'undefined')
					v[o[i].name] = o[i].value;
				else
					v[o[i].name] += "," + o[i].value;
			}
			return v;
		}

		var urls;
		// 打开新增餐品对话框
		function openAddMealDlg() {
			$('#meal_dlg').dialog('open').dialog('setTitle', '新增餐品');
			$('#meal_ff').form('clear');
			urls = 'meal/addMeal'; //将请求提交到MealController类的addMeal方法
		}

		// 保存餐品
		function saveMeal() {
			$('#meal_ff').form("submit", {
				url : urls,
				success : function(result) {
					var result = eval('(' + result + ')'); // eval方法将JSON字符串转成JSON对象
					if (result.success == 'true') {
						$('#meal_dg').datagrid("reload");
						$('#meal_dlg').dialog("close");
					}
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}

		// 餐品下架
		function soldOut() {
			// 获取datagrid控件中选中的行
			var rows = $('#meal_dg').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认要下架么?', function(r) {
					if (r) {
						var ids = "";
						for (var i = 0; i < rows.length; i++) {
							ids += rows[i].mealId + ",";
						}
						$.post('meal/modifyMealStatus', {
							id : ids
						}, function(result) {
							if (result.success == 'true') {
								// 重新加载datagrid
								$('#meal_dg').datagrid('reload');
								$.messager.show({
									title : "提示信息",
									msg : result.message
								});
							} else {
								$.messager.show({
									title : "提示信息",
									msg : result.message
								});
							}
						}, 'json');
					}
				});
			} else {
				$.messager.alert('提示', '请选择要下架的餐品', 'info');
			}
		}

		// 打开餐品修改对话框
		function openUpdateMealDlg() {
			var row = $('#meal_dg').datagrid('getSelected');
			if (row != null) {
				// 打开修改餐品对话框(即添加餐品对话框)
				$('#meal_dlg').dialog('open').dialog('setTitle', '修改餐品');
				// 给对话框中的Form表单中的文本域绑定值
				$('#meal_ff').form("load", {
					"mealStatus" : row.mealStatus,
					"mealseries.seriesId" : row.mealseries.seriesId,
					"mealName" : row.mealName,
					"mealSummarize" : row.mealSummarize,
					"mealDescription" : row.mealDescription,
					"mealPrice" : row.mealPrice
				});
				urls = "meal/updateMeal?mealId=" + row.mealId;
			} else {
				$.messager.alert('提示', '请选择要修改的餐品', 'info');
			}
		}
	</script>
</body>
</html>
