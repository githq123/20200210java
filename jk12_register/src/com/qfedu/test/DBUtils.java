package com.qfedu.test;

import java.util.LinkedList;

//Type
//E  Element
public class DBUtils<T> {

	public String getString() {
		
		return "";
	}
	public Integer getInteger() {
		
		return 1;
	}
	
	public T get(Class<T> cls) {
		//����ܵõ�һ��T���͵Ķ����أ�
		try {
			return cls.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}
