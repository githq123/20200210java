package com.qfedu.bean;

import java.util.List;

public class PageBean {
	//һҳ���ݵĴ�С
	private int pageSize;
	//ҳ��
	private int pageNo;
	//ҳ������
	private int pageSum;
	//��һҳ��ÿһ����Ʒ
	private List<Product> products;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "PageBean [pageSize=" + pageSize + ", pageNo=" + pageNo + ", pageSum=" + pageSum + ", products="
				+ products + "]";
	}
	
}
