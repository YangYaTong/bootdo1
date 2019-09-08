package com.bootdo.matter.util;
public  class MatterUtil {
	

	//合同计划的类型
		public static final String TYPE_MONEY_IN="1";//收款
		public static final String TYPE_MONEY_OUT="-1";//付款
		public static final String TYPE_BILL_IN="2";//收发票
		public static final String TYPE_BILL_OUT="-2";//开发票
		public static final String TYPE_OTHER="500";// 其他

		//合同计划状态
		
		public static final String STATE_UNREMIND="0";//未被提醒
		public static final String STATE_ALREADY_REMIND="-1";//已经被提醒
		public static final String STATE_EXPIRED="2";//已经过期
		public static final String STATE_FINISHED="6";//已经被完成
		
		//是否被伪删除
		public static final String ISDELETE_NO="0";//未删除
		public static final String ISDELETE_YES="1";//已删除
		
		//是否需要提醒
		public static final String NEED_REMIND_NO="0";//不需要提醒
		public static final String NEED_REMIND_YES="1";//需要提醒
		
		//批量添加的合同计划事项的默认提醒提前量
		public static final String DEFAULT_REMIND_DAY="7";//默认提前7天提醒
		
		
		

}
