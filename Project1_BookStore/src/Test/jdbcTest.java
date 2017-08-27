package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sound.midi.VoiceStatus;

import org.junit.Test;

import com.yangguang.dao.CategoryDao;
import com.yangguang.dao.CategoryDaoImp;
import com.yangguang.domain.Category;

public class jdbcTest {
	
	@Test
	public void testSave() {
		
		Category category = new Category();
		category.setId(UUID.randomUUID().toString());
		category.setName("线性代数");
		category.setDescription("本科入门数学教材");
		
		CategoryDao categoryDao = new CategoryDaoImp();
		categoryDao.save(category);
	}
	
	@Test
	public void testfindAll() {
		List<Category> categories = new ArrayList<Category>();
		CategoryDao categoryDao = new CategoryDaoImp();
		categories = categoryDao.findAll();
		
		for(Category category:categories) {
			System.out.println("ID="+category.getId()+"  Name="+category.getName()+"  Description="+category.getDescription());
		}
		
	}
	

}
