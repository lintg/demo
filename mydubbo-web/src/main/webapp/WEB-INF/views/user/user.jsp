<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><%@include file="../common/header_new.jsp"%><style
	type="text/css">
#editForm tr {
	height: 40px;
}

#editForm td label {
	text-align: right;
	padding-left: 50px;
}

.tx_input {
	text-align: left;
	width: 130px;
	padding-left: 10px;
}

.tx_label {
	text-align: right;
	width: 130px;
}

.search_label {
	text-align: right;
	width: 60px;
}

.search_input {
	text-align: left;
	width: 100px;
	padding-left: 8px;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,selected:true">
		<div class="datagrid-toolbar"
			data-options="region:'north',split:true,title:'查询'"
			style="height: 80px; padding: 10px 0px 10px 10px">
			<!-- 查询窗口-->
			<form id="queryForm" method="post">
				<table style="width: 100%;" border="0" cellpadding="2"
					cellspacing="3" align="center">
					<tbody>
						<tr>
							<td class="search_label"><label>姓名:</label></td>
							<td class="search_input"><input id="name2"
								class="easyui-validatebox" type="text" maxlength=""
								style="width: 200px;" data-options="required:false" /></td>
							<td class="search_label"><label>年龄:</label></td>
							<td class="search_input"><input id="age2"
								class="easyui-validatebox" type="text" maxlength=""
								style="width: 200px;" data-options="required:false" /></td>
							<td style="align: left; padding-left: 10px;"><a href="#"
								class="easyui-linkbutton" iconCls="icon-search" id="btnQuery">查询</a>
								<a href="#" class="easyui-linkbutton" iconCls="icon-undo"
								id="btnCancel">清空</a></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:true">
			<!-- 数据表格 -->
			<table id="dataTableId"></table>
		</div>
	</div>
	<!-- 工具条-->
	<div id="tool">
		<a href="javascript:void(0)" id="refresh" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true">刷新</a> <a
			href="javascript:void(0)" id="addInfo" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">增加</a> <a
			href="javascript:void(0)" id="deleteInfo" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<!--表单-->
	<div id="editForm">
		<form>
			<table>
				<tr style="display: none">
					<td class="tx_label"><label>id:</label></td>
					<td class="tx_input"><input class="easyui-validatebox"
						type="text" id="id" /></td>
				</tr>
				<tr>
					<td class="tx_label"><label>姓名：</label></td>
					<td class="tx_input"><input id="name"
						class="easyui-validatebox" type="text" maxlength="25"
						style="width: 200px;" data-options="required:false" /></td>
				</tr>
				<tr>
					<td class="tx_label"><label>年龄：</label></td>
					<td class="tx_input"><input id="age" class="easyui-numberbox"
						maxlength="11" precision="0" style="width: 200px;"
						data-options="required:false" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#dataTableId')
					.datagrid(
							{
								url : ctx + '/qx/user/list',
								title : '用户维护管理',
								fit : true,
								rownumbers : true,
								fitColumns : true,
								singleSelect : true,
								checkOnSelect : false,
								pagination : true,
								toolbar : '#tool',
								columns : [ [
										{
											field : 'name',
											title : '姓名',
											width : 200,
											align : 'center'
										},
										{
											field : 'age',
											title : '年龄',
											width : 200,
											align : 'center'
										},
										{
											field : 'opt',
											title : '操作',
											align : 'center',
											width : 60,
											formatter : function(value, row,
													index) {
												var btn = '';
												return '<button onclick="update('
														+ index
														+ ')" class="easyui-linkbutton">修改</button>'
														+ btn;
											}
										} ] ]
							});
			$('#refresh').click(function() {
				$('#dataTableId').datagrid('reload');
			});
			$('#addInfo').click(function() {
				$('#editForm').dialog({
					title : '增加用户维护',
					width : 430,
					height : 300,
					resizable : false,
					modal : true,
					onBeforeClose : function() {
						$('#editForm').form('clear');
					},
					buttons : [ {
						text : '保存',
						handler : function() {
							submitData(ctx + '/qx/user/add');
						}
					}, {
						text : '取消',
						handler : function() {
							$('#editForm').dialog('close');
						}
					} ]
				});
			});
			$('#deleteInfo').click(
					function() {
						var node = $('#dataTableId').datagrid('getSelected');
						if (node) {
							$.messager.confirm('确认信息', '确定要删除记录?', function(r) {
								if (r) {
									$.ajax({
										type : 'POST',
										url : ctx + '/qx/user/delete',
										data : {
											id : node.id
										},
										success : function(data, status) {
											if (data.status == "SUCCESS") {
												$.messager.show({
													title : '消息',
													msg : '删除成功！',
													timeout : 5000,
													showType : 'slide'
												});
												$('#dataTableId').datagrid(
														'reload');
											} else {
												$.messager.alert('错误',
														data.result, 'error');
											}
										}
									});
								}
							});
						} else {
							$.messager.alert('提示', '请选择一条记录！', 'info');
						}
					});
			/**搜索*/
			$('#btnQuery').click(function() {
				search();
			});
			$("#btnCancel").bind("click", function() {
				queryCancel();
			});
		});
		/** * 搜索 */
		function search() {
			$('#dataTableId').datagrid('load', {
				name : $('#name2').val(),
				age : $('#age2').val()
			});
			return false;
		}/** * 清空查询条件 */
		function queryCancel() {
			$('#queryForm').form('clear');
		}
		function update(row) {
			$('#editForm').dialog({
				title : '修改用户维护',
				width : 430,
				height : 300,
				resizable : false,
				modal : true,
				onBeforeClose : function() {
					$('#editForm').form('clear');
				},
				buttons : [ {
					text : '保存',
					handler : function() {
						submitData(ctx + '/qx/user/update');
					}
				}, {
					text : '取消',
					handler : function() {
						$('#editForm').dialog('close');
					}
				} ]
			});
			$('#dataTableId').datagrid('selectRow', row);
			var node = $('#dataTableId').datagrid('getSelected');
			$('#id').val(node.id);
			$('#name').val(node.name);
			$('#age').numberbox('setValue',node.age);
			/**选中下拉框	$('#pipeiMode').combobox('select',  state); 	获取值	$("#pipeiMode").combobox('getValue');	*/
			$('#editForm').form('validate');
		}
		function submitData(url) {
			if (!$('#editForm').form('validate'))
				return;
			var id = $('#id').val();
			$.ajax({
				type : 'POST',
				url : url,
				data : {
					id : $('#id').val(),
					name : $('#name').val(),
					age : $('#age').val()
				},
				success : function(data, status) {
					if (data.status == "SUCCESS") {
						$.messager.show({
							title : '消息',
							msg : '提交成功！',
							timeout : 5000,
							showType : 'slide'
						});
						$('#editForm').dialog('close');
						/**更新后的动作*/
						if (id) {
							$('#dataTableId').datagrid('reload');
						}/**新增后的动作*/
						else {
							$('#dataTableId').datagrid('load', {
								name : '',
								age : ''
							});
						}
					} else {
						$.messager.alert('错误', data.result, 'error');
					}
				}
			});
		}
		$.extend($.fn.validatebox.defaults.rules, {
			nameValid : {
				validator : function(value, param) {
					var p = new RegExp('[^a-zA-Z0-9\u4E00-\u9FA5_]');
					return !p.test(value);
				},
				message : '名称应由数字、字母、中文字符、下划线组成'
			},
			keywordExist : {
				validator : function(value) {
					var result;
					$.ajax({
						async : false,
						type : 'POST',
						url : ctx + '/qx/user/checkKeyword',
						data : {
							id : $('#id').val(),
							keyword : $('#keyword').val()
						},
						success : function(data, status) {
							result = data;
						}
					});
					return result;
				},
				message : '关键字已存在!'
			}
		});
	</script>
</body>
</html>