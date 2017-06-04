package ar.com.buildingways.webinars.dao;

import java.util.List;

import ar.com.buildingways.webinars.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
    
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
