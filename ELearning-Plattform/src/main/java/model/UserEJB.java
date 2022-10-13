package model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import utils.AuthenticationUtils;

@Stateless
public class UserEJB {
	
	@PersistenceContext(unitName="ELearning-Plattform")
	private EntityManager manager;
	
	public User createUser(User user, String groupname) {
		try {
			user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
		} catch(Exception e) {
			e.printStackTrace();
		}

		UserGroup group = new UserGroup();
		group.setEmail(user.getEmail());
		group.setGroupname(groupname);
		

		manager.persist(user);
		manager.persist(group);

		return user;
	}
	
	public User findUserById(String id) {
		TypedQuery<User> query = manager.createNamedQuery("User.findUserById", User.class);
		query.setParameter("email", id);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public UserGroup findGroupById(String id) {
		TypedQuery<UserGroup> query = manager.createNamedQuery("UserGroup.findGroupById", UserGroup.class);
		query.setParameter("email", id);
		UserGroup group = null;
		try {
			group = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return group;
	}
	
	public List<User> findUsers() {
		TypedQuery<User> query = manager.createNamedQuery("User.findAllUsers", User.class);
		List<User> users = null;
		try {
			users = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public List<UserGroup> findUserGroups() {
		TypedQuery<UserGroup> query = manager.createNamedQuery("UserGroup.findAll", UserGroup.class);
		List<UserGroup> usergroups = null;
		try {
			usergroups = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usergroups;
	}
	
	public User updatePassword(User user, String password) {
		try {
			user.setPassword(AuthenticationUtils.encodeSHA256(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		manager.merge(user);
		
		return user;
	}
	
	public UserGroup updateUserRole(UserGroup group, String role) {
		
		group.setGroupname(role);
		
		manager.merge(group);
		
		return group;
	}
	
}
