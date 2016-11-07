package com.epam.jmp.testing;

public interface UserService {

	public User getUserById(Long id) throws UserServiceException;
	
	public void createUser(User user) throws UserServiceException;
	
	public void updateUser(User user) throws UserServiceException;
	
	public void deleteUserById(Long id) throws UserServiceException;
	
}
