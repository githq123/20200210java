package com.qfedu.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(1);
		user.setUsername("����");
		user.setPassword("123456");
		user.setSex("��");
		user.setAge(18);
		System.out.println(user.toString());
		
		User user1=new User();
		user1.setId(2);
		user1.setUsername("����");
		user1.setPassword("666666");
		user1.setSex("Ů");
		user1.setAge(19);
		//����JsonObject
		JSONObject jsonObject=JSONObject.fromObject(user);
		System.out.println(jsonObject);
		
		
		List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user1);
		//����JsonArray
		JSONArray jsonArray=JSONArray.fromObject(list);
		System.out.println(jsonArray);
		
		//�ڲ��� �������ͳ�Ա�Ķ���
		//����json��ʱ�����������������ͨ�����ͣ����õĳ�ԱҲ����jsonObject
		//������������Ǽ������ͣ����õĳ�Ա�����ΪjsonArray
		
		
		UserManager userManager=new UserManager();
		userManager.setId(98);
		userManager.setName("����Ա");
		userManager.setUser(user);
		userManager.setUsers(list);
		
		JSONObject managerObject=JSONObject.fromObject(userManager);
		System.out.println(managerObject);
		
	}

}
