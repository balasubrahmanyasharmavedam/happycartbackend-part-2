package com.subbu.happycartbackend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.subbu.happycartbackend.dao.SupplierDAO;
import com.subbu.happycartbackend.model.Supplier;

public class SupplierTest {
	
	public static void main(String[] args){
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.subbu.happycartbackend");
		context.refresh();
		
		
		SupplierDAO supplierDAO = (SupplierDAO) context.getBean("supplierDAO");
		
		Supplier supplier = (Supplier) context.getBean("supplier");
		
		supplier.setId("Supl002");
		supplier.setName("Apple");
		supplier.setAddress("California");
		
		supplier.setId("Supl001");
		supplier.setName("Sony");
		supplier.setAddress("Japan");
		
		
		supplier.setId("Supl003");
		supplier.setName("Nokia");
		supplier.setAddress("Finland");
	
		
		supplierDAO.saveOrUpdate(supplier);
		
		
		System.out.println("No.of suppliers available"+supplierDAO.list().size());
		
		if( supplierDAO.get("sdfsf")==null)
		{
			System.out.println("Supplier doesn't exist");
			
		}
		else
		{
			System.out.println("Supplier exist.. no.of suppliers = "+supplierDAO.list().size()+"the details are shown in database only..");
			System.out.println();
		}
		
	}
	
}
	
	