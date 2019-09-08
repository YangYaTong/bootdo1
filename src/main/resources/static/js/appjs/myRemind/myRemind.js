
var prefix = "/myRemind/myRemind"
$(function() {
	load();
});

function load() {


		$.ajax({
			url : "/contractRemind/remind"+"/getMyRemind",
			type : "get",
			data : {
				
			},
			success : function(res) {
				var html='';
					for (var i = 0; i < res.length; i++) {
						if(res[i].matterId!=-1){
							html += '<div class="alert alert-success" role="alert">'
								  +'<span class="glyphicon glyphicon-time">'+res[i].remindName+'将于'+res[i].remindDate+'日到期！</span><a href="#" onclick="chakan('+res[i].remindId+')" class="alert-link">点击查看</a>'
								  +'</div>';
						}else{
							html += '<div class="alert alert-danger" role="alert">'
								  +'<span class="glyphicon glyphicon-time">'+res[i].remindName+'将于'+res[i].remindDate+'日到期！</span><a href="#" onclick="chakan('+res[i].remindId+')" class="alert-link">点击查看</a>'
								  +'</div>';
							
						}
						
					}
					$("#matter_div").append(html);
				
			
				//  +'<span class="glyphicon glyphicon-time">'+res[i].remindName+'将于'+res[i].remindDate+'日到期！</span><a href="/contractRemind/remind/toSee/'+res[i].remindId+'" class="alert-link">点击查看</a>'
					
	
			}
		});
	
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function chakan(id) {
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/contractRemind/remind/toSee/'+ id // iframe的url
	});
}

function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'matterId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['matterId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}