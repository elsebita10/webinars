package ar.com.buildingways.webinars.service;

import java.util.List;

import ar.com.buildingways.webinars.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);
	 
    UserProfile findByType(String type);
     
    List<UserProfile> findAll();
}
