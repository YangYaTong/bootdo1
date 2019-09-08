$().ready(function() {
	validateRule();
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

});
function sreLoad() {
	$('#total_money').bootstrapTable('refresh');
}
$("#checkbox1").click(function(){

	        var s=$("#checkbox1").is(":checked");
	
	            if(s){
	                $("#div_state").attr("style","display:none;")
	                $("#checkbox2").removeAttr("checked");
	            }
	        });
    $("#checkbox2").click(function(){
	
	        var s=$("#checkbox2").is(":checked");
	
	            if(s){
	                $("#div_state").attr("style","display:block;")
	                $("#checkbox1").removeAttr("checked");
	            }
	        });
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadKindType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/contract_kind',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
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
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/contract_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
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
function loadOurCompany(){
	var html = "";
	$.ajax({
		url : '/ourCompany/ourCompany/findAll',
			success : function(res) {
				var data = res.ourCompanyList;
				console.log(data);
				//加载数据
				for (var i = 0; i < data.length; i++) {
					html += '<option value="' + data[i].ourcompanyid + '">' + data[i].name + '</option>'		
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
function loadMoudleList(){
	var html = "";
	
	$.ajax({
		url : '/contractMoule/mould/findAll',
		success : function(res) {
			var data = res.mouldList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].mouldId + '">' + data[i].mouldName + '</option>'		
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
				showBlank(id);
				$('#exampleTable').bootstrapTable('refresh', opt);
				
				
				
				
			});
		}
	});
}
function showBlank(id) {
	var html = "";
	
	$.ajax({
		cache : true,
		type : "get",
		url : "/standardContract/contractStandard/showBlank/"+id,
		data : {},
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				var list = data.mouldList;
				for (var i = 0; i < list.length; i++) {
						html+='<div class="form-group">	'
								+'<label class="col-sm-3 control-label">'+list[i]+'：</label>'
								+'<div class="col-sm-8">'
								+'<input id="contractName" name="'+list[i]+'" class="form-control" type="text">'
								+'</div>'
							+'</div>'
				}
				$("#modle_blank").append(html);
				
				/*parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
	
	
}
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
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
				sreLoad();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function cc(s){
    if(/[^0-9\.]/.test(s)) return "invalid value";
    s=s.replace(/^(\d*)$/,"$1.");
    s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");
    s=s.replace(".",",");
    var re=/(\d)(\d{3},)/;
    while(re.test(s))
            s=s.replace(re,"$1,$2");
    s=s.replace(/,(\d\d)$/,".$1");
    return "￥" + s.replace(/^\./,"0.")
    }
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