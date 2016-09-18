package com.epam.jmp.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.epam.jmp.jdbc.crud.entity.Friendship;
import com.epam.jmp.jdbc.crud.entity.Like;
import com.epam.jmp.jdbc.crud.entity.Post;
import com.epam.jmp.jdbc.crud.entity.User;
import com.epam.jmp.jdbc.crud.generator.FriendshipGenerator;
import com.epam.jmp.jdbc.crud.generator.LikeGenerator;
import com.epam.jmp.jdbc.crud.generator.PostGenerator;
import com.epam.jmp.jdbc.crud.generator.UserGenerator;

public class Main {

	private static final String URL = "jdbc:mysql://localhost:3306/task1";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "pass";

	private static final String CREATE_USERS_TABLE = 
	    "CREATE TABLE users (" + 
	        "id BIGINT PRIMARY KEY," + 
            "name VARCHAR(16)," + 
            "surname VARCHAR(16)," + 
            "birthdate DATE" + 
        ")";

	private static final String CREATE_FRIENDSHIPS_TABLE =
        "CREATE TABLE friendships (" +
            "userid1 BIGINT, " +
            "userid2 BIGINT, " + 
            "timestamp TIMESTAMP, " +
            "PRIMARY KEY (userid1, userid2), " +
            "FOREIGN KEY (userid1) REFERENCES users(id), " + 
            "FOREIGN KEY (userid2) REFERENCES users(id)" +
        ")";
	
	private static final String CREATE_POSTS_TABLE =
	    "CREATE TABLE posts (" +
	        "id BIGINT PRIMARY KEY, " +
	        "userid BIGINT, " + 
	        "text VARCHAR(256), " +
	        "timestamp TIMESTAMP, " +
	        "FOREIGN KEY (userid) REFERENCES users(id)" + 
	    ")";
	
	private static final String CREATE_LIKES_TABLE =
	    "CREATE TABLE likes (" +
	        "postid BIGINT, " +
	        "userid BIGINT, " + 
	        "timestamp TIMESTAMP, " +
	        "PRIMARY KEY (postid, userid), " +
	        "FOREIGN KEY (postid) REFERENCES posts(id), " +
	        "FOREIGN KEY (userid) REFERENCES users(id)" +
	    ")";
	
	private static final String INSERT_USER =
	    "INSERT INTO users (id, name, surname, birthdate) VALUES (?, ?, ?, ?)";
	
	private static final String INSERT_FRIENDSHIP =
		 "INSERT INTO friendships (userid1, userid2, timestamp) VALUES (?, ?, ?)";
	
	private static final String INSERT_POST =
		    "INSERT INTO posts (id, userid, text, timestamp) VALUES (?, ?, ?, ?)";
	
	private static final String INSERT_LIKE =
		    "INSERT INTO likes (postid, userid, timestamp) VALUES (?, ?, ?)";

	
	private static final String SELECT_POPULAR_USERS_NAMES = 
        "SELECT DISTINCT name FROM users " +
	    "JOIN (" +
            "SELECT userid1 AS 'userid', count(*) AS 'friends_number' " +
	        "FROM friendships " + 
            "WHERE timestamp < '2015-04-01' " +
            "GROUP BY userid1 " + 
	    ") f " + 
        "ON users.id = f.friends_number " +
	    "JOIN (" +
            "SELECT posts.userid as 'userid', SUM(l.likes_number) as 'likes_number' " +
	        "FROM posts " +
            "JOIN (" +
	            "SELECT postid as 'postid', COUNT(*) as 'likes_number' " +
                "FROM likes " +
	            "WHERE timestamp < '2015-04-01' " +
                "GROUP BY postid" +
	        ") l " +
            "ON posts.id = l.postid " +
	        "GROUP BY posts.userid" +
        ") p " +
        "ON users.id = p.userid " +
        "WHERE friends_number > 100 "+
        "AND likes_number > 100";

	private static final long USERS_NUMBER = 1_000;
	private static final long FRIENDSHIPS_NUMBER = 70_000;
	private static final long POSTS_NUMBER = 3_000;
	private static final long LIKES_NUMBER = 300_000;

