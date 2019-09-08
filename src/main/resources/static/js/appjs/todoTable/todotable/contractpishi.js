$().ready(function() {
	validateRule();
	removeBlank();
	showAnnexes();
	
});

$.validator.setDefaults({
	submitHandler : function() {
		//pishi();
	}
});

function showAnnexes(){
	var id=contract.contractId;
	
	$.ajax({
		url : '/anexes/annexes/showContractAnnexesInfo?contractId='+id,
		success : function(res) {
		
			var data = res.annexesList;
			if(data==null||data==""){
				$("#contract_annexes_div").hide();
				return;
			}
			
			
			$("#annexes_thead").append('<tr><th "text-primary" >附件名称</th><th "text-primary" >创建人</th><th "text-primary" >创建时间</th><th "text-primary" >操作</th></tr>');
			//$("#annexes_thead").append('<div ><span >附件名称</span><span >创建人</span><span >创建时间</span><span >操作</span></div>');
			
			//$("#contract_annexesid").show();
			var html_body ='';
			//加载数据
			for (var i = 0; i < data.length; i++) {
			
				/*
				html_body +='<div>'
					+'<span>'+data[i].annexesName+'</span>'
					+'<span>'+data[i].creatiedUser+'</span>'
					+'<span>'+data[i].creatiedTime+'</span>'
					+'<span><a href="'+data[i].annexesPath+'">查看</a></span>'
					+'</div>';
				*/
				

				html_body +='<tr>'
					+'<td>'+data[i].annexesName+'</th>'
					+'<td>'+data[i].creatiedUser+'</th>'
					+'<td>'+data[i].creatiedTime+'</th>'
					+'<td><a href="/anexes/annexes/downloads?annexesId='+data[i].annexesId+'">查看</a></th>'

					+'</tr>';
		
			}
			
			$("#annexes_tbody").append(html_body);
			
		
		}
	});
	
	
	
}

function removeBlank(){
	
	if(contract.projectId==null||contract.projectId==""){
		$("#contract_projectId").hide();
	}
	if(contract.contractNo==null||contract.contractNo==""){
		$("#contract_contractNO").hide();
	}
}


function pishi() {
	var todoId = $("#todotable_id").val();
	var data = $('#pishiForm').serialize();

	$.ajax({
		cache : true,
		type : "POST",
		url : "/response/response/saveContractPishi",
		data : data,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				//parent.reLoad();
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