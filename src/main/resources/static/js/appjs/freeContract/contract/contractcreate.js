
var prefix = "/freeContract/contract"
$(function() {
	//load();
});



function toSubmit(){

	//$("#execl").val("");
	//执行隐藏的按钮点击事件
	 $("#execl").click();

	
	
	// $("#submit_btn").click();
	 
	
	
	
}


function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function addnormal() {
	layer.open({
		type : 2,
		title : '增加普通合同',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}

function exportExcel() {
	

    //后端导出的方法
    document.location.href = prefix + "/exportModelExcel";
}

function addstandard() {
	layer.open({
		type : 2,
		title : '增加框架合同',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/standardContract/contractStandard/add' // iframe的url
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
function chakan(mainpaper) {
	var data1 =mainpaper+"";
var data = JSON.parse('[' + mainpaper + ']');
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content :prefix + '/chakan?mainpaper='+data // iframe的url
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
				'contractId' : id
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
	layer.open({
		type : 2,
		title : '选择要上报对象',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1000px', '850px' ],
		content : prefix + '/up/' + id // iframe的url
	});
	
	
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
			ids[i] = row['contractId'];
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