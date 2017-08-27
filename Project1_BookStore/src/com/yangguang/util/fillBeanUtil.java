package com.yangguang.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 封装请求数据到JavaBean中
 * @author yangguang
 *2017年8月24日
 */

//Class<T> calzz可以接受任何类型的javaBean，使用泛型调用者不用进行强制性转换
public class fillBeanUtil {
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz) {
		try {
			//创建封装数据的bean
			T bean = clazz.newInstance();
			BeanUtils.copyProperties(bean,request.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
