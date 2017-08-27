package com.yangguang.dao;

import java.util.List;

import com.yangguang.domain.Book;

public interface BookDao {
	/**
	 * 保存图书
	 * @param book
	 */
	void save(Book book);
	
	/**
	 * 书籍的总条数
	 * @return 
	 */
	int findAllBooksNumber();
	
	/**
	 * 根据要求读取数据库中的书籍
	 * @param startIndex
	 * @param offset
	 * @return
	 */
	List<Book> findPageBooks(int startIndex,int offset);
	
	/**
	 * 根据书本ID号查询图书
	 * @param bookId
	 * @return
	 */
	Book findById(String bookId);
}
