$().ready(function() {
	validateRule();
	loadType();
	loadContractList();
	$("#contract_money").hide();

});

var needToPlanCost=0;
var needToPlanBill=0;

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
				
				needToPlanCost=map.needPlanCost;
			    needToPlanBill=map.needPlanBill;
			    var contractKind=map.contractKind=="in"?"收":"付";
			    var billKind=map.contractKind=="in"?"开":"收";
				//金额提示部分显示
				$("#contract_money").show();
				
				html +='	<label class="col-sm-11 control-label text-danger">该'+contractKind+'型合同总金额为：'+map.cost+'，待计划'+contractKind+'款金额：'+map.needPlanCost+'，待计划'+billKind+'票额：'+map.needPlanBill+'</label>	';
				$("#contract_money").append(html);
			}
			}
		});
}

function loadContractList(){
	var html = "";
	
	$.ajax({
		url : '/freeContract/contract/findAll',
		success : function(res) {
			var data = res.contractList;
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


$(function () {
    laydate({
        elem : '#planDate'
    });
});
/*function laydate(){
	layui.use(['form','laydate'], function(){
	      var  form = layui.form ;
	              $ = layui.jquery ;
	               laydate = layui.laydate;
	            
	          laydate.render({     //创建时间选择框
	                elem: '#borrowtimebox' //指定元素
	              });
	          
	    });
}*/

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/matter_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#matterType").append(html);
			$("#matterType").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#matterType').on('change', function(e, params) {
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
	
	var a =$('#matterCost').val();
	var b =$('#billCost').val();


    if(returnNormol(a)>needToPlanCost) {
    	parent.layer.alert("你填写的金额已经超出需要计划的额度");
    	return ;
    }
    
    if(returnNormol(b)>needToPlanBill){
    	parent.layer.alert("你填写的发票额已经超出需要计划的额度");
    	return ;
    }
    
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/matter/matter/save",
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