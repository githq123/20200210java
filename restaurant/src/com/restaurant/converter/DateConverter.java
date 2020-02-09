package com.restaurant.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConverter extends StrutsTypeConverter {
	private final DateFormat[] dfs={
		new SimpleDateFormat("yyyy年MM月dd日"),new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy/MM/dd"),new SimpleDateFormat("yyyy.MM.dd"),
		new SimpleDateFormat("yy/MM/dd"),new SimpleDateFormat("MM/dd/yy")
		//还可以加更多的类型
	};
	//将指定的格式字符串转换为日期类型
	@Override
	public Object convertFromString(Map context, String[] values, Class toType) {
		String dateStr=values[0];
		for (int i = 0; i <dfs.length; i++) {
			try {
				return dfs[i].parse(dateStr);
			} catch (Exception e) {
				continue;
			}
		}
		throw new TypeConversionException();
	}
	//将日期转换为指定格式字符串
	@Override
	public String convertToString(Map context, Object object) {
		Date date=(Date)object;
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}
