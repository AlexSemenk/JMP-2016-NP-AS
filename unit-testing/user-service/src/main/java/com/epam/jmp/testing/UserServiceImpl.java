package com.epam.jmp.testing;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User getUserById(Long id) throws UserServiceException {
		try {
			return userDao.selectUser(id);
		} catch (UserDaoException e) {
			throw new UserServiceException(e);
		}
	}

	@Override
	public void createUser(User user) throws UserServiceException {
		check(user.getId() == null, "User id should not be set");
		check(!Utils.isBlank(user.getFirstName()), "User first name should not be blank");
		check(!Utils.isBlank(user.getLastName()), "User last name should not be blank");
		try {
			userDao.createUser(user);
		} catch (UserDaoException e) {
			throw new UserServiceException(e);
		}
	}

	@Override
	public void updateUser(User user) throws UserServiceException {
		check(!Utils.isBlank(user.getFirstName()), "User first name should not be blank");
		check(!Utils.isBlank(user.getLastName()), "User last name should not be blank");
		try {
			userDao.updateUser(user);
		} catch (UserDaoException e) {
			throw new UserServiceException(e);
		}
	}

	@Override
	public void deleteUserById(Long id) throws UserServiceException {
		try {
			userDao.deleteUser(id);
		} catch (UserDaoException e) {
			throw new UserServiceException(e);
		}
	}
		
	private void check(boolean check, String message) throws UserServiceException {
		if (!check) {
			throw new UserServiceException(message);
		}
	}
	
}
