package com.bootdo.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringUtil {
	public static String getStringValue(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static int getStringLength(String str) {
		if (str == null) {
			return 0;
		}
		return str.getBytes().length;
	}

	public static boolean hasValue(Object o) {
		if (o == null || o.toString().trim().equals("") || o.toString().trim().equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	public static boolean isNumber(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		String reg = "^[-|+]?\\d*([.]\\d+)?$";
		return str.matches(reg);
	}

	public static int toInteger(String str) {
		return Integer.parseInt(str);
	}

	/**
	 * 功能：把数字转化成字符串， 并根据长度在前面补0
	 * 
	 * 
	 */
	public static String switchToString(int val, int len) {
		String strVal = "" + val;
		int strLen = strVal.length();
		if (strVal.length() < len) {
			for (int i = 0; i < len - strLen; ++i) {
				strVal = "0" + strVal;
			}
		}
		return strVal;
	}
	
	/**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        //2019-2-10 解决StringUtils.lowerCaseFirst潜在的NPE异常@liutf
        return (str!=null&&str.length()>1)?str.substring(0, 1).toLowerCase() + str.substring(1):"";
    }
    
    /**
     * 全部小写
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        return (str!=null&&str.length()>1)?str.toLowerCase():"";
    }
    

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
    
    /*
     * 作者:赵星海
     * 作用:自定义String过滤
     * content 输入的内容--例:"dsadsada<@#$@#$>啦啦啦123123<&*&*&*>"
     * start 要剔除内容的开头字--例:"<"
     * end 要剔除内容的结尾字符--例:">"
     * return 返回剔除后得到的结果--例:"dsadsada啦啦啦123123<&*&*&*>"
     * */
     public static String getText(String content, String start, String end) {
         // Log.e("截取前++--  ", content);
         int start1 = 0;
         int end1 = 0;
         if (content.indexOf(start) != -1 && content.indexOf(end) != -1) {
             start1 = content.indexOf(start);
             end1 = content.indexOf(end) + 1;
             if (start1 < end1) {
                 String delete = content.substring(start1, end1);
                 String replace = content.replace(delete, "");
                 return replace;
             }
             return content;
  
         } else {
             return content;
         }
  
     }
     
  // 计算字符串在给定字符串出现的次数
     public static List<String> findCount(String src,
                                          String des,
                                          String des2) {
       int index = 0;
       int startIndex = 0;
       int endIndex = 0;
       String tempDes = "";

       List<String> list = new ArrayList<>();

       while ((index = src.indexOf(des, index)) != -1) {
         index = index + des.length();

         if (startIndex == 0) {
           startIndex = index;
           // 交换查找的字符串，用于处理${notes}形式的参数,这种情况用于处理开始的标志和结尾的标志不一样的参数
           if (StringUtils.isNotEmpty(des2)) {
             // 如果有第二个参数标志则替换
             tempDes = des;
             des = des2;
           }
         } else {
           endIndex = index;
           // 参数交换，查找以${开头的索引值
           if (StringUtils.isNotEmpty(des2)) {
             des = tempDes;
           }
         }

         if (startIndex > 0 && endIndex > 0) {
           list.add(src.substring(startIndex, endIndex - 1));
           startIndex = 0;
           endIndex = 0;
         }
       }

       return list;
     }
/**
 * 去掉list里面的重复的
 * @param list
 * @return
 */
     public static List removeDuplicate(List list) {   
    	    HashSet h = new HashSet(list);   
    	    list.clear();   
    	    list.addAll(h);   
    	    return list;   
    	}  

    public static void main(String args[]) { 
    	System.out.println(underlineToCamelCase("a_aaa_bb_c"));
    }  

}
