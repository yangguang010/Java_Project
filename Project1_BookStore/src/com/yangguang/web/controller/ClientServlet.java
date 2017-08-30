package com.yangguang.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangguang.domain.Category;
import com.yangguang.domain.Page;
import com.yangguang.service.BusinessService;
import com.yangguang.service.BusinessServiceImp;

public class ClientServlet extends HttpServlet {
	private BusinessService businessService = new BusinessServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String op = req.getParameter("op");
		if ("showIndex".trim().equals(op)) {
			showIndex(req,resp);
		}else if ("showCategoryBooks".trim().equals(op)) {
			showCategoryBooks(req,resp);
		}
	}

	//按照分类进行分页查询
	private void showCategoryBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String num = request.getParameter("num");	
		String categoryId = request.getParameter("categoryId");
		//查询所有的分类
		List<Category> categories = businessService.findAllCategories();
		
		//查询所有分类书籍
		Page page = businessService.findAllBookPageRecords(num, categoryId);
		
		page.setUrl("servlet/ClientServlet?op=showCategoryBooks&categoryId="+categoryId);
		
		request.setAttribute("list", categories);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/listAllBooks.jsp").forward(request, response);
	}

	private void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String num = request.getParameter("num");
		//查询所有的分类
		List<Category> list = businessService.findAllCategories();
		//查询所有的分页书籍
		Page page = businessService.findAllBookPageRecords(num);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/listAllBooks.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
}
