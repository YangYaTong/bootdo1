package com.bootdo.standardContract.requestVO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于封装由前端向后台传递表格内容 模板所附表格的VO类，模板表格最大限度为10列
 * 
 * @author Administrator
 *
 */
public class TableVO {
	String[] clum0 = {};
	String[] clum1 = {};
	String[] clum2 = {};
	String[] clum3 = {};
	String[] clum4 = {};
	String[] clum5 = {};
	String[] clum6 = {};
	String[] clum7 = {};
	String[] clum8 = {};
	String[] clum9 = {};
	String[] clum10 = {};

	@Override
	public String toString() {
		return "tableVO [clum0=" + Arrays.toString(clum0) + ", clum1=" + Arrays.toString(clum1) + ", clum2="
				+ Arrays.toString(clum2) + ", clum3=" + Arrays.toString(clum3) + ", clum4=" + Arrays.toString(clum4)
				+ ", clum5=" + Arrays.toString(clum5) + ", clum6=" + Arrays.toString(clum6) + ", clum7="
				+ Arrays.toString(clum7) + ", clum8=" + Arrays.toString(clum8) + ", clum9=" + Arrays.toString(clum9)
				+ ", clum10=" + Arrays.toString(clum10) + "]";
	}

	public TableVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableVO(String[] clum0, String[] clum1, String[] clum2, String[] clum3, String[] clum4, String[] clum5,
			String[] clum6, String[] clum7, String[] clum8, String[] clum9, String[] clum10) {
		super();
		this.clum0 = clum0;
		this.clum1 = clum1;
		this.clum2 = clum2;
		this.clum3 = clum3;
		this.clum4 = clum4;
		this.clum5 = clum5;
		this.clum6 = clum6;
		this.clum7 = clum7;
		this.clum8 = clum8;
		this.clum9 = clum9;
		this.clum10 = clum10;
	}

	public String[] getClum0() {
		return clum0;
	}

	public void setClum0(String[] clum0) {
		this.clum0 = clum0;
	}

	public String[] getClum1() {
		return clum1;
	}

	public void setClum1(String[] clum1) {
		this.clum1 = clum1;
	}

	public String[] getClum2() {
		return clum2;
	}

	public void setClum2(String[] clum2) {
		this.clum2 = clum2;
	}

	public String[] getClum3() {
		return clum3;
	}

	public void setClum3(String[] clum3) {
		this.clum3 = clum3;
	}

	public String[] getClum4() {
		return clum4;
	}

	public void setClum4(String[] clum4) {
		this.clum4 = clum4;
	}

	public String[] getClum5() {
		return clum5;
	}

	public void setClum5(String[] clum5) {
		this.clum5 = clum5;
	}

	public String[] getClum6() {
		return clum6;
	}

	public void setClum6(String[] clum6) {
		this.clum6 = clum6;
	}

	public String[] getClum7() {
		return clum7;
	}

	public void setClum7(String[] clum7) {
		this.clum7 = clum7;
	}

	public String[] getClum8() {
		return clum8;
	}

	public void setClum8(String[] clum8) {
		this.clum8 = clum8;
	}

	public String[] getClum9() {
		return clum9;
	}

	public void setClum9(String[] clum9) {
		this.clum9 = clum9;
	}

	public String[] getClum10() {
		return clum10;
	}

	public void setClum10(String[] clum10) {
		this.clum10 = clum10;
	}

	Map<String, String[]> map = new HashMap<String, String[]>();

	public Map<String, String[]> toMap() {

		map.put("clum0", clum0);
		map.put("clum1", clum1);
		map.put("clum2", clum2);
		map.put("clum3", clum3);
		map.put("clum4", clum4);
		map.put("clum5", clum5);
		map.put("clum6", clum6);
		map.put("clum7", clum7);
		map.put("clum8", clum8);
		map.put("clum9", clum9);
		map.put("clum10", clum10);

		return map;
	}

}
