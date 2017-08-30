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
	
	/**
	 * 根据分类信息查询该分类的所有图书数量
	 * @return
	 */
	int findCategoryBooksNumber(String categoryId);
	
	/**
	 * 根据页码信息+分类信息查询图书
	 * @param startIndex 查询起始位置
	 * @param pageSize   每页中数据量
	 * @param categoryId 分类Id
	 * @return 查询到的书本信息
	 */
	List<Book> findPageBooks(int startIndex, int pageSize, String categoryId);
}
