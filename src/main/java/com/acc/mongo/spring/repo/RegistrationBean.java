package com.acc.mongo.spring.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationBean {
	@Autowired
	private UserRepository repository;

	public RegistrationBean(){

	}

	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}
}
