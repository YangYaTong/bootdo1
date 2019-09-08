$().ready(function() {
	validateRule();
	//显示合同的基本信息附件信息
	loadContractBasicInfo();
	
	//显示我方公司
	loadOurCompany();
	//显示对方公司
	loadOppsiteCompany();
	 $("#ibox-content").attr("style","display:none;")
	 $("#abnormal-content").attr("style","display:none;")
});


  $("#checkbox1").click(function(){
	        var s=$("#checkbox1").is(":checked");
	
	            if(s){
	                $("#ibox-content").attr("style","display:block;")
					$("#finsh-content").attr("style","display:none;")
					$("#abnormal-content").attr("style","display:none;")
	
	                $("#checkbox2").removeAttr("checked");
					$("#checkbox3").removeAttr("checked");
	            }
	        })
    $("#checkbox2").click(function(){
	        var s=$("#checkbox2").is(":checked");
	
	            if(s){
		 			 $("#ibox-content").attr("style","display:none;")
	              	$("#finsh-content").attr("style","display:block;")
					$("#abnormal-content").attr("style","display:none;")
	                $("#checkbox1").removeAttr("checked");
					$("#checkbox3").removeAttr("checked");
	            }
	        })
	   $("#checkbox3").click(function(){
	        var s=$("#checkbox3").is(":checked");
	
	            if(s){
		  			$("#ibox-content").attr("style","display:none;")
	               	$("#finsh-content").attr("style","display:none;")
					$("#abnormal-content").attr("style","display:block;")
	                $("#checkbox1").removeAttr("checked");
					$("#checkbox2").removeAttr("checked");
	            }
	        })


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

