
var prefix = "/freeContract/contract"
$(function() {
	$("#contract_annexesid").hide();
	$("#contract_annexes_div").hide();
	$("#contract_response_div").hide();
	$("#contract_Money_div").hide();
	
	
	//显示合同的基本信息附件信息
	loadContractBasicInfo();
	//显示合同附件信息
	loadContractAnnexesInfo();
	//显示合同批示信息
	loadContractResponseInfo();
	//显示合同资金情况
	loadContractMoneyInfo();
	
	//显示合同的计划信息
	//loadContractMatterInfo();
	//显示合同的变更记录
	//loadContractChangeInfo();
	//显示合同的财务信息
	//loadContractFinanceInfo();
	//显示合同的批示信息
	//loadContractResponseInfo();
	

	

});

var contract_kind="";

function loadContractResponseInfo(){


	$.ajax({
		url : '/response/response/showContractResponseInfo?contractId='+contractId,
		success : function(res) {
			console.log("response"+res.responseList);

			var data = res.responseList;
			if(data==null||data==""){
				return;
			}
			
			$("#contract_response_div").show();
			console.log(data);
			$("#response_thead").append('<tr><th>名称</th><th>批示人</th><th>批示意见</th><th>批示结论</th><th>批示时间</th></tr>');
			//$("#contract_annexesid").show();
			var html_body ='';
			//加载数据
			for (var i = 0; i < data.length; i++) {
			
				
				html_body +='<tr>'
					+'<td>'+data[i].responseName+'</th>'
					+'<td>'+data[i].maker+'</th>'
					+'<td>'+data[i].opinion+'</th>'
					+'<td>'+data[i].result+'</th>'
					+'<td>'+data[i].creatiedTime+'</th>'
					+'</tr>';
		
			}
			
			$("#response_tbody").append(html_body);
			
		
		}
	});
	
}
function loadContractAnnexesInfo(){

	var id=66;
	$.ajax({
		url : '/anexes/annexes/showContractAnnexesInfo?contractId='+contractId,
		success : function(res) {
			
			console.log("annexes"+res.annexesList);
			var data = res.annexesList;
			if(data==null||data==""){
				return;
			}
			$("#contract_annexes_div").show();
			
			$("#annexes_thead").append('<tr><th>附件名称</th><th>创建人</th><th>创建时间</th><th>操作</th></tr>');
			//$("#contract_annexesid").show();
			var html_body ='';
			//加载数据
			for (var i = 0; i < data.length; i++) {
			
				
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

var html = "";


function loadContractBasicInfo(){

	

	$.ajax({
		url : '/freeContract/contract/showBasicInfo?contractId='+contractId,
		async : false,
		success : function(data) {
			var html='';
			var contract = data.contract;
			contract_kind=contract.contractKind;
		
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
	});
	
}

function loadContractMoneyInfo(){

	var id=66;
	var url ="";

	if(contract_kind=="收入合同"){

		$.ajax({
			url : '/receive/receive/showContractReceiveInfo?contractId='+contractId,
			success : function(res) {
				console.log("money"+res.receiveList);
		
				var data = res.receiveList;
				if(data==null||data==""){
					return;
				}
				
				
				$("#contract_Money_div").show();
				console.log(data);
				$("#money_thead").append('<tr><th>收款时间</th><th>收款金额</th><th>开票金额</th><th>收款公司</th><th>收款账号</th><th>承办人</th></tr>');
				//$("#contract_annexesid").show();
				var html_body ='';
				//加载数据
				for (var i = 0; i < data.length; i++) {
					html_body +='<tr>'
						+'<td>'+data[i].actualDate+'</th>'
						+'<td>'+data[i].actualMoney+'</th>'
						+'<td>'+data[i].actualBill+'</th>'
						+'<td>'+data[i].acceptCompany+'</th>'
						+'<td>'+data[i].acceptCount+'</th>'
						+'<td>'+data[i].myself+'</th>'
		
						+'</tr>';
				}
				
				$("#money_tbody").append(html_body);
				
			
			}
		});
		
		
		
	}else if(contract_kind=="支出合同"){


		$.ajax({
			url : '/payment/payment/showContractPaymentInfo?contractId='+id,
			success : function(res) {
		
				console.log("money"+res.paymentList);
				var data = res.paymentList;
	
				if(data==null||data==""){
					return;
				}
				$("#contract_Money_div").show();
			
				$("#money_thead").append('<tr><th>付款时间</th><th>付款金额</th><th>发票金额</th><th>收款公司</th><th>收款账号</th><th>承办人</th><th>批准人</th></tr>');
				//$("#contract_annexesid").show();
				var html_body ='';
				//加载数据
				for (var i = 0; i < data.length; i++) {
					html_body +='<tr>'
						+'<td>'+data[i].actualDate+'</th>'
						+'<td>'+data[i].actualMoney+'</th>'
						+'<td>'+data[i].actualBill+'</th>'
						+'<td>'+data[i].acceptCompany+'</th>'
						+'<td>'+data[i].acceptCount+'</th>'
						+'<td>'+data[i].myself+'</th>'
						+'<td>'+data[i].checker+'</th>'
						+'</tr>';
			
				}
				
				$("#money_tbody").append(html_body);
				
			
			}
		});
	}
	
	
}
































function load2() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : false, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
															{
									field : 'contractId', 
									title : 'ID' 
								},
																{
									field : 'contractName', 
									title : '合同名称' 
								},

																{
									field : 'contractKind', 
									title : '资金类型' ,
									align : 'center',
									formatter : function(value, row, index) {
										var hh=''
										var text='';
										if(row.contractKind=="in"){
											hh='primary';
											text="收款";
										}else{
											hh='danger';
											text="付款";
										}
										var e = '<span class="label label-'+hh+'">'+text+'</span>';
										return e  ;
									}
								},  
																{
									field : 'contractType', 
									title : '合同类型' 
								},
																{
									field : 'cintractNo', 
									title : '合同编号' 
								},
																{
									field : 'projectId', 
									title : '所属项目' 
								},
																{
									field : 'otherpartId', 
									title : '对方公司' 
								},
																{
									field : 'ourpartId', 
									title : '我方公司' 
								},
																{
									field : 'cost', 
									title : '金额' 
								},
															
																{
									field : 'needCost', 
									title : '待收/付金额' 
								},

																{
									field : 'moneyProgress', 
									title : '资金进度' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<div class="progress">'
											  +'<div class="progress-bar progress-bar-info"   style="width: '+ row.moneyProgress+'">'
											 
									  +'</div>'+row.moneyProgress
									  +'</div>';
										return e  ;
									}
								},

																{
									field : 'billProgress', 
									title : '发票进度' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<div class="progress">'
											  +'<div class="progress-bar progress-bar-success"   style="width: '+  row.billProgress+'">'
											
									  +'</div>'+row.billProgress
									  +'</div>';
										return e  ;
									}
									
									
									
								},
										
									
																{
									field : 'mainpaper', 
									title : '主要附件' ,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看" onclick="chakan(\''
												+ row.mainpaper
												+ '\')"><i class="fa fa-eye"></i></a> ';
										return e  ;
									}
									
								},				            	{
									field : 'state', 
									title : '状态' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.contractId
										+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.contractId
										+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="上报审批"  mce_href="#" onclick="resetPwd(\''
										+ row.contractId
										+ '\')"><i class="fa fa-send"></i></a> ';
										var k = '<a class="btn btn-success btn-sm" href="#" title="变更"  mce_href="#" onclick="resetPwd(\''
									+ row.contractId
									+ '\')"><i class="fa fa-edit"></i></a> ';
					
										if(row.state=="审批中"){
											return "";
										}else{
											
											return e + d+f ;
										}
										
									
										
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
	showtotal();
}
