package com.yangguang.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.yangguang.domain.Book;
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
		} else if ("addBookURL".equals(op)) {
			addBookURL(req,resp);
		} else if ("addBook".equals(op)) {
			
			addBook(req, resp);
		}
	}


	//添加一本图书
	private void addBook(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		BusinessService businessService = new BusinessServiceImp();
		//Book book = fillBeanUtil.fillBean(request, Book.class);不能使用此方法了
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
			request.setAttribute("msg", "表单有误，请检查您的表单！");
			request.getRequestDispatcher("/manager/savaSuccess.jsp").forward(request, response);
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sFileUpload = new ServletFileUpload(factory);
		Book book = new Book();
		try{
			List<FileItem> items = sFileUpload.parseRequest(request);
			int len = items.size();
			for(FileItem item:items) {
				//封装页面请求中的表格数据到Book中
				if(item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString(request.getCharacterEncoding());
					BeanUtils.setProperty(book, fieldName, fieldValue);
				} else {

					//文件上传
					String fileName = item.getName();
					if(fileName != null && !fileName.trim().equals("")){
						//该文件名：唯一的文件名
						fileName = UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(fileName);
						//计算存储路径
						String storeDirectory = getServletContext().getRealPath("/images");
						String path = makeDirs(storeDirectory, fileName);// /dir1/dir2
						
						book.setPath(path);
						book.setPhotoFileName(fileName);
						
						//上传
						
						item.write(new File(storeDirectory+path+"/"+fileName));
						
					}	
						
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
			//保存书籍信息到数据库中
			businessService.addBook(book);
			request.setAttribute("msg", "书籍保存成功！");
			request.getRequestDispatcher("/manager/savaSuccess.jsp").forward(request, response);
		
		
	}



	//显示添加书籍的界面
	private void addBookURL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	
	//storeDirecotry: images的真是路径
	//filename:UUID的文件名
	public String makeDirs(String storeDirectory,String filename){
		int hashCode = filename.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String newPath = "/"+dir1+"/"+dir2;
		File file = new File(storeDirectory,newPath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		return newPath;
	}

}
