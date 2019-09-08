$().ready(function() {
	validateRule();
	$("#contract_money").hide();
	loadType();
	loadContractList();
	loadproject();
	
});
var needToActualCost=0;
var needToActualBill=0;

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
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

function returnNormol(str){
	str = str.substr(1);//去掉首字符
	 var num = str.trim();
	    var ss = num.toString();
	    if (ss.length == 0) {
	        return "0";
	    }
	    return ss.replace(/,/g, "");
}

function getContractMoneyDetails(id){
	
	$.ajax({
		url : '/freeContract/contract/showMoneyDetails?contractId='+id,
		async : false,
		success : function(data) {
			var html='';
			var map = data.moneyMap;
			console.log(map);
			if(map!=null){
		
			  
			    needToActualCost=map.cost-map.actualCost;
			    needToActualBill=map.cost-map.actualBill;
			 
				//金额提示部分显示
				$("#contract_money").show();
				
				html +='<label class="col-sm-9 control-label text-danger">该收款型合同，总金额为：'+map.cost+'，已经收款金额：'+map.actualCost+'，已经开出发票额：'+map.actualBill+'</label>	';
				$("#contract_money").append(html);
			}
			}
		});
}
function loadContractList(){
	var html = "";
	
	$.ajax({
		url : '/freeContract/contract/getMoneyInContract',
		success : function(res) {
			var data = res.contractList;
			console.log(data);
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].contractId + '">' + data[i].contractName + '</option>'		
			}
			$("#contractId").append(html);
			$("#contractId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#contractId').on('change', function(e, params) {
				$("#contract_money").empty();
				getContractMoneyDetails(params.selected);
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
function save() {
	var a =$('#actualMoney').val();
	var b =$('#actualBill').val();
    if(returnNormol(a)>needToActualCost) {
    	parent.layer.alert("你填写的金额已经超出合同待收支的金额");
    	return ;
    }
    
    if(returnNormol(b)>needToActualBill){
    	parent.layer.alert("你填写的发票额已经超出合同待开的发票额");
    	return ;
    }
    
	$.ajax({
		cache : true,
		type : "POST",
		url : "/receive/receive/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/money_type',
		success : function(data) {
			console.log(data.length);
			//加载数据
			for (var i = 0; i < 3; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#typeId").append(html);
			$("#typeId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#typeId').on('change', function(e, params) {
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