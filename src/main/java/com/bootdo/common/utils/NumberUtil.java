package com.bootdo.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


public class NumberUtil {
	/**
	 * 将货币格式转换为数字
	 * @param str 如￥1000，000，44的字符串
	 * @return  成功--返回数字  不成功---- 返回原来字符串
	 */
	   public static  String   changeNomarlFormat (String str1) {
		   if(!str1.contains("￥")) {
			   return str1;
		   }
		   
		   if(str1==null||"".equals(str1)) {
			   return "0";
		   }
		   String str=str1.replace("￥", "");
			try {
				str = new DecimalFormat().parse(str).longValue()+"";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return str1;
			}
			
   	
   	    return str;   
   	}  
	   
	   
	   /**
	    * 将数字格式转换为带千分符的货币格式
	    * @param str  如7575489576389的字符串
	    * @return  如￥1000，000，44的字符串
	    */
	   public static String changeMoneyFormat(String str) {
		   if(str.contains("￥")) {
			   return str;
		   }
		   
		   if(str==null||"".equals(str)) {
			   return "0";
		   }
		   
		   NumberFormat number = NumberFormat.getNumberInstance();
		   String moneyStr =number.format(Long.parseLong(str));
		   
		   return "￥"+moneyStr;
	   }
	   
		public static boolean isNumber(String str) {
			if (str == null || "".equals(str)) {
				return false;
			}
			String reg = "^[-|+]?\\d*([.]\\d+)?$";
			return str.matches(reg);
		}

}
