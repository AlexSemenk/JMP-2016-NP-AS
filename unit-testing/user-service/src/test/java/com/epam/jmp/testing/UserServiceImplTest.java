package com.epam.jmp.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	private User user;
	@Mock
	private UserDao userDao;
	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void initUserService() {
		user = new User();
		user.setLogin("Monty.Python");
		user.setPassword("password");
		user.setFirstName("Monty");
		user.setLastName("Python");
		user.setEmail("Monty.Python@gmail.com");
	}

	@Test
	public void userShouldBeSelectedById() throws Exception {
		Long userId = 10L;
		when(userDao.selectUser(userId)).thenReturn(user);
		User selectedUser = userService.getUserById(userId);
		assertSame(user, selectedUser);
	}
	
	@Test
	public void regularUserShouldBeCreated() throws Exception {
		userService.createUser(user);
		verify(userDao).createUser(user);
	}

	@Test(expected = UserServiceException.class)
	public void userWithIdShouldNotBeCreated() throws Exception {
		user.setId(10L);
		userService.createUser(user);
	}

	@Test(expected = UserServiceException.class)
	public void userWithoutFirstNameShouldNotBeCreated() throws Exception {
		user.setFirstName(null);
		userService.createUser(user);
	}

	@Test(expected = UserServiceException.class)
	public void userWithoutLastNameShouldNotBeCreated() throws Exception {
		user.setLastName(null);
		userService.createUser(user);
	}
	
	@Test
	public void regularUserShouldBeUpdated() throws Exception {
		user.setId(13L);
		userService.updateUser(user);
		verify(userDao).updateUser(user);
	}
	
	@Test(expected = UserServiceException.class)
	public void userWithUnexisitngIdShouldNotBeUpdated() throws Exception {
		user.setId(13L);
		doThrow(new UserDaoException()).when(userDao).updateUser(user);
		userService.updateUser(user);
	}
	
	@Test
	public void userShouldBeDeleted() throws Exception {
		Long userId = 10L;
		userService.deleteUserById(userId);
		verify(userDao).deleteUser(userId);
	}
	
}
