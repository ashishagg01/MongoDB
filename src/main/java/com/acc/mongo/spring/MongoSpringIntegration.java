package com.acc.mongo.spring;



import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.acc.mongo.spring.beans.Address;
import com.acc.mongo.spring.beans.User;
import com.acc.mongo.spring.repo.RegistrationBean;

public class MongoSpringIntegration {
	public static RegistrationBean registrationBean;
	public static ClassPathXmlApplicationContext context;

	static {
		// Acquire Context
		context = new ClassPathXmlApplicationContext("mongo-servlet.xml");
	}

	public static void main(String [] args) throws Exception{
		// Create by MongoTemplate
		createUserThroughMonogoTamplate();
		// Create By Spring Data
		createUserThroughMongoRepository();
	}

	public static void createUserThroughMonogoTamplate(){
		System.out.println("createUserThroughMonogoTamplate start");
		MongoTemplate mongoOps = (MongoTemplate)context.getBean("mongoTemplate");
		User user = new User();
		user.setId("200");
		user.setFullName("Mongo Template");
		user.setStatus("A");
		user.setAge("29");
		Address address = new Address();
		address.setAddressId("200");
		address.setAddressValue("UK/London");
		user.setAddress(address);
		mongoOps.insert(user);
		System.out.println("user created with id " + user.getId());
		System.out.println("createUserThroughMonogoTamplate end ");
	}

	public static void createUserThroughMongoRepository(){
		System.out.println("createUserThroughMongoRepository start");
		User user = new User();
		user.setId("201");
		user.setFullName("Spring Data");
		user.setStatus("B");
		user.setAge("25");
		Address address = new Address();
		address.setAddressId("201");
		address.setAddressValue("UK/Manchester");
		user.setAddress(address);
		RegistrationBean bean = (RegistrationBean)context.getBean("registrationBean");
		bean.getRepository().save(user);
		System.out.println("user created with id " + user.getId());
		System.out.println("createUserThroughMongoRepository end");
	}
}
