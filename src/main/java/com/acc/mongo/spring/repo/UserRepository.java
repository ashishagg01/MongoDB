package com.acc.mongo.spring.repo;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acc.mongo.spring.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{}

