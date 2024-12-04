package com.zach.beltexam.repositories;

import org.springframework.stereotype.Repository;
import com.zach.beltexam.models.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
