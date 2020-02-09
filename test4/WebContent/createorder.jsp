<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>后台管理-订单管理-创建订单</title>

</head>

<body>
	<table id="odbox" class="easyui-datagrid"></table>

	<!-- 创建datagird控件的工具栏     -->
	<div id="ordertb" style="padding: 2px 5px;">
		<a href="javascript:void(0)" iconCls="icon-add"
			class="easyui-linkbutton" onclick="addOrderDts()">添加订单明细</a> <a
			href="javascript:void(0)" iconCls="icon-edit"
			class="easyui-linkbutton" onclick="saveorder()">保存订单</a> <a
			href="javascript:void(0)" iconCls="icon-remove"
			class="easyui-linkbutton" onclick="removeOrderDts()">删除订单明细</a>
	</div>

	<!-- 创建订单主表数据录入布局 -->
	<div id="divOrder">
		<div style="padding:3px">
			客户名称&nbsp;<input style="width:115px;" id="create_userid"
				class="easyui-combobox" name="create_userid" value="0"
				data-options="valueField:'id',textField:'loginName',url:'user/getValidUser'">&nbsp;&nbsp;&nbsp;
			订单金额&nbsp;<input type="text" name="create_orderprice"
				id="create_orderprice" class="easyui-textbox" readonly="readonly"
				style="width:115px" /> &nbsp;&nbsp;
		</div>
		<div style="padding:3px">
			订单日期&nbsp;<input type="text" name="create_ordertime"
				id="create_ordertime" value="<%=new Date() %>" class="easyui-datebox" style="width:115px" />
			&nbsp;&nbsp; 订单状态&nbsp;<select id="create_orderstatus"
				class="easyui-combobox" name="create_orderstatus" style="width:115px;">
				<option value="未付款" selected>未付款</option>
				<option value="已付款">已付款</option>
				<option value="待发货">待发货</option>
				<option value="已发货">已发货</option>
				<option value="已完成">已完成</option>
			</select>
		</div>
	</div>


	<script type="text/javascript">
		var $odbox = $('#odbox');
		$(function() {
			$odbox.datagrid({
				rownumbers : true,
				singleSelect : false,
				fit : true,
				toolbar : '#ordertb',
				header : '#divOrder',
				columns : [ [ {
					title : '序号',
					field : '',
					align : 'center',
					checkbox : true
				}, {
					field : 'mealId',
					title : '餐品名',
					width : 300,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'mealId',
							textField : 'mealName',
							url : 'meal/getOnSaleMeal', 
							onChange : function(newValue, oldValue) {							   					
								// 当选择了不同的餐品，更新当前行的餐品价格、小计、订单金额
								var rows = $odbox.datagrid('getRows');
								var orderprice = 0;
								for (var i = 0; i < rows.length; i++) {
									//获取餐品下拉列表框编辑器,getEditor方法用于获取指定编辑器
									var mealIdEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'mealId'
									});
									// 获取单价文本框编辑器
									var priceEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'mealPrice'
									});
									// 获取数量文本框编辑器
									var mealCountEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'mealCount'
									});
									// 获取小计文本框编辑器
									var totalpriceEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'totalprice'
									});
									
									if(mealIdEd!=null){
										// 获取选择餐品的id
										var mealId=$(mealIdEd.target).combobox('getValue');
										// 采用Ajax发送向服务器发送请求，根据餐品id获取餐品对象，最终获得餐品价格
										$.ajax({
											type: 'POST',
											url:'meal/getMealPriceByMealId',
											data:{mealId:mealId},
											success: function(result){
												// 根据获取的价格，设置单价数字框编辑器的值
												$(priceEd.target).numberbox('setValue',result);
												// 设置小计数字框编辑器的值
												$(totalpriceEd.target).numberbox('setValue',result*$(mealCountEd.target).numberbox('getValue'));
												// 设置orderprice的值
												orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
												},
												dataType: 'json',
												async: false
										});
									}
								}
								// 循环结束后，给订单主表部分的名称为create_orderprice的文本域设置值
								$('#create_orderprice').textbox('setValue',orderprice);
							} // end of onChange
						}
					}
				},{
					field: 'mealPrice',
					title: '单价',
					width: 80,
					editor: {
						type:"numberbox",
						options: {
							editable: false
						}
					}
				},{
					field: 'mealCount',
					title: '数量',
					width: 50,
					editor: {
						type:"numberbox",
						options: {
							onChange: function(newValue, oldValue) {	
								var rows = $odbox.datagrid('getRows');
								var orderprice = 0;
								for (var i = 0; i < rows.length; i++) {
									// 获取单价文本框编辑器
									var priceEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'mealPrice'
									});
									// 获取数量文本框编辑器
									var mealCountEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'mealCount'
									});
									// 获取小计文本框编辑器
									var totalpriceEd=$odbox.datagrid('getEditor', {
										index : i,
										field : 'totalprice'
									});
									// 设置小计数字框编辑器的值
									$(totalpriceEd.target).numberbox('setValue',$(priceEd.target).numberbox('getValue')*$(mealCountEd.target).numberbox('getValue'));
									// 设置orderprice的值
									orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
								}
								
								// 循环结束后，给订单主表部分的名称为create_orderprice的文本域设置值
								$('#create_orderprice').textbox('setValue',orderprice); 																
							} // end of onChange
						}
					}
					
				},{
					field: 'totalprice',
					title: '小计',
					width: 100,
					editor: {
						type:"numberbox",
						options: {
							editable: false
						}
					}
				} ] ]
			});

		});
		
		
		// 给datagrid控件添加一个编辑行
		function addOrderDts(){
			$odbox.datagrid('appendRow',{
				mealCount:'1',
				totalprice:'0'
			});
			var rows=$odbox.datagrid('getRows');
			$odbox.datagrid('beginEdit',rows.length-1); //让添加的行处于可编辑状态
		}
		
		// 取消选中的订单明细
		function removeOrderDts(){
			var rows=$odbox.datagrid('getSelections');
			if(rows.length>0){
				// 获取订单主表部分文本框中的订单金额
				var create_orderprice=$('#create_orderprice').textbox("getValue");
				for(var i=0;i<rows.length;i++){
					var index=$odbox.datagrid('getRowIndex',rows[i]);
					// 获取该行中的小计数字框编辑器					
					var totalpriceEd = $odbox.datagrid('getEditor', {
							index : index,
							field : 'totalprice'
					});
					create_orderprice=create_orderprice-Number($(totalpriceEd.target).numberbox('getValue'));
					$odbox.datagrid('deleteRow',index);
				}
				// 重新给订单主表部分文本框中的订单金额设置值
				$('#create_orderprice').textbox('setValue',create_orderprice);
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}
		
		function saveorder(){
			// 获取订单用户
			var userid=$('#create_userid').combobox('getValue');
			if(userid==0){
				$.messager.alert('提示','请选择客户名称','info');
			}else{
				// 调用自定义方法取消datagrid控件的行编辑状态
				create_endEdit();
				// 定义orderinfo数组存放订单主表数据
				var order=[];
				// 获取订单时间
				var ordertime=$('#create_ordertime').datebox('getValue');
				// 获取订单状态
				var status=$('#create_orderstatus').datebox('getValue');
				// 向数组的末尾添加元素（订单主表数据）
				order.push({
					orderTime:ordertime,
					userId:userid,
					orderStatus:status
				});
				// 获取订单明细(datagrid控件中的记录)
				if($odbox.datagrid('getChanges').length){
					// 获取datagrid控件中插入的记录行
					var inserted=$odbox.datagrid('getChanges',"inserted");
					// 获取datagrid控件中删除的记录行
					var deleted=$odbox.datagrid('getChanges',"deleted");					
					// 获取datagrid控件中更新的记录行
					var updated=$odbox.datagrid('getChanges',"updated");
					// 定义effectRow,保存inserted和order
					var effectRow=new Object();
					if(inserted.length){
						// JSON.stringify将对象数组转换成JSON字符串
						effectRow["inserted"]=JSON.stringify(inserted);						
					}
					effectRow["order"]=JSON.stringify(order);	
					// 提交请求
					$.post(
						"order/commitOrder",
						effectRow,
						function(data){
							if(data=='success'){
								$.messager.alert('提示','创建成功！','info');
								// 提交datagird控件的当前行
								$odbox.datagrid('acceptChanges');
								if($('#tabs').tabs('exists','创建订单')){
									$('#tabs').tabs('close','创建订单');
								}
								// 重新加载“查询订单页”中用于显示订单记录的 datagrid控件的数据
								$('#orderDg').datagrid('reload');
							}else{
								$.messager.alert('提示','创建失败！','info');
							}
						}
					);
				}
			}
			
		}
		
		
		function create_endEdit() {
			var rows = $odbox.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$odbox.datagrid('endEdit', i);
			}
		}
	</script>
</body>
</html>
