package com.bootdo.common.ExportWord07Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClass2 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		MSWordTool changer = new MSWordTool();
		changer.setTemplate("C:\\MYTEST.docx");
		Map<String, String> content = new HashMap<String, String>();
		content.put("contractNO", "Hao格式规范、标准统一、利于阅览");
		content.put("opppsiteCompany", "规范会议操作、提高会议质量");
		content.put("ourCompany", "公司会议、部门之间业务协调会议");
		content.put("contractName", "XX合同");
		content.put("invatationNO", "**有限公司");
		content.put("supplyDate", "机场路2号");
		content.put("disagreeDate", "3021170207");
		content.put("startTime", "水泥制造");
		content.put("endTime", "1.085");
		content.put("largeMoney", "888");

		changer.replaceBookMark(content);

		/*
		 * // 替换表格标签 List<Map<String, String>> content2 = new ArrayList<Map<String,
		 * String>>(); Map<String, String> table1 = new HashMap<String, String>();
		 * 
		 * 
		 * 
		 * table1.put("xvhao", "*月份"); table1.put("pinpai", "1"); table1.put("peizhi",
		 * "2"); table1.put("jizhun", "3"); table1.put("shuliang", "4");
		 * table1.put("youhui", "5"); table1.put("caigou", "6"); table1.put("xianshiqi",
		 * "7"); table1.put("chajia", "8"); table1.put("xiaoji", "9");
		 * content2.add(table1); System.err.println("content2--"+content2);
		 * changer.fillTableAtBookMark("Table" ,content2);
		 */
		
		
		//替换表格标签
		List<Map<String ,String>> content3 = new ArrayList<Map<String, String>>();
		Map<String, String> table2 = new HashMap<String, String>();
 
		table2.put("A", "*月份");
		table2.put("B", "75分");
		table2.put("C", "80分");
		table2.put("D", "85分");
		table2.put("E", "90分");
		table2.put("F", "95分");
		table2.put("G", "80分");
		table2.put("H", "85分");
 

			content3.add(table2);
		
		changer.fillTableAtBookMark("Table3" ,content3);
		changer.fillTableAtBookMark("Table" ,content3);
		

		 //changer.replaceText(table1,"Table");

		// 保存替换后的WORD
		changer.saveAs("c://test0099.docx");
		System.out.println("time==" + (System.currentTimeMillis() - startTime));

	}

}
