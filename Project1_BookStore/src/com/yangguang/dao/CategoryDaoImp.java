package com.yangguang.dao;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yangguang.domain.Book;
import com.yangguang.domain.Category;
import com.yangguang.exception.DaoException;
import com.yangguang.util.JDBCUtil;

public class CategoryDaoImp implements CategoryDao {
	
	private QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
	public void save(Category category) {
		try {
			queryRunner.update("insert into categorys (id,name,description) value(?,?,?)",category.getId(),category.getName(),category.getDescription());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}

	public List<Category> findAll() {
		try {
			return queryRunner.query("select * from categorys", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Category findById(String categoryId) {
		try {
			return queryRunner.query("select * from categorys where id=?", new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
