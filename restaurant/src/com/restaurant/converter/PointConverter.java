package com.restaurant.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.restaurant.entity.Point;

public class PointConverter extends StrutsTypeConverter {
	//将字符串转为坐标类型
	@Override
	public Object convertFromString(Map context, String[] values, Class toType) {
		//获取x，y的坐标
		String str=values[0];
		String xy[]=str.split(",");
		if (xy.length!=2) {
			throw new TypeConversionException();
		}
		double x=Double.parseDouble(xy[0]);
		double y=Double.parseDouble(xy[1]);
		Point point=new Point();
		point.setX(x);
		point.setY(y);
		return point;
	}
	//将坐标对象转换为字符串
	@Override
	public String convertToString(Map context, Object object) {
		Point point=(Point)object;
		double x=point.getX();
		double y=point.getX();
		String str="("+x+","+y+")";
		return str;
	}

}
