package com.yangguang.common;

import com.yangguang.domain.Category;
import com.yangguang.service.BusinessService;
import com.yangguang.service.BusinessServiceImp;

public class myFunctions {
	private static BusinessService businessService = new BusinessServiceImp();
	public static String showCategoryName(String category) {
		Category c = businessService.findCategoryById(category);
		if(c != null){
			return c.getName();
		}
		return null;
	}
}
