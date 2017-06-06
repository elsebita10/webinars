package ar.com.buildingways.webinars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buildingways.webinars.dao.UserDao;
import ar.com.buildingways.webinars.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao dao;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findByCode(String code) {
		User user = dao.findByCode(code);
        return user;
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
	}

	@Override
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setCode(user.getCode());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setUserProfiles(user.getUserProfiles());
        }
	}

	@Override
	public void deleteUserByCode(String code) {
		dao.deleteByCode(code);
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserCodeUnique(Integer id, String code) {
		User user = findByCode(code);
        return ( user == null || ((id != null) && (user.getId() == id)));
	}

}
