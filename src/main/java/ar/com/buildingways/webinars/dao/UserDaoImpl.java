package ar.com.buildingways.webinars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buildingways.webinars.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	
	@Override
	public User findById(int id) {
		User user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
	}

	@Transactional(readOnly=true)
	@Override
	public User findByCode(String code) {
		logger.info("Code : {}", code);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("code", code));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
	}

	@Override
	public void save(User user) {
		persist(user);
	}

	@Override
	public void deleteByCode(String code) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("code", code));
        User user = (User)crit.uniqueResult();
        delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }
        return users;
	}

}
