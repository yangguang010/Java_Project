package com.yangguang.dao;

import java.util.List;

import com.yangguang.domain.Book;
import com.yangguang.domain.Category;

public interface CategoryDao {

	void save(Category category);

	List<Category> findAll();

	Category findById(String categoryId);
	
	void addBook(Book book);

}
