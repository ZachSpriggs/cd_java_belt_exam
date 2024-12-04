package com.zach.beltexam.services;

import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.zach.beltexam.models.Show;
import com.zach.beltexam.models.User;
import com.zach.beltexam.models.UserRating;
import com.zach.beltexam.repositories.RatingRepo;
import com.zach.beltexam.repositories.ShowRepo;
import com.zach.beltexam.repositories.UserRepo;


@Service
public class UserService {
	private final UserRepo userRepo;
	private final ShowRepo showRepo;
	private final RatingRepo ratingRepo;
	
	public UserService(UserRepo userRepo, ShowRepo showRepo, RatingRepo ratingRepo) {
		this.userRepo = userRepo;
		this.showRepo = showRepo;
		this.ratingRepo = ratingRepo;
	}
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public void updateUser(User user) {
    	userRepo.save(user);
    }
    
    public Show createShow(Show show) {
    	return showRepo.save(show);
    }
    
    public List<Show> allShows(){
    	return showRepo.findAll();
    }
    
    public Show findShow(Long id) {
    	Optional<Show> show = showRepo.findById(id);
    	if(show.isPresent()) {
            return show.get();
    	} else {
    	    return null;
    	}
    }
    
    public void updateShow(Show show) {
    	showRepo.save(show);
    }
    
    public void deleteShow(Long id) {
    	showRepo.deleteById(id);
    }
    
    public void addRating(User user, Show show, UserRating rating) {
    	rating.setUser(user);
    	rating.setShow(show);
    	userRepo.save(user);
    	showRepo.save(show);
    	ratingRepo.save(rating);
    }
    
    public Show findTitle(Show show) {
    	 return showRepo.findByTitleContains(show.getTitle());
    }
    
    public Show findNetwork(Show show) {
    	return showRepo.findByNetworkContains(show.getNetwork());
    }
    
    
    
}

