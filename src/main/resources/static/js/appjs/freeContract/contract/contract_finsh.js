
var prefix = "/freeContract/contract"
$(function() {
	load();
	showtotal();
});


function showtotal(){
	$("#total_money").empty();
	var html = "";
	
	$.ajax({
		url : '/freeContract/contract/gettotal',
		success : function(res) {
			var receiveTotal = res.map.receiveTotal;
			var payTotal=res.map.payTotal;
			var needReceive=res.map.needReceive;
			var needPay = res.map.needPay;
			
			
			//加载数据
			
				//html += '<option value="' + data[i].contractId + '">' + data[i].contractName + '</option>'	;	
				html+='<div class="alert alert-success" role="alert">'
					+'当前收款合同总金额为：'
					+'<span class="label label-primary">'+receiveTotal+'</span>'
					+'  需收款金额：'
					+'<span class="label label-primary">'+needReceive+'</span>'
					+'。付款合同总金额为：'
					+'<span class="label label-danger">'+payTotal+'</span>'
					+'需付款金额：'
					+'<span class="label label-danger">'+needPay+'</span>'
					+'</div>';
			
			$("#total_money").append(html);
		
			
				//$('#exampleTable').bootstrapTable('refresh', opt);
		}
			});
		

}
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/finshContract", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
								{
									title: '序号',
									align: 'center',
									valign: 'bottom',
									formatter: function(value, row, index) {
									return index + 1;
									}
									},
																{
									field : 'contractId', 
									title : 'ID' 
								},
																{
									field : 'contractName', 
									title : '合同名称' 
								},

																{
									field : 'contractKind', 
									title : '资金类型' ,
									align : 'center',
									formatter : function(value, row, index) {
										var hh=''
										var text='';
										if(row.contractKind=="in"){
											hh='primary';
											text="收款";
										}else{
											hh='danger';
											text="付款";
										}
										var e = '<span class="label label-'+hh+'">'+text+'</span>';
										return e  ;
									}
								},  
																{
									field : 'contractType', 
									title : '合同类型' 
								},
																{
									field : 'cintractNo', 
									title : '合同编号' 
								},
																{
									field : 'projectId', 
									title : '所属项目' 
								},
																{
									field : 'otherpartId', 
									title : '对方公司' 
								},
																{
									field : 'ourpartId', 
									title : '我方公司' 
								},
																{
									field : 'cost', 
									title : '金额' 
								},
									
								{
									field : 'startTime', 
									title : '开始时间' 
								},
								{
									field : 'endTime', 
									title : '结束时间' 
								},
								{
									field : 'documentPlace', 
									title : '合同文本位置' 
								},

																            	{
									field : 'state', 
									title : '状态' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看" onclick="chakan(\''
											+ row.contractId
											+ '\')"><i class="fa fa-eye"></i></a> ';
									return e  ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
	showtotal();
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
	//showtotal();
}
function change(id) {
	layer.open({
		type : 2,
		title : '合同变更',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/change/' + id // iframe的url
	});
	//showtotal();
}
function chakan(contractId) {
	
//var data = JSON.parse('[' + mainpaper + ']');
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :prefix + '/showDetauls?contractId='+contractId // iframe的url
	});
}

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'contractId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
					//showtotal();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
	layer.open({
		type : 2,
		title : '选择要上报对象',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/up/' + id // iframe的url
	});
	
	
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['contractId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
					//showtotal();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}