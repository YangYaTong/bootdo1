
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
						url : prefix + "/searchFulfillingContract", // 服务器数据的加载地址
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
								},{
									title: '序号',
									align: 'center',
									
									formatter: function(value, row, index) {
									return index + 1;
									}
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
									title : '对方' 
								},
																{
									field : 'ourpartId', 
									title : '我方' 
								},
																{
									field : 'cost', 
									title : '金额' 
								},
															
																{
									field : 'needCost', 
									title : '待收/付金额' 
								},

																{
									field : 'moneyProgress', 
									title : '资金进度' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<div class="progress">'
											  +'<div class="progress-bar progress-bar-info"   style="width: '+ row.moneyProgress+'">'
											 
									  +'</div>'+row.moneyProgress
									  +'</div>';
										return e  ;
									}
								},

																{
									field : 'billProgress', 
									title : '发票进度' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<div class="progress">'
											  +'<div class="progress-bar progress-bar-success"   style="width: '+  row.billProgress+'">'
											
									  +'</div>'+row.billProgress
									  +'</div>';
										return e  ;
									}
									
									
									
								},
										
									
																{
									field : 'mainpaper', 
									title : '主要附件' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="'+prefix + '/downloads?contractId='+row.contractId+'" mce_href="#" title="查看" ><i class="fa fa-eye"></i></a> ';
										return e  ;
									}
									
								},				 
								
								
								{
									
									title : '状态' ,
									formatter: function(value, row, index) {
										var stateStr="";
										switch (row.state){
							            case "4":
							            	stateStr="初稿";
							                break;
							          
							            case "15":
							            	stateStr="审批中";
							                break;
							            case "18":
							            	stateStr="审批通过";
							                break;
							            case "22":
							            	stateStr="审批未通过";
							                break;
							            case "40":
							            	stateStr="合同履行中";
							                break;
							            case "64":
							            	stateStr="变更待上报";
							                break;
							            case "75":
							            	stateStr="变更审批中";
							                break;
							            case "78":
							            	stateStr="变更审批通过";
							                break;
							            case "83":
							            	stateStr="变更审批未通过"; 
							                break;
										case "88":
							            	stateStr="合同履行异常";
							                break;
							            case "90":
							            	stateStr="合同履行完毕";
							            	 break;
							            case "95":
							            	stateStr="合同已归档";
							                break;
							            case "100":
							            	stateStr="合同被变更待批准";
							                break;
							            case "101":
							            	stateStr="合同已被变更";
							                break;
							            default:
							            	stateStr="状态未定义";
							                break;
							           
							        }
										
										
		
										return stateStr;
										}
										
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.contractId
										+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.contractId
										+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="上报审批"  mce_href="#" onclick="resetPwd(\''
										+ row.contractId
										+ '\')"><i class="fa fa-send"></i></a> ';
										var k = '<a class="btn btn-success btn-sm" href="#" title="变更"  mce_href="#" onclick="resetPwd(\''
									+ row.contractId
									+ '\')"><i class="fa fa-edit"></i></a> ';
										
										var h = '<a class="btn btn-success btn-sm" href="/response/response/downloadResponsePaper?contractId='+row.contractId+'" title="打印审批件"  )"><i class="fa fa-print"></i></a> ';
										
										var m='<a class="btn btn-success btn-sm" href="#" title="添加签署日期"  mce_href="#" onclick="createSignDate(\''
											+ row.contractId
											+ '\')"><i class="fa fa-edit"></i></a> ';
										
										
					
										if(row.state=="审批中"||row.state=="合同履行中"){
											return "";
										}else {
											
											return e + d+f+h +m;
										}
										
									
										
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

function edit(id) {
	
	
	
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	//showtotal();
}
function createSignDate(id) {
	
	
	
	layer.open({
		type : 2,
		title : '添加签署日期',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/addSignDate/' + id // iframe的url
	});
	//showtotal();
}
function chakan(id) {


	layer.open({
		type : 2,
		title : '附件下载',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :prefix + '/downloads?contractId='+id // iframe的url
		
	});
}

function printSP(id){
	layer.open({
		type : 2,
		title : '附件下载',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :'/response/response/downloadResponsePaper?contractId='+id // iframe的url
		
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