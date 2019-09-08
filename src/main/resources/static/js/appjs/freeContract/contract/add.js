$().ready(function() {
	validateRule();
	//显示合同类型
	
	loadproject();
	//显示我方公司
	loadOurCompany();
	//显示对方公司
	loadOppsiteCompany();
	//显示合同资金类型
	loadKindType();
	loadType();
	//显示合同所属项目
	//showStateDiv();
	//显示预算科目
	loadbudgetType();
	

});
//声明全局变量，合同信息保存以后会自动返回响应的contractId否则为空
var contractId="";

//金额转换
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

function  remov(){
	$("#matter_list").empty();	
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






    $("#checkbox1").click(function(){

	        var s=$("#checkbox1").is(":checked");
	
	            if(s){
	                $("#div_state").attr("style","display:none;")
	                $("#checkbox2").removeAttr("checked");
	            }
	        })
    $("#checkbox2").click(function(){
	
	        var s=$("#checkbox2").is(":checked");
	
	            if(s){
	                $("#div_state").attr("style","display:block;")
	                $("#checkbox1").removeAttr("checked");
	            }
	        })
/*function showStateDiv(){
	alert($(".shengpi").val());
	if($(".shengpi").val()==0){
		$("#div_state").attr("style","display:none;");//隐藏div
	}else{
		$("#div_state").attr("style","display:block;")
	}
}*/
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

function loadbudgetType(){
	var html = "";
	$.ajax({
		url : '/budget/budget/listDept',
		success : function(res) {
			var data = res.budgetList;
			console.log(data);
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
			}
			$("#budgetId").append(html);
			$("#budgetId").chosen({
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
			$("#contract_type").append(html);
			$("#contract_type").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#contract_type').on('change', function(e, params) {
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
				//加载数据
				for (var i = 0; i < data.length; i++) {
					html += '<option value="' + data[i].ourcompanyid + '">' + data[i].name + '</option>'		
				}
				$("#our_company").append(html);
				$("#our_company").chosen({
					maxHeight : 200
				});
			//点击事件
			$('#our_company').on('change', function(e, params) {
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
			$("#opposite_company").append(html);
			$("#opposite_company").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#opposite_company').on('change', function(e, params) {
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
function save() {
	var form=$('#signupForm').serialize();
	//检查必选项
	
	
	if($('#contractKind').val()==""){
		parent.layer.msg("合同的资金类型必须选择！");
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/freeContract/contract/save",
		//data : form+"&pictureIds="+pictureIds,// 你的formid
		data:form,
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("合同信息保存成功，您可以添加合同附加或者合同收付款计划");
				parent.reLoad();
				contractId=data.contractId;
				/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}


function removeUpload(id) {
	
	 layer.confirm('确定要删除选中的记录？', {
	        btn: ['确定', '取消']
	    }, function () {
	        $.ajax({
	            url: "/anexes/annexes/remove",
	            type: "post",
	            data: {
	                'id': id
	            },
	            success: function (r) {
	            	pictureIds.splice(jQuery.inArray(id,pictureIds),1);
	            	alert(pictureIds);
	                if (r.code == 0) {
	                   alert(r.msg);
	                    //app.getData();
	                } else {
	                    layer.msg(r.msg);
	                    //app.getData();
	                }
	            }
	        });
	    })
}

	
/* 	
removeUpload: function (id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: "/anexes/annexes/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    app.getData();
                } else {
                    layer.msg(r.msg);
                    app.getData();
                }
            }
        });
    })
*/
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
