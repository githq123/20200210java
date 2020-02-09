package com.qfedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfedu.bean.PageBean;
import com.qfedu.service.ProductService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/page")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private ProductService productService=new ProductService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagesize=request.getParameter("pagesize");
		String pageno=request.getParameter("pageno");
		int pageSize=Integer.parseInt(pagesize);
		int pageNo=Integer.parseInt(pageno);
		PageBean pageBean=productService.getPage(pageSize, pageNo);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(JSONObject.fromObject(pageBean));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
