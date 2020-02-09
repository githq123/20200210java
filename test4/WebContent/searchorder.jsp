<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>    
    <title>订单查询</title>
  </head>
  
  <body>
  	<table id="orderDg" class="easyui-datagrid"></table>
  	
  	<!-- 工具栏 -->
	<div id="orderTb" style="padding:2px 5px;">
	   <a href="javascript:void(0)" class="easyui-linkbutton" 
	      iconCls="icon-edit" plain="true" onclick="editOrder();">修改订单/查看明细</a> 
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"
			onclick="removeOrder();" plain="true">删除订单</a>
	</div>
	
    <!-- 查询窗体 -->
	<div id="searchOrderTb" style="padding:2px 5px;">
		<form id="searchOrderForm">
			<div style="padding:3px">
				订单编号&nbsp;<input class="easyui-textbox" name="search_oid"
					id="search_oid" style="width:110px" />
			</div>
			<div style="padding:3px">
				客户名称&nbsp;<input style="width:115px;" id="search_userid"
					class="easyui-combobox" value="0" name="search_userid"
					data-options="valueField:'id',textField:'loginName',url:'user/getValidUser'">&nbsp;&nbsp;&nbsp;
				订单状态&nbsp;<select id="search_orderstatus" class="easyui-combobox" name="search_orderstatus" style="width:115px;">
					<option value="请选择" selected>--请选择--</option>
					<option value="未付款">未付款</option>
					<option value="已付款">已付款</option>
					<option value="待发货">待发货</option>
					<option value="已发货">已发货</option>
					<option value="已完成">已完成</option>
				</select>&nbsp;&nbsp;&nbsp; 订单时间 &nbsp;<input class="easyui-datebox"
					name="orderTimeFrom" id="orderTimeFrom" style="width:115px;" /> ~
				<input class="easyui-datebox" name="orderTimeTo" id="orderTimeTo"
					style="width:115px;" /> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-search" plain="true"
					onclick="searchOrder();">查找</a>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
	 	$(function() {
			$('#orderDg').datagrid({
				singleSelect : false,
				url : 'order/getAllOrder', //为datagrid设置数据源
				queryParams : {}, //查询条件
				pagination : true, //启用分页
				pageSize : 5, //设置初始每页记录数（页大小）
				pageList : [ 5, 10, 15 ], //设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#orderTb', //为datagrid添加工具栏
				header : '#searchOrderTb', //为datagrid标头添加搜索栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'oid',
					align : 'center',
					checkbox : true
				}, {
					field : 'u',
					title : '订单客户',
					formatter : function(value, row, index) {
						if (row.u) {
							return row.u.loginName;
						} else {
							return value;
						}
					},
					width : 100
				}, {
					field : 'orderStatus',
					title : '订单状态',
					width : 80
				}, {
					field : 'orderTime',
					title : '订单时间',
					width : 150
				}, {
					field : 'orderPrice',
					title : '订单金额',
					width : 100
				} ] ]
			});
		});
		
		function searchOrder(){		
			var search_oid=$('#search_oid').val();
			var orderStatus=$('#search_orderstatus').combobox("getValue");
			var userId=$('#search_userid').combobox("getValue");
			var orderTimeFrom=$('#orderTimeFrom').datebox("getValue");
			var orderTimeTo=$('#orderTimeTo').datebox("getValue");
			// 重新加载datagrid控件的数据源
			$('#orderDg').datagrid('load',{
				search_oid:search_oid,
				orderStatus:orderStatus,
				userId:userId,
				orderTimeFrom:orderTimeFrom,
				orderTimeTo:orderTimeTo
			});			
		}
		
		function removeOrder(){
			// 获取选中的订单记录行
			var rows=$('#orderDg').datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('Confirm','确认要删除么?',function(r){
					if(r){
						var ids="";
						// 获取选中订单记录的订单id，保存到ids中
						for(var i=0;i<rows.length;i++){
							ids+=rows[i].oid+",";
						}
						// 发送请求到服务器，执行删除操作
						$.post('order/deleteOrder',{
							oids:ids
						},function(result){
							if(result.success='true'){
								// 删除成功，重新加载datagrid控件
								$('#orderDg').datagrid('reload');
								$.messager.show({
									title:'提示信息',
									msg: result.message
								});
							}else{
								$.messager.show({
									title:'提示信息',
									msg: result.message
								});
							}
						},'json');
					}
				});
			}else{
				$.messager.alert('提示','请选择要删除的行！','info');
			}
		}
		
		function editOrder(){
			var rows=$('#orderDg').datagrid('getSelections');
			if(rows.length>0){
				var row=$('#orderDg').datagrid('getSelected');
				if($('#tabs').tabs('exists','修改订单')){
					$('#tabs').tabs('close','修改订单');
				}
				$('#tabs').tabs('add',{
					title:"修改订单",
					href: 'order/getOrder?oid='+row.oid,
					closable: true
				});
			}else{
				$.messager.alert('提示','请选择要修改的订单！','info');
			}
		}
	</script>
  </body>
</html>
