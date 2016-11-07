package com.epam.jmp.testing;

public interface UserDao {

	User selectUser(Long id) throws UserDaoException;
	
	void createUser(User user) throws UserDaoException;
	
	void updateUser(User user) throws UserDaoException;
	
	void deleteUser(Long id) throws UserDaoException;
	
}