function updateState() {
	var id =contract.contractId;
	var state=64;

	 var s=$("#checkbox2").is(":checked");
	 var s2=$("#checkbox3").is(":checked");
	 var s3=$("#checkbox1").is(":checked");

	 var label="";
	if(s){
		label="contractFinsh";
	}else if(s2){
		label="contractAbnormal";
	}else if(s3){
		label="contractUpdateInfo";
		
	}
	
	alert(label);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/freeContract/contract/updateState",
		data : {"contractId":id,"state":state,"label":label},
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

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var label = "contractUpdateInfo";
	var form=$('#signupForm').serialize();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/freeContract/contract/contractUpdateNeedShengPi",
		data : form+"&label="+label,// 你的formid
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

function loadContractBasicInfo(){
	

	
			var html='';
			html+='<caption>合同信息</caption>'
				if(contract.contractId!=""){
					html+='<tr height="30px"><th width="122px" >合同ID：</th>'
						+'<td width="1200px">'+contract.contractId+'</td></tr>'
					
				}
			if(contract.contractName!=""&&contract.contractName!=null){
				html+='<tr height="30px"><th width="122px">合同名称：</th>'
					+'<td width="900px">'+contract.contractName+'</td></tr>'
			}
			if(contract.contractType!=""&&contract.contractType!=null){
					html+='<tr height="30px"><th width="122px">合同类型：</th>'
					+'<td width="900px">'+contract.contractType+'</td></tr>'
			}
			if(contract.cintractNo!=""&&contract.cintractNo!=null){
				html+='<tr height="30px"><th width="122px">合同编号：</th>'
				+'<td width="900px">'+contract.cintractNo+'</td></tr>'
			}
			if(contract.projectId!=""&&contract.projectId!=null){
				html+='<tr height="30px"><th width="122px">所属项目：</th>'
				+'<td width="900px">'+contract.projectId+'</td></tr>'
			}
			if(contract.otherpartId!=""&&contract.otherpartId!=null){
				html+='<tr height="30px"><th width="122px">对方：</th>'
				+'<td width="900px">'+contract.otherpartId+'</td></tr>'
			}
			if(contract.ourpartId!=""&&contract.ourpartId!=null){
				html+='<tr height="30px"><th width="122px">我方：</th>'
				+'<td width="900px">'+contract.ourpartId+'</td></tr>'
			}
			if(contract.maincontent!=""&&contract.maincontent!=null){
				html+='<tr height="30px"><th width="122px">主要条款：</th>'
				+'<td width="900px">'+contract.maincontent+'</td></tr>'
			}
			if(contract.cost!=""&&contract.cost!=null){
				html+='<tr height="30px"><th width="122px">合同金额：</th>'
				+'<td width="900px">'+contract.cost+'</td></tr>'
			}
			if(contract.attorneyNo!=""&&contract.attorneyNo!=null){
				html+='<tr height="30px"><th width="122px">法律备案编号：</th>'
				+'<td width="900px">'+contract.attorneyNo+'</td></tr>'
			}
			if(contract.startIme!=""&&contract.startIme!=null){
				html+='<tr height="30px"><th width="122px">合同开始时间：</th>'
				+'<td width="900px">'+contract.startIme+'</td></tr>'
			}
			if(contract.endTime!=""&&contract.endTime!=null){
				html+='<tr height="30px"><th width="122px">合同结束时间：</th>'
				+'<td width="900px">'+contract.endTime+'</td></tr>'
			}
			if(contract.place!=""&&contract.place!=null){
				html+='<tr height="30px"><th width="122px">约定履行地：</th>'
				+'<td width="900px">'+contract.place+'</td></tr>'
			}
			if(contract.paymentway!=""&&contract.paymentway!=null){
				html+='<tr height="30px"><th width="122px">付款方式：</th>'
				+'<td width="900px">'+contract.paymentway+'</td></tr>'
			}
			if(contract.breach!=""&&contract.breach!=null){
				html+='<tr height="30px"><th width="122px">违约条款：</th>'
				+'<td width="900px">'+contract.breach+'</td></tr>'
			}
			if(contract.resolution!=""&&contract.resolution!=null){
				html+='<tr height="30px"><th width="122px">争议解决方式：</th>'
				+'<td width="900px">'+contract.resolution+'</td></tr>'
			}
			if(contract.agrement!=""&&contract.agrement!=null){
				html+='<tr height="30px"><th width="122px">双方一致的附加条款：</th>'
				+'<td width="900px">'+contract.agrement+'</td></tr>'
			}
			if(contract.remark!=""&&contract.remark!=null){
				html+='<tr height="30px"><th width="122px">合同备注：</th>'
				+'<td width="900px">'+contract.remark+'</td></tr>'
			}
			if(contract.parentId!=""&&contract.parentId!=null){
				html+='<tr height="30px"><th width="122px">修改前的合同Id：</th>'
				+'<td width="900px">'+contract.parentId+'</td></tr>'
			}
			if(contract.myself!=""&&contract.myself!=null){
				html+='<tr height="30px"><th width="122px">合同信息维护人ID：</th>'
				+'<td width="900px">'+contract.myself+'</td></tr>'
			}
			if(contract.modelId!=""&&contract.modelId!=null){
				html+='<tr height="30px"><th width="122px">合同的模板ID：</th>'
				+'<td width="900px">'+contract.modelId+'</td></tr>'
			}
			if(contract.needCost!=""&&contract.needCost!=null){
				html+='<tr height="30px"><th width="122px">待收/付金额：</th>'
				+'<td width="900px">'+contract.needCost+'</td></tr>'
			}
			if(contract.actualCost!=""&&contract.actualCost!=null){
				html+='<tr height="30px"><th width="122px">实收/付金额：</th>'
				+'<td width="900px">'+contract.actualCost+'</td></tr>'
			}
			if(contract.needBill!=""&&contract.needBill!=null){
				html+='<tr height="30px"><th width="122px">待开票金额：</th>'
				+'<td width="900px">'+contract.needBill+'</td></tr>'
			}
			if(contract.actualBill!=""&&contract.actualBill!=null){
				html+='<tr height="30px"><th width="122px">实开票金额：</th>'
				+'<td width="900px">'+contract.actualBill+'</td></tr>'
			}
			if(contract.moneyProgress!=""&&contract.moneyProgress!=null){
				html+='<tr height="30px"><th width="122px">合同资金进度：</th>'
				+'<td width="900px">'+contract.moneyProgress+'</td></tr>'
			}
			if(contract.billProgress!=""&&contract.billProgress!=null){
				html+='<tr height="30px"><th width="122px">合同发票进度：</th>'
				+'<td width="900px">'+contract.billProgress+'</td></tr>'
			}
			if(contract.charger!=""&&contract.charger!=null){
				html+='<tr height="30px"><th width="122px">合同负责人：</th>'
				+'<td width="900px">'+contract.charger+'</td></tr>'
			}
			if(contract.office!=""&&contract.office!=null){
				html+='<tr height="30px"><th width="122px">合同负责科室：</th>'
				+'<td width="900px">'+contract.office+'</td></tr>'
			}
			if(contract.contractKind!=""&&contract.contractKind!=null){
				html+='<tr height="30px"><th width="122px">合同资金类型：</th>'
				+'<td width="900px">'+contract.contractKind+'</td></tr>'
			}
			if(contract.documentPlace!=""&&contract.documentPlace!=null){
				html+='<tr height="30px"><th width="122px">合同文档存放位置：</th>'
				+'<td width="900px">'+contract.documentPlace+'</td></tr>'
			}
			if(contract.state!=""&&contract.state!=null){
				html+='<tr height="30px"><th width="122px">合同状态：</th>'
				+'<td width="900px">'+contract.state+'</td></tr>'
			}
			if(contract.creatiedUser!=""&&contract.creatiedUser!=null){
				html+='<tr height="30px"><th width="122px">合同记录创建人：</th>'
				+'<td width="900px">'+contract.creatiedUser+'</td></tr>'
			}
			if(contract.creatiedTime!=""&&contract.creatiedTime!=null){
				html+='<tr height="30px"><th width="122px">合同记录创建时间：</th>'
				+'<td width="900px">'+contract.creatiedTime+'</td></tr>'
			}
			if(contract.modifiedUser!=""&&contract.modifiedUser!=null){
				html+='<tr height="30px"><th width="122px">合同记录变更人：</th>'
				+'<td width="900px">'+contract.modifiedUser+'</td></tr>'
			}
			if(contract.modifiedTime!=""&&contract.modifiedTime!=null){
				html+='<tr height="30px"><th width="122px">合同记录变更时间：</th>'
				+'<td width="900px">'+contract.modifiedTime+'</td></tr>'
			}
			
	
			
			
			
			
			
		
			$("#contract_info").append(html);
			
	
		

}

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/freeContract/contract/update",
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