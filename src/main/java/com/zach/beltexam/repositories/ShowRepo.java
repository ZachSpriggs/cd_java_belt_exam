package com.zach.beltexam.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zach.beltexam.models.Show;

@Repository
public interface ShowRepo extends CrudRepository<Show, Long>{
	List<Show> findAll();
	
	Show findByTitleContains(String title);
	Show findByNetworkContains(String network);
}
