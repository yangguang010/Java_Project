package com.yangguang.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangguang.domain.Category;
import com.yangguang.service.BusinessService;
import com.yangguang.service.BusinessServiceImp;
import com.yangguang.util.fillBeanUtil;

public class ControlServlet extends HttpServlet {
	
	private BusinessService businessService = new BusinessServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String op = req.getParameter("op");
		if("addCategory".equals(op)) {
			addCategory(req,resp);
		}
		else if("showAllCategories".equals(op)) {
			showAllCategories(req,resp);
		} else if ("findAllCategory".equals(op)) {
			findAllCategory(req,resp);
		} else if ("addBook".equals(op)) {
			addBook(req,resp);
		}
	}

	
	//显示添加书籍的界面
	private void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = new ArrayList<Category>();
		categories = businessService.findAllCategories();
		
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
		
	}


	//查询所有分类
	private void showAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = new ArrayList<Category>();
		categories = businessService.findAllCategories();
		
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
	}



	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从请求里面将数据进行封装
		Category category = fillBeanUtil.fillBean(request,Category.class);
		
		category.setId(UUID.randomUUID().toString());
		businessService.addCategory(category);
		
		//转到一个界面
		request.setAttribute("msg", "保存成功！");
		request.getRequestDispatcher("/manager/savaSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req,  resp);
	}
	
	
	

}
