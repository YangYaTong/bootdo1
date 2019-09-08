
var prefix = "/freeContract/contract"
$(function() {
	//load();
	//显示合同类型
	loadType();
	//显示合同所属项目
	loadproject();
	//显示我方公司
	loadOurCompany();
	//显示对方公司
	loadOppsiteCompany();
});
function exportExcel() {
	
  var name = $('#search_name').val();
  var no=$('#search_no').val();
  var startTime=$('#search_starttime').val();
  var endTime=$('#search_endtime').val();
  var minMoney=$('#min_money').val();
  var maxMoney=$('#max_money').val();
  var ourCompany=$('#search_our').val();
  var oppositeCompany=$('#search_opposite').val();
  var project=$('#search_project').val();
  var signDate=$('#sign_date').val();
  var type=$('#search_type').val();

var offset=0;
var limit=10;


var data =$('#search_form').serialize();
    data = data+'limit='+limit+'&offset='+offset;
    
    console.log(data);
    //后端导出的方法
    document.location.href = prefix + "/exportExcel?"+ data;
}

function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/contract_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#search_type").append(html);
			$("#search_type").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#search_type').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function loadproject(){
	var html = "";
	
	$.ajax({
		url : '/project/project/findAll',
		success : function(res) {
			var data = res.projectList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].pid + '">' + data[i].projectname + '</option>'		
			}
			$("#search_project").append(html);
			$("#search_project").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#search_project').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function loadOurCompany(){
	var html = "";
	$.ajax({
		url : '/ourCompany/ourCompany/findAll',
			success : function(res) {
				var data = res.ourCompanyList;
				//加载数据
				for (var i = 0; i < data.length; i++) {
					html += '<option value="' + data[i].ourcompanyid + '">' + data[i].name + '</option>'		
				}
				$("#search_our").append(html);
				$("#search_our").chosen({
					maxHeight : 200
				});
			//点击事件
			$('#search_our').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

function loadOppsiteCompany(){
	var html = "";
	$.ajax({
		url : '/company/company/findAll',
		success : function(res) {
			var data = res.companyList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].collaboratorid + '">' + data[i].name + '</option>'		
			}
			$("#search_opposite").append(html);
			$("#search_opposite").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#search_opposite').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

function load() {
	reLoad();//清空数据
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/showSearch", // 服务器数据的加载地址
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
								offset:params.offset,
					           name:$('#search_name').val(),
					           no:$('#search_no').val(),
					           startTime:$('#search_starttime').val(),
					           endTime:$('#search_endtime').val(),
					           minMoney:$('#min_money').val(),
					           maxMoney:$('#max_money').val(),
					           ourCompany:$('#search_our').val(),
					           oppositeCompany:$('#search_opposite').val(),
					           project:$('#search_project').val(),
					           signDate:$('#sign_date').val(),
					           type:$('#search_type').val()
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
									field : 'contractId', 
									title : 'ID' 
								},
																{
									field : 'contractName', 
									title : '合同名称' 
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
									field : 'startIme', 
									title : '开始时间' 
								},
																{
									field : 'endTime', 
									title : '结束时间' 
								},
									
																{
									field : 'mainpaper', 
									title : '主要附件' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看" onclick="chakan(\''
												+ row.contractId
												+ '\')"><i class="fa fa-eye"></i></a> ';
										return e  ;
									}
									
								},				            	{
									field : 'state', 
									title : '状态' 
								} ]
					});
}


function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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
}
function chakan(mainpaper) {
	var data1 =mainpaper+"";
var data = JSON.parse('[' + mainpaper + ']');
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :prefix + '/chakan?mainpaper='+data // iframe的url
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
		area : [ '1000px', '850px' ],
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
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}