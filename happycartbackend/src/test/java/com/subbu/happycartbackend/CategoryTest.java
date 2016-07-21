package com.subbu.happycartbackend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.subbu.happycartbackend.dao.CategoryDAO;
import com.subbu.happycartbackend.model.Category;

public class CategoryTest {
	public static void main(String[]args){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.subbu.happycartbackend");
		context.refresh();
		
		CategoryDAO categoryDAO =(CategoryDAO) context.getBean("categoryDAO");
		Category category= (Category) context.getBean("category");
		category.setId("Cat002");
		category.setName("Gadgets");
		category.setDescription("Tablets");
		category.setId("Cat003");
		category.setName("Gadgets");
		category.setDescription("mp3pods");
		
		categoryDAO.saveOrUpdate(category);
		
		if(categoryDAO.get("sdfsf")==null);
		{
			System.out.println("Category exist..the detials are..");
			System.out.println();
		}
		
	}

}
