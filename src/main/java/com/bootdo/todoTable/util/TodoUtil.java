package com.bootdo.todoTable.util;

public class TodoUtil {
	
	//待办工作的状态
		public static final String STATE_UNREAD="0";//未读
		public static final String STATE_READ="1";//已读
		public static final String STATE_FINISHED="2";//已经完成
		public static final String STATE_WITHDRAW="2";//已经撤回
		
	//待办工作的类型
		public static final String TODOTYPE_PAY="payment";//合同付款
		public static final String TODOTYPE_CONTRACT_BEGIN="contractBegin";//合同初稿
		public static final String TODOTYPE_CONTRACT_ABNORMAL="contractAbnormal";//合同异常
		public static final String TODOTYPE_CONTRACT_UPDATE_INFO="contractUpdateInfo";//变更履行中的合同信息
		public static final String TODOTYPE_CONTRACT_FINISH="contractFinsh";//合同归档

}
