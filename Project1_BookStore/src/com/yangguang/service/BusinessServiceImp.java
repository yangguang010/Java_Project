package com.yangguang.service;

import java.util.List;
import java.util.UUID;

import com.yangguang.dao.BookDao;
import com.yangguang.dao.BookDaoImp;
import com.yangguang.dao.CategoryDao;
import com.yangguang.dao.CategoryDaoImp;
import com.yangguang.domain.Book;
import com.yangguang.domain.Category;
import com.yangguang.domain.Page;

public class BusinessServiceImp implements BusinessService {

	private CategoryDao categoryDao = new CategoryDaoImp();
	private BookDao bookDao = new BookDaoImp();
	public void addCategory(Category category) {
		category.setId(UUID.randomUUID().toString());
		categoryDao.save(category);
	}

	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	public Category findCategoryById(String CategoryId) {
		return categoryDao.findById(CategoryId);
	}

	
	public void addBook(Book book) {
		book.setId(UUID.randomUUID().toString());
		bookDao.save(book);
		
	}

	
	public Page findAllBookPageRecords(String pagenum) {
		
		int currentPageNum = 1;
		if (pagenum != null) {
			currentPageNum = Integer.parseInt(pagenum);
		}
		
		int totalRecords = bookDao.findAllBooksNumber();
		Page page = new Page(currentPageNum, totalRecords);
		
		page.setRecords(bookDao.findPageBooks(page.getStartIndex(), page.getPageSize()));
		
		return page;
	}

	
	public Book findBookById(String bookId) {
		
		return bookDao.findById(bookId);
	}

}
