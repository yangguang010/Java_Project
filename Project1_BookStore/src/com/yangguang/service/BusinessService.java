package com.yangguang.service;

import java.util.List;

import com.yangguang.domain.Book;
import com.yangguang.domain.Category;
import com.yangguang.domain.Page;

public interface BusinessService {
	/**
	 * 添加一个分类
	 * @param category
	 */
	void addCategory(Category category);
	
	/**
	 * 查询所有的分类
	 * @return
	 */
	List<Category> findAllCategories();
	/**
	 * 根据ID查询单个分类
	 * @param CategoryId
	 * @return 没有找到返回null
	 */
	Category findCategoryById(String CategoryId);
	
	/**
	 * 添加图书
	 * @param book
	 */
	void addBook(Book book);
	
	Page findAllBookPageRecords(String pagenum);
	
	Book findBookById(String bookId);
	
}
