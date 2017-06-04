package ar.com.buildingways.webinars.dao;

import java.util.List;

import ar.com.buildingways.webinars.model.User;

public interface UserDao {

	User findById(int id);
    
    User findByCode(String code);
     
    void save(User user);
     
    void deleteByCode(String code);
     
    List<User> findAllUsers();
}
