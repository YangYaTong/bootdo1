package com.bootdo.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.R;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;

@Controller
@RequestMapping("analysis")
public class TestController {
	
	@GetMapping()
	String Project(){
	    return "line5";
	}
	
	@RequestMapping("/removecauses")  
	@ResponseBody
	public  R removecauses() throws Exception {  
		System.err.println("testController");
	    R result = new R();  

	    Option option = new Option();
	        option.legend("高度(km)与气温(°C)变化关系");

	        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);

	        option.calculable(true);
	        option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

	        ValueAxis valueAxis = new ValueAxis();
	        valueAxis.axisLabel().formatter("{value} °C");
	        option.xAxis(valueAxis);

	        CategoryAxis categoryAxis = new CategoryAxis();
	        categoryAxis.axisLine().onZero(false);
	        categoryAxis.axisLabel().formatter("{value} km");
	        categoryAxis.boundaryGap(false);
	        categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
	        option.yAxis(categoryAxis);

	        Line line = new Line();
	        line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
	        option.series(line); 
	        String optionStr = JSON.toJSONString(option);
	        result.put("option", optionStr);
	         
	   
	    return result;  
	}  
	
	/*
	 * @RequestMapping("/removecauses")
	 * 
	 * @ResponseBody public R removecauses2() throws Exception {
	 * System.err.println("testController"); R result = new R();
	 * 
	 * Option option = new Option();
	 * 
	 * option.legend("合同收入","合同支出");
	 * 
	 * option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new
	 * MagicType(Magic.stack, Magic.tiled), Tool.restore, Tool.saveAsImage);
	 * 
	 * option.calculable(true); option.tooltip();
	 * 
	 * ValueAxis valueAxis = new ValueAxis(); valueAxis.data(1, 2, 3, 4, 5, 6, 7, 8,
	 * 9,10,11,12); valueAxis.axisLabel().formatter("{value} 月份");
	 * option.xAxis(valueAxis);
	 * 
	 * CategoryAxis categoryAxis = new CategoryAxis();
	 * categoryAxis.axisLine().onZero(false);
	 * categoryAxis.axisLabel().formatter("{value} 元");
	 * categoryAxis.boundaryGap(false);
	 * 
	 * categoryAxis.type(AxisType.value); categoryAxis.data(1, 2, 3, 4, 5, 6, 7, 8,
	 * 9,10,11,12); option.yAxis(categoryAxis); Series series = new Series<T>() { };
	 * series.name(""); series. series.data(5345,64376,46,6547,77,54,867,87);
	 * series.animationDuration(); Line line = new Line();
	 * line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5,
	 * -22.1, -2.5, -27.7, -55.7,
	 * -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
	 * option.series(line); String optionStr = JSON.toJSONString(option);
	 * result.put("option", optionStr);
	 * 
	 * 
	 * return result; }
	 */
	
	

}
