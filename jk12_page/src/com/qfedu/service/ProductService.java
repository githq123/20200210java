package com.qfedu.service;

import java.util.List;

import com.qfedu.bean.PageBean;
import com.qfedu.bean.Product;
import com.qfedu.dao.ProductDao;

public class ProductService {
	private ProductDao productDao=new ProductDao();
	
	//Ϊ��ѯһҳ�����ṩҵ���߼�
	public PageBean getPage(int pageSize,int pageNo) {
		
		List<Product> products=productDao.selectPage(pageSize, pageNo);
		long count=productDao.selectProductCount();
		//���һ��count�����ݣ�ÿһҳ������pageSize�����ʣ�һ���ж���ҳ��
		//count/pageSizeҳ��
		long pageSum=count/pageSize+(count%pageSize==0?0:1);
		PageBean pageBean=new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setPageSum((int) pageSum);
		pageBean.setProducts(products);
		return pageBean;
		
	}
	public void addProduct(String name,String price,int num,String category) {
		productDao.insertToProduct(name,price, num,  category);
	}
	public void delProduct(int id) {
		productDao.deleteProduct(id);
	}
	public void updateProduct(int id,String name,String price,int num,String category) {
		productDao.updateProduct(id,name,price, num, category);
	}
	public Product selectProduct(int id) {
		return productDao.selectProduct(id);
	}
	
}
