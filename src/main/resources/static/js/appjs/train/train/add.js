$().ready(function() {

	validateRule();
	loadPoorList();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/train/train/save",
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

function loadPoorList(){
	var html = "";

	$.ajax({
		url : '/poor/poor/findAll',
		success : function(res) {
			var data = res.poorList;
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].username + '">' + data[i].username + '</option>'
			}
			$("#username").append(html);
			$("#username").chosen({
				maxHeight : 200
			});
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