	public static void main(String[] args) throws SQLException {	
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			createTables(connection);
			fillTables(connection);
			queryDatabase(connection);
			dropTables(connection);
		}
	}

	private static void createTables(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute(CREATE_USERS_TABLE);
			statement.execute(CREATE_FRIENDSHIPS_TABLE);
			statement.execute(CREATE_POSTS_TABLE);
			statement.execute(CREATE_LIKES_TABLE);
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void fillTables(Connection connection) throws SQLException {
		fillUsersTable(connection);
		fillFriendshipsTable(connection);
		fillPostsTable(connection);
		fillLikesTable(connection);
	}

	private static void fillUsersTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
			UserGenerator users = new UserGenerator(USERS_NUMBER);
			while(users.hasNext()) {
				for(int i=0; i<100 && users.hasNext(); i++) {
					User user = users.next();
					statement.setLong(1, user.getId());
					statement.setString(2, user.getName());
					statement.setString(3, user.getSurname());
					statement.setDate(4, java.sql.Date.valueOf(user.getBirthdate()));
					statement.addBatch();
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void fillFriendshipsTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_FRIENDSHIP)) {
			FriendshipGenerator friendships = new FriendshipGenerator(FRIENDSHIPS_NUMBER, USERS_NUMBER);				
			while(friendships.hasNext()) {
				for(int i = 0; i < 100 && friendships.hasNext(); i++) {
					Friendship friendship = friendships.next();
					statement.setLong(1, friendship.getUserId1());
					statement.setLong(2, friendship.getUserId2());
					statement.setTimestamp(3, java.sql.Timestamp.valueOf(friendship.getTimestamp()));
					statement.addBatch();
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void fillPostsTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_POST)) {
			PostGenerator posts = new PostGenerator(POSTS_NUMBER, USERS_NUMBER);
			while(posts.hasNext()) {
				for(int i=0; i<100 && posts.hasNext(); i++) {
					Post post = posts.next();
					statement.setLong(1, post.getId());
					statement.setLong(2, post.getUserId());
					statement.setString(3, post.getText());
					statement.setTimestamp(4, java.sql.Timestamp.valueOf(post.getTimestamp()));
					statement.addBatch();
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void fillLikesTable(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(INSERT_LIKE)) {
			LikeGenerator likes = new LikeGenerator(LIKES_NUMBER, USERS_NUMBER, POSTS_NUMBER);
			while(likes.hasNext()) {
				for(int i=0; i<100 && likes.hasNext(); i++) {
					Like like = likes.next();
					statement.setLong(1, like.getPostId());
					statement.setLong(2, like.getUserId());
					statement.setTimestamp(3, java.sql.Timestamp.valueOf(like.getTimestamp()));
					statement.addBatch();
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void queryDatabase(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			System.out.println("Users Number: " + selectUsersCount(statement));
			System.out.println("Frienships Number: " + selectFriendshipsCount(statement));
			System.out.println("Posts Number: " + selectPostsCount(statement));
			System.out.println("Likes Number: " + selectLikesCount(statement));
			System.out.println("Popular User's Names: ");
			List<String> names = selectPopularUsersNames(statement);
			if (names.isEmpty()) {
				System.out.println("  Empty");
			} else {
				for(String name : names) {
					System.out.println("  " + name);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	private static long selectUsersCount(Statement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM users")) {
			result.next();
			return result.getLong(1);
		} catch (SQLException e) {
			throw e;
		}
	}

	private static long selectFriendshipsCount(Statement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM friendships")) {
			result.next();
			return result.getLong(1);
		} catch (SQLException e) {
			throw e;
		}
	}

	private static long selectPostsCount(Statement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM posts")) {
			result.next();
			return result.getLong(1);
		} catch (SQLException e) {
			throw e;
		}
	}

	private static long selectLikesCount(Statement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM likes")) {
			result.next();
			return result.getLong(1);
		} catch (SQLException e) {
			throw e;
		}
	}

	private static List<String> selectPopularUsersNames(Statement statement) throws SQLException {
		LinkedList<String> names = new LinkedList<>();
		try (ResultSet result = statement.executeQuery(SELECT_POPULAR_USERS_NAMES)) {
			while (result.next()) {
				names.add(result.getString(1));
			}
			return names;
		} catch (SQLException e) {
			throw e;
		}
	}

	private static void dropTables(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute("DROP TABLE likes");
			statement.execute("DROP TABLE posts");
			statement.execute("DROP TABLE friendships");
			statement.execute("DROP TABLE users");
		} catch (SQLException e) {
			throw e;
		}
	}

}
