$().ready(function() {
	validateRule();
	loadType();
	loadContractList();
	$("#contract_money").hide();
});
var needToPlanCost=0;
var needToPlanBill=0;

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function getContractMoneyDetails(id){
	
	$.ajax({
		url : '/freeContract/contract/showMoneyDetails?contractId='+id,
		async : false,
		success : function(data) {
			var html='';
			var map = data.moneyMap;
			console.log(map);
			if(map!=null){
				
				needToPlanCost=map.needPlanCost;
			    needToPlanBill=map.needPlanBill;
			    var contractKind=map.contractKind=="in"?"收":"付";
			    var billKind=map.contractKind=="in"?"开":"收";
				//金额提示部分显示
				$("#contract_money").show();
				
				html +='	<label class="col-sm-11 control-label text-danger">该付款型合同总金额为：'+map.cost+'元，已付款'+map.actualCost+'元，已收回发票'+map.actualBill+'元</label>	';
				$("#contract_money").append(html);
			}
			}
		});
}

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
function loadContractList(){
	var html = "";
	
	$.ajax({
		url : '/freeContract/contract/getMoneyOutContract',
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
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/money_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#paytypeId").append(html);
			$("#paytypeId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#paytypeId').on('change', function(e, params) {
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
	$.ajax({
		cache : true,
		type : "POST",
		url : "/payment/payment/save",
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