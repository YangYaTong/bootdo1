package com.bootdo.payment.util;

public class PaymentUtil {

		//合同付款的方式
		public static final String TYPE_TRANSFER="1";//转账
		public static final String TYPE_TELEGRAPHIC="2";//付款
		public static final String TYPE_CASH="0";//现金
	
		//合同付款的状态
		public static final String STATE_NOTBEGIN="0";//未开始。未上报
		public static final String STATE_IN_APPROVAL="1";//审批中
		public static final String STATE_APPROVAL_END="2";//审批完成
		public static final String STATE_NORMAL="3";//正常
		public static final String STATE_APPROVAL_OK="5";//审批通过
		public static final String STATE_APPROVAL_NO="4";//审批不通过
		public static final String STATE_EXECTING="7";//正在执行
		public static final String STATE_FINISHED="6";//已经被完成
		
		//合同付款是否需要审批
		public static final String NEED_SP_YES="1";//需要审批
		public static final String NEED_SP_NO="0";//不需要审批
		
		//是否伪删除
		public static final String ISDELETE_NO="0";//没有伪删除
		public static final String ISDELETE_YES="1";//已经伪删除
		
		

}
