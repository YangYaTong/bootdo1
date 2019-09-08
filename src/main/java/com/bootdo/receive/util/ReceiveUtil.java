package com.bootdo.receive.util;

public class ReceiveUtil {

	//合同收款的方式
	public static final String TYPE_TRANSFER="1";//转账
	public static final String TYPE_TELEGRAPHIC="2";//付款
	public static final String TYPE_CASH="0";//现金


	//合同收款的方式


	public static final String STATE_NOT_BEGIN="0";//未开始
	public static final String STATE_IN_APPROVAL="1";//审批中
	public static final String STATE_APPROVAL_END="2";//已经过期
	public static final String STATE_NORMAL="3";//正常
	public static final String STATE_AOOROVAL_NO="4";//审批未通过
	public static final String STATE_AOOROVAL_OK="5";//审批通过
	public static final String STATE_FINISHED="6";//已经被完成

}
