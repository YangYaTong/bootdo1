$().ready(function() {
	//validateRule();
	loadMoudleList();
	validateRule();
	//显示合同类型
	loadType();
	//显示合同所属项目
	loadproject();
	//显示我方公司
	loadOurCompany();
	//显示对方公司
	loadOppsiteCompany();
	//显示合同资金类型
	loadKindType();
	//Map();

});
var index = 0;
var mouldId = '';
var contractId="";


function sreLoad() {
	$('#total_money').bootstrapTable('refresh');
}

//切换是否需要审批
$("#checkbox1").click(function() {

	var s = $("#checkbox1").is(":checked");

	if (s) {
		$("#div_state").attr("style", "display:none;")
		$("#checkbox2").removeAttr("checked");
	}
});
$("#checkbox2").click(function() {

	var s = $("#checkbox2").is(":checked");

	if (s) {
		$("#div_state").attr("style", "display:block;")
		$("#checkbox1").removeAttr("checked");
	}
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

//加载合同资金类型的下拉选
function loadKindType() {
	var html = "";
	$.ajax({
		url : '/common/dict/list/contract_kind',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name
						+ '</option>'
			}
			$("#contractKind").append(html);
			$("#contractKind").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#contractKind').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

//合同类型的下拉选
function loadType() {
	var html = "";
	$.ajax({
		url : '/common/dict/list/contract_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name
						+ '</option>'
			}
			$("#contractType").append(html);
			$("#contractType").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#contractType').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
//加载项目下拉选
function loadproject() {
	var html = "";

	$.ajax({
		url : '/project/project/findAll',
		success : function(res) {
			var data = res.projectList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].pid + '">'
						+ data[i].projectname + '</option>'
			}
			$("#projectId").append(html);
			$("#projectId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#projectId').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
//加载合同我方单位下拉选
function loadOurCompany() {
	var html = "";
	$.ajax({
		url : '/ourCompany/ourCompany/findAll',
		success : function(res) {
			var data = res.ourCompanyList;
			console.log(data);
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].ourcompanyid + '">'
						+ data[i].name + '</option>'
			}
			$("#ourpartId").append(html);
			$("#ourpartId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#ourpartId').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
//加载合同对方下拉选
function loadOppsiteCompany() {
	var html = "";
	$.ajax({
		url : '/company/company/findAll',
		success : function(res) {
			var data = res.companyList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].collaboratorid + '">'
						+ data[i].name + '</option>'
			}
			$("#otherpartId").append(html);
			$("#otherpartId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#otherpartId').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
//加载合同模板下拉选
function loadMoudleList() {
	var html = "";

	$.ajax({
		url : '/contractMoule/mould/findAll',
		success : function(res) {
			var data = res.mouldList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].mouldId + '">'
						+ data[i].mouldName + '</option>'
			}
			$("#contractModle").append(html);
			$("#contractModle").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#contractModle').on('change', function(e, params) {
				console.log(params.selected);
				
				
				var opt = {
					query : {
						type : params.selected,
					}
				}

				//显示替换框
				var id = params.selected;
				showTable(id);
				mouldId = id;
				$('#exampleTable').bootstrapTable('refresh', opt);

			});
		}
	});
}

var tableClum = "";
var tableClumLength = "";
//添加一行附件列表
//添加一行附件列表
$("#add_line").click(function(){
var format="format: 'YYYY-MM-DD'";
var style='style="background-color: #fff;"';
var readonly='readonly="readonly"';
var i=1;
 var html='';
  html+='<tr ><td><input type="text" class="laydate-icon  form-control" name="planDate"  placeholder="请选择开始时间"'
                                          +' onclick="laydate({istime: true, '+format+'})"  '+style+''+readonly+' /></td>'
                                          +'<td><input name="matterCost" class="form-control" type="text"></td>'
                                          +'<td><input  name="billCost" class="form-control" type="text"></td>'
                                          +'<td><input  name="matterPeople" class="form-control" type="text"></td>'
                                          +'<td><button type="button" class="layui-btn" id="remove_line" onclick="remov()">删除</button></td>'
                                          +'</tr>'
                                      	$("#matter_list").append(html);
})

var tableList = ""
	
//显示合同附件的表格
function showTable(id) {
	var html = "";
	var html_head = "";
	var html_body = ";"

	$.ajax({
		cache : true,
		type : "get",
		url : "/standardContract/contractStandard/getTableClumName/" + id,
		data : {},
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				tableClum = data.tableClumList;
				var list = data.tableClumList;
				tableList = data.tableClumList;
				tableClumLength = list.length;

				for (var i = 0; i < list.length; i++) {
					html_head += '<th>' + list[i] + '</th>';

					html_body += '<td><input class="clum' + i + '"name="clum' + i
							+ '" class="form-control" type="text"></td>';

				}
				html_body = '<tr>' + html_body + '</tr>';
				html_head = '<tr>' + html_head + '</tr>'

				$("#table_thead").append(html_head);
				$("#table_body").append(html_body);
		

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

/**
 * 添加一行
 */
var rowNum=1;
function addrow() {
	rowNum++;
	if (tableList == "") {
		return;
	}
	
	var list = tableList;
	var html_body='';
	for (var i = 0; i < list.length; i++) {
	
		html_body += '<td><input class="clum' + i + '"name="clum' + i
				+ '" class="form-control" type="text"></td>';

	}
	html_body = '<tr>' + html_body + '</tr>';

	$("#table_body").append(html_body);
	

}


/**
 * 保存表格内容
 */
function tablesave() {
var data = $('#table_form').serialize()+ "&mouldId=" + mouldId;  
	$.ajax({
		cache : true,
		type : "post",
		url : "/mouldTable/mouldTable/tablesave/",
		data : data,
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/
				sreLoad();
			} else {
				
				parent.layer.alert(data.msg)
			}

		}
	});

}


//保存合同的基本信息
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/standardContract/contractStandard/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
			
				contractId=data.contractId;
				/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
				sreLoad();*/
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
//普通数字转变为金额样式
function cc(s) {
	if (/[^0-9\.]/.test(s))
		return "invalid value";
	s = s.replace(/^(\d*)$/, "$1.");
	s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
	s = s.replace(".", ",");
	var re = /(\d)(\d{3},)/;
	while (re.test(s))
		s = s.replace(re, "$1,$2");
	s = s.replace(/,(\d\d)$/, ".$1");
	return "￥" + s.replace(/^\./, "0.")
}


//保存合同计划事项
$("#matter_submit").click(function(){

	if(contractId==""){
		parent.layer.msg("请先保存合同信息，再添加合同收付款计划！");
		return;
	}else{
		 $('#matterform_contractId').val(contractId);
		var form=$('#matter_form').serialize();
		$.ajax({
			cache : true,
			type : "POST",
			url : "/matter/matter/batchsave",
			data : form,// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("保存成功");
					//parent.reLoad();
				
					/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);*/

				} else {
					parent.layer.alert(data.msg)
				}

			}
		});	
	}
	
});
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}

