package ar.com.buildingways.webinars.service;

import java.util.List;

import ar.com.buildingways.webinars.model.User;

public interface UserService {

	User findById(int id);
    
    User findByCode(String code);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserByCode(String code);
 
    List<User> findAllUsers(); 
     
    boolean isUserCodeUnique(Integer id, String string);
}
