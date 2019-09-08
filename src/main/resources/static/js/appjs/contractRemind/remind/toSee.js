$().ready(function() {
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function close(){

	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

	parent.layer.close(index);
}

function dele(){
	var id = $("#remind_id").val();
	
	layer.confirm('确定要删除此提醒吗？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/contractRemind/remind/dele",
			type : "post",
			data : {
				'remindId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					close();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function toReceive(){
	var matterId  = $("#matter_id").val();
	layer.open({
		type : 2,
		title : '合同收款登记',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '810px', '520px' ],
		content : '/receive/receive/toReceive/' + matterId // iframe的url
	});
	
}
function toPayment(){
	var matterId  = $("#matter_id").val();
	layer.open({
		type : 2,
		title : '合同付款登记',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '810px', '520px' ],
		content : '/payment/payment/toPayment/' + matterId // iframe的url
	});
}

function dosome() {
	var noRemind =$("input[id='noRemind']").is(':checked');
	var toPage =$("input[id='toPage']").is(':checked');
	var remark = $("#remind_remark").val();
	var remmindType  = $("#remind_type").val();
	if(noRemind==false&&toPage==false){
	
		close();
	
	}else if(noRemind==true&&toPage==false){
		
		dele();
		
		
	}else if(noRemind==false&&toPage==true){


		
		if(remark=="matter"){
			if(remmindType=="1"){//收款类型的提醒
				toReceive();	
			}else if(remmindType=="-1"){//付款类型的提醒
				toPayment();
				
			}
			
		}
		
	}else if(noRemind==true&&toPage==true){
	alert("wait。。。")
	}
	
	/*$.ajax({
		cache : true,
		type : "POST",
		url : "/contractRemind/remind/update7",
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

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});*/

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