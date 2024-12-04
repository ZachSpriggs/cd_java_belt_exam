package com.zach.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zach.beltexam.models.UserRating;

@Repository
public interface RatingRepo extends CrudRepository<UserRating, Long>{

}
