$().ready(function() {
	validateRule();
});

$("#checkbox1").click(function(){

	        var s=$("#checkbox1").is(":checked");
	
	            if(s){
	                  $("#div_state").attr("style","display:block;")
	                $("#checkbox2").removeAttr("checked");
	            }
	        })
    $("#checkbox2").click(function(){
	
	        var s=$("#checkbox2").is(":checked");
	
	            if(s){
		$("#div_state").attr("style","display:none;")
	              
	                $("#checkbox1").removeAttr("checked");
	            }
	        })

$.validator.setDefaults({
	submitHandler : function() {
		//pishi();
	}
});


function pishi() {
	var todoId = $("#todotable_id").val();
	var nextStepUserIds = $("#userIds").val();

	var data = $('#pishiForm').serialize()+todoId+nextStepUserIds;

	$.ajax({
		cache : true,
		type : "POST",
		url : "/response/response/savePaymentPishi",
		data : data,// 你的formid
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

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});


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
				required : icon + "请输入名字"
			}
		}
	})
}

var openUser = function(){
	layer.open({
		type:2,
		title:"选择人员",
		area : [ '400px', '450px' ],
		content:"/sys/user/treeView"
	})
}

function loadUser(userIds,userNames){
	$("#userIds").val(userIds);
	$("#userNames").val(userNames);
}