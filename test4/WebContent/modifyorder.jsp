<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>订单修改页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table id="edit_odbox"></table>
	<div id="edit_ordertb" style="padding:2px 5px;">
		<!-- 订单修改页面工具栏部分 -->
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="edit_addOrderDts();">添加订单明细</a><a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-save" plain="true" onclick="edit_saveorder();">保存订单</a><a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="edit_removeOrderDts();">删除订单明细</a>
		</div>

		<!-- 订单主表数据部分  -->
		<div id="edit_divOrder">
			<div style="padding:3px">
				客户名称&nbsp;<input style="width:115px;" id="edit_userid"
					class="easyui-combobox" name="edit_userid"
					value="${requestScope.o.u.id }"
					data-options="valueField:'id',textField:'loginName',url:'user/getValidUser'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				订单金额&nbsp;<input type="text" name="edit_orderprice"
					id="edit_orderprice" class="easyui-textbox" readonly="readonly"
					style="width:115px" /> &nbsp;&nbsp;
			</div>
			<div style="padding:3px">
				订单日期&nbsp;<input type="text" name="edit_ordertime"
					id="edit_ordertime" value="${requestScope.o.orderTime }"
					class="easyui-datebox" style="width:115px" /> &nbsp;&nbsp; <input
					type="hidden" name="oid" id="oid" value="${requestScope.o.oid }"
					style="width:10px" /> &nbsp;&nbsp; 订单状态&nbsp;<select
					id="edit_orderstatus" class="easyui-combobox" name="edit_status"
					style="width:115px;">
					<c:if test='${requestScope.o.orderStatus == "未付款"}'>
						<option value="未付款" selected>未付款</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus != "未付款"}'>
						<option value="未付款">未付款</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus == "已付款"}'>
						<option value="已付款" selected>已付款</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus != "已付款"}'>
						<option value="已付款">已付款</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus == "待发货"}'>
						<option value="待发货" selected>待发货</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus != "待发货"}'>
						<option value="待发货">待发货</option>
					</c:if>

					<c:if test='${requestScope.o.orderStatus == "已发货"}'>
						<option value="已发货" selected>已发货</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus != "已发货"}'>
						<option value="已发货">已发货</option>
					</c:if>

					<c:if test='${requestScope.o.orderStatus == "已完成"}'>
						<option value="已完成" selected>已完成</option>
					</c:if>
					<c:if test='${requestScope.o.orderStatus != "已完成"}'>
						<option value="已完成">已完成</option>
					</c:if>
				</select>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var $editodbox = $('#edit_odbox');
		$(function() {
			$editodbox
					.datagrid({
						url : 'order/getOrderDts?oid=${requestScope.o.oid }', // 获取订单明细数据
						onLoadSuccess : function(data) { // 当数据加载成功后，使得datagrid控件上所有行都处于可编辑状态
							var rows = $editodbox.datagrid('getRows');
							for (var i = 0; i < rows.length; i++) {
								var index = $editodbox.datagrid('getRowIndex',
										rows[i]);
								$editodbox.datagrid('beginEdit', index);
							}
						},
						rownumbers : true,
						singleSelect : false,
						fit : true,
						toolbar : '#edit_ordertb',
						header : '#edit_divOrder',
						columns : [ [
								{
									title : '序号',
									field : 'oid',
									align : 'center',
									checkbox : true
								},
								{
									field : 'mealId',
									title : '餐品名',
									width : 300,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'mealId',
											textField : 'mealName',
											url : 'meal/getOnSaleMeal',
											onChange : function(newValue,
													oldValue) {
												var rows = $editodbox
														.datagrid('getRows');
												var orderprice = 0;
												for (var i = 0; i < rows.length; i++) {

													var mealIdEd_1 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealId'
																	});
													var priceEd_1 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealPrice'
																	});
													var totalpriceEd_1 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'totalprice'
																	});
													var mealCountEd_1 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealCount'
																	});

													if (mealIdEd_1 != null) {
														var mealId = $(
																mealIdEd_1.target)
																.combobox(
																		'getValue');
														$
																.ajax({
																	type : 'POST',
																	url : 'meal/getMealPriceByMealId',
																	data : {
																		mealId : mealId
																	},
																	success : function(
																			result) {
																		$(
																				priceEd_1.target)
																				.numberbox(
																						'setValue',
																						result);
																		$(
																				totalpriceEd_1.target)
																				.numberbox(
																						'setValue',
																						result
																								* $(
																										mealCountEd_1.target)
																										.numberbox(
																												'getValue'));
																		orderprice = Number(orderprice)
																				+ Number($(
																						totalpriceEd_1.target)
																						.numberbox(
																								'getValue'));
																	},
																	dataType : 'json',
																	async : false
																});
													}
													$("#edit_orderprice")
															.textbox(
																	"setValue",
																	orderprice);
												}

											}
										}
									}
								},
								{
									field : 'mealPrice',
									title : '单价',
									width : 80,
									editor : {
										type : "numberbox",
										options : {
											editable : false
										}
									}
								},
								{
									field : 'mealCount',
									title : '数量',
									width : 50,
									editor : {
										type : 'numberbox',
										options : {
											onChange : function(newValue,
													oldValue) {
												var rows = $editodbox
														.datagrid('getRows');
												var orderprice = 0;
												for (var i = 0; i < rows.length; i++) {
													var mealIdEd_2 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealId'
																	});
													var priceEd_2 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealPrice'
																	});
													var totalpriceEd_2 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'totalprice'
																	});

													var mealCountEd_2 = $editodbox
															.datagrid(
																	'getEditor',
																	{
																		index : i,
																		field : 'mealCount'
																	});
													if (mealIdEd_2 != null
															&& mealCountEd_2 != null) {
														$(totalpriceEd_2.target)
																.numberbox(
																		'setValue',
																		$(
																				priceEd_2.target)
																				.numberbox(
																						'getValue')
																				* $(
																						mealCountEd_2.target)
																						.numberbox(
																								'getValue'));
														orderprice = Number(orderprice)
																+ Number($(
																		totalpriceEd_2.target)
																		.numberbox(
																				'getValue'));
													}
												}
												$("#edit_orderprice").textbox(
														"setValue", orderprice);
											}
										}
									}
								}, {
									field : 'totalprice',
									title : '小计',
									width : 100,
									editor : {
										type : "numberbox",
										options : {
											editable : false
										}
									}
								} ] ]
					});
		});

		function edit_addOrderDts() {
			$editodbox.datagrid('appendRow', {
				mealCount : '1',
				mealPrice : '0',
				totalprice : '0'
			});
			var rows = $editodbox.datagrid('getRows');
			$editodbox.datagrid('beginEdit', rows.length - 1);
		}

		function edit_removeOrderDts() {
			var rows = $editodbox.datagrid('getSelections');
			var edit_orderprice = $("#edit_orderprice").textbox("getValue");
			if (rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					var index = $editodbox.datagrid('getRowIndex', rows[i]);
					var totalpriceEd2 = $editodbox.datagrid('getEditor', {
						index : index,
						field : 'totalprice'
					});
					edit_orderprice = edit_orderprice
							- Number($(totalpriceEd2.target).numberbox(
									'getValue'));
					$editodbox.datagrid('deleteRow', index);
				}
				$("#edit_orderprice").textbox("setValue", edit_orderprice);
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}

		function edit_saveorder() {
			// 获取订单客户
			var userid = $('#edit_userid').combobox('getValue');
			if (userid == 0) {
				$.messager.alert('提示', '请选择客户名称', 'info');
			} else {
				// 调用自定义方法取消datagrid控件的行编辑状态
				edit_endEdit();
				// 定义order数组存放订单主表数据
				var order = [];
				// 获取订单时间
				var ordertime = $('#edit_ordertime').datebox('getValue');
				// 获取订单状态
				var orderstatus = $('#edit_orderstatus').datebox('getValue');
				// 获取订单的id号
				var oid = $('#oid').val();
				// 获取订单总金额
				var orderprice = $('#edit_orderprice').textbox('getValue');
				// 向数组的末尾添加元素（订单主表数据）
				order.push({
					orderTime : ordertime,
					userId : userid,
					orderStatus : orderstatus,
					oid : oid,
					orderPrice : orderprice
				});
				// 获取订单明细(datagrid控件中的记录)
				if ($editodbox.datagrid('getChanges').length) {
					// 获取datagrid控件中插入的记录行
					var inserted = $editodbox
							.datagrid('getChanges', "inserted");
					// 获取datagrid控件中删除的记录行
					var deleted = $editodbox.datagrid('getChanges', "deleted");
					// 获取datagrid控件中更新的记录行
					var updated = $editodbox.datagrid('getChanges', "updated");
					// 定义effectRow,保存inserted和order
					var effectRow = new Object();
					if (inserted.length) {
						// JSON.stringify将对象数组转换成JSON字符串
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					if (updated.length) {
						// JSON.stringify将对象数组转换成JSON字符串
						effectRow["updated"] = JSON.stringify(updated);
					}
					if (deleted.length) {
						// JSON.stringify将对象数组转换成JSON字符串
						effectRow["deleted"] = JSON.stringify(deleted);
					}
					effectRow["order"] = JSON.stringify(order);
					// 提交请求
					$.post("order/commitModifyOrder", effectRow,
						function(data) {
							if (data == 'success') {
								$.messager.alert('提示', '修改成功！', 'info');
								// 提交datagird控件的当前行
								$editodbox.datagrid('acceptChanges');
								if ($('#tabs').tabs('exists', '修改订单')) {
									$('#tabs').tabs('close', '修改订单');
								}
								// 重新加载“查询订单页”中用于显示订单记录的 datagrid控件的数据
								$('#orderDg').datagrid('reload');
							} else {
								$.messager.alert('提示', '修改失败！', 'info');
							}
						});
				}

			}

		}

		function edit_endEdit() {
			var rows = $editodbox.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$editodbox.datagrid('endEdit', i);
			}
		}
	</script>
</body>
</html>
