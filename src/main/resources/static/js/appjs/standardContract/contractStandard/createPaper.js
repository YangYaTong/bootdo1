$().ready(function() {
	validateRule();
	loadMoudleList();

});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
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