package com.yangguang.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangguang.domain.Book;
import com.yangguang.domain.Category;
import com.yangguang.domain.Page;
import com.yangguang.service.BusinessService;
import com.yangguang.service.BusinessServiceImp;
import com.yangguang.web.beans.Cart;
import com.yangguang.web.beans.CartItem;

public class ClientServlet extends HttpServlet {
	private BusinessService businessService = new BusinessServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String op = req.getParameter("op");
		if ("showIndex".trim().equals(op)) {
			showIndex(req,resp);
		}else if ("showCategoryBooks".trim().equals(op)) {
			showCategoryBooks(req,resp);
		}else if ("showBookDetails".trim().equals(op)) {
			showBookDetails(req,resp);
		}else if ("buyBooks".trim().equals(op)) {
			buyBooks(req,resp);
		}else if ("changeNum".trim().equals(op)) {
			changeNum(req,resp);
		}else if ("delOneItem".trim().equals(op)) {
			delOneItem(req,resp);
		}
	}
	
	//删除购物车中的某项
	private void delOneItem(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			String bookId = request.getParameter("bookId");
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.getItems().remove(bookId);
			response.sendRedirect(request.getContextPath()+"/showCart.jsp");
			
	}
	//修改购物车中的书本数量及价格
	private void changeNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String bookId = request.getParameter("bookId");
		int bookNum = Integer.parseInt(request.getParameter("num"));
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		CartItem item = cart.getItems().get(bookId);
		item.setQuantity(bookNum);
		
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
		
	}

	//将要买的书籍添加到购物车
	private void buyBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String bookId = request.getParameter("bookId");
		Book book = businessService.findBookById(bookId);
		//放入购物车
			//为了保证每一个用户只有一个购物车，必须首先进行判断
		
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			session.setAttribute("cart",cart);
		}
		//用户肯定有一个购物车
		cart.addBook(book);
		
		request.setAttribute("msg", "书籍以及放入您的购物车，<a href='"+request.getContextPath()+"'>继续购物</a>");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	//显示书本的id查询书籍的详细信息
	private void showBookDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("id");
		Book book = businessService.findBookById(id);
		
		request.setAttribute("book", book);
		
		request.getRequestDispatcher("/showDetails.jsp").forward(request, response);
		
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
