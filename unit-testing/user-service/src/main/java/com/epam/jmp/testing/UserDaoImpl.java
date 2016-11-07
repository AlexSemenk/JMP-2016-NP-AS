package com.epam.jmp.testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

	private static final String SELECT_USER_SQL 
		= "SELECT login, firstName, lastName, email, password FROM users WHERE id = ?";

	private static final String CREATE_USER_SQL 
		= "INSERT INTO users (login, firstName, lastName, email, password) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_USER_SQL 
		= "UPDATE users SET login = ?, firstName = ?, lastName = ?, email = ?, password = ? "
		+ "WHERE id = ?";

	private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";

	private static final ConnectionSource connectionSrc = new ConnectionSource();

	@Override
	public User selectUser(Long id) throws UserDaoException {
		Connection connection = connectionSrc.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
			statement.setLong(1, id);
			statement.executeQuery();
			try (ResultSet resultSet = statement.getResultSet()) {
				if (resultSet.next()) {
					User user = new User();
					user.setId(id);
					user.setLogin(resultSet.getString(1));
					user.setFirstName(resultSet.getString(2));
					user.setLastName(resultSet.getString(3));
					user.setEmail(resultSet.getString(4));
					user.setPassword(resultSet.getString(5));
					return user;
				} else {
					throw new UserDaoException("User wasn't found");
				}
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			connectionSrc.releaseConnection(connection);
		}
	}

	@Override
	public void createUser(User user) throws UserDaoException {
		Connection connection = connectionSrc.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_SQL, new String[] { "ID" })) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.executeUpdate();
			int created = statement.executeUpdate();
			if (created == 0) {
				throw new UserDaoException("User wasn't created");
			}
			try (ResultSet keySet = statement.getGeneratedKeys()) {
				if(keySet.next()) {
					Long id = keySet.getLong(1);
					user.setId(id);
				} else {
					throw new UserDaoException("Id wasn't generated");
				}
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			connectionSrc.releaseConnection(connection);
		}
	}

	@Override
	public void updateUser(User user) throws UserDaoException {
		Connection connection = connectionSrc.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.setLong(6, user.getId());
			int updated = statement.executeUpdate();
			if (updated == 0) {
				throw new UserDaoException("User wasn't updated");
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			connectionSrc.releaseConnection(connection);
		}
	}

	@Override
	public void deleteUser(Long id) throws UserDaoException {
		Connection connection = connectionSrc.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
			statement.setLong(1, id);
			int deleted = statement.executeUpdate();
			if (deleted == 0) {
				throw new UserDaoException("User wasn't deleted");
			}
		} catch (SQLException e) {
			throw new UserDaoException(e);
		} finally {
			connectionSrc.releaseConnection(connection);
		}
	}

}
