package com.yangguang.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yangguang.domain.Book;
import com.yangguang.util.JDBCUtil;

public class BookDaoImp implements BookDao {
	
	private QueryRunner qRunner = new QueryRunner(JDBCUtil.getDataSource());
	public void save(Book book) {
		try {
			qRunner.update("insert into books (id,name,author,price,path,photoFileName,description,categoryId) values(?,?,?,?,?,?,?,?)",
					book.getId(),
					book.getName(),
					book.getAuthor(),
					book.getPrice(),
					book.getPath(),
					book.getPhotoFileName(),
					book.getDescription(),
					book.getCategoryID()
					);
		} catch (SQLException e) {
			
			throw new RuntimeException();
		}
	}

	

	public Book findById(String bookId) {
		
		try {
			 return qRunner.query("select * from books where id=?", new BeanHandler<Book>(Book.class), bookId);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		

	}

	public int findAllBooksNumber() {
		try {
			Object object = qRunner.query("select count(*) from books", new ScalarHandler(1));
			Long num = (Long)object;
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	
	public List<Book> findPageBooks(int startIndex, int offset) {
		
		try {
			qRunner.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,offset);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return null;
	}

}
